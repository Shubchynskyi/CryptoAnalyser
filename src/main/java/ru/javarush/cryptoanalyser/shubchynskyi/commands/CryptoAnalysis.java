package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.ResultCode;
import ru.javarush.cryptoanalyser.shubchynskyi.exception.ApplicationException;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CryptoAnalysis implements Action {
    @Override
    public Result execute(String[] parameters) {
        //TODO вынести замену буквы в интерфейс, валидацию в замену буквы
        //0 - источник, 1 - назначение, 2 - ключ(тут он не нужен) 3 - образец текста

        String sourceAlphabet = textToAlphabet(parameters[0], false);
        String dictAlphabet = textToAlphabet(parameters[parameters.length-1], true);

        sourceAlphabet = trimAlphabet(sourceAlphabet, dictAlphabet);
        dictAlphabet = trimAlphabet(dictAlphabet, sourceAlphabet);
        char[] dictAlphabetChars = dictAlphabet.toCharArray();

        System.out.println(sourceAlphabet);
        System.out.println(dictAlphabetChars);

        Path pathSource = Path.of(PathFinder.getRoot() + parameters[0]);
        Path pathDest = Path.of(PathFinder.getRoot() + parameters[1]);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(pathSource)));
            if (Files.notExists(pathDest)) {
                Files.createFile(pathDest);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathDest)));

            char ch;
            int index;
            while (reader.ready()) {
                ch = (char) reader.read();
                index = sourceAlphabet.indexOf(ch);
                if (index >= 0) {
                    writer.write(dictAlphabetChars[index]);
                } else {
                    writer.write(ch);
                }
            }
            writer.flush();

            Scanner console = new Scanner(System.in);

            while(true) {
                System.out.println("Введите два символа для замены, первый символ меняем на второй.\n" +
                        "\"exit\" для завершения работы" );
                String firstString = console.next();
                if (firstString.equals("exit")) {
                    break;
                }
                String secondString = console.next();
                if (secondString.equals("exit")){
                    break;
                }
                if (validateString(firstString) && validateString(secondString)) {
                    replaceLetter(pathDest, firstString.charAt(0), secondString.charAt(0));
                } else {
                    System.out.println("Некорректные данные, повторите ввод.");
                }
            }

            writer.flush();
            writer.close();
            reader.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Result(ResultCode.OK, "");
    }

    private boolean validateString(String str) {
        return str != null && str.length() == 1;
    }

    private String trimAlphabet(String alphabet, String compareAlphabet) {
        if (alphabet.length() > compareAlphabet.length()) {
            alphabet = alphabet.substring(0, compareAlphabet.length());
        }
        return alphabet;
    }

    void replaceLetter(Path pathDest, char firstChar, char secondChar) throws IOException {
        Path tmp = Path.of(pathDest.getParent().toString()+"tmp.txt");
        Files.copy(pathDest,tmp, REPLACE_EXISTING);

        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(tmp)));
        if (Files.notExists(pathDest)) {
            Files.createFile(pathDest);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathDest)));
        char ch;
        while (reader.ready()){
            ch = (char) reader.read();
            if (ch != firstChar && ch != secondChar){
                writer.write(ch);
            } else if (ch == firstChar) {
                writer.write(secondChar);
            } else {
                writer.write(firstChar);
            }

        }
        writer.flush();
        writer.close();
        reader.close();
        Files.deleteIfExists(tmp);
    }


    private String textToAlphabet(String sourceText, boolean lowRegister) {
        Path pathFrom = Path.of(PathFinder.getRoot() + sourceText);
        HashMap<Character, Integer> charsCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFrom.toString()))) {
            while (reader.ready()) {
                String stringline = reader.readLine();
                if (lowRegister) {
                    stringline = stringline.toLowerCase();
                }
                for (char ch : stringline.toCharArray()) {
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

        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(charsCount.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        StringBuilder result = new StringBuilder();
        for (var var : list) {
            result.append(var.getKey());
        }

        return result.toString();
    }
}
