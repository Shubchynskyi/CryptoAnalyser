package ru.javarush.cryptoanalyser.shubchynskyi.util;

import java.io.File;

public class PathFinder {
    private PathFinder(){}

    public static String getRoot() {
        String root = System.getProperty("user.dir");
        return root + File.separator + "text" + File.separator;
    }
}
