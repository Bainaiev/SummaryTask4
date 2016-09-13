package ua.nure.bainaiev.SummaryTask4.servlet.admin;

import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.service.AnswerService;
import ua.nure.bainaiev.SummaryTask4.service.QuestionService;
import ua.nure.bainaiev.SummaryTask4.service.TestService;
import ua.nure.bainaiev.SummaryTask4.servlet.BaseServlet;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;
import ua.nure.bainaiev.SummaryTask4.util.validation.QuestionValidator;
import ua.nure.bainaiev.SummaryTask4.util.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(Constants.ServletPaths.Admin.UPDATE_QUESTION)
public class UpdateQuestionServlet extends BaseServlet {
    private Map<String, String> messages;
    private Test test;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(test != null){
            req.setAttribute(Attributes.TEST, test);
            test = null;
            forward(Constants.ServletPaths.Admin.UPDATE_TEST, req, resp);
            return;
        }

        if (messages == null) {
            messages = new HashMap<>();
        }

        req.setAttribute(Attributes.NO_CORRECT_ANSWERS, messages.get(Constants.Keys.NO_CORRECT_ANSWER));
        req.setAttribute(Attributes.SAME_ANSWERS, messages.get(Constants.Keys.SAME_ANSWERS));

        if (messages != null) {
            messages.clear();
        }

        Question question = getQuestionService().get(id);
        req.setAttribute(Attributes.QUESTION, question);
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.UPDATE_QUESTION);
        forward(Constants.Pages.Admin.EDIT_ANSWERS, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = getIntParam(req, Constants.Parameters.ID);
        if(id != 0){
            redirectToAction(Constants.ServletPaths.Admin.UPDATE_QUESTION, req, resp);
            return;
        }
        int testId = getIntParam(req, Attributes.TEST_ID);
        int questionId = getIntParam(req, Attributes.QUESTION_ID);
        String questionText = getStringParam(req, Attributes.QUESTION_TEXT);
        String[] answers = req.getParameterValues(Attributes.ANSWER);
        String[] corrects = req.getParameterValues(Attributes.CORRECT);
        String[] answersId = req.getParameterValues(Attributes.ANSWER_ID);

        AnswerService answerService = getAnswerService();
        QuestionService questionService = getQuestionService();
        TestService testService = getTestService();

        Answer answer;
        Question question = new Question(questionText, testId, questionId, new ArrayList<>());
        question.getAnswers();

        for (int i = 0; i < answers.length; i++) {
            answer = new Answer(answers[i], Boolean.valueOf(corrects[i].toLowerCase().trim()),
                    question.getId(), Integer.parseInt(answersId[i]));
            question.addAnswer(answer);
        }

        Validator validator = new QuestionValidator(getLocale(req), question);

        if (validator.hasErrors()) {
            id = questionId;
            messages.put(Constants.Keys.NO_CORRECT_ANSWER, validator.getMessages().get(Constants.Keys.NO_CORRECT_ANSWER));
            messages.put(Constants.Keys.SAME_ANSWERS, validator.getMessages().get(Constants.Keys.SAME_ANSWERS));

            redirectToAction(Constants.ServletPaths.Admin.UPDATE_QUESTION, req, resp);

            return;
        }

        questionService.update(question);
        for (Answer a : question.getAnswers()) {
            answerService.update(a);
        }


        test = testService.get(testId);
        redirectToAction(Constants.ServletPaths.Admin.UPDATE_QUESTION, req, resp);
    }
}
