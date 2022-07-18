package ru.javarush.cryptoanalyser.shubchynskyi.view.swing;

import javax.swing.*;

public class MainForm extends JFrame{
    private JPanel mainpanel;
    private JTextArea textTo;
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


    public MainForm() {
        initView();
        this.setVisible(true);
    }

    private void initView() {
        this.setBounds(300, 300, 800, 600);
        this.add(mainpanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
