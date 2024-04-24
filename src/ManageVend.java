import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;

public class ManageVend implements InterfaceManageData, Serializable{
    private static final long serialVersionUID = 512971284447009527L;
    transient Scanner scanInt = new Scanner(System.in);
    transient Scanner scanStr = new Scanner(System.in);
    transient Scanner scanDo = new Scanner(System.in);
    ArrayList<Vendor> vendors = new ArrayList<Vendor>();
    int current = -1;
    public ManageVend(){
        // DEFAULT DATA

        //VENDORID;;B OR F;;ITEMID,....
/*
        Vendor v1 = new Vendor("V01","Vendor01_","Pagi Malam","KLCC","01234","pagimalam@vendor.apu.my","Nasi Padang");
        Vendor v2 = new Vendor("V02","Vendor02_","Sate Madura Pak Amat","Bukit Bintang","01235","satemadura@vendor.apu.my","Sate Madura Pak Muh");

        Food item1 = new Food("FD01","Spicy Fried Rice","Savor the bold flavors of Malaysia with our Spicy Fried Rice, a tantalizing blend of fragrant jasmine rice, vibrant vegetables, and a kick of fiery spices that ignite your taste buds.",25);
        Food item2 = new Food("FD02","Nasi Lemak Special","a fragrant coconut rice dish accompanied by anchovies, peanuts, boiled eggs, curry beef, balado eggplant and spicy sambal. ",18);
        Food item3 = new Food("FD03","Sushi Salmon Mentai","Delight in the exquisite harmony of fresh, succulent salmon atop vinegared rice,  with a creamy mentai mayo glaze with Tobiko Topping (5 pcs)",15);
        Beverage item4 = new Beverage("BV01","Teh C Ice","Iced tea with combination of sweet condensed milk",4);
        vendors.add(v1);
        vendors.add(v2);
        //System.out.println(vendors.size());
        vendors.get(0).getMenus().add(item1);
        vendors.get(0).getMenus().add(item2);
        vendors.get(0).getMenus().add(item3);
        vendors.get(0).getMenus().add(item4);
        Food item5 = new Food("FD01","Gulai Kambing","Rich and aromatic Indonesian goat curry simmered in a fragrant blend of spices and coconut milk.",18);
        Food item6 = new Food("FD02","Nasi Opor","Creamy and mildly spiced Indonesian chicken stew cooked with coconut milk, lemongrass, and a medley of aromatic spices.", 10);
        Food item7 = new Food("FD03","Nasi Rawon","Indonesian beef soup enriched with black nuts, infused with a blend of spices, and characterized by its dark color and deep, earthy flavor.",15);
        Beverage item8 = new Beverage("BV01","Es Pisang Ijo","A traditional Indonesian dessert featuring slices of banana wrapped in green rice flour pancake, served with sweet syrup and coconut milk.",8);
        Beverage item9 = new Beverage("BV02","Apple Juice","Fresh apple juice",6);
        vendors.get(1).getMenus().add(item5);
        vendors.get(1).getMenus().add(item6);
        vendors.get(1).getMenus().add(item7);
        vendors.get(1).getMenus().add(item8);
        vendors.get(1).getMenus().add(item9);

 */


    }
    public ArrayList<Vendor> getVendorList(){
        return vendors;
    }

