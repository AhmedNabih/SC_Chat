package Client;

import Server.ServerFrame.ServerFrame;

import javax.sql.ConnectionEventListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomePage {
    private JTextArea textArea1;
    public JPanel Panel;
    private JButton chatButton;
    private JTextField enterUsernameOfTheTextField;

    HomePage() throws IOException {
        ServerFrame frame=new ServerFrame("Contacts",LoginForm.username," ");
        Client.request.writeUTF(frame.GetFrame());
        String contacts=Client.response.readUTF();
        String []Contacts=contacts.split(",");
        for (String contact : Contacts) textArea1.append(contact + "\n");
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Chat chat=new Chat();
                    chat.To=enterUsernameOfTheTextField.getText();
                    chat.Username=LoginForm.username;
                    System.out.println(LoginForm.username);
                    JFrame frame=new JFrame(LoginForm.username);
                    frame.setContentPane(chat.Panel1);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (true)
                                {
                                    chat.responed();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }).start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
