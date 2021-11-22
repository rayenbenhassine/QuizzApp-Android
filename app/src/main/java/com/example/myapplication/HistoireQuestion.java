package com.example.myapplication;

public class HistoireQuestion {
    private String question;
    private String prop1;
    private String prop2;
    private String prop3;
    private String reponse;

    public HistoireQuestion(String question,String prop1,String prop2,String prop3,String reponse){
        this.question = question;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public String getProp1() {
        return prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public String getReponse() {
        return reponse;
    }
}
