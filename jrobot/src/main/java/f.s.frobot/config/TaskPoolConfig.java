package f.s.frobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 线程池配置属性类
 * @author lijiafu
 * @date 2020/4/2 20:58
 */
@ConfigurationProperties(prefix = "task.pool")
@Component
@Data
public class TaskPoolConfig {
    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int scheduleCorePoolSize;
}
