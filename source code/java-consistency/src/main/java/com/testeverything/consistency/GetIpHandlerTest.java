package com.testeverything.consistency;

import com.testeverything.common.LogHelper;
import com.testeverything.consistency.handler.GetIpHandler;
import com.testeverything.consistency.hash.impl.Md5HashAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取ip 处理类的测试用例
 * Created by lijinsheng on 14-8-12.
 */
public class GetIpHandlerTest {
    public static void main(String[] args) {
        GetIpHandler getIpHandler = new GetIpHandler();
        getIpHandler.setHashAlgorithm(new Md5HashAlgorithm());
        List<String> addressList = new ArrayList<String>();
        addressList.add("127.0.0.1:1155");
        addressList.add("127.0.0.1:1156");
        addressList.add("127.0.0.1:1157");
        addressList.add("127.0.0.1:1158");
        getIpHandler.setAddressList(addressList);
        getIpHandler.setVirtualNumber(1);
        getIpHandler.afterPropertiesSet();
        LogHelper.CONSISTENT_LOGGER.info(getIpHandler.getAddress("Hello World"));
        LogHelper.CONSISTENT_LOGGER.info(getIpHandler.getAddress("sdfsdfdaf"));
        LogHelper.CONSISTENT_LOGGER.info(getIpHandler.getAddress("fdsgdsgdsfg"));
        LogHelper.CONSISTENT_LOGGER.info(getIpHandler.getAddress("dfgdfhgfhgdfh"));
    }
}
