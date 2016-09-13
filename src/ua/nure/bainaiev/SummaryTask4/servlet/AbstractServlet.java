package ua.nure.bainaiev.SummaryTask4.servlet;


import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.service.*;
import ua.nure.bainaiev.SummaryTask4.util.LocaleUtil;
import ua.nure.bainaiev.SummaryTask4.util.constant.Attributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * A base servlet for all servlets. Provides access to services.
 */
public abstract class AbstractServlet extends HttpServlet {
    private UserService userService;
    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;
    private StorageService storageService;
    private String defaultLocale;
    private LocaleUtil translator;
    private String[] locales;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute(UserService.class.getName());
        testService = (TestService) context.getAttribute(TestService.class.getName());
        questionService = (QuestionService) context.getAttribute(QuestionService.class.getName());
        answerService = (AnswerService) context.getAttribute(AnswerService.class.getName());
        storageService = (StorageService) context.getAttribute(StorageService.class.getName());
        translator = (LocaleUtil) context.getAttribute(LocaleUtil.class.getName());
        defaultLocale = (String) context.getAttribute(Attributes.DEFAULT_LOCALE);
        locales = (String[]) context.getAttribute(Attributes.LOCALES);
    }

    protected UserService getUserService() {
        return userService;
    }

    protected TestService getTestService() {
        return testService;
    }

    protected QuestionService getQuestionService() {
        return questionService;
    }

    protected AnswerService getAnswerService() {
        return answerService;
    }

    public StorageService getStorageService() {
        return storageService;
    }

    protected String getDefaultLocale() {
        return defaultLocale;
    }

    protected LocaleUtil getTranslator() {
        return translator;
    }

    protected String[] getLocales() {
        return locales;
    }

    protected String getLocale(HttpServletRequest request) {
        return (String) request.getAttribute(Attributes.CURRENT_LOCALE);
    }

    protected Integer getIntParam(HttpServletRequest req, String paramName) {
        String param = req.getParameter(paramName);
        if (param == null) {
            return null;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected List<Integer> getIntParamValues(HttpServletRequest req, String paramName) {
        String[] values = req.getParameterValues(paramName);
        if (values == null) {
            return null;
        }
        List<Integer> integerValues = new ArrayList<>();
        for (String value : values) {
            try {
                Integer i = Integer.parseInt(value);
                integerValues.add(i);
            } catch (NumberFormatException e) {
                integerValues.add(null);
            }
        }
        return integerValues;
    }

    protected String getStringParam(HttpServletRequest req, String paramName) {
        String param = req.getParameter(paramName);
        if (param == null) {
            return null;
        }
        return param.trim();
    }

    protected String getStringParam(HttpServletRequest req, String paramName, String defaultValue) {
        String value = getStringParam(req, paramName);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return value;
    }

    protected int getIntParam(HttpServletRequest req, String paramName, int defaultValue) {
        Integer value = getIntParam(req, paramName);
        if (value == null || value < 1) {
            return defaultValue;
        }
        return value;
    }

    public List<String> getListSubject() {
        List<String> list = new ArrayList<>();
        for (Subject subject : Subject.values()) {
            list.add(subject.toString());
        }
        return list;
    }

}
