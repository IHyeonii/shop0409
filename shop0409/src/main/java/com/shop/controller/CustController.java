package com.shop.controller;

import com.github.pagehelper.PageInfo;
import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cust")
public class CustController {

  @Autowired
  CustService custService;

  private final String dir = "/cust/";


  @RequestMapping("")
  public String main(Model model) {
    model.addAttribute("center", dir+"register");
    return "main";
  }

  @RequestMapping("/register")
  public String register(Model model) {
    model.addAttribute("center", dir+"register");
    return "main";
  }

  @RequestMapping("/registerimpl")
  public String registerimpl(Model model, CustDTO custDTO, HttpSession session) {
    System.out.println(custDTO);
    try {
      custService.register(custDTO);
      session.setAttribute("logincust", custDTO); // 이게 로그인처리, 가입하면 바로 로그인 돼
      return "redirect:/";
    } catch (Exception e) {
      model.addAttribute("err", "회원가입 실패, 다시 시도해주세요!");
      model.addAttribute("center", dir+"register");
      return "main";
    }
  }
}
