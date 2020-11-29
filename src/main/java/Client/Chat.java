package Client;

import Server.ServerFrame.ServerFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.time.temporal.JulianFields;
import java.util.EventListener;

public class Chat extends javax.swing.JPanel {
    private JButton button1;
    public  JTextArea textArea1;
    private JTextField textField1;
    public JPanel Panel1;
    public String Username;
    public String To;
    String respone="";

    public  Client client ;
    public  void responed() throws IOException {

            respone=Client.response.readUTF();
            textArea1.append(respone+"\n");
            System.out.println(respone);
    }
    public Chat() throws IOException {
        client=new Client();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerFrame frame=new ServerFrame("Chat",Username,To+"@#"+textField1.getText());
                try {
                    Client.request.writeUTF(frame.GetFrame());
                    Client.request.flush();
                    textArea1.append("Me"+": "+textField1.getText()+"\n");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


        });

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String []args) throws IOException {

        Chat chat=new Chat();
        chat.Username=LoginForm.username;
        JFrame frame=new JFrame("App");
        frame.setContentPane(chat.Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
