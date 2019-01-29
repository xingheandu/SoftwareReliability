package com.view.login;
/*
 *Created by haseeon 2018/12/31.
 */


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.view.util.*;

public class RegisterView {
    JFrame fra_main  ;
    JLabel lab_user;
    JLabel lab_pwd;
    JLabel lab_again;
    JTextField tf_user;
    JTextField tf_pwd;
    JTextField tf_again;
    JButton btn_register;

    public RegisterView(){
        fra_main = new JFrame("用户注册");

        lab_user = new JLabel("输入账号");
        tf_user = new JTextField(28);
        lab_pwd = new JLabel("输入密码");
        tf_pwd = new JTextField(28);
        lab_again = new JLabel("确认密码");
        tf_again = new JTextField(28);

        btn_register = new JButton("点击注册");

        fra_main.setLayout(new FlowLayout(FlowLayout.CENTER,15, 20));
        fra_main.add(BorderLayout.CENTER,lab_user);
        fra_main.add(BorderLayout.CENTER,tf_user);
        fra_main.add(BorderLayout.CENTER,lab_pwd);
        fra_main.add(BorderLayout.CENTER,tf_pwd);
        fra_main.add(BorderLayout.CENTER,lab_again);
        fra_main.add(BorderLayout.CENTER,tf_again);
        fra_main.add(BorderLayout.CENTER,btn_register);
        fra_main.setSize(420,250);
        fra_main.setVisible(true);

        btn_register.addActionListener(actionEvent -> {
            String userName = tf_user.getText();
            String pwd= tf_pwd.getText();
            String pwdAgain = tf_again.getText();
            RegisterUtil registerUtil = new RegisterUtil(userName, pwd, pwdAgain);
        });
    }
}
