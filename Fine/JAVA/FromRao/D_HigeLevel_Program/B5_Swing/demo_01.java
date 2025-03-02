package grammar.D_HigeLevel_Program.B5_Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Swing：GUI为程序提供图形界面
    特点：
            轻量级组件，可以在所有平台上运行
            可插入外观组件
    类的继承关系：Java.lang.Object->Java.awt.Component->Java.awt.Container->Javax.swing.JComponent
    常用组件：
    JButton 代表Swing按钮，按钮可以带一些图片或文字
    JCheckBox   代表Swing中的复选框组件
    JComBox  代表下拉列表框，可以在下拉显示区域显示多个选项
    JFrame  代表Swing的框架类
    JDialog 代表Swing版本的对话框
    JLabel 待变Swing中的标签组件
    JRadioButton    代表Swing的单选按钮
    JList代表能够在用户界面中显示一系列条目的组件
    JTextField  代表文本框
    JPasswordField  代表密码框
    JTextArea   代表Swing中的文本区域
    JOptionPane 代表Swing中的一些对话框

*/
// 定义一个类继承JFrame
public class demo_01{

    public static void main(String[] args) {
        new TestJFrame().CreatJFrame("创建一个JFrame窗体");
    }

}
class TestJFrame extends JFrame{
    public void CreatJFrame(String title){
        //实例化一个JFrame对象
        JFrame jf = new JFrame(title);
        //获取一个容器
        Container container = jf.getContentPane();
        //实例化一个JLabel对象，创建一个JLabel标签
        JLabel jl = new JLabel("这是一个JFrame窗体");
        jl.setHorizontalAlignment(SwingConstants.CENTER);

        //将标签添加到容器中
        container.add(jl);
        //设置容器背景的颜色
        container.setBackground(Color.WHITE);
        //使窗体可视
        jf.setVisible(true);
        //设置窗口大小
        jf.setSize(200,150);
        //设置窗口的关闭方式
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
class TestJDialog extends JDialog{
    public TestJDialog(MyFrame myFrame) {
        //实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
        super(myFrame,"第一个JDialog窗体",true);
        Container container = getContentPane();
        container.add(new JLabel("这是一个对话框"));
        setBounds(120,120,100,100);
    }
}
class MyFrame extends JFrame{
    public void myFrame(){
        //创建一个容器
        Container container = getContentPane();
        container.setLayout(null);
        //在窗体中设置标签
        JLabel jl = new JLabel("这是一个JFrame窗体");
        //将标签的文字置于标签的中间位置
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jl);
        //定义一个按钮
        JButton jb = new JButton("弹出对话框");
        jb.setBounds(10,10,100,21);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TestJDialog(MyFrame.this).setVisible(true);
            }
        });
        //将标签添加到容器中
        container.add(jb);
        //设置容器背景的颜色
        container.setBackground(Color.WHITE);
        //使窗体可视
        jb.setVisible(true);
        //设置窗口大小
        jb.setSize(200,150);
        //设置窗口的关闭方式
    }
}