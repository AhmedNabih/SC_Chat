package Server.ServerClientHandler;

import Server.ServerFrame.ServerFrame;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class SocketClientHandler implements Runnable {

    private Socket client;
    String UserNameFile;
    String PasswordFile;
    DataInputStream request;
    DataOutputStream response;
    String UserName;
 static HashMap<String,Socket>Online=new HashMap<>();
    public SocketClientHandler(Socket client) throws IOException {
        this.client = client;
         request = new DataInputStream(
                client.getInputStream());
         response = new DataOutputStream((client.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread started with name:"
                    + Thread.currentThread().getName());
            readResponse();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readResponse() throws IOException, InterruptedException {

        try {

             while (true){
               if(!client.isClosed()) {
                String Request = request.readUTF();
                System.out.println(Request);
                ServerFrame newFrame = new ServerFrame(Request);
                String ss="";
                UserName=newFrame.UserName;
                System.out.println(newFrame.Operation);
                switch (newFrame.Operation)
                {
                    
                    case "Login": {
                        String UserName = newFrame.UserName;
                        String Password = newFrame.Msg;
                        System.out.println(newFrame.Msg);
                        System.out.println(newFrame.UserName);
                        try {
                            // assuming we have 1 user only for now for simplicity
                            File myObj = new File("filename.txt");
                            Scanner myReader = new Scanner(myObj);

                            UserNameFile = myReader.nextLine();
                            PasswordFile = myReader.nextLine();
                            myReader.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        boolean condition1=UserName.equals(UserNameFile);
                        boolean condition2=Password.equals(PasswordFile);
                        if (condition1 && condition2 ) {
                            response.writeUTF("Login Successfully");
                            System.out.println("Login successfully");
                            Online.put(UserName,client);
                            System.out.println(Online.size());
                        }
                        if (!condition2)
                        {
                            response.writeUTF("401");
                        }
                        if(!condition1)
                        {
                            response.writeUTF("404");
                        }
                        response.flush();
                        break;
                    }
                    case "Register": {
                        String UserName = newFrame.UserName;
                        String []temp = newFrame.Msg.split("@#");
                        String Password = temp[0];
//                        String Name = temp[1];

                        try {
                            FileWriter myWriter = new FileWriter("filename.txt");
                            myWriter.write(UserName);
                            myWriter.write("\n");
                            myWriter.write(Password);
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                            response.writeUTF("Registered successfully");
                            response.flush();
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            response.writeUTF("Registered Failed");
                            response.flush();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "Close":
                        request.close();
                        response.close();
                        client.close();
                        break;
                    case "Chat":
                        {String []To_Message=newFrame.Msg.split("@#");
                        System.out.println(newFrame.Msg);
                        String To=To_Message[0];
                        System.out.println(To);
                        String Message=To_Message[1];
                        System.out.println(Message);
                        DataOutputStream   response = new DataOutputStream((Online.get(To).getOutputStream()));
                        response.writeUTF(UserName+": "+Message);
                        System.out.println("Sent");
                        response.flush();
                        break;}
                    case "Contacts":
                        StringBuilder contacts=new StringBuilder();
                        for (String tab : Online.keySet()) {
                            // do something with tab
                            contacts.append(tab).append(",");
                        }
                        response.writeUTF(contacts.toString());
                        response.flush();
                }
            } }}catch(Exception e){
                e.printStackTrace();
            }

            }

}