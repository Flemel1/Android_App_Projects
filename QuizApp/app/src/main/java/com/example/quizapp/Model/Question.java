package com.example.quizapp.Model;

public class Question {
    private int number;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

    public int getNumber() {
        return number;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
