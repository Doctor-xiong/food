package com.xjt.food.mapper;

import com.xjt.food.entity.TableInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TableFoodInfoMapper {

    @Insert("insert into desk_food_info values(name=#{name},num=#{num},price=#{price},table_id=#{tableId})")
    int insert(TableInfo info);

    @Select("select * from desk_food_info where table_id = #{id}")
    List<TableInfo> queryAll(Long id);
}
