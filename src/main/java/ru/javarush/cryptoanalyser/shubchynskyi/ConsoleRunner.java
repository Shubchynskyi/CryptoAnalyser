package ru.javarush.cryptoanalyser.shubchynskyi;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.topLevel.Application;
import ru.javarush.cryptoanalyser.shubchynskyi.controller.MainController;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;

import java.util.Scanner;


public class ConsoleRunner {

    public static boolean isConsoleRunning = false;

    public static void main(String[] args) {
        isConsoleRunning = true;
        if (args.length < 3) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\u001b[32m");
            System.out.println("Commands available:");
            System.out.print("\u001b[33m");
            System.out.println(Strings.ENCODE_INFO);
            System.out.println(Strings.DECODE_INFO);
            System.out.println(Strings.BRUTEFORCE_INFO);
            System.out.println(Strings.CRYPTANALYSIS_INFO);
            System.out.println("\u001b[32m");
            System.out.println("Enter the following parameters separated by a space:");
            System.out.print("\u001b[33m");
            System.out.println("Command, Source filename, Destination filename, Key(if needed), Dictionary(if needed)");
            System.out.print("\u001b[0m");
            args = scanner.nextLine().split(" ",5);
        }

        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);
        System.out.println(result);
    }
}
