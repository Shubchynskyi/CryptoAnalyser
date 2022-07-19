package ru.javarush.cryptoanalyser.shubchynskyi.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CharReplacer {
    public static void replaceLetter(Path pathDest, char firstChar, char secondChar) throws IOException {
        Path tmp = Path.of(pathDest.getParent().toString() + "tmp.txt");
        Files.copy(pathDest, tmp, REPLACE_EXISTING);

        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(tmp)));
        if (Files.notExists(pathDest)) {
            Files.createFile(pathDest);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathDest)));
        char ch;
        while (reader.ready()) {
            ch = (char) reader.read();
            if (ch != firstChar && ch != secondChar) {
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

    public static boolean validateString(String str) {
        return str != null && str.length() == 1;
    }

}
