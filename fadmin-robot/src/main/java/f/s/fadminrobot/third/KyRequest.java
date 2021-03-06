package f.s.fadminrobot.third;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import f.s.fadminrobot.service.CallKyOrderService;
import f.s.fadminrobot.service.CallThirdConfigService;
import f.s.fadminrobot.vo.ext.KyBackOrder;
import f.s.fadminrobot.vo.ext.KyResponse;
import f.s.frobot.model.CallKyOrder;
import f.s.frobot.util.GsonUtil;
import f.s.utils.DateUtil;
import f.s.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/**
 * 开元接口
 * @author lijiafu
 * @date 2020/4/1 22:27
 * @since 1.0
 */
@Component
@Slf4j
public class KyRequest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CallKyOrderService callKyOrderService;
    @Autowired
    private CallThirdConfigService callThirdConfig;
    /**
     * 催单接口
     * @author lijiafu
     * @date 2020/4/1 22:28
     */
    public List<KyBackOrder> backOrder(){
        List<KyBackOrder> orderList = new ArrayList<>();
        Map<String,KyBackOrder> kyBackOrderMap = new HashMap<>();
        Integer merchantId = 30;//商户id
        try {
            //崔退url
            String url =callThirdConfig.getValue(merchantId,"ky_request","backOrderUrl");
            //公钥
            String publicKey=callThirdConfig.getValue(merchantId,"ky_request","publicKey");
            //酒店code
            String hotelCode = callThirdConfig.getValue(merchantId,"ky_request","hotelCode");
            RSA operaRsa = new RSA(null,publicKey);
            String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+hotelCode+"\"}", KeyType.PublicKey));
            Map<String, String> map = new HashMap<>();
            map.put("encrypt",encrypt);
            url = url+"?encrypt="+encrypt;
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
            String  body  =entity.getBody();
            KyResponse kyResponse = GsonUtil.getGsonInstance().fromJson(body,KyResponse.class);
            log.info("ky json : {}",kyResponse.getMessage());
            List<KyBackOrder> kyBackOrders = GsonUtil.getGsonInstance().fromJson(kyResponse.getMessage(), new TypeToken<List<KyBackOrder>>() {}.getType());
            for(KyBackOrder kyBackOrder : kyBackOrders){
                //判断离店时间是否为今天
                String depDateStr = kyBackOrder.getDep();
                depDateStr = DateUtil.getDay(DateUtil.parseDay(depDateStr));
                String nowDateStr = DateUtil.getDay();
                if(depDateStr.equals(nowDateStr)){
                    //如果没有会员信息，会员类型为A
                    if(StringUtils.isBlank(kyBackOrder.getMemberType())){
                        kyBackOrder.setMemberType("A");
                    }
                    //去除重复订单
                    kyBackOrderMap.put(kyBackOrder.getRmno(),kyBackOrder);
                }
            }
            //保存开元数据
            if(!CollectionUtils.isEmpty(kyBackOrderMap)){
                orderList = new ArrayList(kyBackOrderMap.values());
                saveKyOrder(orderList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("开元崔退订单保存异常：{}",e.getMessage());
        }
        return orderList;
    }



    /**
     * 通知接口
     * @author lijiafu
     * @date 2020/4/1 22:28
     */
    public String kyNotify(String traderNo,String result) throws  Exception{
        Integer merchantId = 30;//商户id
        //通知结果url
        String url =callThirdConfig.getValue(merchantId,"ky_request","notifyUrl");
        //公钥
        String publicKey=callThirdConfig.getValue(merchantId,"ky_request","publicKey");
        //酒店code
        String hotelCode = callThirdConfig.getValue(merchantId,"ky_request","hotelCode");
        RSA operaRsa = new RSA(null,publicKey);
        String tradeNo = traderNo;
        String remark = result;

        String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+hotelCode+"\",\"tradeNo\":\""+tradeNo+"\",\"remark\":\""+remark+"\"}",KeyType.PublicKey));
        Map<String, String> map = new HashMap<>();
        map.put("encrypt",encrypt);
        url = url+"?encrypt="+encrypt;
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
        String  body  =entity.getBody();
        if(StringUtils.isNotBlank(body)){
            if(body.length() > 128){
                body = body.substring(1,128);
            }
        }
        log.info("订单号：{},开元通知结果 {}",tradeNo,body);
        return body;
    }

    //保存开元订单
    private void saveKyOrder(List<KyBackOrder> list){
        List<CallKyOrder> orderList  = new ArrayList<>();
        CallKyOrder order = null;
        for(KyBackOrder backOrder : list){

            //查询开元订单是否存在，如果已存在，则过滤
            CallKyOrder callKyOrder = callKyOrderService.findByOrderId(backOrder.getId());
            if(null != callKyOrder){//不为空，代表开元订单已存在，需过滤掉
                continue;
            }
            order = new CallKyOrder();
            order.setArr(DateUtil.parseToDateTime(backOrder.getArr()));
            order.setDep(DateUtil.parseToDateTime(backOrder.getDep()));
            order.setMemberNo(backOrder.getMemberNo());
            order.setMemberType(backOrder.getMemberType());
            order.setMobile(backOrder.getMobile());
            if(StringUtils.isNotBlank(backOrder.getMorningCallTime())){
                order.setMorningCallTime(DateUtil.parseToDateTime(backOrder.getMorningCallTime()));
            }
            order.setName(backOrder.getName());
            order.setOrderId(backOrder.getId());
            order.setRmno(backOrder.getRmno());
            order.setCtime(new Date());
            orderList.add(order);
        }
        if(!CollectionUtils.isEmpty(orderList)){
            callKyOrderService.batchAdd(orderList);
        }
    }
}
