package com.testeverything.serializable.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lijinsheng on 14-3-13.
 */
public class OnlineProblem implements Serializable{
    private static final long serialVersionUID = 6780704419767324274L;
    private Long problemId;//BIGINT NOT NULL AUTO_INCREMENT,
    private Integer problemType; //TINYINT NOT NULL COMMENT '1 单选，2 多选，3 问答题',
    private String problemStem;//VARCHAR(50) NOT NULL COMMENT '问题题干',
    private Integer difficultyLevel; //TINYINT NOT NULL COMMENT '题目难度1.简单 2.中等 3.难 ',
    private transient Date created; //DATE NOT NULL COMMENT '创建时间',
    private transient Date modified; //DATE NOT NULL COMMENT '修改时间',
    private List<OnlineItem> onlineItemList;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Integer getProblemType() {
        return problemType;
    }

    public void setProblemType(Integer problemType) {
        this.problemType = problemType;
    }

    public String getProblemStem() {
        return problemStem;
    }

    public void setProblemStem(String problemStem) {
        this.problemStem = problemStem;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<OnlineItem> getOnlineItemList() {
        return onlineItemList;
    }

    public void setOnlineItemList(List<OnlineItem> onlineItemList) {
        this.onlineItemList = onlineItemList;
    }

    @Override
    public String toString() {
        return "OnlineProblem{" +
                "problemId=" + problemId +
                ", problemType=" + problemType +
                ", problemStem='" + problemStem + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                ", created=" + created +
                ", modified=" + modified +
                ", onlineItemList=" + onlineItemList +
                '}';
    }
}
