package com.sergeyk.course.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;

@SpringBootApplication
public class MobileWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileWebAppApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(formFilter());
		registration.addUrlPatterns("/*");
		registration.setName("formFilter");
		registration.setOrder(1);
		return registration;
	}

	public FormContentFilter formFilter() {
		return new FormContentFilter();
	}
}
