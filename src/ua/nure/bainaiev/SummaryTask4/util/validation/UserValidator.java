package ua.nure.bainaiev.SummaryTask4.util.validation;


import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants.Validation;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants.Keys;

import java.util.Set;
import java.util.regex.Pattern;

public class UserValidator extends AbstractValidator {
    private static final Pattern NAME_TEMPLATE = Pattern.compile(
            "^(\\p{L}+)(,\\s\\p{L}+\\s\\p{L}\\.)?(\\s?\\p{L}+)?$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EMAIL_TEMPLATE = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern LOGIN_TEMPLATE = Pattern.compile(
            "^[A-ZА-Яа-яa-z0-9_-]+$", Pattern.CASE_INSENSITIVE);


    /**
     * Instantiates a new UserValidator for validating {@link User}s.
     *
     * @param user   user that needs validation
     * @param locale current locale value
     */
    public UserValidator(User user, String locale) {
        super(locale);
        if (user == null) {
            return;
        }
        putIssue(Keys.FIRST_NAME, validateName(user.getFirstName()));
        putIssue(Keys.LAST_NAME, validateName(user.getLastName()));
        putIssue(Keys.EMAIL, validateEmail(user.getEmail()));
        putIssue(Keys.LOGIN, validateLogin(user.getLogin()));
        putIssue(Keys.PASSWORD, validatePassword(user.getPassword()));
        putIssue(Keys.ROLES, validateRoles(user.getRoles()));
    }

    /**
     * Instantiates a new UserValidator that validates only user's login and password immediately.
     *
     * @param login       login to validate
     * @param password    password to validate
     * @param locale      current locale value
     */
    public UserValidator(String login, String password, String locale) {
        super(locale);
        putIssue(Keys.LOGIN, validateLogin(login));
        putIssue(Keys.PASSWORD, validatePassword(password));
    }


    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (!NAME_TEMPLATE.matcher(name).matches()) {
            return Validation.LETTERS_ONLY;
        }
        if (name.length() < 3 || name.length() > 100) {
            return Validation.LEN_3_TO_100;
        }
        return null;
    }

    private String validateRoles(Set<Role> roles) {
        if (roles == null) {
            return Validation.CANT_BE_EMPTY;
        }
        return null;
    }

    private String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (!EMAIL_TEMPLATE.matcher(email).matches()) {
            return Validation.INV_EMAIL;
        }
        if (email.length() < 5 || email.length() > 100) {
            return Validation.LEN_5_TO_100;
        }
        return null;
    }

    private String validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (!LOGIN_TEMPLATE.matcher(login).matches()) {
            return Validation.PATT_MATCH;
        }
        if (login.length() < 4 || login.length() > 100) {
            return Validation.LEN_4_TO_100;
        }
        return null;
    }

    private String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (password.contains(" ")) {
            return Validation.NO_WHITESPACES;
        }
        if (password.length() < 5 || password.length() > 100) {
            return Validation.LEN_5_TO_100;
        }
        return null;
    }


}
