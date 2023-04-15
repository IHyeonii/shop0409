package com.shop.config;

import com.shop.dto.CustDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    CustDTO user = (CustDTO) session.getAttribute("logincust"); //이게 Object라 CustDTO로 다운해줘야 돼. 그래야 logincust 가져올 수 있어
    if (user.getName().equals("관리자")) {
      // 관리자 상태
      return true;
    } else {
      // 일반 사용자 상태
      return false;
    }
  }
}
