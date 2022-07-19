package ru.javarush.cryptoanalyser.shubchynskyi;

import ru.javarush.cryptoanalyser.shubchynskyi.controller.MainController;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.topLevel.Application;
import ru.javarush.cryptoanalyser.shubchynskyi.view.console.ConsoleMenu;

import java.util.Scanner;

public class ConsoleRunner {

    public static boolean isConsoleRunning = false;

    public static void main(String[] args) {
        isConsoleRunning = true;
        if (args.length < 3) {
            Scanner scanner = new Scanner(System.in);
            ConsoleMenu.initStartMenu();
            args = scanner.nextLine().split(" ", 5);
        }

        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);
        System.out.println(result);
    }

}
