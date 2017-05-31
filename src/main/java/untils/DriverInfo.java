package untils;

import java.util.ArrayList;
import java.util.List;

public class DriverInfo {
    public String cellPhone;
    public String vehicle_number;
    public String driverID;
    public String name;
    public String imei;
    public String accessToken;
    public String driverAppVersion;
    public String os_name;
    public String os_version;
    public String x_auth_mode;
    public String is_zip;
    public String device_type;
    public DriverInfo driverInfo;
    public List<DriverInfo> drivers = new ArrayList<>();




    public DriverInfo() {

    }

    public DriverInfo(String cellPhone, String name) {
        this.cellPhone = cellPhone;
        this.name = name;
    }

    public DriverInfo(String cellPhone, String name, String imei) {
        this.cellPhone = cellPhone;
        this.name = name;
    }


    public DriverInfo(String name, String cellPhone, String vehicle_number, String imei, String driverAppVersion) {
        this.name = name;
        this.cellPhone = cellPhone;
        this.vehicle_number = vehicle_number;
        this.imei = imei;
        this.driverAppVersion = driverAppVersion;
    }

    public DriverInfo(String cellPhone, String vehicle_number, String driverName, String imei, String driverAppVersion, String os_name, String os_version, String x_auth_mode, String is_zip, String device_type) {
        this.cellPhone = cellPhone;
        this.vehicle_number = vehicle_number;
        this.name = name;
        this.imei = imei;
        this.driverAppVersion = driverAppVersion;
        this.os_name = os_name;
        this.os_version = os_version;
        this.x_auth_mode = x_auth_mode;
        this.is_zip = is_zip;
        this.device_type = device_type;
    }



    public  List<DriverInfo> getDiverInfo() {
//        DriverInfo driverInfo_android = new DriverInfo("16820165888","5621", "易到测试司机北京", "861379031612225", "269", "MI MAX", "6.5.1", "client_auth", "1", "1");
        DriverInfo driverInfo_android = new DriverInfo(ProUtil.getInstance().getPropertyValues("config/login.properties","driver1"),ProUtil.getInstance().getPropertyValues("config/login.properties","vehicle_number1"),ProUtil.getInstance().getPropertyValues("config/login.properties","driverName1"), ProUtil.getInstance().getPropertyValues("config/login.properties","imei1"), ProUtil.getInstance().getPropertyValues("config/login.properties","driverAppVersion1"), ProUtil.getInstance().getPropertyValues("config/login.properties","os_name1"), ProUtil.getInstance().getPropertyValues("config/login.properties","os_vesion1"),ProUtil.getInstance().getPropertyValues("config/login.properties","x_auth_mode1"), ProUtil.getInstance().getPropertyValues("config/login.properties","is_zip1"), ProUtil.getInstance().getPropertyValues("config/login.properties","device_type1"));
//       DriverInfo driverInfo_android = new DriverInfo("16820160309","0309", "易到测试司机16820160309", "353222063475100", "259", "samsung-SM-G900F", "6.0.1", "client_auth", "1", "1");,
       DriverInfo driverInfo_ios = new DriverInfo("16820160325","0325", "易到测试司机16820160311", "e2cae261563d7dbba2621139134a596f", "259", "iPhone9,1", "10.2.1", "client_auth", "1", "1");
       drivers.add(0,driverInfo_android);
       drivers.add(1,driverInfo_ios);
       return drivers;
    }



}
