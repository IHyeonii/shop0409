package com.shop.controller;

import com.shop.dto.CustDTO;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MainController {

  @Autowired
  CustService custService;

//  @RequestMapping("/")
//  public String main() {
//    return "main";
//  }

//  @RequestMapping("/login")
//  public String login(Model model) {
//    model.addAttribute("center", "login");
//    return "main";
//  }

//  @RequestMapping("/logout")
//  public String logout(HttpSession session) {
//    if(session != null)
//      session.invalidate();
//    return "main";
//  }

  @RequestMapping("/loginimpl")
  public CustDTO loginimpl(CustDTO custDTO) { // 세션처리를 이제 프론트에서 해
    try {
      CustDTO cust = custService.get(custDTO.getId());//로그인 했는지 확인하려면 custDTO 에서 id 있는지부터 확인해야지
      if(cust != null && cust.getPwd().equals(custDTO.getPwd())) {
        // 로그인 성공
        return cust;
      } else {
        // 로그인 못해
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // 회원 전체 리스트를 뿌려주는 API
  @RequestMapping("/user")
  public List<CustDTO> user() {
    try {
//      List<CustDTO> custDTOS = custService.get(); //여러 개 뿌려줘야 하니까 List로 받아야
//      return custDTOS;
      return custService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  // user에서 each로 반복하기 때문에 컨트롤러에서는 따로 for문으로 반복하지 않아도 돼

  @RequestMapping("/userdetail")
  public CustDTO userdetail(String id) {
    try {
      CustDTO custDTO = custService.get(id);
      return custDTO;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  } //id를 가지고 가야 하니까 user에서 디테일로 넘어갈 때도 id가 있어야지

  @RequestMapping("/modifyimpl")
  public CustDTO modifyimpl(CustDTO custDTO) { //왜 String id로만 하면 안 되는거야
    System.out.println("입력받은 내용은 바로! " + custDTO);
    try {
      custService.modify(custDTO); // modify 메서드는 리턴타입이 void 즉, return 하지 않아.
      // 하지만 변경 사항을 보여주는 것을 원하니까
      return custService.get(custDTO.getId()); // modify 후에 get 메서드를 이용해 PK 값인 ID를 이용해 CustDTO 반환
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/deleteimpl")
  public Void deleteimpl(String id) {
    try {
      custService.remove(id);
      return null; // return null이라 void야
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // 메서드는
  // 접근제한자 리턴타입 메서드명(매개변수타입 매개변수명) { 실행되는구문; return 리턴타입의변수명 }
}
