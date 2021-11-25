package com.example.bmicalculator;

class UserInfo{
    public String name;
    public String age;
    public String email;
    public String password;
    public UserInfo(String name,String age, String email,String pass){
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = pass;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(){
    }

}