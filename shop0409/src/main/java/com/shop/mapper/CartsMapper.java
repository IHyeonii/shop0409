package com.shop.mapper;

import com.shop.dto.CartsDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartsMapper extends MyMapper<Integer, CartsDTO> {
//  void insert(CartsDTO c);
//  void update(CartsDTO c);
//  void delete(Integer i);
//  CartsDTO select(Integer i);
//  List<CartsDTO> selectAll();
//  public 반환타입 메서드명(매개변수);
  public List<CartsDTO> cartsall(String cust_id); //장바구니 여러개 담으니까 리스트로 담아라
}
