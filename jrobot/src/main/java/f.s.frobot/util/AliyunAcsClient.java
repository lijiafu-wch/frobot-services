package f.s.frobot.util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 阿里云ACSClient
 * @author lijiafu
 * @date 2020/1/30 16:22
 * @since 1.0
 */
public class AliyunAcsClient {
    private String regionId; //地域编码
    private String accessKeyId;//阿里云key
    private String secret;//阿里云secret

    public  AliyunAcsClient(String regionId,String accessKeyId,String secret){
        this.regionId=regionId;
        this.accessKeyId=accessKeyId;
        this.secret=secret;
    }

    public IAcsClient getIAcsClient(){
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        return new DefaultAcsClient(profile);
    }
}
