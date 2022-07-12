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

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String sourceTextFile = parameters[0];
        String encryptedFile = parameters[1];
        int key = Integer.parseInt(parameters[2]) % Strings.ALPHABET.length();
        return copyWithKey(sourceTextFile, encryptedFile, key);
    }
}
