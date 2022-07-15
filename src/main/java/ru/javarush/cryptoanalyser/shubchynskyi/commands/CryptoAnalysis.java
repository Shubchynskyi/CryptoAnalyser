package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.exception.ApplicationException;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CryptoAnalysis implements Action {
    @Override
    public Result execute(String[] parameters) {
        // параметры:
        // 0 - источник, 1 - назначение, 2 - ключ(тут он не нужен) 3 - образец текста
        NavigableMap<Integer, Character> sourceHashMap = textToHashMap(parameters[0]);
        NavigableMap<Integer, Character> dictHashMap = textToHashMap(parameters[3]);

        Path pathSource = Path.of(PathFinder.getRoot() + parameters[0]);
        Path pathDest = Path.of(PathFinder.getRoot() + parameters[1]);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(pathSource)));
            if (Files.notExists(pathDest)) {
                Files.createFile(pathDest);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathDest)));

            while (reader.ready()) {
                replaceLetter(reader, writer, sourceHashMap.pollLastEntry().getValue(), dictHashMap.pollLastEntry().getValue());
            }

            writer.flush();
            reader.close();
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //TODO
        // читаем символы из текста донора в hashmap, предварительно приводим к маленьким буквам - можно вынести в метод
        // преобразуем в treemap ИЛИ через итератор обходим, находим наибольшее значение (процент символа в тексте)
        // и такое же наибольшее значение в тексте который надо расшифровать и через МЕТОД замены символа


        throw new UnsupportedOperationException();
    }

    // метод - получаем из HashMap ключ максимального значения, возвращаем и удаляем его из карты
    // метод - берем максимальный ключ из текста донора и максимальный текст из текса зашифрованного
    // и передаем в метод, который меняет в тексте источнике символ на другой
    // либо hashMap в treeMap, забираем верхнее значение и удаляем его, потом передаем значения в метод

    private void replaceLetter(BufferedReader reader, BufferedWriter writer, char from, char to) throws IOException {
        String line = reader.readLine();
        line = line.replace(from, to);
        writer.write(line + "\n");
        writer.flush();
    }


    private TreeMap<Integer, Character> hashToTree(Map<Character, Integer> hashMap) {
        TreeMap<Integer, Character> treeMap = new TreeMap<>();

        for (var var : hashMap.entrySet()) {
            treeMap.put(var.getValue(), var.getKey());
        }

        return treeMap;
    }


    private TreeMap<Integer, Character> textToHashMap(String sourceText) {
        Path pathFrom = Path.of(PathFinder.getRoot() + sourceText);
        Map<Character, Integer> charsCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFrom.toString()))) {
            while (reader.ready()) {
                String stringline = reader.readLine();
                for (char ch : stringline.toLowerCase().toCharArray()) {
                    if (charsCount.containsKey(ch)) {
                        charsCount.replace(ch, (charsCount.get(ch) + 1));
                    } else {
                        charsCount.put(ch, 0);
                    }
                }
            }
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return hashToTree(charsCount);
    }

}
