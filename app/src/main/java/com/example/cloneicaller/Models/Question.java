package com.example.cloneicaller.Models;

public class Question {


    /**
     * question : Ứng dụng iCaller là gì?
     * answer : Hiển thị danh tính người gọi:

     Giúp người nghe biết ai gọi mình để chủ động quyết định có nghe không.

     Chặn cuộc gọi quảng cáo, lừa đảo:
     Giúp người dùng tránh bị làm phiền bằng cách chủ động chặn các cuộc gọi quảng cáo, lừa đảo.
     *Người dùng cần thiết lập iCaller trở thành ứng dụng mặc định để bật được tính năng này.
     */

    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
