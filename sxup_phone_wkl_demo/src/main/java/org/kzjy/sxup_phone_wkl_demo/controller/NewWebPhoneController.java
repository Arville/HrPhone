package org.kzjy.sxup_phone_wkl_demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.kzjy.sxup_phone_wkl_demo.service.HrPhone_w_Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("NewWebPhone")
public class NewWebPhoneController {
    @Resource
    HrPhone_w_Service hrPhone_w_service;

    @ResponseBody
    @RequestMapping(value = "EducationForm")
    public JSONPObject EducationForm(String callback, HttpServletRequest req, String names, String phones, String remark, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {

        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        String queryPhone = hrPhone_w_service.queryPhone(phones);
        if (queryPhone == null && phones!=null) {
            try {
                names = URLDecoder.decode(URLDecoder.decode(names, "UTF-8"), "UTF-8");
                remark = URLDecoder.decode(URLDecoder.decode(remark, "UTF-8"), "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
                Map<String, Object> bean = new HashMap();
                bean.put("PROJECT", "17");//学历表单
                bean.put("DATA_FROM", "3");
                bean.put("IS_VOLUNTARILY", "1");
                bean.put("SIGN_LEVEL", "5");
                bean.put("WEBSITE", "2");
                bean.put("NAME", names);
                bean.put("PHONE",phones);
                bean.put("REMARK",remark);
                try {
                    hrPhone_w_service.save_w(bean);
                }catch (Exception e){
                    e.printStackTrace();
                }

                //wkl注释
                /*try {
                    bean=hrPhoneService.get(bean);
                    String NUM_IDS=bean.get("NUM_ID").toString();
                    if(NUM_IDS!=null&&!NUM_IDS.equals("")){
                        String[] numIds=NUM_IDS.split(",");
                        for (int i = 0; i < numIds.length; i++) {
                            Map<String,Object> map=new HashMap<String, Object>();
                            map.put("NUM_ID", numIds[i]);
                            map=hrPhoneService.get(map);//获得单详细信息
                            if(MapUtils.getString(map, "PROJECT")!=null&&!MapUtils.getString(map, "PROJECT").equals("")){
                                Map<String,Object> query=new HashMap<String, Object>();
                                query.put("PROJECT", MapUtils.getString(map, "PROJECT"));
                                query.put("STATUS", "2");
                                List<Map<String,Object>> list=disPlanService.listTitle(query);//获得配置title
                                if(list!=null&&list.size()>0){
                                    String titleId=list.get(0).get("NUM_ID").toString();
                                    Map<String,Object> query2=new HashMap<String, Object>();
                                    query2.put("TITLE_ID", titleId);
                                    //获取排班系统
                                    Calendar c = Calendar.getInstance();
                                    int hour=c.get(Calendar.HOUR_OF_DAY);
                                    int min=c.get(Calendar.MINUTE);
                                    //早班9:30——20:00
                                    if(hour<9||hour>=20){
                                        query2.put("ZAOBAN", "1");
                                    }
                                    if(hour==9&&min<30){
                                        query2.put("ZAOBAN", "1");
                                    }
                                                    //原本就有的注释
													*//*if(hour==18&&min>30){
														query2.put("ZAOBAN", "1");
													}
													//晚班1:30——9:30
													if(hour<13||hour>=22){
														query2.put("WANBAN", "1");
													}
													if(hour==13&&min<30){
														query2.put("WANBAN", "1");
													}
													if(hour==21&&min>30){
														query2.put("WANBAN", "1");
													}*//*
                                    List<Map<String,Object>> list2=disPlanService.listPlanWeightOrder(query2);//获得所有人员权重配置
                                    if(list2!=null&&list2.size()>0){
                                        Map<String,Object> phone=new HashMap<String, Object>();
                                        phone.put("NUM_IDS", numIds[i]);
                                        phone.put("USER_DEPT", list2.get(0).get("USER_NO"));
                                        hrPhoneService.fenpei(phone);//分配
                                        list2.get(0).put("HAVE_NUM", "1");
                                        hrPhoneService.updateHaveNum(list2.get(0));
                                        addMessage(redirectAttributes, "分配成功，已分配"+(i+1)+"条！");
                                    }else{
                                        addMessage(redirectAttributes, "已分配"+i+"条，分配失败"+(numIds.length-i)+"条，请检查排班和分配计划是否满足要求！");
                                        break;
                                    }
                                }else{
                                    //没有配置
                                    addMessage(redirectAttributes, "自动分配失败，当前项目类型没有设计分配计划！");
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    addMessage(redirectAttributes, "操作失败，请检查分配计划是否合理！");
                }*/


            json.put("success", "1");
            json.put("msg", "添加成功");
            return new JSONPObject(callback, json);
        }else {
            json.put("success", "0");
            json.put("msg", "手机号已存在");
            return new JSONPObject(callback, json);
        }

    }



    @ResponseBody
    @RequestMapping(value = "saveInteNewMode")
    public JSONPObject saveIntenewMode(String callback,String names,String phones,String urlStr,String edu,String shebao,String zhengshu,String marry,String marks,RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        String queryPhone = hrPhone_w_service.queryPhone(phones);
        if(queryPhone == null && phones!=null){
            try {
                names=URLDecoder.decode(URLDecoder.decode(names, "UTF-8"),"UTF-8"); //姓名
                edu=URLDecoder.decode(URLDecoder.decode(edu, "UTF-8"),"UTF-8");  // 学历
                shebao=URLDecoder.decode(URLDecoder.decode(shebao, "UTF-8"),"UTF-8"); //社保
                zhengshu=URLDecoder.decode(URLDecoder.decode(zhengshu, "UTF-8"),"UTF-8"); // 证书
                marry=URLDecoder.decode(URLDecoder.decode(marry, "UTF-8"),"UTF-8");//婚否
                marks=URLDecoder.decode(URLDecoder.decode(marks, "UTF-8"),"UTF-8");//留言内容

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                Map<String, Object> bean = new HashMap<String, Object>();
                bean.put("PROJECT", "8");//积分新模式
                bean.put("DATA_FROM", "3"); //数据来源  留言
                bean.put("IS_VOLUNTARILY", "1"); // 是否自动分配  1分配 0不分配
                bean.put("SIGN_LEVEL", "5");    //报名意愿
                bean.put("WEBSITE", "4");    //网站号
                bean.put("NAME", names);
                bean.put("PHONE",phones.replaceAll("[^0-9]", ""));
                bean.put("REMARK",marks);
                hrPhone_w_service.save_w(bean);

                //自动分配
                //wkl注释
                /*try {
                    bean=hrPhoneService.get(bean);
                    String NUM_IDS=bean.get("NUM_ID").toString();
                    if(NUM_IDS!=null&&!NUM_IDS.equals("")){
                        String[] numIds=NUM_IDS.split(",");
                        for (int i = 0; i < numIds.length; i++) {
                            Map<String,Object> map=new HashMap<String, Object>();
                            map.put("NUM_ID", numIds[i]);
                            map=hrPhoneService.get(map);//获得单详细信息
                            if(MapUtils.getString(map, "PROJECT")!=null&&!MapUtils.getString(map, "PROJECT").equals("")){
                                Map<String,Object> query=new HashMap<String, Object>();
                                query.put("PROJECT", MapUtils.getString(map, "PROJECT"));
                                query.put("STATUS", "2");
                                List<Map<String,Object>> list=disPlanService.listTitle(query);//获得配置title
                                if(list!=null&&list.size()>0){
                                    String titleId=list.get(0).get("NUM_ID").toString();
                                    Map<String,Object> query2=new HashMap<String, Object>();
                                    query2.put("TITLE_ID", titleId);
                                    //获取排班系统
                                    Calendar c = Calendar.getInstance();
                                    int hour=c.get(Calendar.HOUR_OF_DAY);
                                    int min=c.get(Calendar.MINUTE);
                                    //早班9:30——20:00
                                    if(hour<9||hour>=20){
                                        query2.put("ZAOBAN", "1");
                                    }
                                    if(hour==9&&min<30){
                                        query2.put("ZAOBAN", "1");
                                    }
                                        //原本就有的注释
										*//*if(hour==18&&min>30){
											query2.put("ZAOBAN", "1");
										}
										//晚班1:30——9:30
										if(hour<13||hour>=22){
											query2.put("WANBAN", "1");
										}
										if(hour==13&&min<30){
											query2.put("WANBAN", "1");
										}
										if(hour==21&&min>30){
											query2.put("WANBAN", "1");
										}*//*
                                    List<Map<String,Object>> list2=disPlanService.listPlanWeightOrder(query2);//获得所有人员权重配置
                                    if(list2!=null&&list2.size()>0){
                                        Map<String,Object> phone=new HashMap<String, Object>();
                                        phone.put("NUM_IDS", numIds[i]);
                                        phone.put("USER_DEPT", list2.get(0).get("USER_NO"));
                                        hrPhoneService.fenpei(phone);//分配
                                        list2.get(0).put("HAVE_NUM", "1");
                                        hrPhoneService.updateHaveNum(list2.get(0));
                                        addMessage(redirectAttributes, "分配成功，已分配"+(i+1)+"条！");
                                    }else{
                                        addMessage(redirectAttributes, "已分配"+i+"条，分配失败"+(numIds.length-i)+"条，请检查排班和分配计划是否满足要求！");
                                        break;
                                    }
                                }else{
                                    //没有配置
                                    addMessage(redirectAttributes, "自动分配失败，当前项目类型没有设计分配计划！");
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    addMessage(redirectAttributes, "操作失败，请检查分配计划是否合理！");
                }*/

                json.put("code", "0");
                json.put("msg", "添加成功");
                return new JSONPObject(callback, json);
            }else{
                json.put("code", "1");
                json.put("msg", "手机号已存在");
                return new JSONPObject(callback, json);
            }
    }


}
