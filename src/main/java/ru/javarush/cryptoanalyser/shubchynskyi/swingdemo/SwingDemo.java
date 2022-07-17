package ru.javarush.cryptoanalyser.shubchynskyi.swingdemo;

import javax.swing.*;

public class SwingDemo extends JFrame{

    private JPanel mainPanel;
    private JLabel label;
    private JTextField login;
    private JPasswordField password;
    private JButton enter;

    public SwingDemo() {

        mainPanel = new JPanel();
        this.add(mainPanel);
        this.setBounds(300, 300, 400, 200);
        label = new JLabel("label");
        login = new JTextField(10);
        password = new JPasswordField(10);
        enter = new JButton("Enter");

        mainPanel.add(label);
        mainPanel.add(login);
        mainPanel.add(password);
        mainPanel.add(enter);

        enter.addActionListener(e -> {
            String passwordTxt = new String(password.getPassword());
            if (passwordTxt.equals("qwerty")) {
                label.setText(login.getText() + " is ok");
            } else {
                label.setText("incorrect password");
            }
        });

        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
