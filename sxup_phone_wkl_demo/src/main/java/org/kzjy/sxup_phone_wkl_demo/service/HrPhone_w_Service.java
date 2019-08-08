package org.kzjy.sxup_phone_wkl_demo.service;


import java.util.Map;

public interface HrPhone_w_Service {
    //插入前端传来的参数方法
    int save_w(Map<String,Object> map);
    //查询手机号是否已存在
    String queryPhone(String phone);
}
