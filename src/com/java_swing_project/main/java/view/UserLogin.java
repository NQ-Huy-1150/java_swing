package com.java_swing_project.main.java.view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserLogin extends JFrame {
    private JPanel userLoginView;
    private JTextField emailLoginField;
    private JPasswordField passwordLoginField;
    private JButton loginButton;
    private JPanel div;
    //private final UserService userService;

//    public UserLogin() {
//        userService = new UserService();
//        setContentPane(div);
//        setSize(800, 600);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String email = emailLoginField.getText();
//                String password = passwordLoginField.getText();
//                if (email.equals("") || password.equals("")) {
//                    JOptionPane.showMessageDialog(null, "Thong tin khong duoc bo trong !");
//                } else {
//                    boolean flag = false;
//
//                    List<User> users = userService.getAllUser();
//                    for (User user : users) {
//                        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                            flag = true;
//                        }
//                    }
//                    if (flag) {
//                        new Container().setVisible(true);
//                        dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "email hoac mat khau sai !");
//                    }
//                }
//
//            }
//        });
//    }
}
