package ru.javarush.cryptoanalyser.shubchynskyi;

import ru.javarush.cryptoanalyser.shubchynskyi.topLevel.Application;
import ru.javarush.cryptoanalyser.shubchynskyi.controller.MainController;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;

public class Runner {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        // TODO delete comment
        // параметры
        // команда фаилИсточник зашифрованныйФаил ключ
        // encode text.txt encoded.txt 45
        Application application = new Application(mainController);
        Result result = application.run(args);
        System.out.println(result);








        /*
        TODO delete comment
        получить на вход массив стрингов из args
        это единственная точка входа с методом Run
        отсюда передаем в контроллер и уже он решает что делать дальше /Main controller
         Main controller должен вернуть Result (как класс), он определяет какая команда поступила и в зависимости
        от этого использует команду (commands), каждая из которых возвращает свой Result контроллеру
        команды имеют общий интерфейс с методом типа Result

         */

    }

}
