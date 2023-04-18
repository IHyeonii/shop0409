package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsDTO {
  private int id;
  private String cust_id;
  private int item_id;
  private int cnt;
  private Date rdate;

  // 여기 조회 시에 필요한 속성 4개가 추가되면서

  //select
  private String item_name;
  private int item_price;
  private String item_imgname;

  //
  private String cust_name;

  // Insert할 때 필요한 속성들을 가지고 생성자를 만든다.
  public CartsDTO(int id, String cust_id, int item_id, int cnt, Date rdate) {
    this.id = id;
    this.cust_id = cust_id;
    this.item_id = item_id;
    this.cnt = cnt;
    this.rdate = rdate;
  }

}
