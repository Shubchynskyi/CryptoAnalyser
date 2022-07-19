package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.ConsoleRunner;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.ResultCode;
import ru.javarush.cryptoanalyser.shubchynskyi.exception.ApplicationException;
import ru.javarush.cryptoanalyser.shubchynskyi.util.CharReplacer;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CryptoAnalysis implements Action {
    @Override
    public Result execute(String[] parameters) {
        String sourceAlphabet = textToAlphabet(parameters[0], false);
        String dictAlphabet = textToAlphabet(parameters[parameters.length-1], true);

        sourceAlphabet = trimAlphabet(sourceAlphabet, dictAlphabet);
        dictAlphabet = trimAlphabet(dictAlphabet, sourceAlphabet);
        char[] dictAlphabetChars = dictAlphabet.toCharArray();

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
            writer.close();
            reader.close();

            if (ConsoleRunner.isConsoleRunning) {
                Scanner console = new Scanner(System.in);
                while(true) {
                    System.out.println("Enter two characters to replace, change the first character to the second.\n" +
                            "\"exit\" to complete work" );
                    String firstString = console.next();
                    if (firstString.equals("exit")) {
                        break;
                    }
                    String secondString = console.next();
                    if (secondString.equals("exit")){
                        break;
                    }
                    if (CharReplacer.validateString(firstString) && CharReplacer.validateString(secondString)) {
                            CharReplacer.replaceLetter(pathDest, firstString.charAt(0), secondString.charAt(0));
                    } else {
                        System.out.println("Incorrect data, please re-enter.");
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Result(ResultCode.OK, "read all bytes " + pathSource);
    }

    private String trimAlphabet(String alphabet, String compareAlphabet) {
        if (alphabet.length() > compareAlphabet.length()) {
            alphabet = alphabet.substring(0, compareAlphabet.length());
        }
        return alphabet;
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
