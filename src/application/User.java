package application;

public class User {
    private String foreName;
    private String surName;
    private String username;
    private String role;
    private String mobileNo;
    private String email;
    private String userType;
    private String password;

    public User(String foreName, String surName, String username, String role, String mobileNo, String email, String userType, String password) {

        this.foreName = foreName;
        this.surName = surName;
        this.username = username;
        this.role = role;
        this.mobileNo = mobileNo;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public User(String foreName, String surName, String username, String role, String mobileNo, String email, String password) {

        this.foreName = foreName;
        this.surName = surName;
        this.username = username;
        this.role = role;
        this.mobileNo = mobileNo;
        this.email = email;
        userType = "User";
        this.password = password;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  foreName + " " + surName;

    }
}
