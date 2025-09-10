ChatServer.java
import java.net.*;
import java.io.*;
class ChatServer
{
	public static void main(String args[])
	{
		try
		{
			ServerSocket ss=new ServerSocket(8000);
			System.out.println("Waiting for client to connect..");
			Socket s=ss.accept();
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in=new DataInputStream(s.getInputStream());
			String recieve,send;
			while((recieve=in.readLine())!=null)
			{
				if(recieve.equals("STOP"))
				break;
				System.out.println("Client Says:"+recieve);
				System.out.println("Server Says:");
				send=br.readLine();
				out.writeBytes(send+"\n");
			}
			br.close();
			in.close();
			out.close();
			s.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
//NEW FILE
ChatClient.java
import java.net.*;
import java.io.*;
class ChatClient
{
	public static void main(String args[])
	{
		try
		{
			Socket s=new Socket("Localhost",8000);
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in=new DataInputStream(s.getInputStream());
			String msg;
			System.out.println("To stop chatting with server type STOP");
			System.out.println("Client Says:");
			while((msg=br.readLine())!=null)
			{
				out.writeBytes(msg+"\n");
				if(msg.equals("STOP"))
				break;
				System.out.println("Server Says:"+in.readLine());
				System.out.println("Client Says:");
			}
			br.close();
			in.close();
			out.close();
			s.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
