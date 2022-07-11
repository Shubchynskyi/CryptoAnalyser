package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;

public interface Action {

    Result execute(String[] parameters);

}
