package com.dcu.wfd.spring.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;

/**
 * Servlet 관련 설정 Class
 *
 * @author 김건우
 * @since 2022.10.21
 */
@Configuration 
@EnableWebMvc
@ComponentScan(basePackages = {"com.dcu.wfd"})
public class ServletContext implements WebMvcConfigurer{
	/**
     * 1. 특정 URL(Path)에 대한 HTTP Request 처리를 DispatcherServlet이 담당하지 않도록 만들어 주는 설정 (html, js, css, file 등..)
     * 2. Client가 접근하지 못하는 WEB-INF 폴더안에 위치해 있는 정적 자원에 Clien가 접근할 수 있도록 만들어 주는 설정
     *
     * @author 김건우
     * @since 2022.10.21
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
        //registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/jsp/");
    }
    
    /**
     * JSP ViewResolver를 제공해 주기 위한 Bean
     *
     * @author 김건우
     * @since 2022.10.21
     * @return InternalResourceViewResolver
     */
    @Bean(name="jspView")
    public ViewResolver getJspView() {
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
        jspViewResolver.setPrefix("/views");
        jspViewResolver.setSuffix(".jsp");
        jspViewResolver.setOrder(1);
        return jspViewResolver;
    }
    
    /**
     * Custom Bean Class ViewResolver를 제공해 주기 위한 Bean (파일 다운로드, JSON 등..)
     *
     * @author 최정우
     * @since 2022.08.31
     * @return BeanNameViewResolver
     */
    @Bean(name="beanView")
    public BeanNameViewResolver getBeanView () {
        BeanNameViewResolver beanView = new BeanNameViewResolver();
        beanView.setOrder(0);
        return beanView;
    }

    /**
     * JSON ViewResolver를 제공해 주기 위한 Bean
     *
     * @author 최정우
     * @since 2022.08.31
     * @return MappingJackson2JsonView
     */
    @Bean(name="jsonView")
    public MappingJackson2JsonView getJsonView () {
        ObjectMapper objectMapper = new ObjectMapper();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView(objectMapper);
        jsonView.setExtractValueFromSingleKeyModel(true);
        return jsonView;
    }
}
