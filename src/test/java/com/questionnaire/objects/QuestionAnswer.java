package com.questionnaire.objects;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer {
    private Integer questionId;
    private List<Integer> answerOptionIds = new ArrayList<Integer>();
    private String answerText;
    public Integer getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public List<Integer> getAnswerOptionIds() {
        return answerOptionIds;
    }
    public void setAnswerOptionIds(List<Integer> answerOptionIds) {
        this.answerOptionIds = answerOptionIds;
    }
    public String getAnswerText() {
        return answerText;
    }
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}