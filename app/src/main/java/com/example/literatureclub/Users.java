package com.example.literatureclub;

public class Users {

    public String name;
    public String department;
    public String section;
    public String year;
    public String code;
    public String phone;
    public String email;
    public String password;

    public Users(String name,String section, String code, String phone, String email, String password,String department, String year) {
        this.department = department;
        this.name=name;
        this.section = section;
        this.year = year;
        this.code = code;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getSection() {
        return section;
    }

    public String getYear() {
        return year;
    }

    public String getCode() {
        return code;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
