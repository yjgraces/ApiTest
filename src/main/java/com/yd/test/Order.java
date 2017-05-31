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
 * Created by kuicui on 2017/3/20.
 */
public class Order extends AbstractTest{

    private static final Logger log = LoggerFactory.getLogger(Order.class);
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
        params.put("oauth_token", GetOuthToken.getOuthToken(ProUtil.getInstance().getPropertyValues("config/login.properties","driver1"),ProUtil.getInstance().getPropertyValues("config/login.properties","imei1"),ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1")));
        params.put("access_token", GetOuthToken.Access_token);

    }

    @Test(description = "接单")
    public void testAccept(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertTrue(result.toJSONResponseData().getIntValue("code")==200||result.toJSONResponseData().getIntValue("code")==431, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "已就位")
    public void testDepart(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "到达乘客指定上车地点")
    public void testArrive(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "服务开始")
    public void testStart(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "服务结束")
    public void testEnd(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "确认订单")
    public void testBillConfirm(){
        try {
            HttpResult result= this.execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "完成评价")
    public void testCotent(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertTrue(result.toJSONResponseData().getIntValue("code")==200||result.toJSONResponseData().getIntValue("code")==401, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "获取订详情")
    public void testGetItemOrder(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "获取订单决策状态")
    public void testGetDecisionStatus(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "申请改派")
    public void testGetDispatchRemind(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "确认改派")
    public void testReassignChoice(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertTrue(result.toJSONResponseData().getIntValue("code")==200||result.toJSONResponseData().getIntValue("code")==499, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "订单列表")
    public void testGetOrderList(){
        try {
            HttpResult result= execute(headerParams,params);
            Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/V4/Driver/operateOrder 接口参数如下：" + params.toString()+":"+headerParams.toString());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test(description = "获取订单坐标")
    public void testGetOrderTrack(){
    	try{
    		HttpResult result = execute(headerParams,params);
    		Assert.assertEquals(result.toJSONResponseData().getIntValue("code"), 200, result.toJSONResponseData().toString() + "非200" + "/Order/GetOrderTrack接口参数如下：" + params.toString() + ":" + headerParams.toString());
    	}catch(TestHttpInvokeException e){
    		e.printStackTrace();
    	}
    }
    
    
    @Test(description = "司机现在出发")
   	public void testSetDriverDepart(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("司机现在出发response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "获取预估费用")
   	public void testGetEstimatePrice(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取预估费用response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 

    
    @Test(description = "获取预估公里")
   	public void testEstimatedDistance(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("获取预估公里response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "账单重新算费")
   	public void testCalculateOrder(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("账单重新算费response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "改派文案")
	public void testGetDispatchContent(){
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("改派文案response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    
    @Test(description = "改派接口")
   	public void testChangeDispatch(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("改派接口response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200 || code == 499,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "司机拒绝接单理由")
   	public void testSetRefuseReason(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("司机拒绝接单理由response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "联系不上乘客")
   	public void testContactPassenger(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("联系不上乘客response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    

    @Test(description = "系统自动化确认改派")
   	public void testReassignAccept(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("系统自动化确认改派response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "系统自动化轮询查看状态")
	public void testReassignRoll(){
        try {           
            HttpResult result = execute(headerParams, params);          
            log.info("系统自动化轮询查看状态response:" + result.getResponseData());
            int code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
    
    
    @Test(description = "订单热力图")
   	public void testOrderHeatingPower(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("订单热力图response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       } 
    
    
    @Test(description = "出发前一小时提示")
   	public void testRemindFeedback(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("出发前一小时提示response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200 || code == 499,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "根据司机ID和乘客ID获取已完成服务的订单")
   	public void testGetServiceOrderList(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("根据司机ID和乘客ID获取已完成服务的订单 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "司机创建与客服聊天的Session ID的接口")
   	public void testCreateSessionWithCRM(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("司机创建与客服聊天的Session ID的接口 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
    
    
    @Test(description = "司机取得SessionId接口")
   	public void testGetSession(){
           try {           
               HttpResult result = execute(headerParams, params);          
               log.info("司机取得SessionId接口 response:" + result.getResponseData());
               int code=result.toJSONResponseData().getIntValue("code");
               Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
           } catch (TestHttpInvokeException e) {
               e.printStackTrace();
           }
       }
   	
   	
    @Override
    public String testDataFileName() {
        return "Order";
    }
}


