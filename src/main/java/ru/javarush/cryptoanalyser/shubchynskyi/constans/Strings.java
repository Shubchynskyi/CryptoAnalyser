package ru.javarush.cryptoanalyser.shubchynskyi.constans;

public class Strings {
    private static final String rus = "йцукеёнгшщзхъфывапролджэячсмитьбю";
    private static final String eng = "qwertyuiopasdfghjklzxcvbnm";
    private static final String cyphers = "1234567890";
    private static final String symbols = "!@#$%^&*()_+-=[]{}:;',./<>|?№ ~";

    public static final String ALPHABET = rus + rus.toUpperCase()
                                        + eng + eng.toUpperCase()
                                        + cyphers + symbols;

    // TODO разделить алфавиты, добавить возможность выбора

}
