package ru.javarush.cryptoanalyser.shubchynskyi.view.swing;

import ru.javarush.cryptoanalyser.shubchynskyi.controller.MainController;
import ru.javarush.cryptoanalyser.shubchynskyi.entity.Result;
import ru.javarush.cryptoanalyser.shubchynskyi.exception.ApplicationException;
import ru.javarush.cryptoanalyser.shubchynskyi.topLevel.Application;
import ru.javarush.cryptoanalyser.shubchynskyi.util.PathFinder;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainForm extends JFrame{
    private JPanel mainpanel;
    private JTextField charFrom;
    private JTextField charTo;
    private JButton analysisButton;
    private JButton bruteForceButton;
    private JButton decodeButton;
    private JButton replaceButton;
    private JButton encodeButton;
    private JTextField dictField;
    private JTextField sourceField;
    private JTextField destField;
    private JTextField keyField;
    private JButton helpButton;
    private JTextArea infoArea;
    private JTextPane textTo;


    private final String ENCODE = "encode";
    private final String DECODE = "decode";
    private final String BRUTEFORCE = "bruteforce";
    private final String CRYPTANALYSIS = "cryptoanalysis";


    public MainForm() {
        initView();
        initListeners();
        this.setVisible(true);
    }

    private void initListeners() {

        encodeButton.addActionListener(e -> {
            run(new String[]{ENCODE, sourceField.getText(), destField.getText(), keyField.getText()});
            fileToTextArea(destField.getText());
        });

        decodeButton.addActionListener(e -> {
            run(new String[]{DECODE, sourceField.getText(), destField.getText(), keyField.getText()});
            fileToTextArea(destField.getText());
        });

        bruteForceButton.addActionListener(e -> {
            run(new String[]{BRUTEFORCE, sourceField.getText(), destField.getText()});
            fileToTextArea(destField.getText());
        });

        analysisButton.addActionListener(e -> {
            run(new String[]{CRYPTANALYSIS, sourceField.getText(), destField.getText(), dictField.getText()});
            fileToTextArea(destField.getText());
        });
    }

    private void fileToTextArea(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PathFinder.getRoot() + fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (reader.ready()) {
                stringBuilder.append((char)reader.read());
            }
            textTo.setText(stringBuilder.toString());

        } catch (IOException e) {
            textTo.setText("File read error: \n" + e.getMessage());
            throw new ApplicationException();
        }
    }

    private void initView() {
        this.setBounds(300, 300, 600, 400);
        this.add(mainpanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        textTo.setAutoscrolls(true);
    }
    //TODO исправить
    private void run(String[] args){
        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);
        infoArea.setText(result.getMessage());
    }



}
