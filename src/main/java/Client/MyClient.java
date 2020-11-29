package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class MyClient {
   public static Client client = new Client();
    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int port = 889;
        client.CreateSocket(host, port);
        if(client.Conneceted)
        {
//            client.Register("Mohamed123", "12345");
            client.Login("Mohamed", "Mada123");
//            client.Chat("Mohamed","Ahmed","B2olk eehhhh");

//            client.Close();
        }

    }

}