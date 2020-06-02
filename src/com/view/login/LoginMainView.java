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

public class LoginMainView {
    JFrame fra_main  ;
    JLabel lab_user;
    JLabel lab_pwd;
    JTextField tf_user;
    JTextField tf_pwd;
    JButton btn_register;
    JButton btn_login;
    public static int LOGIN_RESULT = -1;

    public LoginMainView() {
        fra_main  =  new JFrame("软件可靠性建模与分析系统");
        fra_main.setLocationRelativeTo(null);
        lab_user = new JLabel("账号");
        tf_user = new JTextField(28);
        lab_pwd = new JLabel("密码");
        tf_pwd = new JTextField(28);

        fra_main.setLayout(new FlowLayout(FlowLayout.CENTER,15, 20));
        fra_main.add(BorderLayout.CENTER,lab_user);
        fra_main.add(BorderLayout.CENTER,tf_user);
        fra_main.add(BorderLayout.CENTER,lab_pwd);
        fra_main.add(BorderLayout.CENTER,tf_pwd);


        btn_register = new JButton("点击注册");
        fra_main.add(BorderLayout.CENTER, btn_register);
        btn_login = new JButton("点击登录");
        fra_main.add(btn_login);

        fra_main.setSize(400,250);
        //fra_main.setVisible(true);

        btn_register.addActionListener(actionEvent -> {
            RegisterView registerView = new RegisterView();
        });

        btn_login.addActionListener(actionEvent -> {
            String name = tf_user.getText();
            String pwd = tf_pwd.getText();
            LoginUtil.login(name, pwd);
            LOGIN_RESULT = LoginUtil.RESULT;
            System.out.println(LOGIN_RESULT);
        });
    }
    
    public JFrame getFrame()
    {
    	return fra_main;
    }
    
}
