import java.util.ArrayList;

import java.io.Serializable;

public class Customer extends User implements Serializable{
    public Customer(){
        super();
    }
    private double balance;
    private ArrayList<TopUp> historytopup = new ArrayList<TopUp>();
    private ArrayList<Order> customerOrder = new ArrayList<Order>();
    private ArrayList<Order> historyOrderCust = new ArrayList<Order>();
    private ArrayList<Order> historyTransaction = new ArrayList<Order>(); //TAMBAH INI
    private ArrayList<ReviewVendor> givenVendorReview = new ArrayList<ReviewVendor>();
    private ArrayList<ReviewDelrun> givenDelrunReview = new ArrayList<ReviewDelrun>();


    public Customer (String user_id,String password, String name, String address, String phone_num, String email_add){
        super(user_id,password,name,address,phone_num,email_add);
        historyOrderCust = new ArrayList<Order>();
        historyTransaction = new ArrayList<Order>();
    }
    public Customer (String user_id,String password, String name, String address, String phone_num, String email_add, double balance){
        super(user_id,password,name,address,phone_num,email_add);
        historyOrderCust = new ArrayList<Order>();
        historyTransaction = new ArrayList<Order>();
        this.balance=balance;
    }

    public void addVendorReview(ReviewVendor reviewVendor){
        givenVendorReview.add(reviewVendor);
    }
    public void addDelrunReview(ReviewDelrun reviewDelRun){
        givenDelrunReview.add(reviewDelRun);
    }
    public ArrayList<ReviewVendor> getGivenVendorReview() {return givenVendorReview;}
    public ArrayList<ReviewDelrun> getGivenDelRunReview() {
        return givenDelrunReview;
    }


    public ArrayList<TopUp> getHistorytopup(){
        return this.historytopup;
    }
    public ArrayList<Order> getCustomerOrder() {
        return customerOrder;
    }
    public ArrayList<Order> getHistoryOrderCust(){
        return this.historyOrderCust;
    }
    public ArrayList<Order> getHistoryTransaction(){ //TAMBAH INI
        return this.historyTransaction;
    }
    public void viewHistoryTransationCust(){ //TAMBAH INI
        for (int i = 0; i < getHistoryTransaction().size(); i++) {
            getHistoryTransaction().get(i).showHistTrans();
        }
    }


    public void setCustomerOrder(ArrayList<Order> customerOrder) {
        this.customerOrder = customerOrder;
    }

    public void insert(String user_id){
        super.insert(user_id);
    }
    public void topup(TopUp newtopup){
        historytopup.add(newtopup);
    }
    public void update (int opt){
        if (opt>=1 && opt<=5)
        {
            super.update(opt);
        }
    }
    public String toString(){
        return super.toString();
    }

    public void addBalance(double amount){
        balance += amount;
        //System.out.println("Current balance = "+balance);
    }

    public double getBalance(){
        int scale = 2;
        double balance_round = Math.round(balance * Math.pow(10, scale)) / Math.pow(10, scale);
        return balance_round;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void refundCust(double del_fee_ref){
        balance+=del_fee_ref;
    }

}

