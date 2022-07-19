package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.ResultCode;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import java.io.BufferedReader;
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

        try (BufferedReader reader = getBufferedReader(pathFrom)) {
            fillCharMapFromFile(charsCount, reader);
        } catch (IOException e) {
            return new Result(ResultCode.ERROR, Strings.MESSAGE_FILE_READ_ERROR);
        }

        char maxChar = charWithMaxValue(charsCount);

        int spacePosition = Strings.ALPHABET.indexOf(" ");
        int spacePlusKeyPosition = Strings.ALPHABET.indexOf(maxChar);
        int key = spacePlusKeyPosition - spacePosition;

        Result result = copyWithKey(parameters[0], parameters[1], key);

        boolean validation = false;
        if (result.resultCode().equals(ResultCode.OK)) {
            try (BufferedReader reader = getBufferedReader(Path.of(PathFinder.getRoot() + parameters[1]))) {
                validation = isTextValid(reader);
            } catch (IOException e) {
                return new Result(ResultCode.ERROR, Strings.MESSAGE_FILE_READ_ERROR);
            }
        }

        if (validation) {
            return result;
        } else {
            return new Result(ResultCode.ERROR, Strings.MESSAGE_RESULT_VALIDATION_IS_FAIL);
        }

    }

    private boolean isTextValid(BufferedReader reader) throws IOException {
        Pattern pattern = Pattern.compile(DotOrCommaPlusSpace);
        Matcher matcher;
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

        return countOfValidationError < totalChar / 1000 && countOfValidationTrigger > totalChar / 1000;
    }

    private char charWithMaxValue(Map<Character, Integer> charsCount) {
        char maxChar = '\0';
        int maxValue = 0;
        for (var var : charsCount.entrySet()) {
            if (var.getValue() > maxValue) {
                maxValue = var.getValue();
                maxChar = var.getKey();
            }
        }
        return maxChar;
    }

    private void fillCharMapFromFile(Map<Character, Integer> charsCount, BufferedReader reader) throws IOException {
        char ch;
        while (reader.ready()) {
            ch = (char) reader.read();
            if (charsCount.containsKey(ch)) {
                charsCount.replace(ch, (charsCount.get(ch) + 1));
            } else {
                charsCount.put(ch, 0);
            }
        }
    }
}
