package ru.javarush.cryptoanalyser.shubchynskyi.topLevel;

import ru.javarush.cryptoanalyser.shubchynskyi.controller.MainController;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;

import java.util.Arrays;

public class Application {
    private final MainController mainController;


    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run(String[] args) {
        // TODO delete comments
        // encode text.txt encoded.txt 45

        String command = args[0]; // encode
        String[] parameters = Arrays.copyOfRange(args,1, args.length); // text.txt encoded.txt 45
        return mainController.execute(command,parameters);
    }


}
