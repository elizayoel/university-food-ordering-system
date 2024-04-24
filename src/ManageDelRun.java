
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.Serializable;

public class ManageDelRun implements InterfaceManageData, Serializable{
    private static final long serialVersionUID = -472899267757567235L;
    transient Scanner scanInt = new Scanner(System.in);
    transient Scanner scanStr = new Scanner(System.in);
    ArrayList<DelRun> drivers = new ArrayList<DelRun>();

    public ManageDelRun(){
/*
        DelRun d1 = new DelRun("D01","Driver01_","Samcan","Sungai Besi","0193892039","samcan@driver.apu.my");
        DelRun d2 = new DelRun("D02","Driver02_","Kayla","Semarang","6289647729","kayla@driver.apu.my");
        drivers.add(d1); drivers.add(d2);

 */


    }
    public ManageDelRun(String user_id, String password, String name, String address, String phone_num, String email_add){
        DelRun newDelRun = new DelRun(user_id, password, name, address, phone_num, email_add);
        drivers.add(newDelRun);
    }

    public void insert_data(){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }

        String user_id = "";
        String checkuser_id = "";
        String password="";
        String checkpassword="";
        int takenid = -1;
        int wrongpw = -1;
        boolean isUserIDTaken = true;
        boolean isPasswordCorrect = false;

