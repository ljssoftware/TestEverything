package com.testeverything.serializable.test;

import com.alibaba.fastjson.JSON;
import com.testeverything.serializable.domain.OnlineExam;
import com.testeverything.serializable.domain.OnlineItem;
import com.testeverything.serializable.domain.OnlineProblem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试 java自带的 序列化的 长度
 * Created by lijinsheng on 14-3-12.
 */
public class TestSerializable {

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
        onlineExam.setProblemList(problemList);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(onlineExam);
        System.out.println(byteArrayOutputStream.toString("UTF-8"));
        System.out.println("字符串长度:" + byteArrayOutputStream.toString("UTF-8").length());

        String examJson = JSON.toJSONString(onlineExam);
        System.out.println(examJson);
        System.out.println("json 串长度:" + examJson.length());
    }
}
