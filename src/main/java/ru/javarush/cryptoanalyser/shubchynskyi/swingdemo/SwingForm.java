package ru.javarush.cryptoanalyser.shubchynskyi.swingdemo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingForm extends JFrame{
    private JPanel mainpanel;
    private JTextArea textArea1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    private JTextField login;
    private JButton enter;
    private JLabel label;
    private JPasswordField password;

    public SwingForm() {
        initView();
        initListeners();
        this.setVisible(true);
    }

    private void initView() {
        this.setBounds(300, 300, 400, 200);
        this.add(mainpanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initListeners() {
        enter.addActionListener(e -> {
            String passwordTxt = new String(password.getPassword());
            if (passwordTxt.equals("qwerty")) {
                label.setText(login.getText() + " is ok");
            } else {
                label.setText("incorrect password");
            }
        });
    }


}