        // taken id = 1 -> id nya blm kepake, loop ke stop
        // wrong pasword = 1 -> pw dah betul, loop nya ke stop
        // wrongps = 0 -> masih salah, loop lagi
        while(isUserIDTaken!=false) {
            System.out.println("================== DELIVERY RUNNER REGISTRATION =================");
            System.out.println("User ID\t:");
            checkuser_id = scanStr.nextLine();
            int found = -1;
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getUserID().equals(checkuser_id)) {
                    found = 1;
                }
            }
            if (found == 1) {
                System.out.printf("Username is already taken, please find another username.\n");
                isUserIDTaken = true;
            } else {
                isUserIDTaken = false;
            }
        }
        user_id = checkuser_id;
        while(isPasswordCorrect!=true) {
            int jLower=0, jUpper=0, jDigit=0, jSymbol=0;
            System.out.println("Enter password : ");
            checkpassword = scanStr.nextLine();
            for (int i = 0; i < checkpassword.length(); i++) {
                if(checkpassword.charAt(i) >= 'a' && checkpassword.charAt(i) <= 'z') jLower++;
                else if(checkpassword.charAt(i) >= 'A' && checkpassword.charAt(i) <= 'Z') jUpper++;
                else if(checkpassword.charAt(i) >= '0' && checkpassword.charAt(i) <= '9') jDigit++;
                else jSymbol++;
            }
            if (jLower==0){
                System.out.println("Password must contain lowercase.");
                isPasswordCorrect=false;
            }if (jUpper==0){
                System.out.println("Password must contain uppercase.");
                isPasswordCorrect=false;
            }if (jDigit==0) {
                System.out.println("Password must contain number.");
                isPasswordCorrect = false;
            }if (jSymbol==0) {
                System.out.println("Password must contain symbol.");
                isPasswordCorrect = false;
            }else {
                System.out.println("Password meets the requirements.");
                password = checkpassword;
                isPasswordCorrect=true;
            }
        }
        System.out.println("HI!"+user_id);
        System.out.println("Enter other details for your registration.");
        System.out.println("Enter name : ");
        String name = scanStr.nextLine();
        System.out.println("Enter address :");
        String address = scanStr.nextLine();
        System.out.println("Enter phone number :");
        String phone_num = scanStr.nextLine();
        System.out.println("Enter email address :");
        String email_address = scanStr.nextLine();
        DelRun newDelRun = new DelRun(user_id,password,name,address,phone_num,email_address);
        drivers.add(newDelRun);
        System.out.println("Delivery Runner has been registered. Check the details in 'Delivery Runner Management'.");
    }
    public void delete_data() {
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String delID = "";
        int position = -1;
        int found = -1;
        int optdel = 1;
        while (optdel == 1) {
            System.out.println("Which Delivery Runner you want to delete?");
            System.out.println("Enter Delivery Runner's user id = ");
            delID = scanStr.nextLine();
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getUserID().equals(delID)) {
                    found = 1;
                    position = i;
                }
            }
            if (found != 1) {
                System.out.println("Vendor's User ID cannot be found.");
                System.out.println("Do you want to find another ID?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                optdel = scanInt.nextInt();
            } else if (found == 1) {
                int suredel = -1;
                System.out.println("WARNING : This Delivery Runner will be deleted permanently.\nAre you sure you want to delete this Delivery Runner?");
                System.out.println("1. Yes, delete Delivery Runner.");
                System.out.println("2. No");
                System.out.println("Enter = ");
                suredel = scanInt.nextInt();
                if (suredel == 1){
                    drivers.remove(position);
                    System.out.println("Delivery Runner " + delID + " has been removed");
                    optdel = -1;

                }else{
                    System.out.println("Deleting this vendor has been canceled.");
                    optdel = -1;

                }

            }
        }
    }
    public int countTask(ManageVend mgmven) {
        int count = 1;
        for (int i = 0; i < mgmven.vendors.size(); i++) {
            for (int j = 0; j < mgmven.vendors.get(i).getReceivedOrders().size(); j++) {
                if (mgmven.vendors.get(i).getReceivedOrders().get(j).getStatusOrder().equalsIgnoreCase("Ready for delivery")) {
                    count++;
                }
            }
        }
        return count;
    }
    public void update_data(){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String updatedelrun = "";
        int position = -1;
        int found = -1;
        int optupdate = 1;
        while (optupdate == 1) {
            System.out.println("Which delivery runner you want to update:");
            System.out.println("Enter delivery runner's user id:");
            updatedelrun = scanStr.nextLine();
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getUserID().equals(updatedelrun)) {
                    found = 1;
                    position = i;
                }
            }
            if (found != 1) {
                System.out.println("Delivery runner could not be found. please check your Delivery runner ID.");
                System.out.println("Do you want to continue update Delivery runner?");
                System.out.println("1. Yes");
                System.out.println("0. No");
            } else {
                System.out.println("which one you want to update?");
                System.out.println("1. password");
                System.out.println("2. name");
                System.out.println("3. address");
                System.out.println("4. phone number");
                System.out.println("5. email");
                System.out.println("option = ");
                int optt = scanInt.nextInt();
                drivers.get(position).update(optt);
                optupdate = -1;
            }
        }
    }
    public void viewProfile(int pos){
        System.out.println("================== MY PROFILE ==================");
        System.out.println("                DELIVERY RUNNER                 ");
        System.out.println(drivers.get(pos));
    }
    public void printDelRun(){
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println(drivers.get(i));
        }
    }

    public int checkLogin(String username, String password){
        int position = -1;
        int correct = -1;
        int foundcust = -1;
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getUserID().equals(username)){
                position=i;
                foundcust = 1;
                if (drivers.get(position).getPassword().equals(password)){
                    correct = 1;
                }
            }
        }
        if (correct==1 && foundcust ==1){
            return position;
        }else{
            return -1;
        }
    }
    public void viewTask(ManageVend mgmven){
        int count = 1;
        for (int i = 0; i < mgmven.vendors.size(); i++) {
            for(int j=0; j< mgmven.vendors.get(i).getReceivedOrders().size(); j++){
                if (mgmven.vendors.get(i).getReceivedOrders().get(j).getStatusOrder().equalsIgnoreCase("Ready for delivery")){
                    //System.out.println("Task "+count);
                    mgmven.viewTaskForDelRun(i);
                    count++;
                }
            }
        }
        if (count==1){
            System.out.println("No task at the moment.");
        }
    }
    public int checkOrderIDaccdel(ManageVend mgmven, String orderid){
        boolean orderFound = false;
        int posVen = -1;
        for (int i = 0; i < mgmven.vendors.size(); i++) {
            for(int j=0; j< mgmven.vendors.get(i).getReceivedOrders().size(); j++){
                if (mgmven.vendors.get(i).getReceivedOrders().get(j).getStatusOrder().equalsIgnoreCase("Ready for delivery")){
                    if (mgmven.vendors.get(i).getReceivedOrders().get(j).getOrder_id().equalsIgnoreCase(orderid)){
                        posVen = i;
                    }
                }
            }
        }
        return posVen;
    }
    public int checkOrderIDcomp(ManageVend mgmven, String orderid){
        int posVen = -1;
        for (int i = 0; i < mgmven.vendors.size(); i++) {
            for(int j=0; j< mgmven.vendors.get(i).getReceivedOrders().size(); j++){
                if (mgmven.vendors.get(i).getReceivedOrders().get(j).getStatusOrder().equalsIgnoreCase("Delivered")){
                    if (mgmven.vendors.get(i).getReceivedOrders().get(j).getOrder_id().equalsIgnoreCase(orderid)){
                        posVen = i;
                    }
                }
            }
        }
        return posVen;
    }
    public void acceptTask(ManageVend mgmven, String user_id){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        int orderpos = -1;
        System.out.println("Enter Order ID you want to accept = ");
        String acc_orderid_check = scanStr.nextLine();
        int check = checkOrderIDaccdel(mgmven,acc_orderid_check);
        System.out.println(check);
        if (check!=-1){
            for(int j=0; j< mgmven.vendors.get(check).getReceivedOrders().size(); j++){
                mgmven.vendors.get(check).getReceivedOrders().get(j).setStatusOrder("Delivered");
                System.out.println("Order has been delivered");
                mgmven.vendors.get(check).getReceivedOrders().get(j).setDriver_id(user_id);

            }
        }else{
            System.out.println("Couldn't accept task due to unfinished order");
        }
    }
    public int findDelrunIndex(String findDelrunID){
        for (int i = 0; i < drivers.size(); i++) {
            if(drivers.get(i).getUserID().equals(findDelrunID)){
                return i;
            }
        }
        return -1; //if driver not found
    }
    public void declineTask(ManageVend mgmven){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        boolean orderFound = false;
        int posOrder = -1;
        System.out.println("Enter Order ID you want to decline = ");
        String acc_orderid_check = scanStr.nextLine();
        int check = checkOrderIDaccdel(mgmven,acc_orderid_check);
        if (check!=-1){
            for(int j=0; j< mgmven.vendors.get(check).getReceivedOrders().size(); j++){
                mgmven.vendors.get(check).getReceivedOrders().get(j).setStatusOrder("Ready for Delivery");
                System.out.println("Order has been canceled by this driver");
                mgmven.vendors.get(check).getReceivedOrders().get(j).driver_decline();
                System.out.print("Decline counter for this order = ");
                int count = mgmven.vendors.get(check).getReceivedOrders().get(j).getDeclined_count();
                System.out.println(count);

            }
        }else{
            System.out.println("Couldn't decline task due to unfinished order");
        }
    }
    /*
    public void updateStatusToComplete(ManageVend mgmven){
        System.out.println("Enter Order ID you want to complete = ");
        String acc_orderid_check = scanStr.nextLine();
        int check = checkOrderIDcomp(mgmven,acc_orderid_check);
        System.out.println(check);
        if (check!=-1){

            for(int j=0; j< mgmven.vendors.get(check).getReceivedOrders().size(); j++){
                mgmven.vendors.get(check).getReceivedOrders().get(j).setStatusOrder("Completed");
                //ArrayList<Order> foundOrder = mgmven.vendors.get(check).getReceivedOrders();

                System.out.println("Order has been received by the customer");
                System.out.println("Order has been completed!");
            }
        }else{
            System.out.println("Order still cannot be changed to 'completed'.");
        }
    }

     */
    public void updateStatusToComplete(ManageVend mgmven, ManageCust mgmcust, int pos) {
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        System.out.println("Enter Order ID you want to complete = ");
        String acc_orderid_check = scanStr.nextLine();
        int check = checkOrderIDcomp(mgmven, acc_orderid_check);
        System.out.println(check);

        if (check != -1) {
            ArrayList<Order> receivedOrders = mgmven.vendors.get(check).getReceivedOrders();
            // Find the order with the specified order ID
            for (Order order : receivedOrders) {
                if (order.getOrder_id().equals(acc_orderid_check)) {
                    double delfee = order.getDelFee();
                    // Set the status of the found order to "Completed"
                    order.setStatusOrder("Completed");
                    System.out.println("Order with ID " + acc_orderid_check + " has been completed!");
                    String cust = order.getCustomer().getUserID();
                    String driv = drivers.get(pos).getUserID();
                    drivers.get(pos).addBalance(delfee);
                    removeAndAddtoHist(mgmven,order,check);
                    removeAndAddtoHist(mgmcust, order, cust);
                    removeAndAddtoHist(order,driv);
                    System.out.println("Balance has been added to driver");
                    return; // Exit the loop since the order is found
                }
            }

            // If the loop completes without finding the order
            System.out.println("Order with ID " + acc_orderid_check + " not found.");
        } else {
            System.out.println("Vendor not found. Order still cannot be changed to 'completed'.");
        }
    }
    public void seeBalance(int pos){
        System.out.println(drivers.get(pos).getBalance());
    }
    public void seeReview(int pos){
        for (int i = 0; i < drivers.get(pos).getReceivedReviews().size(); i++) {
            System.out.println(drivers.get(pos).getReceivedReviews().get(i));
        }
    }
    public void removeAndAddtoHist(ManageVend mgmven, Order completedord, int pos){//for vendor
        mgmven.vendors.get(pos).getHistoryOrdersVendor().add(completedord);
        mgmven.vendors.get(pos).getReceivedOrders().remove(completedord);
        //mgmven.vendors.get(pos).getHistoryOrdersVendor().add()
        //mgmven.vendors.get(pos).
    }
    public void removeAndAddtoHist(ManageCust mgmcust, Order completedord,String cust){ //for customer
        int posCust = -1;
        for (int i = 0; i < mgmcust.customers.size(); i++) {
            if (mgmcust.customers.get(i).getUserID().equalsIgnoreCase(cust)){
                posCust = i;
            }
        }
        mgmcust.customers.get(posCust).getHistoryOrderCust().add(completedord);
        mgmcust.customers.get(posCust).getCustomerOrder().remove(completedord);
        mgmcust.customers.get(posCust).getHistoryTransaction().add(completedord);
        //System.out.println("date:");
        //completedord.getDates();

    }
    public void removeAndAddtoHist(Order completeord, String driver_id){ //TAMBAH INI
        int posDriver = -1;
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getUserID().equalsIgnoreCase(driver_id)){
                posDriver = i;
            }
        }
        drivers.get(posDriver).getHistoryOrderDelRun().add(completeord);
    }
    public void viewTaskHistory(int pos){ //TAMBAH INI
        for (int i = 0; i < drivers.get(pos).getHistoryOrderDelRun().size(); i++) {
            drivers.get(pos).getHistoryOrderDelRun().get(i).printTaskforDelrun();
        }
    }
    public double calculateRevenue(date targetDate, int posDelrun){ // DAILY
        double revenue = 0.0;
        ArrayList<Order> historyOrderDelrun = drivers.get(posDelrun).getHistoryOrderDelRun();
        for (Order order : historyOrderDelrun) {
            date placeOrderTime = order.getPlaceOrderTime();

            if (placeOrderTime.getYear() == targetDate.getYear() &&
                    placeOrderTime.getMonth() == targetDate.getMonth() &&
                    placeOrderTime.getDay() == targetDate.getDay()) {
                revenue += order.getDelFee();
            }
        }
        return revenue;
    }
    public double calculateRevenue(int year, int month, int posDelrun) { // MONTHLY
        double revenue = 0.0;
        ArrayList<Order> historyOrderDelrun = drivers.get(posDelrun).getHistoryOrderDelRun();

        for (Order order : historyOrderDelrun) {
            date placeOrderTime = order.getPlaceOrderTime();

            if (placeOrderTime.getYear() == year && placeOrderTime.getMonth() == month) {
                revenue += order.getDelFee();
            }
        }
        return revenue;
    }

    public double calculateRevenue(int year, int posDelrun) { // YEARLY
        double revenue = 0.0;
        ArrayList<Order> historyOrderDelrun = drivers.get(posDelrun).getHistoryOrderDelRun();

        for (Order order : historyOrderDelrun) {
            date placeOrderTime = order.getPlaceOrderTime();

            if (placeOrderTime.getYear() == year) {
                revenue += order.getDelFee();
            }
        }
        return revenue;
    }
    public void calculateLastSevenDaysRevenue(int posDelrun) {
        double dailyRev;
        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println("==================== WEEKLY REPORT ====================");
        for (int i = 6; i >= 0; i--) {
            LocalDateTime targetDate = currentDate.minusDays(i);
            date date = new date(targetDate);

            dailyRev = calculateRevenue(date, posDelrun);

            System.out.println(date.getFormattedDate() + " Revenue: " + dailyRev);
        }
    }
    public void revenueDashboard(int posDelrun){
        int genreport = 0;
        int dailyoption = 0;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        while(genreport!= 5){
            System.out.println("================== REVENUE DASHBOARD ==================");
            System.out.println("                       BALANCE :                       ");
            System.out.println("                          " + drivers.get(posDelrun).getBalance());
            System.out.println("Which report you want to generate ? ");
            System.out.println("1. Daily Report");
            System.out.println("2. Weekly Report");
            System.out.println("3. Monthly Report");
            System.out.println("4. Yearly Report");
            System.out.println("5. Back to the Menu");
            System.out.println("Enter your choice : ");
            genreport = scanInt.nextInt();
            if(genreport == 1){
                while(dailyoption != 4){
                    System.out.println("1. Today");
                    System.out.println("2. Yesterday");
                    System.out.println("3. Enter date");
                    System.out.println("4. Back");
                    System.out.println("Enter choice : ");
                    dailyoption = scanInt.nextInt();
                    if(dailyoption == 1){
                        LocalDateTime todayDateTime = LocalDateTime.now();
                        date today = new date(todayDateTime);

                        double todayRev = calculateRevenue(today, posDelrun );
                        System.out.println("===================== DAILY REPORT ====================");
                        System.out.println("Today's Date    = " + today.getFormattedDate());
                        System.out.println("Daily Revenue   = " + todayRev);
                    }else if(dailyoption == 2){
                        LocalDateTime yesterdayDateTime = LocalDateTime.now().minusDays(1);
                        date yesterday = new date(yesterdayDateTime);

                        double yesterdayRev = calculateRevenue(yesterday, posDelrun);
                        System.out.println("===================== DAILY REPORT ====================");
                        System.out.println("Yesterday's Date = " + yesterday.getFormattedDate());
                        System.out.println("Daily Revenue    = " + yesterdayRev);
                    }else if(dailyoption == 3){
                        System.out.println("Enter year:");
                        int year = scanInt.nextInt();
                        System.out.println("Enter month:");
                        int month = scanInt.nextInt();
                        System.out.println("Enter day:");
                        int day = scanInt.nextInt();

                        LocalDateTime enteredDateTime = LocalDateTime.of(year, month, day, 0, 0);
                        date enteredDate = new date(enteredDateTime);

                        double enteredDateRev = calculateRevenue(enteredDate, posDelrun);

                        System.out.println("===================== DAILY REPORT ====================");
                        System.out.println(enteredDate.getFormattedDate() + "'s revenue");
                        System.out.println("Daily Revenue  = " + enteredDateRev);
                    }
                }
            }else if(genreport == 2){
                calculateLastSevenDaysRevenue(posDelrun);
            }else if(genreport == 3){
                System.out.println("Enter year : ");
                int year = scanInt.nextInt();
                System.out.println("Enter month (1-12):");
                int month = scanInt.nextInt();
                String monthString = Month.of(month).toString();
                double monthlyRev = calculateRevenue(year,month,posDelrun);
                System.out.println("==================== MONTHLY REPORT ===================");
                System.out.println(year + " " + monthString + "'s revenue");
                System.out.println("Monthly Revenue = " + monthlyRev);
            }else if(genreport == 4){
                System.out.println("Enter year : ");
                int year = scanInt.nextInt();
                double yearlyRev = calculateRevenue(year,posDelrun);
                System.out.println("==================== YEARLY REPORT ====================");
                System.out.println(year + "'s revenue");
                System.out.println("Yearly Revenue = " + yearlyRev);
            }
        }
    }
}
