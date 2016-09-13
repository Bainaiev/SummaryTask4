package ua.nure.bainaiev.SummaryTask4.entity;


public class Answer extends Entity {
    private String content;
    private Boolean correct;
    private int questionId;

    public Answer(){}

    public Answer(String content, Boolean correct, int questionId) {
        this.content = content;
        this.correct = correct;
        this.questionId = questionId;
    }

    public Answer(String content, Boolean correct, int questionId, int id) {
        this.content = content;
        this.correct = correct;
        this.questionId = questionId;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "content='" + content + '\'' +
                ", correct=" + correct +
                ", questionId=" + questionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Answer answer = (Answer) o;

        if (questionId != answer.questionId) return false;
        if (!content.equals(answer.content)) return false;
        return correct.equals(answer.correct);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + correct.hashCode();
        result = 31 * result + questionId;
        return result;
    }
}


