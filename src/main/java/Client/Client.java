package Client;

import Server.ServerFrame.ServerFrame;

import javax.swing.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static   DataOutputStream request;
    public static   DataInputStream response;
    public static Socket clientSocket = null;
    boolean Conneceted = false;

    public void CreateSocket(String host, int port) throws IOException
    {
        Conneceted  = false;
        try {
            clientSocket = new Socket(host, port);
        }
        catch (ConnectException e)
        {
            System.out.println("ConnectException");
            return;
        }

        Conneceted = true;

        System.out.println("======================================");
        System.out.println("Connected");
        System.out.println("======================================");
        // Declare a writer to this url
        request = new DataOutputStream (clientSocket.getOutputStream());

        // Declare a listener to this url
        response = new DataInputStream(
                clientSocket.getInputStream());


    }

    public void Login(String userName, String Password)
            throws IOException
    {
        // Sending request to the server
        ServerFrame frame=new ServerFrame("Login",userName,Password);
        System.out.println(frame.GetFrame());
        request.writeUTF(frame.GetFrame());
//        request.writeUTF("Login,UserName:" + userName + ",Password:" + Password);
//        System.out.println("Login,UserName:" + userName + ",Password:" + Password);
        request.flush();
        System.out.println("Request Sent!");
        System.out.println("======================================");
        // Receiving response from server
        String responseLine;
        responseLine = response.readUTF();
        if(responseLine.equals("404"))
        System.out.println("User Not Found");
        else if(responseLine.equals("401"))
            System.out.println("Password isn't correct");
        else
            System.out.println(responseLine);
        System.out.println("======================================");
        System.out.println("Response Received!!");
        System.out.println("======================================");
    }

    public void Register(String userName, String Password)
            throws IOException
    {
        // Sending request to the server
        // Building HTTP request header
        ServerFrame frame=new ServerFrame("Register",userName,Password+"Mada");
        System.out.println(frame.GetFrame());
        request.writeUTF(frame.GetFrame());
//        request.writeUTF("Register,UserName:" + userName + ",Password:" + Password);
//        System.out.println("Register,UserName:" + userName + ",Password:" + Password);
        request.flush();
        System.out.println("Request Sent!");
        System.out.println("======================================");
        // Receiving response from server
        String responseLine;
        responseLine = response.readUTF();
            System.out.println(responseLine);

        System.out.println("======================================");
        System.out.println("Response Recieved!!");
        System.out.println("======================================");

    }
    public void Chat(String From,String To,String Message) throws IOException {
        ServerFrame frame=new ServerFrame("Chat",From,To+"@#"+Message);
        request.writeUTF(frame.GetFrame());
        request.flush();
        System.out.println("Request Sent!");
        System.out.println("======================================");
        String responseLine;
        responseLine = response.readUTF();
        System.out.println(responseLine);
        System.out.println("======================================");
        System.out.println("Response Recieved!!");
        System.out.println("======================================");
    }
    public void Close()
            throws IOException
    {
        // Sending request to the server
        // Building HTTP request header
        ServerFrame frame=new ServerFrame("Close","asd","asd");
        request.writeUTF(frame.GetFrame());
        request.flush();
        // Receiving response from server

        System.out.println("Connection Close");
        request.close();
        response.close();
        clientSocket.close();
    }


}