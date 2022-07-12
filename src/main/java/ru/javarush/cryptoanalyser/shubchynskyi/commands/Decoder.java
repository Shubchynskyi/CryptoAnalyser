package ru.javarush.cryptoanalyser.shubchynskyi.commands;

import ru.javarush.cryptoanalyser.shubchynskyi.constans.Strings;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;


public class Decoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String encryptedFile = parameters[0];
        String decryptedFile = parameters[1];
        int key = (Integer.parseInt(parameters[2]) % Strings.ALPHABET.length());
        return copyWithKey(encryptedFile, decryptedFile, (key * -1));
    }
}
