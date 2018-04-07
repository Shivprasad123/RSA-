import java.math.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RSAClient2 {
	public static int findGCD(int n1, int n2) { 
		if (n2 == 0)
			return n1;
		return findGCD(n2, n1 % n2);
	}

	public static void main(String[] args) throws Exception{ 
		JFrame frm = new JFrame("RSA");
		frm.setLayout(new FlowLayout());
		frm.setSize(500,500);
		frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);		
		frm.setVisible(true);
		JButton in = new JButton("Encrypt & Send");
		for(;;){
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket("127.0.0.1",1234);
		Scanner sc1 = new Scanner(s.getInputStream());
		String str5  =  sc1.next();
		String str6 = sc1.next();
		String str7 = sc1.next();
		int m = Integer.parseInt(str5);
		int n = Integer.parseInt(str6);
		int k = Integer.parseInt(str7);
		JTextField jf = new JTextField(20);
		//jf.setText("HI");
		frm.add(jf);
		frm.add(in);
		//final String str1="";
		String str2="";
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		frm.add(l2);
		frm.add(l3);
		PrintStream p = new PrintStream(s.getOutputStream());
		in.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae1){
		System.out.println("Enter message:");
		final String str1 = jf.getText();
		//str1 = str2;
		char[] data = str1.toCharArray();
		int count=0;
		for (int z : data){
			System.out.print(z + " ");
			count++;
		}

		System.out.println("\nx=" + m + "\ty=" + n);
		System.out.println("Public key:{" + m + "," + k + "}\nPrivate Key:{" + n + "," + k + "}");

		System.out.print("Encrypted data: ");
		String str2 = "";
		p.println(count);
		l2.setText("Encrypted Values");
		l3.setText("");
		for (int x : data)
		{
			BigInteger c = new BigInteger(String.valueOf(x)).pow(m).mod(new BigInteger(String.valueOf(k)));
			System.out.print(c.intValue() + " ");
			p.println(c);
			String s = l3.getText();
			l3.setText(s+" , "+c.toString());
		}
		}
		});
		System.out.println();
//	s.close();
	}
	}
}	

