package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {
  private final String dir = "/cust/";
  @RequestMapping("")
  public String main(Model model) {
    model.addAttribute("center", dir+"register");
    return "main";
  }
} // 여기는 화면단이라 안 해도 돼
