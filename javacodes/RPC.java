Q)  RPC code to display Hello World.

Code:
Hello.java
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Hello extends Remote{
	String sayHello() throws RemoteException;
}
//NEW FILE 
HelloImpl.java
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class HelloImpl extends UnicastRemoteObject implements Hello{
	protected HelloImpl() throws RemoteException{
		super();
	}
	@Override
	public String sayHello() throws RemoteException{
		return "Hello, RMI World!";
	}
}
//New FILE
Server.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server{
	public static void main(String args[]){
		try{
			HelloImpl obj=new HelloImpl();
			//Craete registry on port 1099
			Registry registry=LocateRegistry.createRegistry(1099);
			//Bind the remote object in registry
			registry.rebind("HelloService",obj);
			System.out.println("Server ready...");
		}
		catch(Exception e){
			System.err.println("Server exception:"+e.toString());
			e.printStackTrace();
		}
	}
}
//NEW FILE
Client.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Client{
	public static void main(String args[]){
		try{
			Registry registry=LocateRegistry.getRegistry("localhost",1099);
			Hello stub=(Hello)registry.lookup("HelloService");
			String response=stub.sayHello();
			System.out.println("Response:"+response);
		}
		catch(Exception e){
			System.err.println("Client exception:"+e.toString());
			e.printStackTrace();
		}
	}
}
