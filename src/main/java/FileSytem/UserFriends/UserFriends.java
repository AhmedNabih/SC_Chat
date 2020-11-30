package FileSytem.UserFriends;

import FileSytem.UserData.UserData;

import java.io.*;
import java.util.ArrayList;

public class UserFriends {
    private File file;
    private String filePath = ".\\FileSystemData\\UsersFriends\\";
    private BufferedReader br;
    private BufferedWriter bw;

    private ArrayList<String> ReadFile()
    {
        ArrayList<String> ret = new ArrayList<String>();

        try {
            this.br = new BufferedReader(new FileReader(this.file));
        } catch (Exception e) { }

        String temp = "";
        while(true)
        {
            try {
                temp = br.readLine();
            } catch (Exception e) { }

            if(temp == null)
                break;

            ret.add(temp);
        }

        try {
            this.br.close();
        } catch (Exception e) { }
        return ret;
    }

    private void WriteFile(ArrayList<String> fileString)
    {
        try {
            this.bw = new BufferedWriter(new FileWriter(this.file));
        } catch (Exception e) { }

        for (String s : fileString)
        {
            try {
                this.bw.write(s);
                this.bw.newLine();
            } catch (Exception e) { }
        }

        try {
            this.bw.close();
        } catch (Exception e) { }
    }

    public UserFriends(String userName)
    {
        this.filePath = this.filePath + userName + ".txt";
        this.file = new File(this.filePath);

        if(this.file.exists())
        {
            try {
                this.br = null;// new BufferedReader(new FileReader(this.file));
                this.bw = null;// new BufferedWriter(new FileWriter(this.file));
            } catch (Exception e) { }
        }
        else
        {
            try {
                this.file.createNewFile();
            } catch (Exception e) { }
        }
    }

    public void AddFriend(String userName)
    {
        ArrayList<String> fileData = ReadFile();

        fileData.add(userName);

        this.WriteFile(fileData);
    }

    public boolean HaveFriend(String userName)
    {
        ArrayList<String> fileData = ReadFile();
        UserData usData = new UserData();
        usData.userName = userName;

        for (String data : fileData)
        {
            UserData uData = new UserData(data);
            if(uData.UserEqual(usData))
                return true;
        }

        return false;
    }

    public void DeleteFriend(String userName)
    {
        ArrayList<String> fileData = ReadFile();
        ArrayList<String> fileDataw = new ArrayList<String>();
        UserData usData = new UserData();
        usData.userName = userName;

        for (String data : fileData)
        {
            UserData uData = new UserData(data);
            if(!uData.UserEqual(usData))
                fileDataw.add(uData.GetString());
        }

        this.WriteFile(fileDataw);
    }

    public ArrayList<String> GetFriend(String userName)
    {
        ArrayList<String> fileData = ReadFile();

        return fileData;
    }
}
