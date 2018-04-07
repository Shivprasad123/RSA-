import java.math.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RSAServer2 {
	private static boolean isPrime(int num) { 
		if (num < 2) return false;
		if (num == 2) return true;
		if (num % 2 == 0) return false;
		for (int i = 3; i * i <= num; i += 2)
			if (num % i == 0) return false;
		return true;
	}

	public static int findGCD(int n1, int n2) { 
		if (n2 == 0)
			return n1;
		return findGCD(n2, n1 % n2);
	}

	public static void main(String[] args) throws Exception{ 
		JFrame frm = new JFrame("RSA");
		frm.setLayout(new FlowLayout());
		frm.setSize(300,500);
		frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
		frm.setVisible(true);
		JButton keys = new JButton("Send_Keys");
		JButton de = new JButton("Decrypt");
		Scanner sc = new Scanner(System.in);
		JLabel lab = new JLabel();
		JLabel l4 = new JLabel();
		frm.add(keys);
		frm.add(de);
		frm.add(lab);
		frm.add(l4);
		for(;;){
		 int k = 0,m = 0,n = 0;
		int max=0;
		Random ran = new Random();
		while(max<200){
			max = ran.nextInt(500);
		}
		ServerSocket s1 = new ServerSocket(1234);
		Socket ss = s1.accept();
		Scanner sc1 = new Scanner(ss.getInputStream());
		System.out.println();
		while(!(isPrime(max))){
			max++;
		}
		System.out.println("Prime1:"+(max));
		int i = max;

		max = max + ran.nextInt(50);

		while(!(isPrime(max))){
			max++;
		}

		System.out.println("Prime2:"+(max));
		int j = max;

		k = i * j;
		if ((isPrime(i)) && (isPrime(j)))
		{
			int z = 3;
			for (;;) {
				if (findGCD(z, (i - 1) * (j - 1)) == 1) {
					m = z;
					break;
				}

				z++;
			}
			z = 1;
			for (;;) {
				if (m * z % ((i - 1) * (j - 1)) == 1) {
					n = z;
					break;
				}

				z++;
			}
		}
		else{
			System.out.println("The entered value(s) not prime");
			System.exit(0);
		}
		final int k1=m;
		final int k2=n;
		final int k3=k;
		PrintStream p = new PrintStream(ss.getOutputStream());
		keys.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
		p.println(Integer.toString(k1));
		p.println(Integer.toString(k2));
		p.println(Integer.toString(k3));
		lab.setText("Keys sent");
			}	});
		System.out.println("\nx=" + m + "\ty=" + n);
		System.out.println("Public key:{" + m + "," + k + "}\nPrivate Key:{" + n + "," + k + "}");
		//String count = sc1.next();
		//int count1 =  Integer.parseInt(count);
		String str5[];
		str5 = new String[20];
		de.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
		String count = sc1.next();
		int count1 =  Integer.parseInt(count);
		//str5 = new String[count1];
		for(int f=0;f<count1;f++){
		System.out.print("Encrypted data: ");
		str5[f] = sc1.next();
		System.out.println(str5[f]);
		}
		final int k4 = k2;
		final int k5 = k3;
		//de.addActionListener(new ActionListener(){
		//	public void actionPerformed(ActionEvent ae){
		String str2 = "";


		for(int f=0;f<count1;f++){
		//System.out.println(str5);
		BigInteger c = new BigInteger(str5[f]);
		str2 = str2 + Character.toString((char)((c).pow(k4).mod(new BigInteger(String.valueOf(k5))).intValue()));
		}
			
		System.out.println("\nDecrypted data: "+str2);
		l4.setText("Decrypted Data:"+str2);
			}
		});
	}
}	}