    public void insert_data() {
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String user_id = "";
        String checkuser_id = "";
        String password = "";
        int takenid = -1;
        int wrongpw = -1;
        boolean isUserIDTaken = true;
        boolean isPasswordCorrect = false;
        // taken id = 1 -> id nya blm kepake, loop ke stop
        // wrong pasword = 1 -> pw dah betul, loop nya ke stop
        // wrongps = 0 -> masih salah, loop lagi
        while (isUserIDTaken != false) {
            System.out.println("==================== VENDOR REGISTRATION ===================");
            System.out.println("User ID\t:");
            checkuser_id = scanStr.nextLine();
            int found = -1;
            for (int i = 0; i < vendors.size(); i++) {
                if (vendors.get(i).getUserID().equals(checkuser_id)) {
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
        Vendor newvendor = new Vendor();
        newvendor.insert(user_id);
        vendors.add(newvendor);
        System.out.println("Vendor has been registered. Check the details in 'Vendor Management'.");
    }
    public void update_data() {
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String updateVen = "";
        int position = -1;
        int found = -1;
        int optupdate = 1;
        while (optupdate == 1) {
            System.out.println("Which vendor you want to update?");
            System.out.println("Enter vendor's user id = ");
            updateVen = scanStr.nextLine();
            for (int i = 0; i < vendors.size(); i++) {
                if (vendors.get(i).getUserID().equals(updateVen)) {
                    found = 1;
                    position = i;
                }
            }
            if (found != 1) {
                System.out.println("Vendor could not be found. please check your vendor ID.");
                System.out.println("Do you want to continue update vendor?");
                System.out.println("1. Yes");
                System.out.println("0. No");
            } else {

                System.out.println("Which one you want to update?");
                System.out.println("1. Password");
                System.out.println("2. Name");
                System.out.println("3. Address");
                System.out.println("4. Phone Number");
                System.out.println("5. Email");
                System.out.println("6. Restaurant Description");
                System.out.println("Enter your choice = ");
                int optt = scanInt.nextInt();
                vendors.get(position).update(optt);
                optupdate=-1;
            }
        }
    }
    public void printVendorDetail(){
        for (int i = 0; i < vendors.size(); i++) {
            System.out.println(vendors.get(i).toString());
        }
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
            System.out.println("Which vendor you want to delete?");
            System.out.println("Enter vendor's user id = ");
            delID = scanStr.nextLine();
            for (int i = 0; i < vendors.size(); i++) {
                if (vendors.get(i).getUserID().equals(delID)) {
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
                System.out.println("WARNING : This vendor data will be deleted permanently.\nAre you sure you want to delete this Vendor?");
                System.out.println("1. Yes, delete vendor.");
                System.out.println("2. No");
                System.out.println("Enter = ");
                suredel = scanInt.nextInt();
                if (suredel == 1){
                    vendors.remove(position);
                    System.out.println("Vendor " + delID + " has been removed");
                    optdel = -1;
                }else{
                    System.out.println("Deleting this vendor has been canceled.");
                    optdel = -1;

                }
            }
        }
    }
    public int checkLogin(String user_id, String password){

        int position = -1;
        int correct = -1;
        for (int i = 0; i < vendors.size(); i++) {
            if (vendors.get(i).getUserID().equals(user_id)){
                position = i;
                current = i;
                if (vendors.get(position).getPassword().equals(password)){
                    correct = 1;
                    position = i;
                }
            }
        }
        if (correct == 1){
            return position;
        }else{
            return -1;
        }
    }
    public void insertItem(){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        if (scanDo == null) {
            scanDo = new Scanner(System.in);
        }

        System.out.println("Choose the type of item you want to insert : ");
        System.out.println("1. Food");
        System.out.println("2. Beverage");
        System.out.println("Enter your choice : ");
        int type = scanInt.nextInt();

        String item_id = "";
        String checkitem_id = "";
        boolean isItemIDTaken = true;
        while(isItemIDTaken != false){
            System.out.println("Insert new item to the menu");
            System.out.println("Item ID : ");
            checkitem_id = scanStr.nextLine();
            int found = -1;
            for (int i = 0; i < vendors.get(current).getMenus().size(); i++) {
                if(vendors.get(current).getMenus().get(i).getItem_id().equals(checkitem_id)){
                    found = 1;
                }
            }
            if(found == 1){
                System.out.println("Item ID has been used, please find another ID");
                isItemIDTaken = true; //balik ke looping lagi
            } else {
                isItemIDTaken = false; //berhenti dari loop, langsung lanjut ke bawah
            }
        }
        item_id = checkitem_id;

        System.out.println("Item name : ");
        String name = scanStr.nextLine();
        System.out.println("Description : ");
        String desc = scanStr.nextLine();
        System.out.println("Price\t:");
        double price = scanDo.nextDouble();

        if(type == 1){
            Food newFood = new Food(item_id,name,desc,price);
            vendors.get(current).getMenus().add(newFood);
        }else if(type == 2){
            Beverage newBeverage = new Beverage(item_id,name,desc,price);
            vendors.get(current).getMenus().add(newBeverage);
        }
    }
    public void updateItem(){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        if (scanDo == null) {
            scanDo = new Scanner(System.in);
        }
        String item_id = "";
        int position = -1;
        int found = -1;
        int optupdate = 1;

        while(optupdate == 1){
            System.out.println("Which item you want to update : ");
            System.out.println("Enter item ID : ");
            item_id = scanStr.nextLine();
            for (int i = 0; i < vendors.get(current).getMenus().size(); i++) {
                if(vendors.get(current).getMenus().get(i).getItem_id().equals(item_id)){
                    found = 1;
                    position = i;
                }
            }
            if(found != 1){
                System.out.println("Item could not be found, please check your Item ID.");
                System.out.println("Do you want to continue update Item?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                optupdate = scanInt.nextInt();
            }else{
                System.out.println("Which one you want to update?");
                System.out.println("1. Item Name");
                System.out.println("2. Item Description");
                System.out.println("3. Item Price");
                int opt2 = scanInt.nextInt();
                if(opt2 == 1){
                    System.out.println("Enter new item name : ");
                    String newName = scanStr.nextLine();
                    vendors.get(current).getMenus().get(position).setName(newName);
                }else if(opt2 == 2){
                    System.out.println("Enter new item description : ");
                    String newDesc = scanStr.nextLine();
                    vendors.get(current).getMenus().get(position).setDesc(newDesc);
                }else if(opt2 == 3){
                    System.out.println("Enter new price : ");
                    double newPrice = scanDo.nextDouble();
                    vendors.get(current).getMenus().get(position).setPrice(newPrice);
                }
            }
        }
    }
    public void deleteItem(){
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
        while(optdel == 1){
            System.out.println("Which item you want to delete : ");
            System.out.println("Enter item ID : ");
            delID = scanStr.nextLine();
            for (int i = 0; i < vendors.get(current).getMenus().size(); i++) {
                if(vendors.get(current).getMenus().get(i).getItem_id().equals(delID)){
                    found = 1;
                    position = i;
                }
            }
            if(found != 1){
                System.out.println("Item ID cannot be found.");
                System.out.println("Do you still want to find another ID?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your choice : ");
                optdel = scanInt.nextInt();
            }else if(found == 1){
                int suredel = -1;
                System.out.println("Are you sure want to delete this Item?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your choice : ");
                suredel = scanInt.nextInt();
                if(suredel == 1){
                    vendors.get(current).getMenus().remove(position);
                    System.out.println("Item " + delID + " has been removed.");
                    optdel=-1;
                }else{
                    System.out.println("Delete this Item has been canceled.");
                    optdel=-1;
                }
            }
        }
    }
    public void viewProfile(int pos){
        System.out.println("================== MY PROFILE ==================");
        System.out.println("                     VENDOR                     ");
        System.out.println(vendors.get(pos));
    }
    public void viewItemList(){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        System.out.println("Which menu you want to see ? ");
        System.out.println("1. Full Menu");
        System.out.println("2. Food");
        System.out.println("3. Beverage");
        int optmenu = scanInt.nextInt();
        if(optmenu == 1){
            System.out.println("FULL MENU");
            for (int i = 0; i < vendors.get(current).getMenus().size(); i++) {
                System.out.println("ITEM " + (i+1));
                System.out.println(vendors.get(current).getMenus().get(i));
            }
        }else if(optmenu == 2){
            System.out.println("FOOD MENU");
            for (int i = 0; i < vendors.get(current).getMenus().size(); i++) {
                if(vendors.get(current).getMenus().get(i) instanceof Food){
                    System.out.println(vendors.get(current).getMenus().get(i));
                }
            }
        }else if(optmenu == 3){
            System.out.println("BEVERAGE MENU");
            for (int i = 0; i < vendors.get(current).getMenus().size(); i++) {
                if(vendors.get(current).getMenus().get(i) instanceof Beverage){
                    System.out.println(vendors.get(current).getMenus().get(i));
                }
            }
        }
    }
    public void viewPendingOrderList(int pos){
        for (int i = 0; i < vendors.get(pos).getReceivedOrders().size(); i++) {
            if (vendors.get(pos).getReceivedOrders().get(i).getStatusOrder().equalsIgnoreCase("pending")){
                vendors.get(pos).getReceivedOrders().get(i).showOrderDetails();
            }
        }
    }
    public void venAccOrder(ManageCust mgmcust){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        boolean orderFound = false;
        int orderPosition = -1;
        System.out.print("Enter order ID that you want to accept/reject");
        String orderID = scanStr.nextLine();
        ArrayList<Order> chooseOrder = vendors.get(current).getReceivedOrders();
        for (int i = 0; i <chooseOrder.size() ; i++){
            if (chooseOrder.get(i).getOrder_id().equalsIgnoreCase(orderID)) {
                orderFound = true;
                orderPosition = i;
            }
        }
        if (orderFound){
            chooseOrder.get(orderPosition).setStatusOrder("Accepted");
            vendors.get(current).addrevenue(chooseOrder.get(orderPosition).getSubtotal());
            System.out.println("Order's status has ben set to 'accepted' and revenue has been added to this vendor");
        }
    }
    public void venRejectOrder(ManageCust mgmcust){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        int custPos = 0;
        boolean orderFound = false;
        int orderPosition = -1;
        System.out.println("Enter order ID that you want to accept/reject");
        String orderID = scanStr.nextLine();
        ArrayList<Order> chooseOrder = vendors.get(current).getReceivedOrders();
        for (int i = 0; i <chooseOrder.size() ; i++){
            if (chooseOrder.get(i).getOrder_id().equals(orderID)) {
                orderFound = true;
                orderPosition = i;
            }
        }
        if (orderFound){
            chooseOrder.get(orderPosition).setStatusOrder("Rejected");
            System.out.println("Order's status has ben set to 'rejected'.");
            String custid = chooseOrder.get(orderPosition).getCustomer().getUserID();
            for (int i = 0; i < mgmcust.customers.size(); i++) {
                if(mgmcust.customers.get(i).getUserID().equals(custid)){
                    custPos = i;
                }
            }
            double delfee = chooseOrder.get(orderPosition).calcDelFee();
            double refundAmount = chooseOrder.get(orderPosition).calcTotalOrderAmount(delfee, 1);
            // refund customer
            mgmcust.refundBalance(custPos, refundAmount);

        }
    }

    public void showReceivedOrder(int pos){
        vendors.get(pos).viewReceivedOrder();
    }
    public void viewOrderList(int pos){
        for (int i = 0; i < vendors.get(pos).getReceivedOrders().size(); i++) {
            vendors.get(pos).getReceivedOrders().get(i).showOrderDetails();
        }
    }

    public void viewTaskForDelRun(int pos){
        for (int i = 0; i < vendors.get(pos).getReceivedOrders().size(); i++){
            vendors.get(pos).getReceivedOrders().get(i).printTaskforDelrun();
        }
    }

    /*
    public void addToRevenue(int pos){
        vendors.get(pos).addrevenue();
        System.out.println("Revenue has been added to vendor's balance.");

    }

     */
    public void printVendor() {
        for (int i = 0; i < vendors.size(); i++) {
            System.out.println(vendors.get(i).getUserID() + "\n" +
                    vendors.get(i).getName() + "\n" +
                    vendors.get(i).getDesc());
            if(vendors.get(i).getAverageRating() > 0.0){
                vendors.get(i).printAverageRating();
            }else if(vendors.get(i).getAverageRating() == 0.0){
                System.out.println("No ratings available.\n");
            }
        }
    }
    public void updateStatusOrder(int pos, int posOrder, ManageCust mgmcust){
        int serviceType = vendors.get(pos).getReceivedOrders().get(posOrder).getServiceType();

        if(serviceType == 1 || serviceType == 2){ // DINE IN OR TAKEAWAY
            System.out.println("What status you want to update this order? ");
            System.out.println("1. Prepared");
            System.out.println("2. Completed");
            int opt = scanInt.nextInt();
            Order order = vendors.get(pos).getReceivedOrders().get(posOrder);

            if (opt==1){
                if (order.getStatusOrder().equalsIgnoreCase("Accepted")){
                    order.setStatusOrder("Prepared");
                    System.out.println("Order's status has been set to 'prepared'.");
                }else{
                    System.out.println("Cannot start preparing order unless you 'ACCEPT' the order first.");
                }
            }else if(opt==2){
                if (order.getStatusOrder().equalsIgnoreCase("Prepared")) {
                    order.setStatusOrder("Completed");
                    System.out.println("Order's status has been set to 'completed'.");
                    String cust = order.getCustomer().getUserID();
                    removeAndAddtoHist(order, pos);
                    removeAndAddtoHist(mgmcust, order, cust);
                }else{
                    System.out.println("Cannot complete order unless you 'PREPARE' the order first.");
                }
            }
        }else if(serviceType == 3){ //DELIVERY
            System.out.println("What status you want to update this order? ");
            System.out.println("1. Prepared");
            System.out.println("2. Find Drivers");
            int opt = scanInt.nextInt();
            if (opt==1){
                if (vendors.get(pos).getReceivedOrders().get(posOrder).getStatusOrder().equalsIgnoreCase("Accepted")){
                    vendors.get(pos).getReceivedOrders().get(posOrder).setStatusOrder("Prepared");
                    System.out.println("Order's status has been set to 'prepared'.");
                }else{
                    System.out.println("Cannot start preparing order unless you 'ACCEPT' the order first.");
                }
            }else if(opt==2){
                if (vendors.get(pos).getReceivedOrders().get(posOrder).getStatusOrder().equalsIgnoreCase("Prepared")) {
                    vendors.get(pos).getReceivedOrders().get(posOrder).setStatusOrder("Ready for Delivery");
                    System.out.println("Order's status has been set to 'ready for delivery'.");
                }else{
                    System.out.println("Cannot deliver order unless you 'PREPARE' the order first.");
                }
            }
        }
    }
    public void removeAndAddtoHist(Order completedord, int pos){
        vendors.get(pos).getHistoryOrdersVendor().add(completedord);
        vendors.get(pos).getReceivedOrders().remove(completedord);
    }
    public void removeAndAddtoHist(ManageCust mgmcust, Order completedord, String cust){
        int posCust = -1;
        for (int i = 0; i < mgmcust.customers.size(); i++) {
            if(mgmcust.customers.get(i).getUserID().equals(cust)){
                posCust = i;
            }
        }
        mgmcust.customers.get(posCust).getHistoryOrderCust().add(completedord);
        mgmcust.customers.get(posCust).getCustomerOrder().remove(completedord);
        mgmcust.customers.get(posCust).getHistoryTransaction().add(completedord);
        System.out.println("Date:");
        completedord.getOrderDateFormatted();
        completedord.getOrderTime();
    }
    public double calculateRevenue(date targetDate, int posVendor){ // DAILY
        double revenue = 0.0;
        ArrayList<Order> historyOrdersVendor = vendors.get(posVendor).getHistoryOrdersVendor();
        for (Order order : historyOrdersVendor) {
            date placeOrderTime = order.getPlaceOrderTime();
            if (placeOrderTime.getYear() == targetDate.getYear() &&
                    placeOrderTime.getMonth() == targetDate.getMonth() &&
                    placeOrderTime.getDay() == targetDate.getDay()) {
                System.out.println(order.getSubtotal());
                revenue += order.getSubtotal();
            }
        }
        return revenue;
    }
    public double calculateRevenue(int year, int month, int posVendor) { // MONTHLY
        double revenue = 0.0;
        ArrayList<Order> historyOrdersVendor = vendors.get(posVendor).getHistoryOrdersVendor();

        for (Order order : historyOrdersVendor) {
            date placeOrderTime = order.getPlaceOrderTime();
            if (placeOrderTime.getYear() == year && placeOrderTime.getMonth() == month) {
                revenue += order.getSubtotal();
            }
        }
        return revenue;
    }
    public double calculateRevenue(int year, int posVendor) { // YEARLY
        double revenue = 0.0;
        ArrayList<Order> historyOrdersVendor = vendors.get(posVendor).getHistoryOrdersVendor();

        for (Order order : historyOrdersVendor) {
            date placeOrderTime = order.getPlaceOrderTime();

            if (placeOrderTime.getYear() == year) {
                revenue += order.getSubtotal();
            }
        }
        return revenue;
    }
    public void calculateLastSevenDaysRevenue(int posVendor) {
        double dailyRev;
        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println("==================== WEEKLY REPORT ====================");
        for (int i = 6; i >= 0; i--) {
            LocalDateTime targetDate = currentDate.minusDays(i);
            date date = new date(targetDate);

            dailyRev = calculateRevenue(date, posVendor);

            System.out.println(date.getFormattedDate() + " Revenue: " + dailyRev);
        }
    }
    public void revenueDashboard(int posVendor){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        int genreport = 0;
        int dailyoption = 0;

        while(genreport!= 5){
            System.out.println("================== REVENUE DASHBOARD ==================");
            System.out.println("                       BALANCE :                       ");
            System.out.println("                          " + vendors.get(posVendor).getBalance());
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

                        double todayRev = calculateRevenue(today, posVendor);
                        System.out.println("===================== DAILY REPORT ====================");
                        System.out.println("Today's Date    = " + today.getFormattedDate());
                        System.out.println("Daily Revenue   = " + todayRev);
                    }else if(dailyoption == 2){
                        LocalDateTime yesterdayDateTime = LocalDateTime.now().minusDays(1);
                        date yesterday = new date(yesterdayDateTime);

                        double yesterdayRev = calculateRevenue(yesterday, posVendor);
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

                        double enteredDateRev = calculateRevenue(enteredDate, posVendor);

                        System.out.println("===================== DAILY REPORT ====================");
                        System.out.println(enteredDate.getFormattedDate() + "'s revenue");
                        System.out.println("Daily Revenue    = " + enteredDateRev);
                    }
                }
            }else if(genreport == 2){
                calculateLastSevenDaysRevenue(posVendor);
            }else if(genreport == 3){
                System.out.println("Enter year : ");
                int year = scanInt.nextInt();
                System.out.println("Enter month (1-12):");
                int month = scanInt.nextInt();
                String monthString = Month.of(month).toString();
                double monthlyRev = calculateRevenue(year,month,posVendor);
                System.out.println("==================== MONTHLY REPORT ===================");
                System.out.println(year + " " + monthString + "'s revenue");
                System.out.println("Monthly Revenue = " + monthlyRev);
            }else if(genreport == 4){
                System.out.println("Enter year : ");
                int year = scanInt.nextInt();
                double yearlyRev = calculateRevenue(year,posVendor);
                System.out.println("==================== YEARLY REPORT ====================");
                System.out.println(year + "'s revenue");
                System.out.println("Yearly Revenue  = " + yearlyRev);
            }
        }
    }
    public void filterVendorRating(int rating){
        int countRating = 0;
        if(rating == 1){
            System.out.println("Vendors with rating " + rating + ":");
            for (int i = 0; i < vendors.size(); i++) {
                if(vendors.get(i).getAverageRating() >= 1 && vendors.get(i).getAverageRating() < 2){
                    System.out.println(vendors.get(i));
                    System.out.println("=========Detail Review and Rating===========");
                    for (int j = 0; j <vendors.get(i).getReceivedReviews().size(); j++) {
                        vendors.get(i).getReceivedReviews().get(j).getReview();
                    }
                    countRating++;
                }
            }
            if(countRating == 0){
                System.out.println("There is no available vendor with " + rating + " rating.");
                printVendor();
            }
        }else if(rating == 2){
            System.out.println("Vendors with rating " + rating + ":");
            for (int i = 0; i < vendors.size(); i++) {
                if(vendors.get(i).getAverageRating() >= 2 && vendors.get(i).getAverageRating() < 3){
                    System.out.println(vendors.get(i));
                    System.out.println("=========Detail Review and Rating===========");
                    for (int j = 0; j <vendors.get(i).getReceivedReviews().size(); j++) {
                        vendors.get(i).getReceivedReviews().get(j).getReview();
                    }
                    countRating++;
                }
            }if(countRating == 0){
                System.out.println("There is no available vendor with " + rating + " rating.");
                printVendor();
            }
        }else if(rating == 3){
            System.out.println("Vendors with rating " + rating + ":");
            for (int i = 0; i < vendors.size(); i++) {
                if(vendors.get(i).getAverageRating() >= 3 && vendors.get(i).getAverageRating() < 4){
                    System.out.println(vendors.get(i));
                    System.out.println("=========Detail Review and Rating===========");
                    for (int j = 0; j <vendors.get(i).getReceivedReviews().size(); j++) {
                        vendors.get(i).getReceivedReviews().get(j).getReview();
                    }
                    countRating++;
                }
            }if(countRating == 0){
                System.out.println("There is no available vendor with " + rating + " rating.");
                printVendor();
            }
        }else if(rating == 4){
            System.out.println("Vendors with rating " + rating + ":");
            for (int i = 0; i < vendors.size(); i++) {
                if(vendors.get(i).getAverageRating() >= 4 && vendors.get(i).getAverageRating() < 5){
                    System.out.println(vendors.get(i));
                    System.out.println("=========Detail Review and Rating===========");
                    //System.out.println("size = "+vendors.get(i).getReceivedReviews().size());
                    for (int j = 0; j <vendors.get(i).getReceivedReviews().size(); j++) {
                        vendors.get(i).getReceivedReviews().get(j).getReview();
                    }
                    countRating++;
                }
            }if(countRating == 0){
                System.out.println("There is no available vendor with " + rating + " rating.");
                printVendor();
            }
        }else if(rating == 5){
            System.out.println("Vendors with rating " + rating + ":");
            for (int i = 0; i < vendors.size(); i++) {
                if(vendors.get(i).getAverageRating() == 5){
                    System.out.println(vendors.get(i));
                    System.out.println("=========Detail Review and Rating===========");
                    for (int j = 0; j <vendors.get(i).getReceivedReviews().size(); j++) {
                        vendors.get(i).getReceivedReviews().get(j).getReview();
                    }
                    countRating++;
                }
            }if(countRating == 0){
                System.out.println("There is no available vendor with " + rating + " rating.");
                printVendor();
            }
        }

    }
    public int findOrderIdtoUpdate(int pos, String orderID){
        int foundOrderId = -1;
        for (int i = 0; i < vendors.get(pos).getReceivedOrders().size(); i++) {
            if (vendors.get(pos).getReceivedOrders().get(i).getOrder_id().equalsIgnoreCase(orderID)){
                foundOrderId =i;
            }
        }
        return foundOrderId;
    }
    public void sortVendorsByRating(){
        Collections.sort(vendors);
    }

    public void seeReview(int pos){
        for (int i = 0; i < vendors.get(pos).getReceivedReviews().size(); i++) {
            vendors.get(pos).getReceivedReviews().get(i).getReview();
        }
    }
    public int findVendorIndex(String findVendorID){
        for (int i = 0; i < vendors.size(); i++) {
            if(vendors.get(i).getUserID().equals(findVendorID)){
                return i;
            }
        }
        return -1; //if vendor not found
    }
    public void viewHistoryOrder(int pos){
        for (int i = 0; i < vendors.get(pos).getHistoryOrdersVendor().size(); i++) {
            vendors.get(pos).getHistoryOrdersVendor().get(i).showOrderDetails();
        }
    }
}


