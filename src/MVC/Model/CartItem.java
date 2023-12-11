package MVC.Model;

public class CartItem {
    private int pid;
    private String pname;
    private String punit;
    private double price;
    private String path;
    private int number;

    // 构造方法
    public CartItem() {
    }

    // Getter 和 Setter 方法
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPunit() {
        return punit;
    }

    public void setPunit(String punit) {
        this.punit = punit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPath(){
        return path;
    }
    public void setPath(String path){
        this.path=path;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
