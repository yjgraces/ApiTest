package com.yd.test;

import com.sun.org.apache.regexp.internal.RE;
import com.yd.test.framework.common.AbstractTest;
import com.yd.test.framework.common.HttpResult;
import com.yd.test.framework.exception.TestHttpInvokeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//import untils.GetDriverToken;
import untils.GetOuthToken;
import untils.ProUtil;
import untils.RequestHeaderConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YaoJing on 2017/3/20.
 */
public class DriverInfo extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(CreateAuthTest.class);

    
    String oauth_timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String oauth_nonce = String.valueOf(Long.parseLong(oauth_timestamp) + 1000);
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
        params.put("oauth_timestamp", oauth_timestamp);
        params.put("oauth_nonce", oauth_nonce);

//       
//		GetDriverToken g1=new GetDriverToken();
//		
//		Map<String, String> tokenMap = new HashMap<>();
//		tokenMap=g1.login();
//		String oauth_token=tokenMap.get("oauth_token");
//		String access_token=tokenMap.get("access_token");
//
//        params.put("oauth_token", oauth_token);
//        params.put("access_token",access_token);
////        params.put("oauth_token", GetOuthToken.getOuthToken());
////        params.put("access_token", GetOuthToken.Access_token);

        params.put("oauth_token", GetOuthToken.getOuthToken(ProUtil.getInstance().getPropertyValues("config/login.properties","driver1"),ProUtil.getInstance().getPropertyValues("config/login.properties","imei1"),ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1")));
        params.put("access_token", GetOuthToken.Access_token);

    }


    @Test (description = "司机信息")
    public void testMember() {
        try {
            HttpResult result = execute(headerParams, params);
            log.info("司机信息 response:" + result.getResponseData());
           
            Object ob=result;
            log.info("sijixinxi response:"+ob.toString());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "收藏我的乘客")
    public void testGetCollectedList() {
        try {            
            HttpResult result = execute(headerParams, params);           
            log.info("收藏我的乘客response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "参与分")
    public void testGetContributionOrder() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("参与分response:" + result.getResponseData());
            log.info("参与分responseCode:"+result.getResponseCode());
            log.info("参与分：code:"+result.toJSONResponseData().getString("code"));
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue((code == 200)||(code == 499),"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "司机今日收入")
    public void testGetDriverDayOrder() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机今日收入response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue((code == 200)||(code == 201),"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "司机好评率")
    public void testGetDriverEvalute() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机好评率response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "司机每日经验明细")
    public void testGetDriverLevelDetailDay() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机每日经验明细response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    @Test(description = "服务特色")
    public void testSetSellingPoint() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("服务特色response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "设置常用上车/下车地点/常活动区域")
    public void testSetMyInfo() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("设置常用上车/下车地点/常活动区域response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "设置服务车型")
    public void testSetCar() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("设置服务车型response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    @Test(description = "给乘客完成评价")
    public void testJudgeOfUser() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("给乘客完成评价response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }   
    
    @Test(description = "订单列表")
    public void testGetDriverTodayData() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("订单列表response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }  
    
    @Test(description = "我的钱包")
    public void testGetDriverAccount() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("我的钱包response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "我的账户详情")
    public void testGetDriverAccountDetail() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("我的账户详情response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    @Test(description = "可绑定银行")
    public void testGetWithdrawCashBank() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("可绑定银行response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }      

    @Test(description = "设置绑定银行数据")
    public void testSetBankCard() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("设置绑定银行数据response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue((code == 200)||(code==499),"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    @Test(description = "司机验证码")
    public void testGetAuthCode() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机验证码response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "车主端首页信息")
    public void testGetHomePage() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("车主端首页信息response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }     
    
    @Test(description = "车主端弹屏")
    public void testGetPopScreen() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("车主端弹屏response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "车主端闪屏")
    public void testGetSplash() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("车主端闪屏response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "发送消息")
    public void testSendMessage() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("发送消息response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue((code == 200)||(code == 462),"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "拉黑我的乘客")
    public void testBlackList() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("拉黑我的乘客response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }   
    
    @Test(description = "司机可变更的车型列表")
    public void testGetLowCarType() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机可变更的车型列表response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "司机申请提现")
    public void testWithdrawDriverCash() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机申请提现response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(description = "司机历史收入")
    public void testGetDriverIncome() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机历史收入response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "司机收入相关订单明细")
    public void testGetDriverIncomeOrder() {
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("司机收入相关订单明细response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    

    
    
    
    
    
    
    
    
    
    
    
    @Test(description = "获取乘客标签")
    public void testGetCommentUserTags() {
        try {
            
            HttpResult result = execute(headerParams, params);
           
            log.info("获取乘客标签response:" + result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    
    @Test(description = "获取司机全部订单收入")
	public void testGetDriverAllOrder(){
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("获取司机全部订单收入response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    
    @Test(description = "获取司机开关设置")
   	public void testDriverOption(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取司机开关设置response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "获取司机开关设置post")
   	public void testDriverOption1(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取司机开关设置post response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "获取派单版本")
   	public void testGetSupportDriverVersion(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取派单版本 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }  	

    @Override
    public String testDataFileName() {
        return "DriverInfo";
    }

}
