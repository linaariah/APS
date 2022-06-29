package com.Naariah.dao;


import com.Naariah.domain.PartBom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PartDao extends BaseMapper<PartBom> {

}
//    @Select("select * from part")
//    public List<Part> getAll();
