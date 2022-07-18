package ru.javarush.cryptoanalyser.shubchynskyi.view.swing;

import javax.swing.*;

public class MainForm extends JFrame{
    private JPanel mainpanel;
    private JButton analysisButton;
    private JTextField charFrom;
    private JTextArea charTo;
    private JButton bruteForceButton;
    private JButton decodeButton;
    private JButton replaceButton;
    private JTextField аTextField1;
    private JButton encodeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton helpButton;

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
