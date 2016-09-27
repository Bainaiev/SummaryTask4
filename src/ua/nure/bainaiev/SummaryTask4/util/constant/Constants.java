package ua.nure.bainaiev.SummaryTask4.util.constant;


public final class Constants {

    public static final String FALSE = "false";


    public static final class Routes {
        public static final String BUNDLE_PATH = "messages";

        private Routes() {
        }
    }

    public static final class Parameters{
        public static final String ORDER = "order";
        public static final String TITLE = "title";
        public static final String SORT = "sort";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String ID = "id";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";

        private Parameters(){}
    }

    public static final class Keys{
        public static final String LOGIN_INVALID = "loginInvalid";
        public static final String PASSWORD_INVALID = "passwordInvalid";
        public static final String STATUS = "status";
        public static final String RIGHTS = "rights";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
        public static final String ROLES = "roles";
        public static final String ERROR = "error";
        public static final String TITLE = "title";
        public static final String COMPLEXITY = "complexity";
        public static final String TIME = "time";
        public static final String SAME_ANSWERS = "sameAnswers";
        public static final String NO_CORRECT_ANSWER = "noCorrectAnswer";



        private Keys(){}
    }


    public static final class Validation {
        public static final String LEN_3_TO_100 = "validator.lengthFrom3to100";
        public static final String LEN_5_TO_100 = "validator.lengthFrom5to100";
        //        public static final String LEN_5_TO_1000 = "validator.lengthFrom5to1000";
        public static final String LETTERS_ONLY = "validator.lettersOnly";
        public static final String INV_EMAIL = "validator.invalidEmail";
        public static final String PATT_MATCH = "validator.loginPatternMatch";
        public static final String NO_WHITESPACES = "validator.noWhitespaces";
        public static final String CANT_BE_EMPTY = "validator.cannotBeEmpty";
        //        public static final String PAGES_COUNT = "validator.pagesCount";
//        public static final String YEAR = "validator.year";
//        public static final String TOO_BIG = "validator.tooBig";
//        public static final String START_DATE_GREATER_END_DATE = "validator.startGreaterEnd";
        public static final String LEN_4_TO_100 = "validator.lengthFrom4to100";

        public static final String INVALID_LOGIN = "validator.invalidLogin";
        public static final String INVALID_PASSWORD = "validator.invalidPassword";
        public static final String BANNED = "validator.banned";
        public static final String NO_RIGHTS = "validator.noRights";
        public static final String LOGIN_TAKEN = "validator.loginTaken";
        public static final String EMAIL_TAKEN = "validator.emailTaken";
        public static final String ERROR_500 = "validator.error500";

        public static final class Test{
            public static final String TITLE_PATT_MATCH = "validator.titlePatternMatch";
            public static final String MORE_THAN_10 = "validator.valueMoreThan10";
            public static final String MORE_THAN_150 = "validator.valueMoreThan150";

            private Test(){}
        }

        public static final class Question{

            public static final String EXIST_CORRECT_ANSWER = "validator.existCorrectAnswer";
            public static final String SAME_ANSWER = "validator.sameAnswers";



            private Question(){}
        }


        private Validation() {
        }
    }


    public static final class ServletPaths {
        public static final String START_PATH = "/";

        public static final class User {
            public static final String USER = "/user/";
            public static final String HOME = USER + "home";
            public static final String LOGIN = USER + "login";
            public static final String LOGOUT = USER + "logout";
            public static final String TEST = USER + "test";
            public static final String REGISTER = USER + "register";
            public static final String PROFILE = USER + "profile";
            public static final String CATALOG = USER + "catalog";

            private User() {
            }

        }

        public class Admin {
            public static final String ADMIN = "/admin/";
            public static final String LOGIN = ADMIN + "login";
            public static final String LOGOUT = ADMIN + "logout";
            public static final String MAIN = ADMIN + "main";
            public static final String USERS = ADMIN + "user";
            public static final String TEST = ADMIN + "test";
            public static final String EDIT_TEST = TEST + "/" + "edit";
            public static final String DELETE_TEST = TEST + "/" + "delete";
            public static final String ADD_TEST = TEST + "/" + "add";
            public static final String ADD_QUESTION = ADD_TEST + "/" + "question";
            public static final String UPDATE_TEST = TEST + "/" + "update";
            public static final String UPDATE_QUESTION = UPDATE_TEST + "/" + "question";



            private Admin() {
            }
        }

        private ServletPaths() {
        }
    }

    public static final class Pages {

        private static final String PREFIX = "/WEB-INF/pages/";

        public static final class User {
            private static final String USER_PREFIX = PREFIX + "user/";

            public static final String HOME = PREFIX + "index.jsp";
            public static final String REGISTRATION = USER_PREFIX + "registration.jsp";
            public static final String TEST = USER_PREFIX + "test.jsp";
            public static final String PROFILE = USER_PREFIX + "profile.jsp";
            public static final String LOGIN = USER_PREFIX + "login.jsp";
            public static final String CATALOG = USER_PREFIX + "catalog.jsp";

            private User() {
            }
        }

        public class Admin {
            private static final String ADMIN_PREFIX = PREFIX + "admin/";

            public static final String MAIN_PAGE = ADMIN_PREFIX + "adminPage.jsp";
            public static final String LOGIN = ADMIN_PREFIX + "login.jsp";
            public static final String USERS = ADMIN_PREFIX + "user.jsp";
            public static final String TEST = ADMIN_PREFIX + "test.jsp";
            public static final String EDIT_TEST = ADMIN_PREFIX + "editTest.jsp";
            public static final String ADD_TEST = ADMIN_PREFIX + "addTest.jsp";
            public static final String ADD_QUESTION = ADMIN_PREFIX + "addQuestion.jsp";
            public static final String EDIT_QUESTIONS = ADMIN_PREFIX + "editQuestion.jsp";
            public static final String EDIT_ANSWERS = ADMIN_PREFIX + "editAnswers.jsp";
   

            private Admin(){}
        }

    }

    private Constants() {
    }
}
