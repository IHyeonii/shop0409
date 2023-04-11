package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
  private int id, price;
  private String name;
  private String imgName;
  private Date rdate;

  private MultipartFile img;

  // 생성자에 5개 지정하고 테스트 했으면 5개 값으로 다 넣어줘야 해
  // img는 생성자가 필요없어
  public ItemDTO(int id, int price, String name, String imgName, Date rdate) {
    this.id = id;
    this.price = price;
    this.name = name;
    this.imgName = imgName;
    this.rdate = rdate;
  }
}
