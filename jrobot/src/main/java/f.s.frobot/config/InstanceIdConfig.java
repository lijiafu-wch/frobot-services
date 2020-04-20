package f.s.frobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author lijiafu
 * @date 2020/2/25 16:40
 */
@Component
@ConfigurationProperties(prefix="instanceid")
public class InstanceIdConfig {
   private String keyPrefix;

   private Long typeCourse;

   private Long dc;

   public String getKeyPrefix() {
       return keyPrefix;
   }

   public void setKeyPrefix(String keyPrefix) {
       this.keyPrefix = keyPrefix;
   }

   public Long getTypeCourse() {
       return typeCourse;
   }

   public void setTypeCourse(Long typeCourse) {
       this.typeCourse = typeCourse;
   }

   public Long getDc() {
       return dc;
   }

   public void setDc(Long dc) {
       this.dc = dc;
   }
}
