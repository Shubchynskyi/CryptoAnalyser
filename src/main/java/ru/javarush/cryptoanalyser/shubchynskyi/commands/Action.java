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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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

        try {
            BufferedReader reader = getReader(pathSource);
            BufferedWriter writer = getBufferedWriter(pathDest);

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
            reader.close();
            writer.close();

        } catch (IOException e) {
            return new Result(ResultCode.ERROR, "I/O Error");
        }

        return new Result(ResultCode.OK, "read all bytes " + pathSource);
    }

    default BufferedWriter getBufferedWriter(Path pathDest) throws IOException {
        if (Files.notExists(pathDest)) {
            Files.createFile(pathDest);
        }
        return new BufferedWriter(new FileWriter(String.valueOf(pathDest)));
    }

    default BufferedReader getReader(Path pathSource) throws FileNotFoundException {
        return new BufferedReader(new FileReader(String.valueOf(pathSource)));
    }

}
