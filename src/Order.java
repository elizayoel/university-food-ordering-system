import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDateTime;

public class Order implements Serializable {
    private static final long serialVersionUID = 5487590198004265123L;

    private static int orderCount = 1;
    int declined_count;

    private String order_id;
    private date placeOrderTime;
    //private date completeOrderTime;
    private Customer customer;
    private Vendor vendor;
    private String driver_id;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Integer> quantity = new ArrayList<Integer>();
    private String statusOrder;
    private int addorder;
    //private date dates;
    private double delFee;
    private double distance;
    private int serviceType;
    private boolean isDelivery;
    private boolean reviewed;
    private double total_price; //with delivery fee is any
    private double subtotal; //without fee (only the subtotal from food ordered)
    
    /*
    public Order(Customer customer, Vendor vendor, date today_date, int addorder, int serviceType){
        this.order_id = "OD" + orderCount;
        this.customer = customer;
        this.vendor = vendor;
        this.statusOrder = "pending";
        this.placeOrderTime = new date(LocalDateTime.now());
        if(addorder!=1){
            orderCount++;
        }
        this.serviceType = serviceType;
        if(this.serviceType == 1 || this.serviceType == 2){
            // 1. dine in, 2. take away
            isDelivery = false;
            delFee = 0.0;
            distance = 0.0;
        } else if(this.serviceType == 3){
            // 3. delivery
            isDelivery = true;
            distance = randomDistance();
            delFee = calcDelFee();
            driver_id="";

        }
        int print = -1;
        total_price = 0.0;
        subtotal=0.0;
        //total_price = calcTotalOrderAmount(delFee,print);
        declined_count=0;
    }

     */
    //REORDER FROM HIST
    public Order(Order copy){
        this.order_id = "OD"+orderCount;
        if (addorder!=1){
            orderCount++;
        }
        this.placeOrderTime = new date(LocalDateTime.now());
        this.declined_count=0;
        this.customer=copy.getCustomer();
        this.vendor = copy.getVendor();
        this.items = new ArrayList<Item>();
        for (int i = 0; i < copy.items.size(); i++) {
            this.items.add(copy.items.get(i));
        }
        this.quantity = new ArrayList<Integer>();
        for (int i = 0; i < copy.quantity.size(); i++) {
            this.quantity.add(copy.quantity.get(i));
        }
        this.statusOrder="pending";
        this.serviceType = copy.serviceType;
        if (this.serviceType==1 || this.serviceType==2){
            isDelivery = false;
            distance =0.0;
            delFee=0.0;
        }else if(this.serviceType==3){
            isDelivery=true;
            distance = randomDistance();
            delFee = calcDelFee();
            driver_id="";
        }
        int print=-1;
        total_price = copy.total_price;
        subtotal= copy.subtotal;
        this.declined_count = 0;
    }

    public Order(Customer customer, Vendor vendor, int addorder, int serviceType, int size){
        orderCount = size+1;
        this.order_id = "OD" + orderCount;
        this.customer = customer;
        this.vendor = vendor;
        this.statusOrder = "pending";
        this.placeOrderTime = new date(LocalDateTime.now());
        //this.completeOrderTime = null;
        if(addorder!=1){
            setOrderCount(getOrderCounts()+1);
            //orderCount++;
        }
        this.serviceType = serviceType;
        if(this.serviceType == 1 || this.serviceType == 2){
            // 1. dine in, 2. take away
            isDelivery = false;
            distance = 0.0;
            delFee = 0.0;
        } else if(this.serviceType == 3){
            // 3. delivery
            isDelivery = true;
            distance = randomDistance();
            delFee = calcDelFee();
            driver_id = "";
        }
        int print = -1;
        total_price = 0.0;
        subtotal = 0.0;
        declined_count=0;
    }

    public date getPlaceOrderTime(){
        return placeOrderTime;
    }
    public double getSubtotal() {
        return calcSubtotal();
    }
    public void setPlaceOrderTime(date placeOrderTime) {

        this.placeOrderTime = placeOrderTime;
    }

    public double randomDistance() {
        double min = 1.0;
        double max = 10.0;
        int scale = 2;
        Random random = new Random();
        double randDistance = min + (max - min) * random.nextDouble();
        randDistance = Math.round(randDistance * Math.pow(10, scale)) / Math.pow(10, scale);
        return randDistance;
    }
    public double calcDelFee(){
        int scale = 2;
        double fee = this.distance*0.8;
        fee = Math.round(fee*Math.pow(10,scale))/ Math.pow(10,scale);
        return fee;
    }
    public void setDelFee(){
        this.delFee = calcDelFee();
    }
    public void setSubtotal(){
        this.subtotal = calcSubtotal();
    }
    public void setTotal_price(){
        int print = 1;
        double delfee = calcDelFee();
        this.total_price = calcTotalOrderAmount(delfee,print);
    }
    public double calcSubtotal(){
        double totalAmount = 0.0;
        int scale = 2;
        if (items.size() == quantity.size()){
            for (int i = 0; i < items.size(); i++) {
                Item currentItem = items.get(i);
                int currentQuantity = quantity.get(i);
                double itemCost = currentItem.getPrice() * currentQuantity;
                totalAmount += itemCost;
            }
            totalAmount = Math.round(totalAmount*Math.pow(10,scale))/ Math.pow(10,scale);
        }
        else{
            System.out.println("Error! items and quantities have different values");
        }
        return totalAmount;
    }
    public double calcTotalOrderAmount(double del_fee, int print){
        double totalAmount = 0.0;
        //double totalAmount_round = 0.0;
        int scale = 2;
        if(items.size() == quantity.size()){
            for (int i = 0; i < items.size(); i++) {
                Item currentItem = items.get(i);
                int currentQuantity = quantity.get(i);
                double itemCost = currentItem.getPrice() * currentQuantity;
                //System.out.println("sblm");
                if (print==1) {
                    System.out.println("Item = " + currentItem.getName() + " - RM" + itemCost);
                }
                //System.out.println("sesudh");
                totalAmount += itemCost;
            }
            totalAmount = Math.round(totalAmount*Math.pow(10,scale))/ Math.pow(10,scale);
            if(isDelivery == true){
                totalAmount += del_fee;
                totalAmount = Math.round(totalAmount*Math.pow(10,scale))/ Math.pow(10,scale);
            }
        }else{
            System.out.println("Error! items and quantities have different values");
        }
        return totalAmount;
    }

