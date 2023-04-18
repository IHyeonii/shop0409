package com.shop.controller;

import com.shop.dto.ItemDTO;
import com.shop.service.CustService;
import com.shop.service.ItemService;
import com.shop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

  @Autowired
  ItemService itemService;

  @Value("${imglocation")
  private String custdir;

  private final String dir = "/item/";

  @RequestMapping("")
  public List<ItemDTO> main() {
    List<ItemDTO> itemList;
    try {
      itemList = itemService.get();
      return itemList;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  // try 밖은 실패했을 때
  }

  // localhost/item/detail?id=100
  @RequestMapping("/detail")
  public ItemDTO detail(int id) { // itemDTO 안에 ID 값을 제외한 나머지는 모두 null
    try {
      ItemDTO itemDTO = itemService.get(id);// 어떻게 id만 가져왔는데 다른걸 다 들고와??
      // mapper 에서 정했으니까, SELECT * FROM item WHERE id=#{id} -> id로 item 다 들고오라고
      return itemDTO;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/modifyimpl")
  public ItemDTO modifyimpl(ItemDTO id) { //int id가 아니야, parameterType="itemDTO" 라서

//    String file = itemDTO.getImg().getOriginalFilename(); 이미지는 프론트 쪽이래
    try {
        itemService.modify(id);
        return itemService.get(id.getId());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/deleteimpl")
  public void deleteimpl(Integer id) { // delete는 반환값이 없으니까 void야
    try {
      itemService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  // 상품등록
  @RequestMapping("/add")
  public ItemDTO add(ItemDTO itemDTO) {
    try {
      return itemService.register(itemDTO);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

//  @RequestMapping("/addimpl") add는 화면, addimpl은 동작 부분이었으니까, 하나만 해주면 돼
//  public String addimpl(ItemDTO itemDTO) {
////    String img = itemDTO.getImg().getOriginalFilename();
////    itemDTO.setImgName(img);
//    try {
//      Util.saveFile(itemDTO.getImg(), custdir); // 저장해야지, img라고 쓰면 안 돼고 itemDTO에서 꺼내와야
//      itemService.register(itemDTO);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//    return "redirect:/item";
//  }

}
