package ru.javarush.cryptoanalyser.shubchynskyi.view.console;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.util.CharReplacer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleMenu {

    private final static String COLOR_GREEN = "\u001b[32m";
    private final static String COLOR_YELLOW = "\u001b[33m";
    private final static String COLOR_WHITE = "\u001b[0m";

    private final static String ENTER_CHARS = "Enter two characters to replace, change the first character to the second.\n" +
            "\"exit\" to complete work";
    private final static String EXIT = "exit";
    private final static String INCORRECT = "Incorrect data, please re-enter.";


    public static void consoleMenuForCharReplacer(Path pathDest) throws IOException {
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.println(ENTER_CHARS);
            String firstString = console.next();
            if (firstString.equals(EXIT)) {
                break;
            }
            String secondString = console.next();
            if (secondString.equals(EXIT)) {
                break;
            }
            if (CharReplacer.validateString(firstString) && CharReplacer.validateString(secondString)) {
                CharReplacer.replaceLetter(pathDest, firstString.charAt(0), secondString.charAt(0));
            } else {
                System.out.println(INCORRECT);
            }
        }
    }

    public static void initStartMenu() {
        System.out.println(COLOR_GREEN + "Commands available:");
        System.out.println(COLOR_YELLOW + Strings.ENCODE_INFO);
        System.out.println(Strings.DECODE_INFO);
        System.out.println(Strings.BRUTEFORCE_INFO);
        System.out.println(Strings.CRYPTANALYSIS_INFO + "\n");
        System.out.println(COLOR_GREEN + "Enter the following parameters separated by a space:");
        System.out.println(COLOR_YELLOW + "Command, Source filename, Destination filename, Key(if needed), Dictionary(if needed)");
        System.out.println(COLOR_WHITE);
    }
}
