package ua.nure.bainaiev.SummaryTask4.entity;


import java.util.ArrayList;
import java.util.List;

public class Question extends Entity {
    private int testId;
    private String questionText;
    private List<Answer> answers;

    public Question(){}

    public Question(String questionText, int testId) {
        this.questionText = questionText;
        this.testId = testId;
    }

    public Question(String questionText, int testId, int id, List<Answer> answers) {
        this.questionText = questionText;
        this.testId = testId;
        this.id = id;
        this.answers = answers;
    }

    public Question(int testId, String questionText, List<Answer> answers) {
        this.testId = testId;
        this.questionText = questionText;
        this.answers = answers;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        if (answers == null) {
            return new ArrayList<>();
        }
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        if (testId != question.testId) return false;
        if (!questionText.equals(question.questionText)) return false;
        return answers.equals(question.answers);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + testId;
        result = 31 * result + questionText.hashCode();
        result = 31 * result + answers.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id +
                ", testId=" + testId +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}
