package com.testeverything.serializable.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lijinsheng on 14-3-13.
 */
public class OnlineItem implements Serializable {
    private static final long serialVersionUID = 8005906124906132320L;

    private Long itemId;  // BIGINT NOT NULL AUTO_INCREMENT COMMENT '选项id',
    private transient Long problemId; // BIGINT NOT NULL,
    private Integer answerFlag; //TINYINT COMMENT '单选题 多选题 0：不是答案 1 是答案',
    private String itemContent; //VARCHAR(50) COMMENT '选项内容',
    private String itemAnswer; //VARCHAR(50) COMMENT '问答题答案 ',
    private transient Date created; //DATE NOT NULL,
    private transient Date modified;//DATE NOT NULL COMMENT '修改时间',

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Integer getAnswerFlag() {
        return answerFlag;
    }

    public void setAnswerFlag(Integer answerFlag) {
        this.answerFlag = answerFlag;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getItemAnswer() {
        return itemAnswer;
    }

    public void setItemAnswer(String itemAnswer) {
        this.itemAnswer = itemAnswer;
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

    @Override
    public String toString() {
        return "OnlineItem{" +
                "itemId=" + itemId +
                ", problemId=" + problemId +
                ", answerFlag=" + answerFlag +
                ", itemContent=" + itemContent +
                ", itemAnswer=" + itemAnswer +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
