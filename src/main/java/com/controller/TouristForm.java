package com.controller;

import com.entity.Insurance;

public class TouristForm {

    private String surname;
    private String name;
    private String middle_name;
    private String dob;
    private String phone;
    private String email;
    private String intl_passport_num;
    private String intl_passport_series;

    public TouristForm() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntl_passport_num() {
        return intl_passport_num;
    }

    public void setIntl_passport_num(String intl_passport_num) {
        this.intl_passport_num = intl_passport_num;
    }

    public String getIntl_passport_series() {
        return intl_passport_series;
    }

    public void setIntl_passport_series(String intl_passport_series) {
        this.intl_passport_series = intl_passport_series;
    }
}
