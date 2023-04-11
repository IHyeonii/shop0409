package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

  @Autowired
  CustService custService;

  @RequestMapping("/")
  public String main() {
    return "main";
  }

  @RequestMapping("/login")
  public String login(Model model) {
    model.addAttribute("center", "login");
    return "main";
  }

  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    if(session != null)
      session.invalidate();
    return "main";
  }

  @RequestMapping("/loginimpl")
  public String loginimpl(Model model,CustDTO custDTO, HttpSession session) {
    custDTO.setId("id01");
    custDTO.setPwd("pwd01");

    try {
      CustDTO cust = custService.get(custDTO.getId());//로그인 했는지 확인하려면 custDTO 에서 id 있는지부터 확인해야지
      if(cust != null && cust.getPwd().equals(custDTO.getPwd())) {
        session.setAttribute("logincust", cust);
      } else {
        // 로그인 못해
        model.addAttribute("errMsg", "로그인 못해");
      }
//
//      if(cust == null){
//        System.out.println("없는 아이디");
//      } else {
//        // 있는 아이디
//        String 사용자의진짜비번 = cust.getPwd();
//        String 내가입력한비번 = custDTO.getPwd();
//        if (사용자의진짜비번.equals(내가입력한비번)) {
//          // 로그인 성공
//          session.setAttribute("logincust", cust);
//        } else {
//          System.out.println("비번 틀림");
//        }
//      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return "main";
  }


}
