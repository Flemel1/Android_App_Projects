package Model;

public class Income {
    private int ID;
    private String Month;
    private int Money;
    private String Date;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getID() {
        return ID;
    }

    public String getMonth() {
        return Month;
    }

    public int getMoney() {
        return Money;
    }

    public String getDate() {
        return Date;
    }
}
