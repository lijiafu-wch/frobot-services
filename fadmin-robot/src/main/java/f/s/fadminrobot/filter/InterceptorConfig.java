package f.s.fadminrobot.filter;

import f.s.utils.DateUtil;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 注册拦截器
 * @author lijiafu
 * @date 2020/3/2 10:35
 * @since 1.0
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private BaseUserInterceptor baseUserInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(baseUserInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
    ///**
    // * 使用此方法, 以下 spring-boot: jackson时间格式化 配置 将会失效
    // * spring.jackson.time-zone=GMT+8
    // * spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
    // * 原因: 会覆盖 @EnableAutoConfiguration 关于 WebMvcAutoConfiguration 的配置
    // * */
    //@Override
    //public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    //    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    //    ObjectMapper objectMapper = converter.getObjectMapper();
    //    // 生成JSON时,将所有Long转换成String
    //    SimpleModule simpleModule = new SimpleModule();
    //    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    //    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    //    objectMapper.registerModule(simpleModule);
    //    // 时间格式化
    //    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    //    // 设置格式化内容
    //    converter.setObjectMapper(objectMapper);
    //    converters.add(0, converter);
    //}
}
