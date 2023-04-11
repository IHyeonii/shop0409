package com.shop.service;

import com.shop.dto.CartsDTO;
import com.shop.frame.MyService;
import com.shop.mapper.CartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartsService implements MyService<Integer, CartsDTO> {

  @Autowired
  CartsMapper cartsMapper;

  @Override
  public void register(CartsDTO cartsDTO) throws Exception {
    cartsMapper.insert(cartsDTO);
  }

  @Override
  public void modify(CartsDTO cartsDTO) throws Exception {
    cartsMapper.update(cartsDTO);
  }

  @Override
  public void remove(Integer integer) throws Exception {
    cartsMapper.delete(integer);
  }

  @Override
  public CartsDTO get(Integer integer) throws Exception {
    return cartsMapper.select(integer);
  }

  @Override
  public List<CartsDTO> get() throws Exception {
    return cartsMapper.selectAll();
  }

  public List<CartsDTO> cartsAll(String cust_id) throws Exception {
    return cartsMapper.cartsall(cust_id);
  }
}
