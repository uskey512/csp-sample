package com.uskey512.csp_sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
            .mediaType("csp-report", MediaType.valueOf("application/csp-report"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Jacksonを使用するJSON用のHttpMessageConverterにカスタムメディアタイプを追加
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(List.of(
            MediaType.APPLICATION_JSON,       // 既存のJSON
            MediaType.valueOf("application/csp-report") // カスタムメディアタイプ
        ));
        converters.add(jsonConverter);
    }
}
