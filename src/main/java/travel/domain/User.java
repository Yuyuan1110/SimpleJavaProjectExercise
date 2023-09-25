package travel.domain;

import java.util.Date;

public class User {
    private int id;
    private String account;
    private String username;
    private String email;
    private String password;
    private Date birthday;
    private String gender;
    private int phone;

    private int active_state;

    private long active_code;

    public int getActive_state() {
        return active_state;
    }

    public void setActive_state(int active_state) {
        this.active_state = active_state;
    }

    public long getActive_code() {
        return active_code;
    }

    public void setActive_code(long active_code) {
        this.active_code = active_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public User() {
    }

    public User(int id, String account, String username, String email, String password, Date birthday, String gender, int phone, int active_state, long active_code) {
        this.id = id;
        this.account = account;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.active_state = active_state;
        this.active_code = active_code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", active_state=" + active_state +
                ", active_code=" + active_code +
                '}';
    }
}
