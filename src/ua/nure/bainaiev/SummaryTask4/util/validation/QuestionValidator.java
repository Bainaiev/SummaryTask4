package ua.nure.bainaiev.SummaryTask4.util.validation;


import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import java.util.List;

public class QuestionValidator extends AbstractValidator {

    public QuestionValidator(String locale, Question question) {
        super(locale);
        if (question == null) {
            return;
        }
        putIssue(Constants.Keys.NO_CORRECT_ANSWER, validateExitCorrectAnswer(question.getAnswers()));
        putIssue(Constants.Keys.SAME_ANSWERS, validateNoSameAnswers(question.getAnswers()));
    }

    private String validateExitCorrectAnswer(List<Answer> list) {
        for (Answer a : list) {
            if (a.getCorrect()) {
                return null;
            }
        }
        return Constants.Validation.Question.EXIST_CORRECT_ANSWER;
    }

    private String validateNoSameAnswers(List<Answer> list) {
        String content = null;
        for (Answer a : list) {
            if (!a.getContent().equals(content)) {
                content = a.getContent();
            } else {
                return Constants.Validation.Question.SAME_ANSWER;
            }
        }
        return null;
    }

}
