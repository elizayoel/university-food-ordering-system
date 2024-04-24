
import java.io.Serializable;
public class Beverage extends Item implements Serializable{
    public Beverage(){
        super();
    }
    public Beverage(String item_id,String name, String desc, double price){
        super(item_id,name,desc,price);
    }
}
