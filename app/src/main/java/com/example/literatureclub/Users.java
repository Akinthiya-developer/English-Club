package com.example.literatureclub;

public class Users {

    public String name,department,section,year,code,phone,email,password;

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
}
