package com.testeverything.serializable.test;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianSerializerOutput;
import com.testeverything.serializable.domain.OnlineExam;
import com.testeverything.serializable.domain.OnlineItem;
import com.testeverything.serializable.domain.OnlineProblem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lijinsheng on 14-3-13.
 */
public class TestHessianSerializable {
    private static final int EXECUTE_TIME = 100000; //执行次数

    public static void main(String[] args) throws IOException {
        List<OnlineProblem> problemList = new ArrayList<OnlineProblem>();
        for (int i = 0; i < 10; i++) {
            List<OnlineItem> itemList = new ArrayList<OnlineItem>();
            for (int j = 0; j < 3; j++) {
                OnlineItem onlineItem = new OnlineItem();
                onlineItem.setItemId(100L);
                onlineItem.setProblemId(100L);
                onlineItem.setItemContent("选项内容");
                onlineItem.setItemAnswer("选项答案");
                onlineItem.setAnswerFlag(0);
                itemList.add(onlineItem);
            }

            OnlineProblem onlineProblem = new OnlineProblem();
            onlineProblem.setProblemId(1000L);
            onlineProblem.setProblemStem("问题题干");
            onlineProblem.setProblemType(100);
            onlineProblem.setDifficultyLevel(3);
            onlineProblem.setOnlineItemList(itemList);
            problemList.add(onlineProblem);
        }

        OnlineExam onlineExam = new OnlineExam();
        onlineExam.setExamId(1000L);
        onlineExam.setExamName("测试名称");
        onlineExam.setExamNote("examNote");
        onlineExam.setDifficultyLevel(2);
        onlineExam.setStartTime(new Date());
        onlineExam.setEndTime(new Date());
        onlineExam.setState(2);
        onlineExam.setInterval(1000);
//        onlineExam.setProblemList(problemList);

        long start = System.currentTimeMillis();
//        java序列化的内容

//        for (int i = 0; i < EXECUTE_TIME; i++) {
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream1);
        objectOutputStream.writeObject(onlineExam);
        System.out.println(new String(byteArrayOutputStream1.toByteArray(), "UTF-8"));
        System.out.println("字符串长度:" + new String(byteArrayOutputStream1.toByteArray(), "UTF-8").length());
//        }
//        System.out.println("java 序列化时间 " + (System.currentTimeMillis() - start) + " ms");

//        hessian 序列化的内容
//        for (int i = 0; i < EXECUTE_TIME; i++) {
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        HessianSerializerOutput hessianSerializerOutput = new HessianSerializerOutput();
        hessianSerializerOutput.init(byteArrayOutputStream2);
        hessianSerializerOutput.writeObject(onlineExam);
        System.out.println("hessian 序列化的内容:" + byteArrayOutputStream2.toString());
        System.out.println("hessian 序列化长度:" + byteArrayOutputStream2.toString().length());
//        }
//        System.out.println("hessian 序列化时间 " + (System.currentTimeMillis() - start) + " ms");

//        hessian反序列化内容
        byte[] bytes = new byte[10000];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput();
        hessianInput.init(byteArrayInputStream);
        OnlineExam onlineExam1 = (OnlineExam) hessianInput.readObject();
        System.out.println("反序列化的 考试信息:" + onlineExam1);


//        序列化成JSON串的内容
//        for (int i = 0; i < EXECUTE_TIME; i++) {
        String examJson = JSON.toJSONString(onlineExam);
        System.out.println("JSON 内容：" + examJson);
        System.out.println("json 串长度:" + examJson.length());
        OnlineExam onlineExam2 = JSON.parseObject(examJson, OnlineExam.class);

//        }
//        System.out.println("json序列化时间 " + (System.currentTimeMillis() - start) + " ms");
    }

}
