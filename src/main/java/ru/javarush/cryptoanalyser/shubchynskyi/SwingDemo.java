package ru.javarush.cryptoanalyser.shubchynskyi;

import javax.swing.*;
import java.awt.*;

public class SwingDemo {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("NewApp");
        JPanel mainPanel = new JPanel();
        mainFrame.add(mainPanel);
        mainFrame.setBounds(300,300 ,400, 200);
        JLabel label = new JLabel("lable");
        JTextField login = new JTextField(10);
        JPasswordField password = new JPasswordField(10);
        JButton enter = new JButton("Enter");
        mainPanel.add(label);
        mainPanel.add(login);
        mainPanel.add(password);
        mainPanel.add(enter);

//        enter.addActionListener();

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        mainFrame.setLocationRelativeTo(null);
//        frame.setLayout(new BoxLayout());
        mainFrame.setResizable(true);



        JButton button1 = new JButton("button1");
        JButton button2 = new JButton("button2");
        JButton button3 = new JButton("button3");
        JButton button4 = new JButton("button4");
        JButton button5 = new JButton("button5");

        mainFrame.setVisible(true);
    }
}
