package com.shop.mapper;

import com.github.pagehelper.Page;
import com.shop.dto.ItemDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper extends MyMapper<Integer, ItemDTO> {
}
