package com.java_swing_project.main.java.view;



import com.java_swing_project.main.java.domain.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUser extends JFrame {
    private JPanel createUserView;
    private JTextField fullNameTxtField;
    private JTextField emailTxtField;
    private JPasswordField passwordField;
    private JTextField phoneNumberTxtField;
    private JTextField addressTxtField;
    private JButton createUserButton;
    private JButton homePageButton;
    private JRadioButton ADMINRadioButton;
    private JRadioButton USERRadioButton;
    //private User user;
    //private UserService userService;

//    public CreateUser() {
//        user = new User();
//        userService = new UserService();
//        setSize(500, 500);
//        setTitle("Create a User");
//        setContentPane(createUserView);
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        createUserButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                user.setFullName(fullNameTxtField.getText());
//                user.setEmail(emailTxtField.getText());
//                String password = String.valueOf(passwordField.getPassword());
//                user.setPassword(password);
//                user.setPhoneNumber(phoneNumberTxtField.getText());
//                user.setAddress(addressTxtField.getText());
//                String role = "";
//                if (ADMINRadioButton.isSelected()) {
//                    role = "ADMIN";
//                }
//                if (USERRadioButton.isSelected()) {
//                    role = "USER";
//                }
//                int temp = userService.getUserRoleId(role);
//                System.out.println(temp);
//                System.out.println(userService.getUserRoleId(role));
//                user.setRole(userService.getUserRoleId(role));
//
//                userService.createUser(user.getFullName(), user.getEmail(), user.getPassword(),
//                        user.getPhoneNumber(), user.getAddress(), user.getRole());
//                System.out.println("create successfully");
//            }
//        });
//        homePageButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new Container().setVisible(true);
//                dispose();
//            }
//        });
//    }
//
}
