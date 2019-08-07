package org.kzjy.sxup_phone_wkl_demo.service.impl;

import org.kzjy.sxup_phone_wkl_demo.dao.HrPhone_w_Dao;
import org.kzjy.sxup_phone_wkl_demo.service.HrPhone_w_Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class HrPhone_w_ServiceImpl implements HrPhone_w_Service {
    @Resource
    HrPhone_w_Dao hrPhone_w_dao;

    @Override
    public int save(Map<String, Object> map) {
        int i = hrPhone_w_dao.insertInfo(map);
        return i;
    }

    @Override
    public String queryPhone(String phone) {
        String phone_new  = hrPhone_w_dao.queryPhone(phone);
        return phone_new;
    }
}
