package com.view.util;


import com.view.login.NoticeDialog;
import com.view.login.UserBean;

/*
 *Created by haseeon 2018/12/31.
 */




public class RegisterUtil {
    public RegisterUtil(String userName, String pwd, String pwdAgain) {
        if ( ! pwd.equals(pwdAgain)){
            NoticeDialog noticeDialog = new NoticeDialog("二次密码不同");
        }
        else {
            UserBean userBean = DBConnectUtil.selectUser(userName);
            if ( userBean == null || !userBean.getName().equals(userName)){
                int i = DBConnectUtil.addUser(userName, pwd);
                if (i != 0){
                    new NoticeDialog("注册成功");
                }
                else {
                    new NoticeDialog("注册失败");
                }
            }
            else {
                new NoticeDialog("用户已存在");
            }
        }

    }
}
