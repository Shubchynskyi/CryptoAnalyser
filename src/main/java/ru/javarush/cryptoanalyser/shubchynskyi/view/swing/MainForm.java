package ru.javarush.cryptoanalyser.shubchynskyi.view.swing;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    private JPanel mainpanel;
    private JPanel top;
    private JPanel left;
    private JPanel bottom;
    private JPanel right;
    private JPanel center;
    private JTextArea textArea1;
    private JTextField textField1;
    private JButton button2;
    private JButton button1;
    private JButton button4;
    private JButton button3;
    private JButton button5;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton button6;

    public MainForm() {
        initView();
        this.setVisible(true);
    }

    private void initView() {
        this.setBounds(300, 300, 500, 300);
        this.add(mainpanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
