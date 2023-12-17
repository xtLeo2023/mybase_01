package Model;

public class Cart {
    private String username;
    private int pid;
    private int number;

    public Cart(String username, int pid, int number) {
        this.username=username;
        this.pid=pid;
        this.number=number;
    }

    public Cart() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
