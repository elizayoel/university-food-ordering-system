
import java.io.Serializable;
public class Food extends Item implements Serializable{
    public Food(){
        super();
    }
    public Food(String item_id,String name, String desc, double price){
        super(item_id,name,desc,price);
    }
}

