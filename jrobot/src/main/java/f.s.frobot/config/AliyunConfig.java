package f.s.frobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 阿里云配置
 * @author lijiafu
 * @date 2019/12/31 14:44
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix="aliyun")
@Data
public class AliyunConfig {
    /**
     * 地域标识
     */
    private String regionId;
    /**
     * 聊天-key
     */
    private String accessKeyId;
    /**
     * 聊天-密钥
     */
    private String secret;
    /**
     * 机器人实例id
     */
    private String instanceId;
    /**
     * 默认值
     */
    private String defaultValue;

}