    public String getOrderDateFormatted() {
        return placeOrderTime.getFormattedDate();
    }
    public String getOrderTime() {
        return placeOrderTime.getFormattedTime();
    }
    public String getDriver_id(){

        return this.driver_id;
    }
    public void setDriver_id(String driver_id){
        this.driver_id = driver_id;
    }
    public boolean isReviewed(){
        return reviewed;
    }
    public void setReviewed(boolean reviewed){
        this.reviewed = reviewed;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addOrderDetail(Item item, Integer quantity){
        items.add(item);
        this.quantity.add(quantity);
    }
    /*
    public void addOrderDetail(String order_id, Item item, Integer quantity){
        items.add(item);
        this.quantity.add(quantity);
        this.order_id = order_id;
    }

     */
    /*
    public void addOrderDetail(Item item, int quantity){
        items.add(item);
        this.quantity.add(quantity);

     */






    public int getOrderCounts(){
        return orderCount;
    }
    public double getDelFee(){
        return delFee;
    }
    public double getDistance(){
        return distance;
    }
    public double getTotal_price(){
        int print=-1;
        return calcTotalOrderAmount(delFee,print);
    }

    public int getServiceType() {
        return serviceType;
    }
    public static void setOrderCount(int orderCount) {
        Order.orderCount = orderCount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }


    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }
    public void setQuantity(int index, int quantityAdd){
        quantity.set(index, quantityAdd);
    }

    public Vendor getVendor() {
        return vendor;
    }
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void showOrderDetails(){
        System.out.println
                ("===============ORDER ID = " + order_id + "===============" +"\n" +
                        getOrderDateFormatted() + "\t\t\t\t" + getOrderTime() + "\n" +
                "--------------CUSTOMER DETAILS--------------\n" +
                "Customer ID  = " + customer.getUserID() + "\n" +
                "Name         = " + customer.getName() + "\n" +
                "Phone number = " + customer.getPhoneNum() + "\n" +
                "Address      = " + customer.getAddress() + "\n\n" +
                "Distance     = " + getDistance() + "\n" +
                "---------------VENDOR DETAILS---------------\n" +
                "Vendor ID    = " + vendor.getUserID() + "\n" +
                "Name         = " + vendor.getName() + "\n" +
                "Address      = " + vendor.getAddress() + "\n" +
                "Phone number = " + vendor.getPhoneNum() + "\n");
        if (items.size() == quantity.size()) {
            System.out.println("---------------ORDER DETAILS----------------");
            for (int i = 0; i < items.size(); i++) {
                System.out.println(items.get(i).getName() + " (" + quantity.get(i) + " pieces) ");
            }
            System.out.println("Subtotal     = " + subtotal);
            System.out.println("Total        = " + total_price);
        }
        System.out.println("Order Status = " + statusOrder + "\n");
    }
    public void printTaskforDelrun(){
        System.out.println(
                "================= TASK ================="+"\n"+
                        "Order ID       = "+order_id+"\n"+
                        "Customer ID    = "+customer.getUserID()+"\n"+
                        "Customer name  = "+customer.getName()+"\n"+
                        "Vendor ID      = "+vendor.getUserID()+"\n"+
                        "Vendor name    = "+vendor.getName()+"\n"+
                        "Distance       = "+getDistance()+" km"+"\n"+
                        "Delivery Fee   = RM "+getDelFee()

        );
    }
    public void driver_decline(){
        this.declined_count++;
    }
    public int getDeclined_count(){
        return declined_count;
    }
    public int checkDecline_count(){
        int change_opt = 0;
        if (this.declined_count>=5){
            System.out.println("Could not find a driver.");
            change_opt = 1; //cust have to change option since no driver want to accept the order
        }else{
            change_opt=-1; // cust doesnot have to change option
        }
        return change_opt;
    }
    public void resetcheckDecline_count(){
        this.declined_count = 0;
    }
    public void showHistTrans(){
        System.out.println("Order ID    : "+order_id);
        System.out.println("Order Date  : " + getOrderDateFormatted());
        System.out.println("Order Time  : " + getOrderTime());
        System.out.println("Transaction : "+ total_price);

    }
}
