package com.java_swing_project.main.java.view;


import com.java_swing_project.main.java.domain.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUser extends JFrame {
    private JPanel updateUserView;
    private JTextField fullNameTxtField;
    private JTextField addressTxtField;
    private JButton updateUserButton;
    private JButton homePageButton;
    private JTextField emailTxtField;
    private JTextField phoneNumberTxtField;
    private JRadioButton ADMINRadioButton;
    private JRadioButton USERRadioButton;
//    private final UserService userService;
//    private final User user;

//    public UpdateUser(int id) {
//        userService = new UserService();
//        setContentPane(updateUserView);
//        setSize(500, 600);
//        setVisible(true);
//        user = userService.getUserById(id);
//        fullNameTxtField.setText(user.getFullName());
//        emailTxtField.setText(user.getEmail());
//        phoneNumberTxtField.setText(user.getPhoneNumber());
//        addressTxtField.setText(user.getAddress());
//        if (user.getRole() == 1) {
//            ADMINRadioButton.setSelected(true);
//        } else USERRadioButton.setSelected(true);
//
//        updateUserButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//                user.setFullName(fullNameTxtField.getText());
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
//                System.out.println(userService.getUserRoleId(role));
//                user.setRole(userService.getUserRoleId(role));
//
//                userService.updateUser(user.getFullName(),
//                        user.getPhoneNumber(), user.getAddress(), user.getRole(), id);
//                System.out.println("Update successfully");
//            }
//        });
//        homePageButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//    }
}
