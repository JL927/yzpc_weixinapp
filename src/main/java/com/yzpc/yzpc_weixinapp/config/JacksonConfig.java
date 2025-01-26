package com.yzpc.yzpc_weixinapp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wq
 * @description 对象转JSON的自定义配置
 * @date 2024/12/24 17:20:36
 */
@Configuration
public class JacksonConfig {
    //将long类型转化为String
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            builder.serializerByType(Long.class, new ToStringSerializer());
            builder.serializerByType(long.class, new ToStringSerializer());
            builder.featuresToEnable(DeserializationFeature.USE_LONG_FOR_INTS);
        };
    }
}
