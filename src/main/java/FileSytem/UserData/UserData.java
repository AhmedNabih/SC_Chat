package FileSytem.UserData;

public class UserData {
    public String userName;
    public String password;
    public String name;
    public boolean blocked;

    public UserData()
    {
        this.userName = "";
        this.password = "";
        this.name = "";
        this.blocked = false;
    }

    public UserData(String s)
    {
        String []temp = s.split(",");

        this.userName = temp[0];
        this.password = temp[1];
        this.name = temp[2];
        this.blocked = Boolean.parseBoolean(temp[3]);
    }

    public boolean Equal(UserData uData)
    {
        boolean ret = true;

        if (!this.userName.equals(uData.userName))
            ret = false;
        if (!this.password.equals(uData.password))
            ret = false;
        if (!this.name.equals(uData.name))
            ret = false;

        return ret;
    }

    public boolean UserBlocked()
    {
        return this.blocked;
    }

    public boolean UserLoginEqual(UserData uData)
    {
        boolean ret = true;

        if (!this.userName.equals(uData.userName))
            ret = false;
        if (!this.password.equals(uData.password))
            ret = false;

        return ret;
    }

    public boolean UserEqual(UserData uData)
    {
        boolean ret = true;

        if (!this.userName.equals(uData.userName))
            ret = false;

        return ret;
    }

    public String GetString()
    {
        StringBuilder ret = new StringBuilder();

        ret.append(this.userName);
        ret.append(",");
        ret.append(this.password);
        ret.append(",");
        ret.append(this.name);
        ret.append(",");
        String t = "";
        if (this.blocked)
            t = "1";
        else
            t = "0";
        ret.append(t);

        return ret.toString();
    }
}
