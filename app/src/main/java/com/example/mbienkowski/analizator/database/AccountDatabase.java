package com.example.mbienkowski.analizator.database;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mbienkowski on 2017-11-13.
 */

@DatabaseTable(tableName = "accountDatabase")


public class AccountDatabase {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String login;

    @DatabaseField
    private String password;

    @DatabaseField
    private String email;

    @DatabaseField
    private String question;

    @DatabaseField
    private String answer;

    public AccountDatabase(){}

    public AccountDatabase(String login, String password, String email, String question, String answer){
        this.login = login;
        this.password = password;
        this.email = email;
        this.question = question;
        this.answer = answer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

