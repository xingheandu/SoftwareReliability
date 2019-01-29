package com.view.util;


import javax.swing.JOptionPane;

import com.global.GlobalVar;
import com.view.login.NoticeDialog;
import com.view.login.UserBean;
import com.view.main.Main;
import com.view.main.MainView;

/*
 *Created by haseeon 2018/12/31.
 */



public class LoginUtil {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	public static int RESULT = 0;
    public static void login(String name, String pwd) {
        UserBean userBean = new UserBean();
        userBean = DBConnectUtil.selectUser(name);
        if (userBean.getPassword().equals(pwd)){
            //new NoticeDialog("登录成功");
            //new LoginSuccess();
        	RESULT = SUCCESS;
        	System.out.println(RESULT);
        	GlobalVar.currentUserName = name;
        	Main.runProgram();
        }
        else {
            //new NoticeDialog("密码或用户名错误,登录失败");
        	RESULT = FAIL;
        	System.out.println(RESULT);
            JOptionPane.showMessageDialog(null, "密码或用户名错误,登录失败", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }
}
