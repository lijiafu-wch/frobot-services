package f.s.fadminrobot;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import f.s.frobot.util.GsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Created by CC~ on 14-6-18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KyTest {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void  t1() throws Exception {

        try {
            //String url ="http://test3.shands.cn/lvYun/checkOrderMessage.htm";//崔退
            //String url ="http://test3.shands.cn/lvYun/morningCall.htm";//早叫
            String url ="http://ets.shands.cn/lvYun/checkOrderMessage.htm";//崔退

            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFcGE6G/+ejaiXRyNdw35SA16JP7Tl4YZW65chrOr3zna/Wlsj5+5EySXRC/w1Z5rnHzoxMupKsI4NqA+28GgHoZ4lG679cEkqDI5kPd3pDLnS4q+ZqboCDNv1UyYnTKaCrYiGssyXpl2kA9z9fRzM0pPrjdIncEMdjHzHGUKVVwIDAQAB";
            RSA operaRsa = new RSA(null,publicKey);
            String hotelCode = "KYHAZH";//正式
            //String hotelCode = "KYGYBSD";//测试

            String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+hotelCode+"\"}",KeyType.PublicKey));
            System.out.println("加密字符串="+encrypt);
            Map<String, String> map = new HashMap<>();
            map.put("encrypt",encrypt);
            url = url+"?encrypt="+encrypt;
            System.out.println(url);
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
            String  body  =entity.getBody();
            KyResponse kyResponse = GsonUtil.getGsonInstance().fromJson(body,KyResponse.class);
            System.out.println(kyResponse.getMessage());

            //System.out.println(GsonUtil.getGsonInstance().toJson(kyResponse.getMessage()));
            List<KyBackOrder> kyBackOrders = GsonUtil.getGsonInstance().fromJson(kyResponse.getMessage(), new TypeToken<List<KyBackOrder>>() {}.getType());
            System.out.println(kyBackOrders.get(0).getDep());
            System.out.println(kyBackOrders.get(0).toString());
            //解密 私钥 MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIVwYTob/56NqJdHI13DflIDXok/tOXhhlbrlyGs6vfOdr9aWyPn7kTJJdEL/DVnmucfOjEy6kqwjg2oD7bwaAehniUbrv1wSSoMjmQ93ekMudLir5mpugIM2/VTJidMpoKtiIayzJemXaQD3P19HMzSk+uN0idwQx2MfMcZQpVXAgMBAAECgYBDMNtET2TfiaUix4dfZ1jsIxYvNgz41B65Fdf79P472ZKht/W8lsS7ji2kncxZd9ngFzFOJL0k7tIYvk9rZpJ6yKIBZ8kFKP2mWYdNU2WRohSk0WwlYXHk47jsm6c/bMRWmLRo/d8IBpiZErzCaYn9QezajM7r80dTx7TeiYmNQQJBANmMUoRJ4NbtJlGtq/ajoJDL8bmkwk3cBmxNpFNm33d9EA3aEqn7REglJqbK9Hlf8usKbyAwWnKdmW04jR+uLHUCQQCdBkN3Oo/Uezx1ss3K1fRFRGaLJxaOJE99Uoyqci1s/rlVbwOf/0RORCjfieV3khnL51lZ4qXwB6QnJKa3q7EbAkB7szeEc0G+1FAyp28dqkL1spG7frzhatfYI3np1pgAqINFbRepwe88mqtUshi7H1zQWzl+8x+rBoQkP8C3H+G1AkEAhn/++fmniZGl0AtASyXI/iwAyeDJaSehDu/OnZD/aAUoKw3URRAaKhAoEpHDhBAZGJcp6dxB1sA003KJe3oNUQJBALbqXZIB4mZXI0hWtuwtyXbYVK5IWowrWKWxU6Dn5YdBkKBqHMP/1EKFQ8zAJoPMTkhZB4MJRIlSTnh3tNRzbX8=
            //String privateKey ="";
            //RSA rsa = new RSA(Config.getConfig().get(Config.OPERA_PRIVATE_KEY),null);
            //String message = StrUtil.str(rsa.decrypt(HexUtil.decodeHex(encrypt), KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void  t2() throws Exception {

        try {
            //String url ="http://test3.shands.cn/lvYun/appendRemarks.htm";//确认结果
            String url ="http://ets.shands.cn/lvYun/appendRemarks.htm";//正式确认结果
            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFcGE6G/+ejaiXRyNdw35SA16JP7Tl4YZW65chrOr3zna/Wlsj5+5EySXRC/w1Z5rnHzoxMupKsI4NqA+28GgHoZ4lG679cEkqDI5kPd3pDLnS4q+ZqboCDNv1UyYnTKaCrYiGssyXpl2kA9z9fRzM0pPrjdIncEMdjHzHGUKVVwIDAQAB";
            RSA operaRsa = new RSA(null,publicKey);
            String hotelCode = "KYHAZH";//正式
            //String hotelCode = "KYGYBSD";//测试
            String tradeNo = "4996454";
            String remark = "不确定";
            String encrypt = HexUtil.encodeHexStr(operaRsa.encrypt("{\"hotelCode\":\""+hotelCode+"\",\"tradeNo\":\""+tradeNo+"\",\"remark\":\""+remark+"\"}",KeyType.PublicKey));
            System.out.println("加密字符串="+encrypt);
            Map<String, String> map = new HashMap<>();
            map.put("encrypt",encrypt);
            url = url+"?encrypt="+encrypt;
            System.out.println(url);
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String .class);
            String  body  =entity.getBody();
            KyResponse kyResponse = GsonUtil.getGsonInstance().fromJson(body,KyResponse.class);
            System.out.println(kyResponse.getMessage());

            System.out.println(GsonUtil.getGsonInstance().toJson(kyResponse.getMessage()));
            List<KyBackOrder> kyBackOrders = GsonUtil.getGsonInstance().fromJson(kyResponse.getMessage(), new TypeToken<List<KyBackOrder>>() {}.getType());
            System.out.println(kyBackOrders.get(0).getDep());
            System.out.println(kyBackOrders.get(0).toString());
            //解密 私钥 MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIVwYTob/56NqJdHI13DflIDXok/tOXhhlbrlyGs6vfOdr9aWyPn7kTJJdEL/DVnmucfOjEy6kqwjg2oD7bwaAehniUbrv1wSSoMjmQ93ekMudLir5mpugIM2/VTJidMpoKtiIayzJemXaQD3P19HMzSk+uN0idwQx2MfMcZQpVXAgMBAAECgYBDMNtET2TfiaUix4dfZ1jsIxYvNgz41B65Fdf79P472ZKht/W8lsS7ji2kncxZd9ngFzFOJL0k7tIYvk9rZpJ6yKIBZ8kFKP2mWYdNU2WRohSk0WwlYXHk47jsm6c/bMRWmLRo/d8IBpiZErzCaYn9QezajM7r80dTx7TeiYmNQQJBANmMUoRJ4NbtJlGtq/ajoJDL8bmkwk3cBmxNpFNm33d9EA3aEqn7REglJqbK9Hlf8usKbyAwWnKdmW04jR+uLHUCQQCdBkN3Oo/Uezx1ss3K1fRFRGaLJxaOJE99Uoyqci1s/rlVbwOf/0RORCjfieV3khnL51lZ4qXwB6QnJKa3q7EbAkB7szeEc0G+1FAyp28dqkL1spG7frzhatfYI3np1pgAqINFbRepwe88mqtUshi7H1zQWzl+8x+rBoQkP8C3H+G1AkEAhn/++fmniZGl0AtASyXI/iwAyeDJaSehDu/OnZD/aAUoKw3URRAaKhAoEpHDhBAZGJcp6dxB1sA003KJe3oNUQJBALbqXZIB4mZXI0hWtuwtyXbYVK5IWowrWKWxU6Dn5YdBkKBqHMP/1EKFQ8zAJoPMTkhZB4MJRIlSTnh3tNRzbX8=
            //String privateKey ="";
            //RSA rsa = new RSA(Config.getConfig().get(Config.OPERA_PRIVATE_KEY),null);
            //String message = StrUtil.str(rsa.decrypt(HexUtil.decodeHex(encrypt), KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
