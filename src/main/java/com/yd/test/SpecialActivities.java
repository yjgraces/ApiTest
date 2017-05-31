package com.yd.test;

import com.yd.test.framework.common.AbstractTest;
import com.yd.test.framework.common.HttpResult;
import com.yd.test.framework.exception.TestHttpInvokeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import untils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SpecialActivities extends AbstractTest{
    private static final Logger log = LoggerFactory.getLogger(My.class);
    String timeStamp=String.valueOf(System.currentTimeMillis()/1000);
    String once=String.valueOf(Long.parseLong(timeStamp)+1000);
    List<untils.DriverInfo> drivers = new untils.DriverInfo().getDiverInfo();
    Map<String, String> headerParams = new HashMap<>();
    Map<String, String> params = new HashMap<>();
    
    
    @BeforeClass
    public void setUp() throws Exception {
        log.info("setup....");
        params.put("cellphone", drivers.get(0).cellPhone);
        params.put("area_code", "86");
        params.put("imei", drivers.get(0).imei);
        params.put("version", drivers.get(0).driverAppVersion);
        params.put("x_auth_mode", drivers.get(0).x_auth_mode);
        params.put("is_gzip", drivers.get(0).is_zip);
        params.put("device_type", drivers.get(0).device_type);
        params.put("os_name", drivers.get(0).os_name);
        params.put("os_version", drivers.get(0).os_version);
        params.put("vehicle_number", drivers.get(0).vehicle_number);
        params.put("city", "北京市");
        params.put("channel_source", "");
        params.put("oauth_timestamp", timeStamp);
        params.put("oauth_nonce",once);
        params.put("oauth_token", GetOuthToken.getOuthToken(ProUtil.getInstance().getPropertyValues("config/login.properties","driver1"),ProUtil.getInstance().getPropertyValues("config/login.properties","imei1"),ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1")));
        params.put("access_token", GetOuthToken.Access_token);

    }

    
    @Test(description = "获取优惠券提示内容")
   	public void testGetCouponContent(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取优惠券提示内容response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "司机拉用户送优惠券的接口")
   	public void testSendCoupon(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("司机拉用户送优惠券的接口response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    

    @Override
    public String testDataFileName() {
        return "SpecialActivities";
    }
    
}
