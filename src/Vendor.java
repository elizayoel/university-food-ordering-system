
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
public class Vendor extends User implements Serializable, Comparable<Vendor>{
    private String desc;
    private int opt;
    private ArrayList<Order> receivedOrders = new ArrayList<Order>();
    private ArrayList<Order> historyOrdersVendor = new ArrayList<Order>();
    private ArrayList<ReviewVendor> receivedReviewVendors = new ArrayList<>();

    private ArrayList<Item> menus = new ArrayList<Item>();
    private double averageRating;
    private double balance;
    public Vendor (){
        super();
    }
    public Vendor (String user_id,String password, String name, String address, String phone_num, String email_add,String desc){
        super(user_id,password,name,address,phone_num,email_add);
        this.desc = desc;
        this.balance = 0.0;
        historyOrdersVendor = new ArrayList<Order>();
        this.averageRating = 0.0;
    }
    public ArrayList<Item> getMenus(){
        return menus;
    }
    public void insert(String user_id){
        super.insert(user_id);
        System.out.println("Restaurant Description : ");
        desc = scanStr.nextLine();
        //Vendor newVendor = new Vendor(super,RestName);
    }
    public ArrayList<Order> getReceivedOrders() {
        return receivedOrders;
    }
    public void setReceivedOrders(ArrayList<Order> receivedOrders) {
        this.receivedOrders = receivedOrders;
    }
    public void calculateAvgRating(){
        if(!receivedReviewVendors.isEmpty()){
            double totalRating = 0.0;
            for (ReviewVendor reviewVendor : receivedReviewVendors){
                totalRating += reviewVendor.getRating();
            }
            this.averageRating = totalRating / receivedReviewVendors.size();
            this.averageRating = Double.parseDouble(String.format("%.1f",this.averageRating));

        }
    }
    public void printAverageRating(){
        System.out.println("Rating = " + averageRating + "/" + "5\n");
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }


    public ArrayList<ReviewVendor> getReceivedReviews() {
        return receivedReviewVendors;
    }

    public void setReceivedReviews(ArrayList<ReviewVendor> receivedReviews) {
        this.receivedReviewVendors = receivedReviews;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void setHistoryOrdersVendor(ArrayList<Order> historyOrders) {
        this.historyOrdersVendor = historyOrdersVendor;
    }

    public void setMenus(ArrayList<Item> menus) {
        this.menus = menus;
    }
    public ArrayList<Order> getHistoryOrdersVendor(){
        return this.historyOrdersVendor;
    }

    public void addReview(ReviewVendor reviewVendor){
        this.receivedReviewVendors.add(reviewVendor);
        calculateAvgRating();
    }

    public void receiveOrder(Order order){
        this.receivedOrders.add(order);
    }
    public void update(int opt){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        if (opt>=1 && opt<=5){
            super.update(opt);
        }else if (opt==6){
            System.out.println("Insert new description : ");
            String newdesc = scanStr.nextLine();
            desc = newdesc;
        }
    }
    public String toString(){
        return super.toString()+
                "Restaurant Description : "+ desc+"\n";
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void viewReceivedOrder() {
        int count = 1;
        for (int i = 0; i < receivedOrders.size(); i++) {
            if (receivedOrders.get(i).getStatusOrder().equalsIgnoreCase("pending")) {
                System.out.println("No " + count);
                System.out.println("Order ID    = " + receivedOrders.get(i).getOrder_id());
                System.out.println("Order Date  = ");
                System.out.println(receivedOrders.get(i).getOrderDateFormatted() + "\t\t" + receivedOrders.get(i).getOrderTime());
                System.out.println("Customer    = " + receivedOrders.get(i).getCustomer().getUserID());
                System.out.println("Vendor      = " + receivedOrders.get(i).getVendor().getUserID());
                System.out.println("Items       :");
                for (int j = 0; j < receivedOrders.get(i).getItems().size(); j++) {
                    System.out.println(receivedOrders.get(i).getItems());
                    System.out.println("Items ID    = " +receivedOrders.get(i).getItems().get(j).getItem_id());
                    System.out.println("Items Name  = " +receivedOrders.get(i).getItems().get(j).getName());
                    System.out.println("Quantity    = " + receivedOrders.get(i).getQuantity());
                    System.out.println("Status      = " + receivedOrders.get(i).getStatusOrder());
                    count++;
                }
            }
        }
    }
    public void addrevenue(double orderCost){ //revenue nambah wktu statusnya accepted, diubah ke status prepared

        this.balance += orderCost;
    }
    public void deductRevenue(double orderCost){
        this.balance -= orderCost;
    }

    public int compareTo(Vendor o) {
        if(this.averageRating < o.averageRating){
            return 1;
        }else if(this.averageRating > o.averageRating){
            return -1;
        }else return 0;
    }

}
