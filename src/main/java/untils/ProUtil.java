package untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by kuicui on 2017/3/16.
 */
public class ProUtil {

    public static   ProUtil instance;
    static Logger logger = LoggerFactory.getLogger(ProUtil.class);
    private ProUtil(){

    }

    public  static  ProUtil getInstance(){
            if(instance==null){
                instance = new ProUtil();
            }
        return instance;
    }


    public String  getFilePath(String fileName){
        String filePath = ProUtil.class.getClassLoader().getResource(fileName).getPath();
        logger.info("filePath--->"+filePath);
        return filePath;
    }


    public   String getPropertyValues(String fileName, String key){
        Properties p = new Properties();
        try {
            FileReader reader = new FileReader(getFilePath(fileName));
            p.load(reader);
            return p.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("can't find fileName");
            return null;
        }
    }

    public void setProperty(String fileName,String name, String value) throws IOException {
        Properties p = new Properties();
        InputStream in = ProUtil.class.getClassLoader().getResourceAsStream(fileName);
        OutputStream ou = new FileOutputStream(new File("/Users/yongche/Documents/comm/projects/Ifreamwork/target/classes/con/server.properties"));
        try {
            p.load(in);
            p.setProperty(name, value);
            p.store(ou,"success");
            ou.close();
            System.out.println("save success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


    }

}
