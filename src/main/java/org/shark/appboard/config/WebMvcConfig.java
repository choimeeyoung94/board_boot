package org.shark.appboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	/*
	 * CORS (Cors-Origin Resource Sharing)
	 * 
	 * 1. 교차 출처 자원 공유
	 * 2. 브라우저가 자신의 출처가 아닌 다른 출처로 부터 자원을 로딩하는 것을 허용하도록 서버가 허가하는 HTTP 헤더 기반 알고리즘
	 *    (보안 상 이유로 브라우저는 다른 추처의 HTTP 요청을 제안한다)
	 * 3. XMLHttpRequest, fetch(), axios 등은 모두 CORS 정책을 따른다
	 * 4. 스프링 부트 서버(http://localhost:8080)와 리액트 서버(http://localhost:3000)는 서로 다른 출처로 인식하므로 CORS 허용 작업이 필요하다
	 * 
	 * */
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET",  "POST", "DELETE", "PUT", "OPTIONS")
			.allowedHeaders("Authorization", "Content-Type", "Accepts");
			
	}
	
	
}
