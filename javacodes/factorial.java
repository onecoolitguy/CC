udpServerFactorial.java

import java.net.*;
import java.io.*;
public class udpServerFactorial
{
public static void main(String args[])
{
try
{
DatagramSocket ds = new DatagramSocket(2000);
byte b[] = new byte[1024];
DatagramPacket dp = new DatagramPacket(b, b.length);
ds.receive(dp);
String str = new String(dp.getData(), 0, dp.getLength());
System.out.println("Received number: " + str);

int a = Integer.parseInt(str);
long factorial = 1;
for(int i = 1; i <= a; i++)
{
factorial = factorial * i;
}
String s = "Factorial of " + a + " is " + factorial;
byte b1[] = new byte[1024];
b1 = s.getBytes();
DatagramPacket dp1 = new DatagramPacket(b1, b1.length, InetAddress.getLocalHost(), 1000);
ds.send(dp1);
}
catch(Exception e)
{
e.printStackTrace();
}
}
}

//NEW FILE

udpClientFactorial.java

import java.net.*;
import java.io.*;
public class udpClientFactorial
{
public static void main(String args[])
{
try
{
DatagramSocket ds = new DatagramSocket(1000);
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter a number: ");
String num = br.readLine();
byte b[] = new byte[1024];
b = num.getBytes();
DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), 2000);

ds.send(dp);
byte b1[] = new byte[1024];
DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
ds.receive(dp1);
String str = new String(dp1.getData(), 0, dp1.getLength());
			System.out.println(str);
}
catch(Exception e)
{
e.printStackTrace();
}
}
}
