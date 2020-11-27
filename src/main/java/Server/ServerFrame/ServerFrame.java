package Server.ServerFrame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ServerFrame {
    public String Operation;
    public String UserName;
    public String EpochTime;
    public String Msg;
    private String spliterChar = "\0";

    private String GetEpochTime() throws ParseException {
        Date today = Calendar.getInstance().getTime();

        // Constructs a SimpleDateFormat using the given pattern
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

        // format() formats a Date into a date/time string.
        String currentTime = crunchifyFormat.format(today);
        System.out.println("Current Time = " + currentTime);
        Long epochTime = 0L;
        // parse() parses text from the beginning of the given string to produce a date.
        Date date = crunchifyFormat.parse(currentTime);

        // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
        epochTime = date.getTime();

        System.out.println("Current Time in Epoch: " + epochTime);

        return epochTime.toString();
    }

    public ServerFrame(String Op, String UserName, String Msg)
    {
        this.Operation = Op;
        this.UserName = UserName;
        this.Msg = Msg;
        try
        {
            this.EpochTime = this.GetEpochTime();
        }
        catch (Exception e)
        {
            System.out.println("parse Exception");
        }
    }

    public ServerFrame(String frame)
    {
        String temp[] = frame.split(this.spliterChar);

        this.Operation = temp[0];
        this.UserName = temp[1];
        this.EpochTime = temp[2];
        this.Msg = temp[3];
    }

    public String GetFrame()
    {
        StringBuilder ret = new StringBuilder();

        ret.append(this.Operation);
        ret.append(spliterChar);

        ret.append(this.UserName);
        ret.append(spliterChar);

        ret.append(this.EpochTime);
        ret.append(spliterChar);

        ret.append(this.Msg);
        ret.append(spliterChar);

        return ret.toString();
    }
}
