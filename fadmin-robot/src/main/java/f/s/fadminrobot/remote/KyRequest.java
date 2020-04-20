package f.s.fadminrobot.remote;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import f.s.fadminrobot.service.CallKyOrderService;
import f.s.fadminrobot.vo.ext.KyBackOrder;
import f.s.fadminrobot.vo.ext.KyResponse;
import f.s.frobot.model.CallKyOrder;
import f.s.frobot.util.GsonUtil;
import f.s.utils.DateUtil;
import f.s.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

    public final static Map<String,String> vipTimeMap = new HashMap<>();
    public final static Map<String,String> vipNameMap = new HashMap<>();

    static{
        vipTimeMap.put("A","14:00");
        vipTimeMap.put("BE","14:00");
        vipTimeMap.put("BS","15:00");
        vipTimeMap.put("BG","16:00");
        vipTimeMap.put("BP","17:00");

        vipNameMap.put("A","普通用户");
        vipNameMap.put("BE","商祺普卡");
        vipNameMap.put("BS","商祺银卡");
        vipNameMap.put("BG","商祺金卡");
        vipNameMap.put("BP","商祺白金卡");
    }
    /**
     * 催单接口
     * @author lijiafu
     * @date 2020/4/1 22:28
     */
    public List<KyBackOrder>  backOrder(){
        try {
            String url ="http://test3.shands.cn/lvYun/checkOrderMessage.htm";
            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFcGE6G/+ejaiXRyNdw35SA16JP7Tl4YZW65chrOr3zna/Wlsj5+5EySXRC/w1Z5rnHzoxMupKsI4NqA+28GgHoZ4lG679cEkqDI5kPd3pDLnS4q+ZqboCDNv1UyYnTKaCrYiGssyXpl2kA9z9fRzM0pPrjdIncEMdjHzHGUKVVwIDAQAB";
            RSA operaRsa = new RSA(null,publicKey);
            String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+"KYGYBSD"+"\"}", KeyType.PublicKey));
            Map<String, String> map = new HashMap<>();
            map.put("encrypt",encrypt);
            url = url+"?encrypt="+encrypt;
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
            String  body  =entity.getBody();
            KyResponse kyResponse = GsonUtil.getGsonInstance().fromJson(body,KyResponse.class);
            List<KyBackOrder> kyBackOrders = GsonUtil.getGsonInstance().fromJson(kyResponse.getMessage(), new TypeToken<List<KyBackOrder>>() {}.getType());
            List<KyBackOrder> orderList = new ArrayList<>();
            for(KyBackOrder kyBackOrder : kyBackOrders){
                //判断离店时间是否为今天
                String depDateStr = kyBackOrder.getDep();
                depDateStr = DateUtil.getDay(DateUtil.parseDay(depDateStr));
                String nowDateStr = DateUtil.getDay();
                if(depDateStr.equals(nowDateStr)){
                    orderList.add(kyBackOrder);
                }
            }
            //保存开元数据
            saveKyOrder(orderList);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 早叫接口
     * @author lijiafu
     * @date 2020/4/1 22:28
     */
    public List<KyBackOrder> morningOrder() {
        try {
            String url ="http://test3.shands.cn/lvYun/morningCall.htm";
            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFcGE6G/+ejaiXRyNdw35SA16JP7Tl4YZW65chrOr3zna/Wlsj5+5EySXRC/w1Z5rnHzoxMupKsI4NqA+28GgHoZ4lG679cEkqDI5kPd3pDLnS4q+ZqboCDNv1UyYnTKaCrYiGssyXpl2kA9z9fRzM0pPrjdIncEMdjHzHGUKVVwIDAQAB";
            RSA operaRsa = new RSA(null,publicKey);
            String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+"KYGYBSD"+"\"}", KeyType.PublicKey));
            Map<String, String> map = new HashMap<>();
            map.put("encrypt",encrypt);
            url = url+"?encrypt="+encrypt;
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
            String  body  =entity.getBody();
            KyResponse kyResponse = GsonUtil.getGsonInstance().fromJson(body,KyResponse.class);
            List<KyBackOrder> kyBackOrders = GsonUtil.getGsonInstance().fromJson(kyResponse.getMessage(), new TypeToken<List<KyBackOrder>>() {}.getType());
            List<KyBackOrder> orderList = new ArrayList<>();
            for(KyBackOrder kyBackOrder : kyBackOrders){
                //判断早叫时间是否为今天
                String depDateStr = kyBackOrder.getMorningCallTime();
                String nowDateStr = DateUtil.getDay();
                if(depDateStr.equals(nowDateStr)){
                    orderList.add(kyBackOrder);
                }
            }

            //TODO 需要保存开元数据
            saveKyOrder(orderList);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通知接口
     * @author lijiafu
     * @date 2020/4/1 22:28
     */
    public String kyNotify(String traderNo,String result) throws  Exception{
            String url ="http://test3.shands.cn/lvYun/appendRemarks.htm";//确认结果
            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFcGE6G/+ejaiXRyNdw35SA16JP7Tl4YZW65chrOr3zna/Wlsj5+5EySXRC/w1Z5rnHzoxMupKsI4NqA+28GgHoZ4lG679cEkqDI5kPd3pDLnS4q+ZqboCDNv1UyYnTKaCrYiGssyXpl2kA9z9fRzM0pPrjdIncEMdjHzHGUKVVwIDAQAB";
            RSA operaRsa = new RSA(null,publicKey);
            String hotelCode = "KYGYBSD";
            String tradeNo = traderNo;
            String remark = result;

            String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+hotelCode+"\",\"tradeNo\":\""+tradeNo+"\",\"remark\":\""+remark+"\"}",KeyType.PublicKey));
            Map<String, String> map = new HashMap<>();
            map.put("encrypt",encrypt);
            url = url+"?encrypt="+encrypt;
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
            String  body  =entity.getBody();
            if(StringUtils.isNotBlank(body)){
                if(body.length() > 64){
                    body = body.substring(1,64);
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

            //TODO 查询开元订单是否存在，如果已存在，则过滤
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
            orderList.add(order);
        }
        callKyOrderService.batchAdd(orderList);
    }
}
