package com.shop.controller;

import com.shop.dto.CartsDTO;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.service.CartsService;
import com.shop.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartsService cartsService;
  private final String dir = "/cart/";


  //로그인 한 사용자의 cart 정보만 불러올거니까 session이 필요해 -> mapper 의 cartsall
  @RequestMapping("")
  public List<CartsDTO> cartListByUserId(String cust_id) {
    // 세션값이 있는 경우에 -> cust_id 가져왔으니까 세션이 있다고 가정하는 거야
    try {
      List<CartsDTO> cartsDTOS = cartsService.cartsAll(cust_id);
      return cartsDTOS;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/addcartimpl")
  public ItemDTO addcartimpl(CartsDTO cartsDTO){ //먼저 장바구니 정보를 들고와야지 ~

    try {
      ItemDTO register = cartsService.register(cartsDTO);
      return register;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/deleteimpl")
  public void deleteimpl(int id) {
    try {
      cartsService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/modifyimpl")
  public CartsDTO modifyimpl(CartsDTO cartsDTO) {
    try {
      cartsService.modify(cartsDTO);
      return cartsService.get(cartsDTO.getId());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
