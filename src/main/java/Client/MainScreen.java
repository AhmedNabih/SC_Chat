package Client;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainScreen {
    private JButton loginButton;
    private JButton registerButton;
    public  JPanel panel;
    public static Client client = new Client();
    public static JFrame frame2=new JFrame("App");
    public static JFrame frame=new JFrame("App");

    public MainScreen() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm obj=new LoginForm();
                frame.setContentPane(obj.panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame2.setVisible(false);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String []args) throws IOException {
        String host = "localhost";
        int port = 889;
        client.CreateSocket(host, port);
        MainScreen obj=new MainScreen();
        frame2.setContentPane(obj.panel);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
    }
}
