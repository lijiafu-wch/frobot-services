package f.s.frobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 *  机器人配置文件
 * @author lijiafu
 * @date 2020/2/25 16:40
 */
@Component
@ConfigurationProperties(prefix="robot")
@Data
public class RobotConfig {
    /**
     * 默认机器人有效期
     * @author lijiafu
     * @date 2020/2/25 17:04
     */
   private Integer inDate;

   private String timeZone;
}
