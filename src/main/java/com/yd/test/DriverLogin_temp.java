package com.yd.test;

import com.yd.test.framework.common.AbstractTest;
import com.yd.test.framework.common.HttpResult;
import com.yd.test.framework.exception.TestHttpInvokeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import untils.GetOuthToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YaoJing on 2017/3/20.
 */
public class DriverLogin_temp extends AbstractTest{
    private static final Logger log = LoggerFactory.getLogger(CreateAuthTest.class);
    String oauth_timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String oauth_nonce = String.valueOf(Long.parseLong(oauth_timestamp) + 1000);

    Map<String, String> headerParams = new HashMap<>();
    Map<String, String> params = new HashMap<>();


    @BeforeClass
    public void setUp() throws Exception {
        log.info("setup....");
        params.put("oauth_timestamp", oauth_timestamp);
        params.put("oauth_nonce", oauth_nonce);
    //    params.put("oauth_token", GetOuthToken.getOuthToken());
    //    params.put("access_token", GetOuthToken.Access_token);
        
    }


//    @Test (description = "修改司机当前状态")
//    public void testMemberStat() {
//        try {
//        	params.put("stat", "busy");
//            HttpResult result = execute(headerParams, params);            
//            log.info("修改司机当前状态response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    @Test (description = "首页信息")
//    public void testIndex() {
//        try {
//            HttpResult result = execute(headerParams, params);            
//            log.info("首页信息response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    
//    @Test (description = "司机当前版本")
//    public void testCurrentVersion() {
//        try {
//            HttpResult result = execute(headerParams, params);            
//            log.info("司机当前版本response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    }   
//    
//    @Test (description = "验证码")
//    public void testCreateDriverPassword() {
//        try {
//            HttpResult result = execute(headerParams, params);            
//            log.info("验证码response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    }  
//    
//    @Test (description = "所有城市信息列表")
//    public void testInitInfo() {
//        try {
//            HttpResult result = execute(headerParams, params);            
//            log.info("所有城市信息列表response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    } 
//
//    @Test (description = "全局配置")
//    public void testGetConfigure() {
//        try {
//            HttpResult result = execute(headerParams, params);            
//            log.info("全局配置response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    } 
//    
//    @Test (description = "获取司机验证码(V5.0.1版本以前)")
//    public void testVerifyCooperaStatus() {
//        try {
//            HttpResult result = execute(headerParams, params);            
//            log.info("获取司机验证码response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    }    
//    
//    @Test (description = "获取司机token(V5.0.1版本以前)")
//    public void testAccessToken() {
//        try {
//        	HttpResult result1 = executeWithDataFile(null,headerParams, params,"DriverLogin","testVerifyCooperaStatus");  
//        	String password=result1.toJSONResponseData().getJSONObject("msg").getString("password");
//        	log.info(result1.getResponseData());
//        	params.put("x_auth_password", password);
//        	params.put("x_auth_username", "16820160309");
//        	HttpResult result = execute(headerParams, params);            
//            log.info("获取司机token response:" + result.getResponseData());
//            int code=result.toJSONResponseData().getIntValue("code");
//            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
//        } catch (TestHttpInvokeException e) {
//            e.printStackTrace();
//        }
//    }  
       
    @Test (description = "获取验证码--登录--解绑",priority=1)
    public void testLoginAndUnbind() {
        try { 
        	Map<String, String> params = new HashMap<>();
        	HttpResult result1 = executeWithDataFile(null,headerParams, params,"DriverLogin_temp","testGetAuthCode");  
            log.info("获取验证码请求："+result1.getResponseData());
        	int code=result1.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result1.getResponseData());
        	String password=result1.toJSONResponseData().getJSONObject("msg").getString("auth_code");
        	Map<String,String> params2 =  new HashMap<String,String>();	
        	params2.put("oauth_timestamp", oauth_timestamp);
            params2.put("oauth_nonce", oauth_nonce);
        	params2.put("x_auth_password", password);
        	params2.put("access_token", "");
        	params2.put("oauth_token", "");
        	       	       	
            HttpResult result = execute(headerParams, params2); 
            log.info("登录接口："+result.getResponseData());
            code=result.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result.getResponseData());
            String oauth_token1=result.toJSONResponseData().getJSONObject("msg").getJSONObject("oauth_token").getString("oauth_token");
            String acc_token1=result.toJSONResponseData().getJSONObject("msg").getJSONObject("oauth2_token").getString("access_token");           			
			Map<String,String> params1 =  new HashMap<String,String>();	
			params1.put("access_token", acc_token1);
			params1.put("oauth_token", oauth_token1);
	        params1.put("oauth_timestamp", oauth_timestamp);
	        params1.put("oauth_nonce", oauth_nonce);
			
			HttpResult result2 = executeWithDataFile(null,headerParams, params1,"DriverLogin_temp","testUnbind"); 
			log.info("unbind解绑接口response："+result2.getResponseData());
            code=result2.toJSONResponseData().getIntValue("code");
            Assert.assertTrue(code == 200,"接口返回信息："+result2.getResponseData());
			
            } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    } 
  
    //  @Test (description = "获取验证码")
	//  public void testGetAuthCode() {
	//  try { 
	//  	params.remove("access_token");
	//  	params.remove("oauth_token");
	//      HttpResult result = execute(headerParams, params);          
	//      log.info("auth_code :response:" + result.getResponseData());
	//      } catch (TestHttpInvokeException e) {
	//      e.printStackTrace();
	//  }
	//} 

    
    @Override
    public String testDataFileName() {
        return "DriverLogin_temp";
    }
    
    
}
