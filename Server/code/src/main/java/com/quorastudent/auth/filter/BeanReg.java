package com.quorastudent.auth.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanReg {

	@Bean
	public FilterRegistrationBean someFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(someFilter());
		registration.addUrlPatterns("/*");
//	    registration.addInitParameter("paramName", "paramValue");
//	    registration.setName("someFilter");
//	    registration.setOrder(1);
		return registration;
	}

	public Filter someFilter() {
		return new RequestAuthenticationFilter();

	}

}
