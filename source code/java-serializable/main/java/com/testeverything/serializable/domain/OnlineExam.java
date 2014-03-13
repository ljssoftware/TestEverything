package com.testeverything.serializable.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lijinsheng on 14-3-13.
 */
public class OnlineExam implements Serializable {

    private static final long serialVersionUID = -7411250319031860939L;
    private Long examId;      //BIGINT NOT NULL AUTO_INCREMENT COMMENT '考试id',
    private String examName;     //VARCHAR(50) NOT NULL COMMENT '考试名称',
    private Date startTime;    //DATE NOT NULL COMMENT '考试开始时间',
    private Date endTime;   //DATE NOT NULL COMMENT '考试关闭时间',
    private Integer interval;  //INTEGER NOT NULL COMMENT '考试时长 以分钟为单位 ',
    private transient Integer state;   //TINYINT NOT NULL COMMENT '考试状态 0 关闭 1开启 ',
    private String examNote; //VARCHAR(250) COMMENT '考试说明',
    private Integer difficultyLevel;//TINYINT NOT NULL COMMENT '题目难度1.简单 2.中等 3.难 ',
    private transient Date created;  //DATE NOT NULL,
    private transient Date modified;  //DATE NOT NULL COMMENT '修改时间 ',

    private List<OnlineProblem> problemList; //考试包含的题目

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExamNote() {
        return examNote;
    }

    public void setExamNote(String examNote) {
        this.examNote = examNote;
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

    public List<OnlineProblem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<OnlineProblem> problemList) {
        this.problemList = problemList;
    }

    @Override
    public String toString() {
        return "OnlineExam{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", interval=" + interval +
                ", state=" + state +
                ", examNote='" + examNote + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                ", created=" + created +
                ", modified=" + modified +
                ", problemList=" + problemList +
                '}';
    }
}
