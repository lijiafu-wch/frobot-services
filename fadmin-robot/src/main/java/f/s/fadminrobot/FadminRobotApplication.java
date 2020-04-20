package f.s.fadminrobot;

import f.s.frobot.config.AliyunConfig;
import f.s.frobot.config.InstanceIdConfig;
import f.s.frobot.config.RobotIdConfig;
import f.s.frobot.config.TaskPoolConfig;
import f.s.frobot.util.AliyunAcsClient;
import f.s.frobot.util.ThreadPoolUtils;
import f.s.idgenerator.CacheCounter;
import f.s.idgenerator.TypeIdGenerator;
import f.s.jcache.Cache;
import f.s.jcache.CacheRedis;
import f.s.jlog.EnableRequestLogger;
import f.s.utils.spring.ErrorHandler;
import java.util.Arrays;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableRequestLogger
@ComponentScan({"f.s.fadminrobot","f.s.frobot"})
@MapperScan({"f.s.frobot.dao"})
@EnableCaching
@EnableScheduling   // 2.开启定时任务
public class FadminRobotApplication {
    @Autowired
    private AliyunConfig aliyunConfig;
    @Autowired
    private RobotIdConfig robotIdConfig;
    @Autowired
    private InstanceIdConfig  instanceIdConfig;
    @Autowired
    private TaskPoolConfig taskPoolConfig;

    public static void main(String[] args) {
        SpringApplication.run(FadminRobotApplication.class, args);
    }

    @ControllerAdvice
    public static class myErrorHandler extends ErrorHandler {
    }

    @Bean
    //@Profile({"dev", "test"})
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("5MB"); //KB,MB
        //设置总上传数据总大小
        factory.setMaxRequestSize("8MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM,MediaType.TEXT_PLAIN,MediaType.APPLICATION_FORM_URLENCODED}));

        return restTemplateBuilder.additionalMessageConverters(converter).build();
    }

    @Bean
    public AliyunAcsClient getIAcsClient(){
        AliyunAcsClient aliyunAcsClient = new AliyunAcsClient(aliyunConfig.getRegionId(), aliyunConfig.getAccessKeyId(), aliyunConfig.getSecret());
        return aliyunAcsClient;
    }

    @Bean
    public ThreadPoolUtils getThreadPoolUtils(){
        return new ThreadPoolUtils(taskPoolConfig);
    }

    @Bean
    public Cache getRedisCache(RedisConnectionFactory connectionFactory){
        return new CacheRedis(connectionFactory);
    }

    @Bean(value = "robotId")
    public TypeIdGenerator createRobotIdGenerator(Cache cache) {
        CacheCounter counter = new CacheCounter(robotIdConfig.getKeyPrefix(),cache);
        counter.initCounter(robotIdConfig.getTypeCourse(),robotIdConfig.getDc(),1);
        return new TypeIdGenerator(counter);
    }
    @Bean(value = "instanceId")
    public TypeIdGenerator createInstanceIdGenerator(Cache cache) {
        CacheCounter counter = new CacheCounter(robotIdConfig.getKeyPrefix(),cache);
        counter.initCounter(instanceIdConfig.getTypeCourse(),instanceIdConfig.getDc(),1);
        return new TypeIdGenerator(counter);
    }
}
