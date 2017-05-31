package com.yd.test;

import com.sun.org.apache.regexp.internal.RE;
import com.yd.test.framework.common.AbstractTest;
import com.yd.test.framework.common.HttpResult;
import com.yd.test.framework.exception.TestHttpInvokeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import untils.GetOuthToken;
import untils.ProUtil;
import untils.RequestHeaderConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuicui on 2017/3/20.
 */
public class Memeber extends AbstractTest {
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
        params.put("oauth_token", GetOuthToken.getOuthToken(ProUtil.getInstance().getPropertyValues("config/login.properties","driver1"),ProUtil.getInstance().getPropertyValues("config/login.properties","imei1"),ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1")));
        params.put("access_token", GetOuthToken.Access_token);
    }


    @Test (description = "司机信息")
    public void testMember() {
        try {
            HttpResult result = execute(headerParams, params);
        } catch (TestHttpInvokeException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String testDataFileName() {
        return "member";
    }

}
