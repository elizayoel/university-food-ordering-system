import java.util.ArrayList;

import java.io.Serializable;

public class DelRun extends User implements Serializable{
    public double balance;
    public DelRun(){
        super();
    }
    public ArrayList<Order> historyOrderDelRun = new ArrayList<Order>();
    private ArrayList<ReviewDelrun> receivedReviewDelruns = new ArrayList<ReviewDelrun>();
    private double averageRating;

    public DelRun(String user_id, String password, String name, String address, String phone_num, String email_add){
        super(user_id,password,name,address,phone_num,email_add);
    }
    /*
    public DelRun(String user_id, String password, String name, String address, String phone_num, String email_add, double balance){
        super(user_id,password,name,address,phone_num,email_add);
        this.balance=balance;
    }

     */
    public void insert(String user_id){
        super.insert(user_id);
    }
    public void update (int opt){
        if (opt>=1 && opt<=5)
        {
            super.update(opt);
        }
    }
    public ArrayList<ReviewDelrun> getReceivedReviews() {
        return receivedReviewDelruns;
    }
    public void addReview(ReviewDelrun newReview){
        this.receivedReviewDelruns.add(newReview);
        calculateAvgRating();
    }
    public void calculateAvgRating(){
        if(!receivedReviewDelruns.isEmpty()){
            double totalRating = 0.0;
            for (ReviewDelrun reviewDelrun : receivedReviewDelruns){
                totalRating += reviewDelrun.getRating();
            }
            this.averageRating = totalRating / receivedReviewDelruns.size();
        }
    }
    public void addBalance(double delfeeAmount){

        this.balance += delfeeAmount;
    }

    public double getBalance() {
        return balance;
    }


    public ArrayList<Order> getHistoryOrderDelRun(){
        return this.historyOrderDelRun;
    }


    public String toString(){
        return super.toString();
    }
}
