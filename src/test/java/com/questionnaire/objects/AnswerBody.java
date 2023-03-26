package com.questionnaire.objects;

import java.util.ArrayList;
import java.util.List;

public class AnswerBody {
    private Integer id;
    private Integer userId;
    private Integer surveyId;
    private String department;
    private List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }
    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}