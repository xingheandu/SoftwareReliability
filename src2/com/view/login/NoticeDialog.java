package com.view.login;
/*
 *Created by haseeon 2018/12/31.
 */


import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NoticeDialog {
    JFrame fra_main ;
    JLabel lab_notice;
    public NoticeDialog(String notice) {
        fra_main = new JFrame("提示");
        lab_notice = new JLabel(notice);
        fra_main.add(lab_notice);
        fra_main.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));
        fra_main.setSize(300,200);
        fra_main.setVisible(true);
    }
}
