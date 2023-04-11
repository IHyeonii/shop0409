package com.shop.controller;

import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

  @Autowired
  ItemService itemService;

  @Value("${imglocation")
  private String custdir;

  private final String dir = "/item/";

  @RequestMapping("")
  public String main(Model model) {
    List<ItemDTO> itemList;
    try {
      itemList = itemService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("itemList", itemList);
    model.addAttribute("center", dir + "list");
    return "main";
  }

  // localhost/item/detail?id=100
  @RequestMapping("/detail") //http://127.0.0.1/item/detail/100 이렇게 나오게
  public String detail(Model model, ItemDTO itemDTO) { // itemDTO 안에 ID 값을 제외한 나머지는 모두 null
    System.out.println(itemDTO);
    try {
      itemDTO = itemService.get(itemDTO.getId());//가져온걸 담아줘야 itemDTO에 값이 채워져
      System.out.println(itemDTO);
      model.addAttribute("item", itemDTO); // 채워진 itemDTO를 반환해
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("center", dir+"detail");
    return "main";
  }

  @RequestMapping("/modifyimpl")
  public String modifyimpl(Model model, ItemDTO itemDTO) {
    try {
      itemService.modify(itemDTO); //list 페이지에서 id 가져오게했으니까 getId 할필요없어, itemService의 modify에서 itemDTO만 가져오라고 명시했잖아
      model.addAttribute("item", itemDTO); //수정된거 뿌려줘
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    model.addAttribute("center", dir+"detail");
    return "main";
  }

  @RequestMapping("/deleteimpl")
  public String deleteimpl(Integer id) { //modify는 각각의 수정 된 변수값 넘어가야 하는데 delete는 id값만 넘어가면 되니까
    try {
      itemService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return "redirect:/item"; //삭제하면 상품조회 페이지로 이동
  }

}
