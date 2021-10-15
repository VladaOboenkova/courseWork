package com.controller;

import com.entity.Insurance;
import com.entity.PersonInfo;
import com.repository.TourStaffRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilities {

    TourStaffRepository tourStaffRepository;

    public static List<TouristForm> touristFormToList(Tourists tourists){
        List<TouristForm> tfl = new ArrayList<>();

        TouristForm tourist1 = new TouristForm();
        tourist1.setSurname(tourists.getSurname1());
        tourist1.setName(tourists.getName1());
        tourist1.setMiddle_name(tourists.getMiddle_name1());
        tourist1.setDob(tourists.getDob1());
        tourist1.setPhone(tourists.getPhone1());
        tourist1.setEmail(tourists.getEmail1());
        tourist1.setIntl_passport_num(tourists.getIntl_passport_num1());
        tourist1.setIntl_passport_series(tourists.getIntl_passport_series1());

        TouristForm tourist2 = new TouristForm();
        tourist2.setSurname(tourists.getSurname2());
        tourist2.setName(tourists.getName2());
        tourist2.setMiddle_name(tourists.getMiddle_name2());
        tourist2.setDob(tourists.getDob2());
        tourist2.setPhone(tourists.getPhone2());
        tourist2.setEmail(tourists.getEmail2());
        tourist2.setIntl_passport_num(tourists.getIntl_passport_num2());
        tourist2.setIntl_passport_series(tourists.getIntl_passport_series2());

        TouristForm tourist3 = new TouristForm();
        tourist3.setSurname(tourists.getSurname3());
        tourist3.setName(tourists.getName3());
        tourist3.setMiddle_name(tourists.getMiddle_name3());
        tourist3.setDob(tourists.getDob3());
        tourist3.setPhone(tourists.getPhone3());
        tourist3.setEmail(tourists.getEmail3());
        tourist3.setIntl_passport_num(tourists.getIntl_passport_num3());
        tourist3.setIntl_passport_series(tourists.getIntl_passport_series3());

        TouristForm tourist4 = new TouristForm();
        tourist4.setSurname(tourists.getSurname4());
        tourist4.setName(tourists.getName4());
        tourist4.setMiddle_name(tourists.getMiddle_name4());
        tourist4.setDob(tourists.getDob4());
        tourist4.setPhone(tourists.getPhone4());
        tourist4.setEmail(tourists.getEmail4());
        tourist4.setIntl_passport_num(tourists.getIntl_passport_num4());
        tourist4.setIntl_passport_series(tourists.getIntl_passport_series4());

        TouristForm tourist5 = new TouristForm();
        tourist5.setSurname(tourists.getSurname5());
        tourist5.setName(tourists.getName5());
        tourist5.setMiddle_name(tourists.getMiddle_name5());
        tourist5.setDob(tourists.getDob5());
        tourist5.setPhone(tourists.getPhone5());
        tourist5.setEmail(tourists.getEmail5());
        tourist5.setIntl_passport_num(tourists.getIntl_passport_num5());
        tourist5.setIntl_passport_series(tourists.getIntl_passport_series5());

        TouristForm tourist6 = new TouristForm();
        tourist6.setSurname(tourists.getSurname6());
        tourist6.setName(tourists.getName6());
        tourist6.setMiddle_name(tourists.getMiddle_name6());
        tourist6.setDob(tourists.getDob6());
        tourist6.setPhone(tourists.getPhone6());
        tourist6.setEmail(tourists.getEmail6());
        tourist6.setIntl_passport_num(tourists.getIntl_passport_num6());
        tourist6.setIntl_passport_series(tourists.getIntl_passport_series6());

        TouristForm tourist7 = new TouristForm();
        tourist7.setSurname(tourists.getSurname7());
        tourist7.setName(tourists.getName7());
        tourist7.setMiddle_name(tourists.getMiddle_name7());
        tourist7.setDob(tourists.getDob7());
        tourist7.setPhone(tourists.getPhone7());
        tourist7.setEmail(tourists.getEmail7());
        tourist7.setIntl_passport_num(tourists.getIntl_passport_num7());
        tourist7.setIntl_passport_series(tourists.getIntl_passport_series7());

        TouristForm tourist8 = new TouristForm();
        tourist8.setSurname(tourists.getSurname8());
        tourist8.setName(tourists.getName8());
        tourist8.setMiddle_name(tourists.getMiddle_name8());
        tourist8.setDob(tourists.getDob8());
        tourist8.setPhone(tourists.getPhone8());
        tourist8.setEmail(tourists.getEmail8());
        tourist8.setIntl_passport_num(tourists.getIntl_passport_num8());
        tourist8.setIntl_passport_series(tourists.getIntl_passport_series8());

        TouristForm tourist9 = new TouristForm();
        tourist9.setSurname(tourists.getSurname9());
        tourist9.setName(tourists.getName9());
        tourist9.setMiddle_name(tourists.getMiddle_name9());
        tourist9.setDob(tourists.getDob9());
        tourist9.setPhone(tourists.getPhone9());
        tourist9.setEmail(tourists.getEmail9());
        tourist9.setIntl_passport_num(tourists.getIntl_passport_num9());
        tourist9.setIntl_passport_series(tourists.getIntl_passport_series9());

        tfl.add(tourist1);
        tfl.add(tourist2);
        tfl.add(tourist3);
        tfl.add(tourist4);
        tfl.add(tourist5);
        tfl.add(tourist6);
        tfl.add(tourist7);
        tfl.add(tourist8);
        tfl.add(tourist9);

        return tfl;

    }

    public static PersonInfo checkClient(ClientForm clientForm, List<PersonInfo> lpi){
        List<Boolean> checkList = new ArrayList<>();
        PersonInfo client = null;
        for (PersonInfo personInfo : lpi) {
            if (personInfo.getSurname().equals(clientForm.getSurname()) &&
                    personInfo.getName().equals(clientForm.getName()) &&
                    personInfo.getMiddle_name().equals(clientForm.getMiddle_name()) &&
                    personInfo.getDob().equals(LocalDate.parse(clientForm.getDob())) &&
                    personInfo.getPhone().equals(clientForm.getPhone()) &&
                    personInfo.getEmail().equals(clientForm.getEmail())) {
                checkList.add(true);
            }
            else {
                checkList.add(false);
            }
        }
        for (int i = 0; i < checkList.size(); i++){
            if (checkList.get(i)){
                client = lpi.get(i);
            }
        }
        return client;
    }

    public static List<Insurance> insuranceFormToList(InsuranceForm insuranceForm){
        List<Insurance> insuranceForms = new ArrayList<>();
        insuranceForms.add(insuranceForm.getInsurance1());
        insuranceForms.add(insuranceForm.getInsurance2());
        insuranceForms.add(insuranceForm.getInsurance3());
        insuranceForms.add(insuranceForm.getInsurance4());
        insuranceForms.add(insuranceForm.getInsurance5());
        insuranceForms.add(insuranceForm.getInsurance6());
        insuranceForms.add(insuranceForm.getInsurance7());
        insuranceForms.add(insuranceForm.getInsurance8());
        insuranceForms.add(insuranceForm.getInsurance9());

        return insuranceForms;
    }

    public static Integer generateNumber (int min, int max, Random r){
        return r.nextInt((max - min)+ 1) + min;
    }

    public static String generateNumberSequence(int length, Random r){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++){
            s.append(generateNumber(0, 9, r));
        }
        return s.toString();
    }

    public static String generatePolicyNumber(Random r) {
        String s;
        String symbols = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        int i = r.nextInt(symbols.length());
        return s = symbols.charAt(i) + generateNumberSequence(4, r) + "-" + generateNumberSequence(9, r);
    }
}
