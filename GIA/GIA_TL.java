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
        	data = "��ʵ��һ�죬�ſ�ʼ��";
        }else if(dat12 >= 10 && dat12 < 14){
        	data = "���磬�ǲ���֪ʶ��ʱ�䣡";
        }else if(dat12 >= 14 && dat12 < 18){
        	data = "���磬�ֵ��˲���֪ʶ��ʱ�䣡";
        }else if(dat12 >= 18 && dat12 < 20){
        	data = "��ʱ��Է��ˣ�";
        }else if(dat12 >= 20 && dat12 < 23){
        	data = "���ϣ�����Ҫ����֪ʶ�أ�";
        }else if(dat12 >= 23 || dat12 < 4){
        	data = "��˯����ʱ���ˣ�";
        }else{
        	data = "�ۣ���������㻹��˯����";
        }
    	return data;
	}
    
    
    private JTextField jtf = new JTextField("����������������Ϣ", 30);    // �����ı������, 30 ��
    private JTextArea jta = new JTextArea("��ã�������GIA����ϵͳ�µ�ͼ���������\n"+time_E1(Integer.parseInt(dat1[0]))+"\n", 10, 30);               // �����ı������,10�У�30��
    private JScrollPane jsp = new JScrollPane(jta);                    // ����������������ʾ�������ı�������
    
    private JTextArea kztwb = new JTextArea("��ã������ǿ���̨\n", 10, 30);               // �����ı������,10�У�30��
    private JScrollPane kztck = new JScrollPane(kztwb);                    // ����������������ʾ�������ı�������
    
    private JButton bt = new JButton("����");
    private JLabel label = new JLabel();
    private ImageIcon img = new ImageIcon("images/GK.png");// ����ͼƬ����
    private Image icon = Toolkit.getDefaultToolkit().getImage("images/logo1.png");  //����ͼ��
    /*
     * ����һ���˵���
     */
    private JMenuBar menuBar = new JMenuBar();

    /*
     * ����һ���˵�
     */
    private JMenu xx = new JMenu("ѡ��");
    private JMenu ck = new JMenu("����");
    private JMenu gy = new JMenu("����");
    private JMenu bz = new JMenu("����");
    // һ���˵���ӵ��˵���
    /*
     * ���� "�༭" һ���˵����Ӳ˵�
     */
    private JMenuItem xx_gl = new JMenuItem("������ϵͳ");
    private JMenuItem ck_kzt = new JMenuItem("����̨");
    private JMenuItem gy_zl = new JMenuItem("����");
    private JMenuItem bz_fh = new JMenuItem("������ϵͳ");
    // �Ӳ˵���ӵ�һ���˵�
    
    
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
    	
    	// ����
    	JFrame f = new JFrame("GIA_TL");
    	f.setIconImage(icon);   //���ô��ڵ�logo
    	f.setBounds(500, 250, 745, 460);
    	f.setResizable(false);
    	f.setLayout(null);
    	
    	xx_gl.addActionListener(new ActionListener() {
            //������ϵͳ����
            public void actionPerformed(ActionEvent e) {
            	APIKEY = JOptionPane.showInputDialog("���¶���ͼ��APIKEY��"); 
            	if(APIKEY == null){
            		kztwb.append("�������ı�ֱ�ӵ��ȡ��\n");
            		JOptionPane.showMessageDialog(f, "APIKEY����Ϊ�գ�", "����ʧ��", 0);
            		APIKEY = "5b57e69405ac4db68b86a21a1fe4435b"; 
            	}else if(APIKEY.equals("")){
            		kztwb.append("�����ı�Ϊ��\n");
            		JOptionPane.showMessageDialog(f, "APIKEY����Ϊ�գ�", "����ʧ��", 0);
            		APIKEY = "5b57e69405ac4db68b86a21a1fe4435b";
            	}
                gl();
            }
        });
    	
    	ck_kzt.addActionListener(new ActionListener() {
            //����̨����
            public void actionPerformed(ActionEvent e) {
            	if(zck_sm){
            		kzt();
            		zck_sm = false;
            	}else{
            		kztwb.append("�ظ���������̨\n");
            		JOptionPane.showMessageDialog(f, "�����ظ��򿪿���̨", "��������̨ʧ��", 0);
            	}
            }
        });
    	
    	gy_zl.addActionListener(new ActionListener() {
            //���ϼ���
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(f, "����GIA�µ�������ϵͳ", "ϵͳ����", 1);
                zl();
            }
        });
    	
    	bz_fh.addActionListener(new ActionListener() {
            //������ϵͳ����
    		
    		
            public void actionPerformed(ActionEvent e) {
                fh();
            }
        });
    	
    	bt.addActionListener(new ActionListener() {
            //��ť�������
            public void actionPerformed(ActionEvent e) {
                methodA();
            }
        });
    	
    	jtf.addActionListener(new ActionListener() {
            //�����س�����
            public void actionPerformed(ActionEvent e) {
                methodA();
            }
        });
    	
    	
    	label.setIcon(img);
        label.setBounds(480, -35 , 369 , 512 );
    	
    	jta.setLineWrap(true); // �����Զ�����
    	//jpf.setBounds(20, 10, 350, 120);
    	menuBar.setBounds(0, 0, 745, 30);
    	jsp.setBounds(20, 50, 450, 300);
    	jtf.setBounds(20, 360, 350, 30);
    	bt.setBounds(380,360,85,30);
    	
    	
    	// �������ӽ�����f��
    	f.add(menuBar);
    	f.add(jtf);
    	f.add(jsp);
    	f.add(bt);
    	f.add(label);

    	
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setVisible(true);
      
    }
		
    
    private void gl() {
    	kztwb.append("�����¼����\n");
		
	}
    
    private void kzt() {
		
		kztwb.append("����̨�¼�����\n");
		
		JFrame f = new JFrame("GIA_TL_KZT");
    	f.setIconImage(icon);   //���ô��ڵ�logo
    	f.setBounds(600, 350, 350, 600);
    	f.setResizable(false);
    	f.setLayout(null);
    	kztck.setBounds(10, 10, 320, 540);
    	
    	f.add(kztck);
    	kztwb.setCaretPosition(kztwb.getText().length());
    	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	f.setVisible(true);
        
    	
    	f.addWindowListener(new WindowAdapter() {
            //���ڵ������
            public void windowClosing(WindowEvent e) {
                zck_sm = true;
            }
        });
    	
    	
    	
    	
	}
    
    private void zl() {
    	kztwb.append("�����¼����\n");
		
	}
    
    private void fh() {
    	kztwb.append("�����¼�����\n");
		System.exit(0);
	}
    
    private void methodA() {
    	try{
			String question = jtf.getText();//��ȡ�������
			String INFO = URLEncoder.encode(question, "utf-8"); 
	        String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
	        URL getUrl = new URL(getURL); 
	        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	        connection.connect(); 
	
	        // ȡ������������ʹ��Reader��ȡ 
	        BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
	        StringBuffer sb = new StringBuffer(); 
	        String line = ""; 
	        while ((line = reader.readLine()) != null) { 
	            sb.append(line); 
	        } 
	        reader.close(); 
	        // �Ͽ����� 
	        connection.disconnect();
	        String text = sb.toString();
	        String[] strs=text.split("\"");
	        //System.out.println(strs[2]);
	        System.out.println("json����������" + strs[2].substring(1,strs[2].length()-1));
	        kztwb.append("json����������" + strs[2].substring(1,strs[2].length()-1)+"\n");
			//txt.setText(strs[5]);
			jta.append(">>"+jtf.getText()+"\n");
			jta.append("TL:"+strs[5]+"\n");
			
			jta.selectAll();//��ʾ�����һ��
			
        }catch (Exception IOException){
        	kztwb.append("Java_IOException_������\n");
        	
        		
        }
		jtf.setText("");
	}
    
    public static void main(String[] args) {

    		(new GIA_TL()).display();
    		
    }
}

