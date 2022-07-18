package ru.javarush.cryptoanalyser.shubchynskyi;

import ru.javarush.cryptoanalyser.shubchynskyi.topLevel.Application;
import ru.javarush.cryptoanalyser.shubchynskyi.controller.MainController;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;

import java.util.Scanner;


public class ConsoleRunner {
    public static void main(String[] args) {
        if (args.length < 3) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\u001b[32m");
            System.out.println("Commands available:");
            System.out.print("\u001b[33m");
            System.out.println("\"Encode\" - encrypt from file to file using key");
            System.out.println("\"Decode\"- decrypt from file to file using key");
            System.out.println("\"Bruteforce\" - decrypt from file to file using brute force");
            System.out.println("\"CryptoAnalysis\" - decrypt from file to file using statistical analysis");
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
