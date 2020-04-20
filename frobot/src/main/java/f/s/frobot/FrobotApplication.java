package f.s.frobot;

import f.s.frobot.config.AliyunConfig;
import f.s.frobot.util.AliyunAcsClient;
import f.s.jcache.Cache;
import f.s.jcache.CacheRedis;
import f.s.jlog.EnableRequestLogger;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.UserAgentConfig;

@SpringBootApplication
@EnableRequestLogger
public class FrobotApplication {
    @Autowired
    private AliyunConfig aliyunConfig;

    public static void main(String[] args) {
        SpringApplication.run(FrobotApplication.class, args);
    }
    @Bean
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
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM,MediaType.TEXT_PLAIN,MediaType.APPLICATION_FORM_URLENCODED}));

        return restTemplateBuilder.additionalMessageConverters(converter).build();
    }
    @Bean
    public Cache getRedisCache(RedisConnectionFactory connectionFactory){
        return new CacheRedis(connectionFactory);
    }

    @Bean
    public AliyunAcsClient getIAcsClient(){
        AliyunAcsClient aliyunAcsClient = new AliyunAcsClient(aliyunConfig.getRegionId(), aliyunConfig.getAccessKeyId(), aliyunConfig.getSecret());
        return aliyunAcsClient;
    }
}
