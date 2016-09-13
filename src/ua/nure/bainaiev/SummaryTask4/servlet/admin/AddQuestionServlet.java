package ua.nure.bainaiev.SummaryTask4.servlet.admin;

import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.service.AnswerService;
import ua.nure.bainaiev.SummaryTask4.service.QuestionService;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(Constants.ServletPaths.Admin.ADD_QUESTION)
public class AddQuestionServlet extends BaseServlet {
    private Map<String, String> messages;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(Attributes.NO_CORRECT_ANSWERS, messages.get(Constants.Keys.NO_CORRECT_ANSWER));
        req.setAttribute(Attributes.SAME_ANSWERS, messages.get(Constants.Keys.SAME_ANSWERS));

        if (messages != null) {
            messages.clear();
        }
        req.setAttribute(Attributes.ID, id);
        getServletContext().setAttribute(Attributes.SERVLET_PATH, Constants.ServletPaths.Admin.ADD_QUESTION);
        forward(Constants.Pages.Admin.ADD_QUESTION, req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (messages == null) {
            messages = new HashMap<>();
        }

        id = getIntParam(req, Attributes.ID);
        String questionText = getStringParam(req, Attributes.QUESTION_TEXT);
        String[] answers = req.getParameterValues(Attributes.ANSWER);
        String[] corrects = req.getParameterValues(Attributes.CORRECT);

        AnswerService answerService = getAnswerService();
        QuestionService questionService = getQuestionService();

        Answer answer;
        Question question = new Question(questionText, id);
        Question question1 = questionService.save(question);


        for (int i = 0; i < answers.length; i++) {
            answer = new Answer(answers[i], Boolean.valueOf(corrects[i].toLowerCase().trim()), question1.getId());
            question1.addAnswer(answer);
        }

        Validator validator = new QuestionValidator(getLocale(req), question1);

        if (validator.hasErrors()) {
            questionService.delete(question1.getId());
            messages.put(Constants.Keys.NO_CORRECT_ANSWER, validator.getMessages().get(Constants.Keys.NO_CORRECT_ANSWER));
            messages.put(Constants.Keys.SAME_ANSWERS, validator.getMessages().get(Constants.Keys.SAME_ANSWERS));

            redirectToAction(Constants.ServletPaths.Admin.ADD_QUESTION, req, resp);

            return;
        }

        for(Answer a: question1.getAnswers()){
            answerService.save(a);
        }

        redirectToAction(Constants.ServletPaths.Admin.ADD_QUESTION, req, resp);
    }
}
