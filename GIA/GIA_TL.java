package com.func;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.text.SimpleDateFormat;
import java.util.*;


public class GIA_TL {
	String APIKEY = "5b57e69405ac4db68b86a21a1fe4435b"; 
    Scanner scaner = new Scanner(System.in);
    
	boolean zck_sm = true;
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    static Date day=new Date();
    static String[] dat = (df.format(day)).split(" ");
    static String data = dat[1];
    static String[] dat1 = data.split(":");
    
    
    static String time_E1(int dat12) {
    	//System.out.println(dat12);
    	if(dat12 >= 5 && dat12 < 10){
        	data = "充实的一天，才开始？";
        }else if(dat12 >= 10 && dat12 < 14){
        	data = "上午，是补充知识的时间！";
        }else if(dat12 >= 14 && dat12 < 18){
        	data = "下午，又到了补充知识的时间！";
        }else if(dat12 >= 18 && dat12 < 20){
        	data = "是时候吃饭了！";
        }else if(dat12 >= 20 && dat12 < 23){
        	data = "晚上，还是要补充知识呢！";
        }else if(dat12 >= 23 || dat12 < 4){
        	data = "是睡觉的时间了！";
        }else{
        	data = "哇，现在这个点还能睡会呢";
        }
    	return data;
	}
    
    
    private JTextField jtf = new JTextField("在这里输入聊天信息", 30);    // 创建文本行组件, 30 列
    private JTextArea jta = new JTextArea("你好，这里是GIA管理系统下的图灵聊天软件\n"+time_E1(Integer.parseInt(dat1[0]))+"\n", 10, 30);               // 创建文本区组件,10行，30列
    private JScrollPane jsp = new JScrollPane(jta);                    // 创建滚动窗格，其显示内容是文本区对象
    
    private JTextArea kztwb = new JTextArea("你好，这里是控制台\n", 10, 30);               // 创建文本区组件,10行，30列
    private JScrollPane kztck = new JScrollPane(kztwb);                    // 创建滚动窗格，其显示内容是文本区对象
    
    private JButton bt = new JButton("发送");
    private JLabel label = new JLabel();
    private ImageIcon img = new ImageIcon("images/GK.png");// 创建图片对象
    private Image icon = Toolkit.getDefaultToolkit().getImage("images/logo1.png");  //设置图标
    /*
     * 创建一个菜单栏
     */
    private JMenuBar menuBar = new JMenuBar();

    /*
     * 创建一级菜单
     */
    private JMenu xx = new JMenu("选项");
    private JMenu ck = new JMenu("窗口");
    private JMenu gy = new JMenu("关于");
    private JMenu bz = new JMenu("帮助");
    // 一级菜单添加到菜单栏
    /*
     * 创建 "编辑" 一级菜单的子菜单
     */
    private JMenuItem xx_gl = new JMenuItem("管理子系统");
    private JMenuItem ck_kzt = new JMenuItem("控制台");
    private JMenuItem gy_zl = new JMenuItem("资料");
    private JMenuItem bz_fh = new JMenuItem("返回主系统");
    // 子菜单添加到一级菜单
    
    
    void display() {
    	
    	
    	
    	xx.add(xx_gl);
    	ck.add(ck_kzt);
    	gy.add(gy_zl);
    	bz.add(bz_fh);
    	
    	
        menuBar.add(xx);
        menuBar.add(ck);
        menuBar.add(gy);
        menuBar.add(bz);
        menuBar.setBackground(Color.lightGray);
    	
    	// 布局
    	JFrame f = new JFrame("GIA_TL");
    	f.setIconImage(icon);   //设置窗口的logo
    	f.setBounds(500, 250, 745, 460);
    	f.setResizable(false);
    	f.setLayout(null);
    	
    	xx_gl.addActionListener(new ActionListener() {
            //管理子系统监听
            public void actionPerformed(ActionEvent e) {
            	APIKEY = JOptionPane.showInputDialog("重新定义图灵APIKEY："); 
            	if(APIKEY == null){
            		kztwb.append("无输入文本直接点击取消\n");
            		JOptionPane.showMessageDialog(f, "APIKEY不能为空！", "更改失败", 0);
            		APIKEY = "5b57e69405ac4db68b86a21a1fe4435b"; 
            	}else if(APIKEY.equals("")){
            		kztwb.append("输入文本为空\n");
            		JOptionPane.showMessageDialog(f, "APIKEY不能为空！", "更改失败", 0);
            		APIKEY = "5b57e69405ac4db68b86a21a1fe4435b";
            	}
                gl();
            }
        });
    	
    	ck_kzt.addActionListener(new ActionListener() {
            //控制台监听
            public void actionPerformed(ActionEvent e) {
            	if(zck_sm){
            		kzt();
            		zck_sm = false;
            	}else{
            		kztwb.append("重复开启控制台\n");
            		JOptionPane.showMessageDialog(f, "不能重复打开控制台", "开启控制台失败", 0);
            	}
            }
        });
    	
    	gy_zl.addActionListener(new ActionListener() {
            //资料监听
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(f, "这是GIA下的聊天子系统", "系统介绍", 1);
                zl();
            }
        });
    	
