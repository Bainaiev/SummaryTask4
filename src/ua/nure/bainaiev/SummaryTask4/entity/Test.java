package ua.nure.bainaiev.SummaryTask4.entity;


import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;

import java.util.ArrayList;
import java.util.List;

public class Test extends Entity {
    private String title;
    private List<Question> questions;
    private int complexity;
    private int timePassing;
    private Subject subject;

    public Test(){}

    public Test(String title, int complexity, int timePassing, Subject subject) {
        this.title = title;
        this.complexity = complexity;
        this.timePassing = timePassing;
        this.subject = subject;
    }

    public Test(String title, int complexity, int timePassing, Subject subject, int id) {
        this.title = title;
        this.complexity = complexity;
        this.timePassing = timePassing;
        this.subject = subject;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getTimePassing() {
        return timePassing;
    }

    public void setTimePassing(int timePassing) {
        this.timePassing = timePassing;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Test{" +
                "title='" + title + '\'' +
                ", questions=" + questions +
                ", complexity=" + complexity +
                ", timePassing=" + timePassing +
                ", subjectId=" + subject +
                '}';
    }
}
