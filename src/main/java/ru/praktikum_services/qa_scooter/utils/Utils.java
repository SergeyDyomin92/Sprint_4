package ru.praktikum_services.qa_scooter.utils;

import com.google.common.collect.Interners;

import java.util.Random;


public class Utils {
    private static final int PHONE_NUMBER_LENGTH = 9;
    private static final int NAME_LENGTH = 6;

    public static String getRandomPhone() {
        String s = "123456789";
        StringBuffer phoneNumber = new StringBuffer();

        for (int i = 0; i < PHONE_NUMBER_LENGTH; i++) {
            phoneNumber.append(s.charAt(new Random().nextInt(s.length())));
        }
        return "79" + phoneNumber;
    }

    public static String getRandomCyrillicText() {
        String s = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ";
        StringBuffer text = new StringBuffer();

        for (int i = 0; i < NAME_LENGTH; i++) {
            text.append(s.charAt(new Random().nextInt(s.length())));
        }
        return text.toString();
    }
}
