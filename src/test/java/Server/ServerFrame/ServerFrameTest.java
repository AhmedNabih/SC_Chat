package Server.ServerFrame;

public class ServerFrameTest {
    public static void main(String[] args)
    {
        ServerFrame frame1 = new ServerFrame("Chat", "Hamada", "ahahha");
        System.out.println("Frame 1: " + frame1.GetFrame());

        ServerFrame frame2 = new ServerFrame(frame1.GetFrame());
        System.out.println("Frame 2: " + frame2.GetFrame());

        System.out.println(frame2.UserName);
    }
}