    	bz_fh.addActionListener(new ActionListener() {
            //返回主系统监听
    		
    		
            public void actionPerformed(ActionEvent e) {
                fh();
            }
        });
    	
    	bt.addActionListener(new ActionListener() {
            //按钮点击监听
            public void actionPerformed(ActionEvent e) {
                methodA();
            }
        });
    	
    	jtf.addActionListener(new ActionListener() {
            //输入框回车监听
            public void actionPerformed(ActionEvent e) {
                methodA();
            }
        });
    	
    	
    	label.setIcon(img);
        label.setBounds(480, -35 , 369 , 512 );
    	
    	jta.setLineWrap(true); // 设置自动换行
    	//jpf.setBounds(20, 10, 350, 120);
    	menuBar.setBounds(0, 0, 745, 30);
    	jsp.setBounds(20, 50, 450, 300);
    	jtf.setBounds(20, 360, 350, 30);
    	bt.setBounds(380,360,85,30);
    	
    	
    	// 把组件添加进窗口f中
    	f.add(menuBar);
    	f.add(jtf);
    	f.add(jsp);
    	f.add(bt);
    	f.add(label);

    	
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setVisible(true);
      
    }
		
    
    private void gl() {
    	kztwb.append("管理事件完成\n");
		
	}
    
    private void kzt() {
		
		kztwb.append("控制台事件发生\n");
		
		JFrame f = new JFrame("GIA_TL_KZT");
    	f.setIconImage(icon);   //设置窗口的logo
    	f.setBounds(600, 350, 350, 600);
    	f.setResizable(false);
    	f.setLayout(null);
    	kztck.setBounds(10, 10, 320, 540);
    	
    	f.add(kztck);
    	kztwb.setCaretPosition(kztwb.getText().length());
    	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	f.setVisible(true);
        
    	
    	f.addWindowListener(new WindowAdapter() {
            //窗口点击监听
            public void windowClosing(WindowEvent e) {
                zck_sm = true;
            }
        });
    	
    	
    	
    	
	}
    
    private void zl() {
    	kztwb.append("资料事件完成\n");
		
	}
    
    private void fh() {
    	kztwb.append("返回事件发生\n");
		System.exit(0);
	}
    
    private void methodA() {
    	try{
			String question = jtf.getText();//获取输入语句
			String INFO = URLEncoder.encode(question, "utf-8"); 
	        String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
	        URL getUrl = new URL(getURL); 
	        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	        connection.connect(); 
	
	        // 取得输入流，并使用Reader读取 
	        BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
	        StringBuffer sb = new StringBuffer(); 
	        String line = ""; 
	        while ((line = reader.readLine()) != null) { 
	            sb.append(line); 
	        } 
	        reader.close(); 
	        // 断开连接 
	        connection.disconnect();
	        String text = sb.toString();
	        String[] strs=text.split("\"");
	        //System.out.println(strs[2]);
	        System.out.println("json返回类型量" + strs[2].substring(1,strs[2].length()-1));
	        kztwb.append("json返回类型量" + strs[2].substring(1,strs[2].length()-1)+"\n");
			//txt.setText(strs[5]);
			jta.append(">>"+jtf.getText()+"\n");
			jta.append("TL:"+strs[5]+"\n");
			
			jta.selectAll();//显示在最后一行
			
        }catch (Exception IOException){
        	kztwb.append("Java_IOException_流错误\n");
        	
        		
        }
		jtf.setText("");
	}
    
    public static void main(String[] args) {

    		(new GIA_TL()).display();
    		
    }
}

