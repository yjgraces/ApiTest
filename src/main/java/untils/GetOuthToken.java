package untils;

import com.github.kevinsawicki.http.HttpRequest;
import com.jayway.jsonpath.JsonPath;
import com.yd.conf.Constant;
import com.yd.test.CreateAuthTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GetOuthToken {
    private static final Logger logger = LoggerFactory.getLogger(CreateAuthTest.class);
    public static String Access_token;


    public String login_android(String cellphone, String imei, String device_type) {
        List<DriverInfo> drivers = new DriverInfo().getDiverInfo();
        RequestHeaderConfig header = new RequestHeaderConfig();
        String hearders = header.getHeaderString();
        HashMap<String, String> params = new HashMap<>();
        String url = Constant.CREATEAUTHCODE;
        params.put("cellphone", cellphone);
        params.put("area_code", "86");
        params.put("imei", imei);
        params.put("version", drivers.get(0).driverAppVersion);
        params.put("x_auth_mode", "client_auth");
        params.put("is_gzip", "1");
        params.put("device_type", device_type);
        params.put("os_name", drivers.get(0).os_name);
        params.put("os_version", drivers.get(0).os_version);
        params.put("city", "北京市");
        params.put("channel_source", "");
        HttpRequest request = HttpRequest.post(url).authorization(hearders).userAgent(header.get_userAgent_and()).acceptEncoding("gzip,deflate,sdch").uncompress(true).form(params);
        String response = request.body();
        String password = JsonPath.read(response, "$.msg.auth_code").toString();
        url = Constant.LOGIN;
        params.put("x_auth_username", drivers.get(0).cellPhone);
        params.put("x_auth_mode", "client_auth");
        params.put("cellphone", drivers.get(0).cellPhone);
        params.put("client_secret", "9a9c7d97429b8737bf604d0a56aee505");
        params.put("client_id", "car_master");
        params.put("area_code", "86");
        params.put("imei", drivers.get(0).imei);
        params.put("is_gzip", "1");
        params.put("device_type", "1");
        params.put("os_name", drivers.get(0).os_name);
        params.put("os_version", drivers.get(0).os_version);
        params.put("version", drivers.get(0).driverAppVersion);
        params.put("x_auth_password", password);
        params.put("city", "北京市");
        params.put("channel_source", "");
        params.put("access_token", "");
        request = HttpRequest.post(url).authorization(hearders).userAgent(header.get_userAgent_and()).acceptEncoding("gzip,deflate,sdch").uncompress(true).form(params);
        response = request.body();
        String Access_token = JsonPath.read(response, "$.msg.oauth2_token.access_token");
        String Oauth_token = JsonPath.read(response, "$.msg.oauth_token.oauth_token");
        logger.info("\r\nOauth_token:" + Oauth_token + "\r\n" + "Access_token:" + Access_token);
        return Oauth_token;
    }


    public String login_ios(String cellphone, String imei, String device_type) {
        List<DriverInfo> drivers = new DriverInfo().getDiverInfo();
        RequestHeaderConfig header = new RequestHeaderConfig();
        String hearders = header.getHeaderString();
        HashMap<String, String> params = new HashMap<>();
        String url = Constant.CREATEAUTHCODE;
        params.put("cellphone", cellphone);
        params.put("area_code", "86");
        params.put("imei", imei);
        params.put("version", drivers.get(1).driverAppVersion);
        params.put("x_auth_mode", "client_auth");
        params.put("code_type", "1");
        params.put("device_type", device_type);
        params.put("os_name", drivers.get(1).os_name);
        params.put("os_version", drivers.get(1).os_version);
        params.put("os_agent", drivers.get(1).os_name);
        params.put("city", "北京市");
        params.put("channel_source", "");
        HttpRequest request = HttpRequest.post(url).authorization(hearders).userAgent(header.get_userAgent_and()).acceptEncoding("gzip,deflate,sdch").uncompress(true).form(params);
        String response = request.body();
        String password = JsonPath.read(response, "$.msg.auth_code").toString();
        url = Constant.LOGIN;
        params.put("x_auth_username", drivers.get(1).cellPhone);
//        params.put("x_auth_mode", "client_auth");
        params.put("cellphone", drivers.get(1).cellPhone);
        params.put("client_secret", "9a9c7d97429b8737bf604d0a56aee505");
        params.put("client_id", "car_master");
        params.put("area_code", "86");
        params.put("imei", drivers.get(1).imei);
        params.put("device_type", "1");
        params.put("os_name", drivers.get(1).os_name);
        params.put("os_version", drivers.get(1).os_version);
        params.put("os_agent", drivers.get(1).os_name);
        params.put("version", drivers.get(1).driverAppVersion);
        params.put("x_auth_password", password);
        params.put("x_auth_username", cellphone);
        params.put("city", "北京市");
        request = HttpRequest.post(url).authorization(hearders).userAgent(header.getUser_Agent()).acceptEncoding("gzip,deflate,sdch").uncompress(true).form(params);
        response = request.body();
        String Access_token = JsonPath.read(response, "$.msg.oauth2_token.access_token");
        String Oauth_token = JsonPath.read(response, "$.msg.oauth_token.oauth_token");
        logger.info("\r\nOauth_token:" + Oauth_token + "\r\n" + "Access_token:" + Access_token);
        return Oauth_token;
    }

    static String Oauth_token = null;
    public static String getOuthToken(String cellphone, String imei, String device_type) {
        //String Oauth_token = null;
        if (Oauth_token == null && device_type.equals(String.valueOf(1))) {
            Oauth_token = new GetOuthToken().login_android(cellphone, imei, device_type);
        } else if (Oauth_token == null && device_type.equals(String.valueOf(2))) {
            Oauth_token = new GetOuthToken().login_ios(cellphone, imei, device_type);

        } else {
            logger.info("请重新传device_type");
        }
        return Oauth_token;
    }


    public static void main(String[] args) {
        System.out.println((ProUtil.getInstance().getPropertyValues("config/login.properties","driver1")+":"+ProUtil.getInstance().getPropertyValues("config/login.properties","imei1")+":"+ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1")));
        System.out.println(getOuthToken(ProUtil.getInstance().getPropertyValues("config/login.properties","driver1"),ProUtil.getInstance().getPropertyValues("config/login.properties","imei1"),ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1")));
    }
}
