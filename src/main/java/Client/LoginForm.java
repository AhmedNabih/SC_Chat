package Client;

import Server.ServerFrame.ServerFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginForm {
    private JTextField textField1;
    private JButton loginButton;
    private JTextField textField2;
    public  JPanel panel;
    public static String username;
    public LoginForm() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 username=textField1.getText();
                String password=textField2.getText();
                ServerFrame frame=new ServerFrame("Login",username,password);
                try {
                    Client.request.writeUTF(frame.GetFrame());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    String message=Client.response.readUTF();
                    JOptionPane.showMessageDialog(null,
                            message,
                            "TITLE",
                            JOptionPane.PLAIN_MESSAGE);
                    JFrame screen=new JFrame(username);
                    HomePage obj=new HomePage();
                    screen.setContentPane(obj.Panel);
                    screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    screen.pack();
                    screen.setVisible(true);
                    MainScreen.frame.setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
