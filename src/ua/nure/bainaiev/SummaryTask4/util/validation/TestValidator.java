package ua.nure.bainaiev.SummaryTask4.util.validation;

import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants.Validation;


import java.util.regex.Pattern;

public class TestValidator extends AbstractValidator {
    private static final Pattern TITLE_TEMPLATE = Pattern.compile("^[A-ZА-Яа-яa-z0-9_-]+$", Pattern.CASE_INSENSITIVE);

    /**
     * Instantiates a new TestValidator for validating {@link Test}s.
     *
     * @param test   test that needs validation
     * @param locale current locale value
     */
    public TestValidator(Test test, String locale) {
        super(locale);
        if (test == null) {
            return;
        }
        putIssue(Constants.Keys.TITLE, validateTitle(test.getTitle()));
        putIssue(Constants.Keys.COMPLEXITY, validateComplexity(test.getComplexity()));
        putIssue(Constants.Keys.TIME, validateTime(test.getTimePassing()));
    }

    private String validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (!TITLE_TEMPLATE.matcher(title).matches()) {
            return Validation.Test.TITLE_PATT_MATCH;
        }
        if (title.length() < 3 || title.length() > 100) {
            return Validation.LEN_3_TO_100;
        }
        return null;
    }

    private String validateComplexity(int complexity) {
        if(complexity > 10){
            return Validation.Test.MORE_THAN_10;
        }

        return null;
    }

    private String validateTime(int time) {
        if(time > 150){
            return Validation.Test.MORE_THAN_150;
        }

        return null;
    }


}
