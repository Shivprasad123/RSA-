import java.math.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class RSAServer1 {
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
		Scanner sc = new Scanner(System.in);
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
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println(Integer.toString(m));
		p.println(Integer.toString(n));
		p.println(Integer.toString(k));
		System.out.println("\nx=" + m + "\ty=" + n);
		System.out.println("Public key:{" + m + "," + k + "}\nPrivate Key:{" + n + "," + k + "}");
		
		String count = sc1.next();
		int count1 =  Integer.parseInt(count);
		String str2 = "";
		for(int f=0;f<count1;f++){
		System.out.print("Encrypted data: ");
		String str5="";
		str5 = sc1.next();
		System.out.println(str5);
	//	char[] data = str5.toCharArray();
	//	for (char x : data)
	//	{
		BigInteger c = new BigInteger(str5);
			str2 = str2 + Character.toString((char)((c).pow(n).mod(new BigInteger(String.valueOf(k))).intValue()));
	//	}
	//	p.println(str2);
	}
		System.out.println("\nDecrypted data: "+str2);
}	}

