package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cust")
public class CustController {

  @Autowired
  CustService custService;

  private final String dir = "/cust/";

//  @RequestMapping("")
//  public String main(Model model) {
//    model.addAttribute("center", dir+"register");
//    return "main";
//  }

//  @RequestMapping("/register")
//  public String register(Model model) {
//    model.addAttribute("center", dir+"register");
//    return "main";
//  }

  @RequestMapping("/registerimpl")
  public ItemDTO registerimpl(CustDTO custDTO) {
    System.out.println(custDTO);
    try {
      ItemDTO register = custService.register(custDTO);
      return register;
    } catch (Exception e) {

    }
    return null; //CustService에서 리턴값이 null이라 void여서
  }
}
