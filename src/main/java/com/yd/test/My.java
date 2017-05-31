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


/**
 * Created by liuhailin on 2017/1/18.
 */
public class My extends AbstractTest {

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


    @Test(description = "好评率")
    public void testGetDriverEvalute(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"),200,result.toJSONResponseData().toString() + "非200" + "/V1/Driver/GetDriverEvalute接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }



    @Test(description = "评价明细")
    public void testGetDriverEvaluteDetail(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"),200, result.toJSONResponseData().toString() + "非200" + "/V1/Driver/GetDriverEvaluteDetail接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }




    @Test(description = "司机等级")
    public void testGetDriverLevelInfo(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"),200, result.toJSONResponseData().toString() + "非200" + "/V1/Driver/GetDriverLevelInfo接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }




    @Test(description = "司机经验值每月明细")
    public void testGetDriverLevelDetailMonth(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"),200, result.toJSONResponseData().toString() + "非200" + "/V1/Driver/GetDriverLevelDetailMonth接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "司机经验值每日明细")
    public void testGetDriverLevelDetailDay(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"),200, result.toJSONResponseData().toString() + "非200" + "/V1/Driver/GetDriverLevelDetailDay接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "绑卡")
    public void testSetBankCard(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertTrue(result.toJSONResponseData().getIntValue("code")==200||result.toJSONResponseData().getIntValue("code")==499, result.toJSONResponseData().toString() + "非200" + "/V1/Driver/SetBankCard接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test(description = "设置司机举报的订单")
   	public void testSetDriverReport(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("设置司机举报的订单response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "一人多车获取车辆信息")
   	public void testGetOneMoreCarInfo(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("一人多车获取车辆信息 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "一人多车选择使用车辆")
   	public void testUpdateOneMoreCarInfo(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("一人多车选择使用车辆 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "司机为收藏乘客添加备注")
   	public void testSetDriverNotes(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("司机为收藏乘客添加备注 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "添加或移除司机拉黑的乘客")
   	public void testBlackList(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("添加或移除司机拉黑的乘客 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    /*@Test(description = "添加或移除司机拉黑的乘客post")
   	public void testBlackList1(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("添加或移除司机拉黑的乘客post response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }*/
     
 
 @Test(description = "获取个性标签列表")
   	public void testGetPersonalLabel(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取个性标签列表 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "结算中")
   	public void testGetInBalanceList(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("结算中 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "提现记录详情页")
	public void testWithDrawDetail(){
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("提现记录详情页 response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test(description = "申诉机会次数获取")
   	public void testGetAppealContent(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("申诉机会次数获取 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "申诉内容提交")
   	public void testAppeal(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("申诉内容提交 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "获取所有申诉记录")
   	public void testGetDriverAppealList(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取所有申诉记录 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "设置当前车型")
   	public void testSetCarType(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("设置当前车型 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "获取用户信息")
   	public void testGetUserInfo(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取用户信息 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "取得司机收入统计信息")
   	public void testGetDriverIncomeStat(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("取得司机收入统计信息 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "手机信息")
   	public void testNotifyRecommend(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("手机信息 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "获取弹屏")
   	public void testGetPopScreen(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取弹屏 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "系统服务时间戳")
   	public void testGetServerTime(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("系统服务时间戳 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "获取账户余额")
   	public void testGetDriverAmount(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取账户余额 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "记录司机导航调用日志")
    public void testMapCallDaily(){
    	try{
    		HttpResult result = execute(headerParams,params);
    		log.info("获取账户余额 response:" + result.getResponseData());
    		Assert.assertEquals(result.getResponseData(), "{\"code\":200,\"msg\":\"success\"}", result.getResponseData());
    	}catch(TestHttpInvokeException e){
    		e.printStackTrace();
    	}
    } 
    

    @Override
    public String testDataFileName() {
        return "My";
    }
}
