package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.ResultCode;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Decoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        // сюда заходят параметры [0]encrypted.txt [1]decrypted.txt [2]123 ключ
        String encryptedFile = parameters[0];
        Path pathFrom = Path.of(PathFinder.getRoot() + encryptedFile); //путь к файлу который читаем

        String decryptedFile = parameters[1];
        Path pathTo = Path.of(PathFinder.getRoot() + decryptedFile); //путь к файлу в который пишем

        int key = Integer.parseInt(parameters[2]) % Strings.ALPHABET.length();


        // делаем лист Character и сдвигаем на ключ
        List<Character> characters = new ArrayList<>();
        for (char ch : Strings.ALPHABET.toCharArray()) {
            characters.add(ch);
        }
        Collections.rotate(characters, key);


        // открываем потоки, читаем символ и записываем со сдвигом
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(pathFrom)));
            if (Files.notExists(pathTo)) {
                Files.createFile(pathTo);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathTo)));

            char ch;
            int index;
            while (reader.ready()) {
                ch = (char) reader.read();
                index = characters.indexOf(ch);
                if (index >= 0) {
                    writer.write(Strings.ALPHABET.charAt(index));
                } else {
                    writer.write(ch);
                }
            }
            writer.flush();
            reader.close();
            writer.close();

        } catch (IOException e) {
            return new Result(ResultCode.ERROR, "I/O Error");
        }

        return new Result(ResultCode.OK, "read all bytes " + pathFrom); //возвращаем результат
    }
}
