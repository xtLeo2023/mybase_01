package MVC.Model;

public class Product {
    private int pid;
    private String pname;
    private String punit;
    private float price;
    private String path;
    public int getPid() { return pid; }

    public String getName() {
        return pname;
    }

    public String getUnit() {
        return punit;
    }

    public float getPrice() {
        return price;
    }
    public String getPath() { return path;}

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.pname = name;
    }
    public void setUnit(String unit) {
        this.punit = unit;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void setPath(String path) { this.path = path; }
}
