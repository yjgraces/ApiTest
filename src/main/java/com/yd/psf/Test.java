package com.yd.psf;

import com.yd.test.framework.common.AbstractPSFTest;
import com.yd.test.framework.common.HttpResult;
import com.yd.test.framework.exception.TestRequestInvokeException;

/**
 * Created by liuhailin on 2017/3/9.
 */
public class Test extends AbstractPSFTest {



    @org.testng.annotations.Test
    public void test(){
        try {
            HttpResult result = this.executePSF(null);
        } catch (TestRequestInvokeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String testDataFileName() {
        return "psf";
    }
}
