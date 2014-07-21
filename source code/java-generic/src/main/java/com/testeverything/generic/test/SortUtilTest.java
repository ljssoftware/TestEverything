package com.testeverything.generic.test;

import com.testeverything.generic.util.Generic;

import java.util.ArrayList;
import java.util.List;

/**
 * sort util 的测试类
 * Created by lijinsheng on 14-6-25.
 */
public class SortUtilTest {
    public static void main(String[] args) {
        //类泛型
//        //integer
//        Generic<Integer, String> integerObject = new Generic<Integer, String>();
//        integerObject.setParameter(new Integer(100));
//        integerObject.setKey("key");
//        integerObject.showParameterType();
//        integerObject.showKeyType();
//        System.out.println(integerObject.getParameter());
//
//        //string
//        Generic<Collection, Integer> stringObject = new Generic<String, Integer>();
//        stringObject.setParameter(new String("test String"));
//        stringObject.showParameterType();
//        stringObject.setKey(new Integer(100));
//        stringObject.showKeyType();
//        System.out.println(stringObject.getParameter());

        Generic<ArrayList<SortUtilTest>, String> listGeneric = new Generic<ArrayList<SortUtilTest>, String>();
        ArrayList<SortUtilTest> intList = new ArrayList();
        intList.add(new SortUtilTest());
        listGeneric.testExtendGeneric(intList);

        //参数泛型
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(5);
//        list.add(2);
//        list.add(6);
//        System.out.println(list);
//        SortUtil.sort(list);
//        System.out.println(list);
    }
}
