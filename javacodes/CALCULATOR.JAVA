Q)  RPC code to perform calculations (addition and subtraction).

Code:
Calculator.java
import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface Calculator extends Remote {
    int add(int a, int b) throws RemoteException;
    int subtract(int a, int b) throws RemoteException;
}
 //NEW FILE 
CalculatorImpl.java
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
 
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    protected CalculatorImpl() throws RemoteException {
        super();
    }
 
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
 
    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }
}
 // NEW FILE
Server.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class Server {
    public static void main(String[] args) {
        try {
            CalculatorImpl calculator = new CalculatorImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", calculator);
            System.out.println("Calculator Server is ready...");
        } 
         catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
 
 //NEW FILE
Client.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
 
public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");
 
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int a = sc.nextInt();
            System.out.print("Enter second number: ");
            int b = sc.nextInt();
 
            System.out.println("Addition: " + calculator.add(a, b));
            System.out.println("Subtraction: " + calculator.subtract(a, b));
 
            sc.close();
        } 
         catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
