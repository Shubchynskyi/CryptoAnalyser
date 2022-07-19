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

public interface Action {

    Result execute(String[] parameters);

    default Result copyWithKey(String source, String dest, int key) {

        Path pathSource = Path.of(PathFinder.getRoot() + source);
        Path pathDest = Path.of(PathFinder.getRoot() + dest);

        List<Character> alphabetWithKey = new ArrayList<>();
        for (char ch : Strings.ALPHABET.toCharArray()) {
            alphabetWithKey.add(ch);
        }
        Collections.rotate(alphabetWithKey, key);

        try (BufferedReader reader = getBufferedReader(pathSource); BufferedWriter writer = getBufferedWriter(pathDest)) {
            changeChars(alphabetWithKey, reader, writer);
        } catch (IOException e) {
            return new Result(ResultCode.ERROR, Strings.MESSAGE_IO_ERROR);
        }

        return new Result(ResultCode.OK, Strings.MESSAGE_RESULT_IS_OK + pathSource);
    }

    private void changeChars(List<Character> alphabetWithKey, BufferedReader reader, BufferedWriter writer) throws IOException {
        char ch;
        int index;
        while (reader.ready()) {
            ch = (char) reader.read();
            index = Strings.ALPHABET.indexOf(ch);
            if (index >= 0) {
                writer.write(alphabetWithKey.get(index));
            } else {
                writer.write(ch);
            }
        }
        writer.flush();
    }

    default BufferedWriter getBufferedWriter(Path pathDest) throws IOException {
        if (Files.notExists(pathDest)) {
            Files.createFile(pathDest);
        }
        return new BufferedWriter(new FileWriter(String.valueOf(pathDest)));
    }

    default BufferedReader getBufferedReader(Path pathSource) throws FileNotFoundException {
        return new BufferedReader(new FileReader(String.valueOf(pathSource)));
    }

}
