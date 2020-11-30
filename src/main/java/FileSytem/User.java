package FileSytem;

import FileSytem.UserData.UserData;

import java.io.*;
import java.util.ArrayList;

public class User {
    private File file;
    private String filePath = ".\\FileSystemData\\SystemUsers\\Users.txt";
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

    public User()
    {
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
            System.out.println("No File");
        }
    }

    public boolean Login(String userName, String pass)
    {
        ArrayList<String> fileData = ReadFile();
        UserData usData = new UserData();
        usData.userName = userName;
        usData.password = pass;

        for (String data : fileData)
        {
            UserData uData = new UserData(data);
            if(uData.UserLoginEqual(usData))
                return true;
        }

        return false;
    }

    public boolean UserExist(String userName)
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

    public void BlockUser(String userName)
    {
        ArrayList<String> fileData = ReadFile();
        ArrayList<String> fileDataw = new ArrayList<String>();
        UserData usData = new UserData();
        usData.userName = userName;

        for (String data : fileData)
        {
            UserData uData = new UserData(data);
            if(uData.UserEqual(usData)) {
                uData.blocked = true;
            }
            fileDataw.add(uData.GetString());
        }

        this.WriteFile(fileDataw);
    }

    public boolean UserBlocked(String userName)
    {
        ArrayList<String> fileData = ReadFile();
        UserData usData = new UserData();
        usData.userName = userName;

        for (String data : fileData)
        {
            UserData uData = new UserData(data);
            if(uData.UserEqual(usData)) {
                return uData.blocked;
            }
        }

        return false;
    }

    public void AddUser(String userName, String pass, String name)
    {
        UserData usData = new UserData();
        usData.userName = userName;
        usData.password = pass;
        usData.name = name;
        usData.blocked = false;

        ArrayList<String> fileData = ReadFile();

        fileData.add(usData.GetString());

        this.WriteFile(fileData);
    }
}
