package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
        // 전체 경로 접근 막음
        .addPathPatterns("/**")
        .order(1)
        // 디자인 경로, 로그인, 상품 목록, 상품 상세, 장바구니 등 로그인하면 갈 수 있는 경로 입력
        .excludePathPatterns("/css/**", "/", "/favicon.ico", "/cust/**", "/login", "/loginimpl",  "/item", "/item/detail","/cart/**");

    registry.addInterceptor(new AdminInterceptor())
        // 관리자만 접근 가능한 경로 입력
        .addPathPatterns("/user","/userdetail", "/deleteimpl", "/modifyimpl", "/item/modifyimpl", "/item/deleteimpl", "/item/add", "/item/addimpl")
        .order(2);
  }
}
