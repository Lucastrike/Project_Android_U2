package es.com.lucassalinas.tinywords;

/**
 * Created by alumno_solvam on 16/11/16.
 */

public class Users {

    private int id;
    private String user;
    private String password;
    private int score;

    public Users(){}

    public Users(String user, String password, Integer score) {
        super();
        this.user = user;
        this.password = password;
        this.score = score;
    }

    //getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
