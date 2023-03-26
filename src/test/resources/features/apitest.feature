Feature: All Rest APi Test of Questionnaire App

  Background: Get the token
    Given user login as admin with valid credentials and gets the token

  Scenario:Get the Survey List with valid auth
    When user send a GET request and survey list should return

  Scenario:Do not Get the Survey List with invalid auth
    When user send a GET request with invalid auth key and survey list should not return

  Scenario Outline: Post answers to survey with different data
    When user posts answer to survey with user ID "<userId>", survey ID "<surveyId>", department "<department>", question ID "<questionId>", answer text "<answerText>", and answer option IDs "<answerOptionIds>"

    Examples:
      | userId | surveyId | department | questionId | answerText | answerOptionIds |
      | 1      | 1000     | HR         | 1          | Yes        | 1,2,3           |
      | 2      | 1001     | IT         | 2          | No         | 4,5,6           |
      | 3      | 1002     | Finance    | 3          | Maybe      | 7,8,9           |


  Scenario Outline: Get user's answers for specific survey
    When send a Get request with "<userId>" and "<surveyId>" and see the response
    Examples:
      | userId | surveyId |
      | 1      | 1000     |
      | 2      | 1000     |
      | 3      | 1000     |

  Scenario Outline: Get survey statistics by survey id
    When send a Get request with "<surveyId>" then see the response
    Examples:
    |surveyId|
    |1000    |
    |2000    |
    |3000    |

  Scenario Outline: Get The Questions By surveyId
    When send a get request with "<surveyId>","<skip>" and "<limit>" parameters and see the questions
    Examples:
    |surveyId|skip|limit|
    |1000    |0   |20   |
    |1000    |6   |20   |


  Scenario Outline: Get The Questions by subject
    When send a get request with "<subjectId>","<skip>" and "<limit>" parameters and see the questions by subjects
    Examples:
      |subjectId|skip|limit|
      |2605515  |0    |20  |
      |2605516  |0    |20  |
      |2605517  |0    |20  |
      |2605518  |0    |20  |

