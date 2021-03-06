package ru.javarush.cryptoanalyser.shubchynskyi.controller;

import ru.javarush.cryptoanalyser.shubchynskyi.commands.Action;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.ResultCode;
import ru.javarush.cryptoanalyser.shubchynskyi.exception.ApplicationException;

public class MainController {

    public Result execute(String command, String[] parameters) {
        try {
            Action action = Actions.find(command);
            return action.execute(parameters);
        } catch (ApplicationException e) {
            return new Result(ResultCode.ERROR,e.getMessage());
        }
    }
}
