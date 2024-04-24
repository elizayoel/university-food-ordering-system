import java.time.LocalDateTime;
import java.io.Serializable;

public class ReviewVendor implements Comparable<ReviewVendor>, Serializable{
    private String customerID;
    private String vendorID;
    private int rating;
    private String reviewContent;
    private date reviewDateTime;

    public ReviewVendor(String customerID, String vendorID, int rating, String reviewContent){
        this.customerID = customerID;
        this.vendorID = vendorID;
        this.rating = rating;
        this.reviewContent = reviewContent;
        this.reviewDateTime = new date(LocalDateTime.now());
    }
    public String getReviewDateFormatted() {
        return reviewDateTime.getFormattedDate();
    }
    public String getReviewTime() {
        return reviewDateTime.getFormattedTime();
    }
    public String getCustomerID() {
        return customerID;
    }
    public String getVendorID() {
        return vendorID;
    }
    public int getRating() {
        return rating;
    }
    public String getReviewContent() {
        return reviewContent;
    }

    public String toString(){
        return "Customer ID = " + customerID + "\n" +
                getReviewDateFormatted() +
                "Rating     = " + rating + "\n" +
                reviewContent + "\n";
    }
    public void getReview(){
        System.out.println("=================================");
        System.out.println("Customer ID = "+customerID);
        System.out.print("Rate        = ");
        if (rating == 1){
            System.out.println("★☆☆☆☆");
        }else if(rating ==2){
            System.out.println("★★☆☆☆");
        }else if(rating ==3){
            System.out.println("★★★☆☆");
        }else if(rating ==4){
            System.out.println("★★★★☆");
        }else if(rating ==5){
            System.out.println("★★★★★");
        }
        System.out.println("Review      = " + reviewContent);
    }
    @Override
    public int compareTo(ReviewVendor o) {
        if(this.rating > o.rating){
            return 1;
        }else if(this.rating < o.rating){
            return -1;
        }else return 0;
    }
}

