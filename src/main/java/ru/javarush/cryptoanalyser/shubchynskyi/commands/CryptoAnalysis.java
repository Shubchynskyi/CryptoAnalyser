package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.exception.ApplicationException;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CryptoAnalysis implements Action {
    @Override
    public Result execute(String[] parameters) {
        // параметры:
        // 0 - источник, 1 - назначение, 2 - ключ(тут он не нужен) 3 - образец текста
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


    private TreeMap<Character, Integer> hashToTree(Map<Character, Integer> hashMap) {
//        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        //TODO logic


        return null;
    }


    private Map<Character, Integer> textToHashMap(String sourceText) {
        //TODO переводим HashMap в TreeMap - может быть отдельным методом?
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

        return charsCount;
    }

}
