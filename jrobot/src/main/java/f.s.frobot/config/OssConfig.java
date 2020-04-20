package f.s.frobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * Oss配置
 * @author lijiafu
 * @date 2020/3/3 11:05
 */
@Component
@ConfigurationProperties(prefix="uploader.oss")
public class OssConfig {

    private String bucketName;
    private String host;
    private String endpoint;
    private Long uploadedFileMaxSize;


    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Long getUploadedFileMaxSize() {
        return uploadedFileMaxSize;
    }

    public void setUploadedFileMaxSize(Long uploadedFileMaxSize) {
        this.uploadedFileMaxSize = uploadedFileMaxSize;
    }
}
