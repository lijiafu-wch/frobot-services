package f.s.fadminrobot.util;

import f.s.frobot.util.AliyunUtil;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.http.MethodType;

/**
 * 云小蜜管理平台-公共请求参数
 * @author lijiafu
 * @date 2020/2/18 15:41
 * @since 1.0
 */
@Component
public class ChatCommonRequest{
    /**
     * 公共请求参数
     * @author lijiafu
     * @date 2020/2/18 15:44
     */
    public CommonRequest getCommonRequest() throws Exception {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysVersion("2017-10-11");
        commonRequest.putQueryParameter("ServiceCode", new String("beebot".getBytes("gbk"),"utf-8"));
        commonRequest.putQueryParameter("Format", new String("JSON".getBytes("gbk"),"utf-8"));
        commonRequest.putQueryParameter("SignatureVersion", new String("1.0".getBytes("gbk"),"utf-8"));
        commonRequest.putQueryParameter("SignatureMethod", new String("HMAC-SHA1".getBytes("gbk"),"utf-8"));
        commonRequest.putQueryParameter("Timestamp", new String(AliyunUtil.getTimestamp().getBytes("gbk"),"utf-8"));
        commonRequest.putQueryParameter("SignatureNonce", UUID.randomUUID().toString());
        return commonRequest;
    }
}
