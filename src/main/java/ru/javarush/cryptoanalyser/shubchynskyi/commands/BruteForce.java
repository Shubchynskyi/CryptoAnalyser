package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.nio.file.Path;

public class BruteForce implements Action {
    @Override
    public Result execute(String[] parameters) {
        //TODO logic
        // посчитать количество каждого символа во входящем файле и добавить в HashMap,
        // ключ - количество или % символов
        // сравниваем самый частый символ с пробелом и находим ключ,
        // после расшифровки проверяем есть ли пробелы после точек и запятых, есть ли большие буквы после точек

        // сюда заходят параметры [0]encrypted.txt [1]decrypted.txt [2]123 ключ,
        String encryptedFile = parameters[0];
        Path pathFrom = Path.of(PathFinder.getRoot() + encryptedFile); //путь к файлу который читаем

        String decryptedFile = parameters[1];
        Path pathTo = Path.of(PathFinder.getRoot() + decryptedFile); //путь к файлу в который пишем

        int key = Integer.parseInt(parameters[2]) % Strings.ALPHABET.length();

        // опционально - не начинаются ли слова на ъ ь ы, регулярное выражение на поиск слов " или " ", но " ", не" " не "
        // поиск вхождений через Matcher / Pattern

        // можно вынести метод декодирования декодера в метод и общаться к нему в брутфорсе (не возвращая результат из декодера)


        throw new UnsupportedOperationException();
    }
}
