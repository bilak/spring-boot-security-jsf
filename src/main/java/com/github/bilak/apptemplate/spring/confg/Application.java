package com.github.bilak.apptemplate.spring.confg;

import com.github.bilak.apptemplate.spring.scope.JsfViewScope;
import org.h2.server.web.WebServlet;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.request.RequestContextListener;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

@Import({MethodSecurityConfig.class, WebSecurityConfig.class})
@ComponentScan(basePackages = {"com.github.bilak.apptemplate.service","com.github.bilak.apptemplate.controller","com.github.bilak.apptemplate.jsf"})
@EntityScan(basePackages = {"com.github.bilak.apptemplate.domain"})
@EnableJpaRepositories(basePackages = {"com.github.bilak.apptemplate.repository"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    // fix for Could not register destruction callback WARNING
    @Bean
    public ServletListenerRegistrationBean requestContextListener() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean(new RequestContextListener());
        registrationBean.setName("RequestContextListener");
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean facesServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), new String[]{"*.xhtml"});
        registration.setName("Faces Servlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean facesUploadFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FileUploadFilter(), facesServlet());
        registrationBean.setName("PrimeFaces FileUpload Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        return registrationBean;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            // JSF
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
            // PRIMEFACES
            servletContext.setInitParameter("primefaces.THEME", "bootstrap");
            servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
            // BOOTSFACES
            servletContext.setInitParameter("net.bootsfaces.get_fontawesome_from_cdn", Boolean.TRUE.toString());
            servletContext.setInitParameter("BootsFaces_USETHEME", Boolean.TRUE.toString());
        };
    }

    @Bean
    public static CustomScopeConfigurer customScope() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<String, Object>();
        scopes.put("view", new JsfViewScope());
        configurer.setScopes(scopes);
        return configurer;
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/view/admin/sqlconsole/*");
        Map initParameters = new HashMap<>();
        initParameters.put("webAllowOthers", "true");
        registration.setInitParameters(initParameters);
        return registration;
    }
}
