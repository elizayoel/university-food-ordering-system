
import java.io.Serializable;
import java.time.LocalDateTime;

public class TopUp implements Serializable {
    private date topUpDate;
    private double amount;
    private String status;

    public TopUp(String date, double amount, String status){
        topUpDate = new date(LocalDateTime.now());
        this.amount=amount;
        this.status=status;
    }
    public String getDateFormatted(){
        return this.topUpDate.getFormattedDate();
    }
    public String getTimeFormatted(){
        return this.topUpDate.getFormattedTime();
    }

    public void setDate(String date){
        this.topUpDate = topUpDate;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public date getDate(){

        return this.topUpDate;
    }
    public double getAmount(){
        return this.amount;
    }
    public String getStatus(){
        return this.status;
    }
    public void showTopUp(){ //generating for admin
        System.out.println("================= Generated Receipt ==================");
        System.out.println("Date            = "+getDateFormatted());
        System.out.println("Time            = "+getTimeFormatted());
        System.out.println("Total Topup     = RM"+amount);
        System.out.println("Status          = Successfully added to balance");
    }
    public void showTopUpCust(){ //printing for cust
        System.out.println("=================== Top Up Receipt ===================");
        System.out.println("Date            = "+getDateFormatted());
        System.out.println("Time            = "+getTimeFormatted());
        System.out.println("Total Topup     = RM"+amount);
        System.out.println("Status          = Successfully added to balance");
        setStatus("done");
    }
}
