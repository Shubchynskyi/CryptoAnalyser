package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.ResultCode;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BruteForce implements Action {
    public static final String DotOrCommaPlusSpace = "(\\.\\s)|(,\\s)";

    @Override
    public Result execute(String[] parameters) {

        String encryptedFile = parameters[0];
        Path pathFrom = Path.of(PathFinder.getRoot() + encryptedFile);

        Map<Character, Integer> charsCount = new HashMap<>();
        char ch;

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFrom.toString()))) {
            while (reader.ready()) {
                ch = (char) reader.read();
                if (charsCount.containsKey(ch)) {
                    charsCount.replace(ch, (charsCount.get(ch) + 1));
                } else {
                    charsCount.put(ch, 0);
                }
            }

        } catch (IOException e) {
            return new Result(ResultCode.ERROR, "File read error");
        }

        char maxChar = '\0';
        int maxValue = 0;
        for (var var : charsCount.entrySet()) {
            if (var.getValue() > maxValue) {
                maxValue = var.getValue();
                maxChar = var.getKey();
            }
        }

        int spacePosition = Strings.ALPHABET.indexOf(" ");
        int spacePlusKeyPosition = Strings.ALPHABET.indexOf(maxChar);
        int key = spacePlusKeyPosition - spacePosition;

        Result result = copyWithKey(parameters[0], parameters[1], key);

        boolean validation = false;
        if (result.getResultCode().equals(ResultCode.OK)) {
            Pattern pattern = Pattern.compile(DotOrCommaPlusSpace);
            Matcher matcher;
            try (BufferedReader reader = new BufferedReader(
                    new FileReader((Path.of(PathFinder.getRoot() + parameters[1])).toString()))) {

                int totalChar = 0;
                int countOfValidationTrigger = 0;
                int countOfValidationError = 0;
                while (reader.ready()) {
                    String str = reader.readLine();
                    String[] words = str.split("\s");
                    for (String word : words) {
                        if (word.length() > 0 && Strings.INVALIDATORS.indexOf(word.charAt(0)) > 0) {
                            countOfValidationError++;
                        }
                    }
                    totalChar = totalChar + str.length();
                    matcher = pattern.matcher(str);
                    if (matcher.find()) {
                        countOfValidationTrigger++;
                    }
                }

                if (countOfValidationError < totalChar / 1000 && countOfValidationTrigger > totalChar / 1000) {
                    validation = true;
                }

            } catch (IOException e) {
                return new Result(ResultCode.ERROR, "File read error");
            }
        }

        if (validation) {
            return result;
        } else {
            return new Result(ResultCode.ERROR, "Validation if fail!");
        }

    }
}
