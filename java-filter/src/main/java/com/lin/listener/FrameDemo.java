package com.lin.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameDemo {
    public static void main(String[] args) {
        // 新建窗体
        Frame frame = new Frame("girls");
        // 新建面板
        Panel panel = new Panel(null);
        // 设置窗体布局
        frame.setLayout(null);
        // 设置坐标
        frame.setBounds(300,300,500,500);
        frame.setBackground(Color.lightGray);

        panel.setBounds(50,50,300,300);
        panel.setBackground(Color.WHITE);

        // 添加面板
        frame.add(panel);
        // 设置可见
        frame.setVisible(true);

        // 添加监听
        // 关闭事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
