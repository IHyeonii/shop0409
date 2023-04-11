package com.shop.controller;

import com.shop.dto.CartsDTO;
import com.shop.dto.CustDTO;
import com.shop.service.CartsService;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartsService cartsService;
  private final String dir = "/cart/";


  @RequestMapping("")
  public String cartListByUserId(Model model, HttpSession session) { //로그인 한 사용자의 cart 정보만 불러올거니까 session이 필요해
    // 세션값이 있는 경우에
    if (session != null && session.getAttribute("logincust") != null) {
      //세션을 통해서 로그인한 사용자 아이디 가져오기 시작
      CustDTO custDTO = (CustDTO) session.getAttribute("logincust");
      String cust_id = custDTO.getId();
      //세션을 통해서 로그인한 사용자 아이디 가져오기 끝
      try {
        List<CartsDTO> cartsDTOS = cartsService.cartsAll(cust_id); //담았으면 변수로 가져와야 모델로 뿌리지 ㅜㅜ
        model.addAttribute("cartList", cartsDTOS);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      model.addAttribute("center", dir + "list");
      return "main";
    // 세션값이 없는 경우, 즉 로그인을 하지 않았으면 장바구니 조회하지 않고 로그인 페이지로 이동
    } else {
      return "redirect:/login";
    }
  }

  @RequestMapping("/addcartimpl")
  public String addcartimpl(Model model, CartsDTO cartsDTO, HttpSession session){ //먼저 장바구니 정보를 들고와야지 ~
    System.out.println(cartsDTO);
    try {
      CustDTO logincust = (CustDTO) session.getAttribute("logincust");
      if(logincust == null) {
        return "redirect:/login";
      }
      cartsDTO.setCnt(1);
      cartsDTO.setCust_id(logincust.getId());
      cartsService.register(cartsDTO); //장바구니에 정보를 등록해줘야지 ,get이 아니야
//      CartsDTO addCart = cartsService.get(cartsDTO.getItem_id());
//      model.addAttribute("cart", addCart);
//      System.out.println(addCart);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return "redirect:/cart";
  }

  @RequestMapping("/deleteimpl")
  public String deleteimpl(CartsDTO cartsDTO) {

    try {
      cartsService.remove(cartsDTO.getId());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return "redirect:/cart";
  }

  @RequestMapping("/modifyimpl")
  public String modifyimpl(int id, String sign) {
    System.out.println("카트 아이디는 " + id + "부호는 " + sign);

    try {
      CartsDTO cartsDTO = cartsService.get(id);
      if (sign.equals("plus")) {
        cartsDTO.setCnt(cartsDTO.getCnt() + 1);
      } else {
        if (cartsDTO.getCnt() > 1) {
          cartsDTO.setCnt(cartsDTO.getCnt() - 1);
        }
      }
      cartsService.modify(cartsDTO);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return "redirect:/cart";
  }
}
