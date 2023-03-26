package com.questionnaire.step_defs;

import com.questionnaire.objects.AnswerBody;
import com.questionnaire.objects.QuestionAnswer;
import com.questionnaire.utils.ConfigReader;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class allsteps {

    private String token;

    @BeforeAll
    public static void setup(){
        baseURI = ConfigReader.getProperty("base.url");
    }

    @Given("user login as admin with valid credentials and gets the token")
    public void user_login_as_admin_with_valid_credentials() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

       token = given().accept(ContentType.JSON).queryParam("username",username).queryParam("password",password)
                .when().post("/Token/login")
                .then().statusCode(200).contentType(ContentType.JSON).extract().jsonPath().get("token");

    }
    @When("user send a GET request and survey list should return")
    public void user_send_a_get_request() {
        given().header("Authorization","Bearer "+token).accept(ContentType.JSON)
                .when().get("/Survey/list")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

    @When("user send a GET request with invalid auth key and survey list should not return")
    public void userSendAGETRequestWithInvalidAuthKeyAndSurveyListShouldReturn() {
        given().header("Authorization","Bearer "+ UUID.randomUUID().toString()).accept(ContentType.JSON)
                .when().get("/Survey/list")
                .then().statusCode(401).contentType( is(not(ContentType.JSON)));
    }

    @When("user post answer to survey as a jsonbody, should get the json response")
    public void userPostAnswerToSurveyAsAJsonbodyShouldGetTheJsonResponse() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionId(3807638);
        //questionAnswer.setAnswerText("Strongly disagree");
        questionAnswer.setAnswerOptionIds(new ArrayList<>(Arrays.asList(17969124,17969123,17969122,17969121,17969120)));

        AnswerBody answerBody = new AnswerBody();
        answerBody.setId(3807638);
        answerBody.setUserId(1);
        answerBody.setSurveyId(1000);
        answerBody.setDepartment("HR");
        answerBody.setQuestionAnswers(new ArrayList<QuestionAnswer>(Arrays.asList(questionAnswer)));


        given().header("Authorization","Bearer "+ token).contentType(ContentType.JSON).accept(ContentType.JSON).body(answerBody)
                .when().post("/Answers")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

    @When("send a Get request with {string} and {string} and see the response")
    public void sendAGetRequestWithUserIdAndSurveyIdAndSeeTheResponse(String userId, String surveyId) {
        given().header("Authorization","Bearer "+ token).accept(ContentType.JSON).pathParam("userId",userId).pathParam("surveyId",surveyId)
                .when().get("/Answers/user/{userId}/survey/{surveyId}")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

    @When("send a Get request with {string} then see the response")
    public void sendAGetRequestWithThenSeeTheResponse(String surveyId) {
        given().header("Authorization","Bearer "+ token).accept(ContentType.JSON).pathParam("surveyId",surveyId)
                .when().get("/Answers/statistics/survey/{surveyId}")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

    @When("send a get request with {string},{string} and {string} parameters and see the questions")
    public void sendAGetRequestWithAndParametersAndSeeTheQuestions(String surveyId, String skip, String limit) {
        given().header("Authorization","Bearer "+ token).accept(ContentType.JSON).pathParam("surveyId",surveyId).queryParam("skip",skip).queryParam("limit",limit)
                .when().get("/Questions/list/survey/{surveyId}")
                .then().statusCode(200).contentType(ContentType.JSON);
    }



    @When("send a get request with {string},{string} and {string} parameters and see the questions by subjects")
    public void sendAGetRequestWithAndParametersAndSeeTheQuestionsBySubjects(String subjectId, String skip, String limit) {
        given().header("Authorization","Bearer "+ token).accept(ContentType.JSON).pathParam("subjectId",subjectId).queryParam("skip",skip).queryParam("limit",limit)
                .when().get("/Questions/list/subject/{subjectId}")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

    @When("user posts answer to survey with user ID {string}, survey ID {string}, department {string}, question ID {string}, answer text {string}, and answer option IDs {string}")
    public void userPostsAnswerToSurveyWithUserIDSurveyIDDepartmentQuestionIDAnswerTextAndAnswerOptionIDs(String userId, String surveyId, String department, String questionId, String answerText, String answerOptionIds) {
        List<Integer> answerOptionIdList = Arrays.stream(answerOptionIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        // Create questionAnswer object
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionId(Integer.parseInt(questionId));
        questionAnswer.setAnswerText(answerText);
        questionAnswer.setAnswerOptionIds(answerOptionIdList);

        // Create answerBody object
        AnswerBody answerBody = new AnswerBody();
        answerBody.setId(1);
        answerBody.setUserId(Integer.parseInt(userId));
        answerBody.setSurveyId(Integer.parseInt(surveyId));
        answerBody.setDepartment(department);
        answerBody.setQuestionAnswers(Collections.singletonList(questionAnswer));

        // REST-assured request
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).accept(ContentType.JSON).body(answerBody)
                .when().post("/Answers")
                .then().statusCode(200).contentType(ContentType.JSON);
    }

}
