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
import java.util.List;

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
//    custDTO.setId("test100");
//    custDTO.setPwd("test100");

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

  // 회원관리 추가
  @RequestMapping("/user")
  public String user(Model model,CustDTO custDTO) {
    try {
      List<CustDTO> custDTOS = custService.get(); //여러 개 뿌려줘야 하니까 List로 받아야
      model.addAttribute("user", custDTOS);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("center", "user");
    return "main";
  }
  // user에서 each로 반복하기 때문에 컨트롤러에서는 따로 for문으로 반복하지 않아도 돼

  @RequestMapping("/userdetail")
  public String userdetail(Model model, String id) {
    try {
      CustDTO custDTO = custService.get(id);
      model.addAttribute("user", custDTO);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("center", "userdetail");
    return "main";
  } //id를 가지고 가야 하니까 user에서 디테일로 넘어갈 때도 id가 있어야지

  @RequestMapping("/modifyimpl")
  public String modifyimpl(CustDTO custDTO) { //왜 String id로만 하면 안 되는거야
    System.out.println("입력받은 내용은 바로! " + custDTO);
    try {
      custService.modify(custDTO);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return "redirect:/userdetail?id=" + custDTO.getId();
  }

  @RequestMapping("/deleteimpl")
  public String deleteimpl(String id) {
    try {
      custService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return "redirect:/user";
  }


}
