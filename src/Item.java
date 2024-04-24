
import java.io.Serializable;
public class Item implements Serializable {
    protected String item_id;
    protected String name;
    protected String desc;
    protected double price;


    public Item(){
        item_id = "";
        name = "";
        desc = "";
        price = 0.0;
    }
    public Item(String item_id,String name, String desc, double price){
        this.item_id = item_id;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
    public String getItem_id() {
        return item_id;
    }
    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String toString(){
        return "Item ID\t\t: " + item_id + "\n" +
                "Name\t\t: " + name + "\n" +
                "Description : " + desc + "\n" +
                "Price\t\t: " + price + "\n";
    }
}
