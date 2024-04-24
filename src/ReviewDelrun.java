import java.time.LocalDateTime;
import java.io.Serializable;

public class ReviewDelrun implements Serializable {
    private String customerID;
    private String delrunID;
    private int rating;
    private String reviewContent;
    private date reviewDateTime;

    public ReviewDelrun(String customerID, String delrunID, int rating, String reviewContent){
        this.customerID = customerID;
        this.delrunID = delrunID;
        this.rating = rating;
        this.reviewContent = reviewContent;
        this.reviewDateTime = new date(LocalDateTime.now());
    }
    public String getReviewDateFormatted(){
        return reviewDateTime.getFormattedDate();
    }
    public String getCustomerID() {
        return customerID;
    }
    public int getRating() {
        return rating;
    }
    public String getReviewContent() {
        return reviewContent;
    }


    public String toString(){
        return "Customer ID          = " + customerID + "\n" +
                getReviewDateFormatted() + "\n" +
                "Rating              = " + rating + "\n" +
                "Review              = "+ reviewContent + "\n";
    }
}
