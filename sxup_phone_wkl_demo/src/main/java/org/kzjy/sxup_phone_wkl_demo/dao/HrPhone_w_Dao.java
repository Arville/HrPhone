package org.kzjy.sxup_phone_wkl_demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface HrPhone_w_Dao {
    //插入前端传过来的数据
    int insertInfo(Map<String,Object> map);
    //查询手机号是否已存在
    String queryPhone(@Param("phone")String phone);

}
