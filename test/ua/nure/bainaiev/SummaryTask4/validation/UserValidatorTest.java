package ua.nure.bainaiev.SummaryTask4.validation;


import org.junit.Test;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.util.validation.UserValidator;
import ua.nure.bainaiev.SummaryTask4.util.validation.Validator;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserValidatorTest {
    @Test
    public void testNull() {
        Validator validator = new UserValidator(null, "ru");
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testEmail() {
        User user = new User();
        Validator validator = new UserValidator(user, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));
        assertNotNull(validator.getMessages().get("email"));
        user.setEmail("");
        validator = new UserValidator(user, "en");
        assertNotNull(validator.getMessages().get("email"));
        user.setEmail("dsdsd");
        validator = new UserValidator(user, "en");
        assertNotNull(validator.getMessages().get("email"));
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            email.append("a");
        }
        email.append("@gmail.com");
        user.setEmail(email.toString());
        validator = new UserValidator(user, "en");
        assertNotNull(validator.getMessages().get("email"));
        user.setEmail("sample@gmail.com");
        validator = new UserValidator(user, "en");
        assertNull(validator.getMessages().get("email"));
    }

    @Test
    public void validateLogin() {
        Validator validator = new UserValidator(null, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));

        assertNull(validator.getMessages().get("login"));
        String login = "";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get("login"));
        login = "d";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get("login"));
        StringBuilder loginString = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            loginString.append("a");
        }
        validator = new UserValidator(loginString.toString(), null, "en");
        assertNotNull(validator.getMessages().get("login"));
        login = "John 95";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get("login"));
        login = "John95";
        validator = new UserValidator(login, null, "en");
        assertNull(validator.getMessages().get("login"));
    }

    @Test
    public void testPassword() {
        Validator validator = new UserValidator(null, null, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));

        assertNotNull(validator.getMessages().get("password"));
        String password = "";
        validator = new UserValidator(null, password, "en");
        assertNotNull(validator.getMessages().get("password"));
        password = "ddd";
        validator = new UserValidator(null, password, "ru");
        assertNotNull(validator.getMessages().get("password"));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            s.append("a");
        }
        validator = new UserValidator(null, s.toString(), "en");
        assertNotNull(validator.getMessages().get("password"));
        password = "John Doe";
        validator = new UserValidator(null, password, "en");
        assertNotNull(validator.getMessages().get("password"));
        password = "John95";
        validator = new UserValidator(null, password, "en");
        assertNull(validator.getMessages().get("password"));
    }
}
