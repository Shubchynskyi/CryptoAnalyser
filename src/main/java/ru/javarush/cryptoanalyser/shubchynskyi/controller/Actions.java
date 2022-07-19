package ru.javarush.cryptoanalyser.shubchynskyi.controller;

import ru.javarush.cryptoanalyser.shubchynskyi.commands.*;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce()),
    CRYPTOANALYSIS(new CryptoAnalysis());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase()).action;

    }
}
