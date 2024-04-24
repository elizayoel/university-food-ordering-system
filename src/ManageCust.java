// YG DIPAKE
import java.util.ArrayList;
import java.util.Scanner;

import java.io.Serializable;

public class ManageCust implements InterfaceManageData, Serializable{
    private static final long serialVersionUID = -4801943309981735143L;
    transient Scanner scanInt = new Scanner(System.in);
    transient Scanner scanStr = new Scanner(System.in);
    transient Scanner scanDbl = new Scanner(System.in);

    ArrayList<Customer> customers = new ArrayList<Customer>();

    Order newOrder = null;
    int position = -1;
    int current = -1;
    int done = -1;
    // DEFAULT CONSTRUCTOR
    public ManageCust(){
/*
        Customer c1 = new Customer("C01","Cust01_","Eliza","Parkhilll Residence","0123096514","eliza@cust.apu.my");
        Customer c2 = new Customer("C02","Cust02_","Vicenza","Parkhill Residence","0289167039","vicenza@cust.apu.my");
        Customer c3 = new Customer("C03","Cust03_","Justyn","Endah Promenade","0390882708","justyn@cust.apu.my");
        customers.add(c1); customers.add(c2); customers.add(c3);
        //c1.topup(new TopUp("12 January 2023",50,"pending"));
        c1.addBalance(50);
        c2.topup(new TopUp("10 February 2023",20,"pending"));
        //c2.addBalance(20);

 */


    }
    // PARAMETER CONSTRUCTOR
    public ManageCust(String user_id, String password, String name, String address, String phone_num, String email_add){
        Customer newCust = new Customer(user_id, password, name, address, phone_num, email_add);
        customers.add(newCust);
    }
    public void insert_data(){

        String user_id = "";
        String checkuser_id = "";
        boolean isUserIDTaken = true;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        while(isUserIDTaken!=false) {
            System.out.println("==================== CUSTOMER REGISTRATION ===================");
            System.out.println("User ID\t:");
            checkuser_id = scanStr.nextLine();
            int found = -1;
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUserID().equals(checkuser_id)) {
                    found = 1;
                }
            }
            if (found == 1) {
                System.out.printf("Username is already taken, please find another user ID.\n");
                isUserIDTaken = true;
            } else {
                isUserIDTaken = false;
            }
        }
        user_id = checkuser_id;
        Customer newcust = new Customer();
        newcust.insert(user_id);
        customers.add(newcust);
        System.out.println("Customer has been registered. Check the details in 'Customer Management'.");
    }

    public int checkLogin(String user_id, String password){

        int position = -1;
        int correct = -1;
        int foundcust = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUserID().equals(user_id)){
                position=i;
                foundcust = 1;
                current = i;
                if (customers.get(position).getPassword().equals(password)){
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
    public void update_data(){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String updatecust = "";
        int position = -1;
        int found = -1;
        int optupdate = 1;
        while (optupdate == 1) {
            System.out.println("Which customer you want to update?");
            System.out.print("Enter customer's user id = \n");
            updatecust = scanStr.nextLine();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUserID().equals(updatecust)) {
                    found = 1;
                    position = i;
                }
            }
            if (found != 1) {
                System.out.println("Customer could not be found. please check your Customer ID.");
                System.out.println("Do you want to continue update Customer?");
                System.out.println("1. Yes");
                System.out.println("0. No");
            } else {
                System.out.println("Which one you want to update?");
                System.out.println("1. Password");
                System.out.println("2. Name");
                System.out.println("3. Address");
                System.out.println("4. Phone Number");
                System.out.println("5. Email");
                System.out.println("Enter your choice = ");
                int optt = scanInt.nextInt();
                customers.get(position).update(optt);
                optupdate = -1;
            }
        }
    }
    public void delete_data(){
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
            System.out.println("Which Customer you want to delete?");
            System.out.print("Enter Customer's user ID =");
            delID = scanStr.nextLine();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUserID().equals(delID)) {
                    found = 1;
                    position = i;
                }
            }
            if (found != 1) {
                System.out.println("Customer's User ID cannot be found.");
                System.out.println("Do you want to find another ID?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                optdel = scanInt.nextInt();
            } else if (found == 1) {
                int suredel = -1;
                System.out.println("WARNING : This customer will be deleted permanently.\nAre you sure you want to delete this Customer?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter = ");
                suredel = scanInt.nextInt();
                if (suredel == 1){
                    customers.remove(position);
                    System.out.println("Customer " + delID + " has been removed");
                    optdel = -1;

                }else{
                    System.out.println("Delete this Customer has been canceled.");
                    optdel = -1;

                }
            }
        }
    }
    public void viewProfile(int pos){
        System.out.println("================== MY PROFILE ==================");
        System.out.println("                    CUSTOMER                    ");
        System.out.println(customers.get(pos));
    }

    public int pendingCount(){
        int count = 0;
        for (int i = 0; i < customers.size(); i++) {
            for( int j = 0; j< customers.get(i).getHistorytopup().size(); j++ ){
                if (customers.get(i).getHistorytopup().get(j).getStatus().equals("pending")){
                    count++;
                }
            }
        }return count;
    }
    public void showPendingTopUp(){
        int position = -1;
        String user_id="";
        for (int i = 0; i < customers.size(); i++) {
            for( int j = 0; j< customers.get(i).getHistorytopup().size(); j++ ){
                if (customers.get(i).getHistorytopup().get(j).getStatus().equals("pending")){
                    position=i;
                    user_id= customers.get(position).getUserID();
                    System.out.println(user_id);
                }
            }
        }
    }
    public int checkAccCust(String userid){
        int found = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUserID().equals(userid)){
                found = i;
            }
        }
        return found;
    }
    public void acceptReqAdmin(int pos){
        for (int i = 0; i < customers.get(pos).getHistorytopup().size(); i++) {
            customers.get(pos).getHistorytopup().get(i).setStatus("accepted");
            double amountTopUp = customers.get(pos).getHistorytopup().get(i).getAmount();
            customers.get(pos).addBalance(amountTopUp);

        }
    }
    public void printReceipt(int pos){
        for (int i = 0; i < customers.get(pos).getHistorytopup().size(); i++) {
            if (customers.get(pos).getHistorytopup().get(i).getStatus().equalsIgnoreCase("successfully added to balance")){
                customers.get(pos).getHistorytopup().get(i).showTopUpCust();
            }
        }

    }
    public void rejectReqAdmin(int pos){
        for (int i = 0; i < customers.get(pos).getHistorytopup().size(); i++) {
            customers.get(pos).getHistorytopup().get(i).setStatus("rejected");
        }
    }
    public void confirmStatus(int pos){
        for (int i = 0; i < customers.get(pos).getHistorytopup().size(); i++) {
            System.out.println(customers.get(pos).getHistorytopup().get(i).getStatus());

        }
    }
    public void topupCredit(int position){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }if(scanDbl==null){
            scanDbl = new Scanner(System.in);

        }
        String date ="";
        double amount = 0.0;
        String status ="";
        System.out.println("Insert amount to topup = ");
        amount = scanDbl.nextDouble();
        System.out.println("Request to topup credit has been sent to admin");
        status = "pending";
        TopUp topup = new TopUp(date,amount,status);
        customers.get(position).topup(topup);
        System.out.println("Waiting for admin to accept the request...");
    }
    public void showBalance(int pos){
        System.out.println("Balance = "+customers.get(pos).getBalance());
    }

    public void printCustomer(){
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
    }
    public void refundBalance(int custPos, double refundAmount){

        customers.get(custPos).refundCust(refundAmount);
    }
    public void placeAndPayment(Order newOrder, int pos, ManageVend mgmven){
        int topupchoice = 0;

        newOrder.setDelFee();
        newOrder.setSubtotal();
        newOrder.setTotal_price();

        System.out.println("Distance        = " + newOrder.getDistance()+ " KM");
        System.out.println("Delivery fee    = RM " + newOrder.getDelFee());
        System.out.println("Subtotal        = RM " + newOrder.getSubtotal());
        System.out.println("Final price     = RM " + newOrder.getTotal_price());
        if(isBalanceEnough(pos,newOrder)) {
            // deduct customer's balance

            double currentBalance = customers.get(pos).getBalance();
            double updatedBalance = currentBalance - newOrder.getTotal_price();
            // deduct customer balance
            customers.get(pos).setBalance(updatedBalance);
            // proceed with order placement
            mgmven.vendors.get(position).receiveOrder(newOrder);
            customers.get(pos).getCustomerOrder().add(newOrder);

            System.out.println("Order placed successfully!");
            System.out.println("Remaining balance = " + customers.get(pos).getBalance());

        }else{
            System.out.println("Insufficient balance. Do you want to top up?\n1. Yes\n2. No");
            topupchoice = scanInt.nextInt();
            if(topupchoice == 1){
                topupCredit(pos);
            }else if(topupchoice == 2){
                System.out.println("Order cannot be placed because your balance is not enough.");
            }
        }
    }
    public void viewAndPlace(ManageVend mgmven, int pos){
        int choice = 0;
        int addorder = 0;
        int changevend = 0;
        int serviceType = 0;
        int viewmenu = 0;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }

        while(changevend != 2){
            boolean vendorFound = browseVendor(mgmven);
            if(vendorFound){
                Vendor selectedVendor=mgmven.vendors.get(position);
                ArrayList<Item> vendorMenu = selectedVendor.getMenus();
                addorder = 0;
                System.out.println("Do you want to continue to place order? ");
                System.out.println("1. Yes");
                System.out.println("2. No");
                viewmenu = scanInt.nextInt();

                if(viewmenu == 1) {
                    System.out.println("Choose the type of service you want : ");
                    System.out.println("1. Dine in");
                    System.out.println("2. Take Away");
                    System.out.println("3. Delivery");

                    serviceType = scanInt.nextInt();

                    while (addorder != 2) {
                        viewMenu(vendorMenu);
                        System.out.println("Would you like to make an order?");
                        System.out.println("1. Yes\n2. No");
                        choice = scanInt.nextInt();
                        if (choice == 1 && addorder != 2) {
                            if (addorder == 0) {
                                newOrder = null;
                            }
                            selectOrder(mgmven, addorder, serviceType);
                            System.out.println("Do you want to add any order for this vendor?");
                            System.out.println("1. Yes\n2. No");
                            System.out.println("Choice = ");
                            addorder = scanInt.nextInt();
                            if (addorder == 2) {
                                // order will be placed
                                placeAndPayment(newOrder, pos, mgmven);
                            }
                        } else if (choice == 2) {
                            System.out.println("Back to Menu Page");
                            addorder = 2;
                        }
                    }
                }
                System.out.println("Do you want to order from another vendor?");
                System.out.println("1. Yes\n2. No");
                System.out.println("Choice = ");
                changevend = scanInt.nextInt();
            }
        }
    }
    public void reorderByHistory(ManageVend mgmven, int pos){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        int reorder = -1;
        String reorderID = "";
        boolean orderHistFound = false;
        int orderHistPos = -1;
        int addMore = 0;
        int itemIndex =0;
        System.out.println("Your order history are :");
        viewOrderHist(pos);
        System.out.println("Do you want to reorder using your order history?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        reorder = scanInt.nextInt();
        while(reorder != 2){
            if(reorder == 1){
                System.out.println("Enter the order ID you want to reorder : ");
                reorderID = scanStr.nextLine();
                for (int i = 0; i < customers.get(pos).getHistoryOrderCust().size(); i++) {
                    if(customers.get(pos).getHistoryOrderCust().get(i).getOrder_id().equals(reorderID)){
                        orderHistFound = true;
                        orderHistPos = i;
                    }
                }
                if(orderHistFound){
                    Order lastOrder = customers.get(pos).getHistoryOrderCust().get(orderHistPos);
                    // copy the order
                    Order newOrder = new Order(lastOrder);
                    System.out.println("Reordered items: ");
                    newOrder.showOrderDetails();
                    while(addMore!=2){
                        System.out.println("Do you want to add more items?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        addMore = scanInt.nextInt();
                        if (addMore==1){
                            viewMenu(lastOrder.getVendor().getMenus());
                            String checkItemID = "";
                            boolean itemIDFound = false;
                            int itemPosition = -1;
                            System.out.println("What would you like to reorder? (enter item ID)");
                            checkItemID = scanStr.nextLine();
                            Vendor selectedVendor = lastOrder.getVendor();
                            ArrayList<Item> vendorMenu = selectedVendor.getMenus();
                            for (int i = 0; i < vendorMenu.size(); i++) {
                                if (vendorMenu.get(i).getItem_id().equalsIgnoreCase(checkItemID)){
                                    itemIDFound = true;
                                    itemPosition=i;
                                }
                            }
                            if (itemIDFound){
                                System.out.println("Enter quantity for "+vendorMenu.get(itemPosition).getName());
                                int quantityAdd = scanInt.nextInt();
                                Item chosenItem = vendorMenu.get(itemPosition);

                                // check if the item is already exist on the order
                                boolean itemExists = false;
                                for (int i = 0; i < newOrder.getItems().size(); i++) {
                                    if (newOrder.getItems().get(i).getItem_id().equals(checkItemID)) {
                                        itemExists = true;
                                        itemIndex = i;
                                    }
                                }
                                if (itemExists) {
                                    int newQuantity = newOrder.getQuantity().get(itemIndex) + quantityAdd;
                                    newOrder.setQuantity(itemIndex, newQuantity);
                                } else {
                                    newOrder.addOrderDetail(chosenItem, quantityAdd);
                                }
                            }
                        }
                    }
                    if (addMore ==2){
                        //checking balance and place the order
                        placeAndPayment(newOrder ,pos,mgmven);
                    }
                    newOrder.showOrderDetails();;

                    reorder = 2;
                }else{
                    System.out.println("Order ID not found in order history.");
                }
            }
        }
    }

    public boolean browseVendor(ManageVend mgmven){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String chosenVendor = "";
        int sort = -1;
        int browseRating = -1;
        mgmven.printVendor();

        System.out.println("1. Choose Vendor");
        System.out.println("2. Browse Vendor by Rating");
        System.out.println("3. Sort Vendor Rating from Highest to Lowest");
        System.out.println("4. Back");
        System.out.println("Enter your choice : ");
        sort = scanInt.nextInt();
        while(sort != 1){
            if(sort == 2){
                System.out.println("Enter the rating to browse vendors (1-5): ");
                browseRating = scanInt.nextInt();
                mgmven.filterVendorRating(browseRating);
                sort = 1;
            }else if(sort == 3){
                mgmven.sortVendorsByRating();
                mgmven.printVendor();
                sort = 1;
            }
        }
        System.out.println("Which Restaurant You Want to Choose?(Vendor ID)");
        chosenVendor = scanStr.nextLine();

        //LOOPING PILIH VENDOR
        boolean vendorFound = false;

        for (int i = 0; i < mgmven.vendors.size(); i++) {
            //gunanya buat ngecek vendor yang di imput sama customer ada gak
            if (mgmven.vendors.get(i).getUserID().equals(chosenVendor)){
                vendorFound = true;
                position = i;
            }
        }
        return vendorFound;
    }
    public boolean isBalanceEnough(int pos, Order newOrder) {
        double balance = customers.get(pos).getBalance();
        double del_fee = newOrder.getDelFee();
        int print = -1;
        double totalCost = newOrder.calcTotalOrderAmount(del_fee,print);
        return balance >= totalCost;
    }


    public void viewMenu(ArrayList<Item> vendorMenu){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        int menutype = 0;
        System.out.println("Which menu you want to see?");
        System.out.println("1. Full Menu");
        System.out.println("2. Food");
        System.out.println("3. Beverage");
        menutype = scanInt.nextInt();
        if(menutype == 1){
            System.out.println("FULL MENU");
            for (int i = 0; i < vendorMenu.size(); i++) {
                System.out.println(vendorMenu.get(i).toString());
            }
        }else if(menutype == 2){
            System.out.println("FOOD MENU");
            for (int i = 0; i < vendorMenu.size(); i++) {
                if(vendorMenu.get(i) instanceof Food){
                    System.out.println(vendorMenu.get(i).toString());
                }
            }
        }else if(menutype == 3){
            System.out.println("BEVERAGE MENU");
            for (int i = 0; i < vendorMenu.size(); i++) {
                if(vendorMenu.get(i) instanceof Beverage){
                    System.out.println(vendorMenu.get(i).toString());
                }
            }
        }
    }
    public void selectOrder(ManageVend mgmven, int addorder, int serviceType){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        String checkItemID = "";
        boolean itemIDFound = false;
        int itemPosition = -1;
        int itemIndex=0;
        System.out.print("What would you like to order? (Enter item ID):\n");
        checkItemID = scanStr.nextLine();
        Vendor selectedVendor=mgmven.vendors.get(position);
        ArrayList<Item> vendorMenu = selectedVendor.getMenus();

        //check itemID yang dimasuki user ada atau gak
        for (int i = 0; i < vendorMenu.size(); i++) {
            if (vendorMenu.get(i).getItem_id().equals(checkItemID)){
                itemIDFound = true;
                itemPosition = i;
            }
        }
        if (itemIDFound){
            System.out.println("Enter Quantity for " + vendorMenu.get(itemPosition).getName() + ":");
            int quantity = scanInt.nextInt();
            //quantities.add(quantity);

            Customer currentCust = this.customers.get(current);

            Item chosenItem = vendorMenu.get(itemPosition);
            //selectedItems.add(chosenItem);
            if(addorder==0){
                int size = 0;
                for (int j=0; j<mgmven.vendors.size(); j++){
                    size+= mgmven.vendors.get(j).getReceivedOrders().size();
                }
                newOrder = new Order(currentCust, selectedVendor, addorder,serviceType, size);

            }
            boolean itemExists = false;
            for (int i = 0; i < newOrder.getItems().size(); i++) {
                if(newOrder.getItems().get(i).getItem_id().equals(checkItemID)){
                    itemExists = true;
                    itemIndex = i;
                }
            }
            if(itemExists){
                int newQuantity = newOrder.getQuantity().get(itemIndex) + quantity;
                newOrder.setQuantity(itemIndex, newQuantity);
            }else{
                newOrder.addOrderDetail(chosenItem, quantity);
            }
            //newOrder.addOrderDetail(chosenItem,quantity);
        }else{
            System.out.println("The item ID is not available");
        }
    }

    /*
    public void placeOrder(ManageVend mgmven, int addorder, int serviceType){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        ArrayList<Item> selectedItems = new ArrayList<Item>();
        ArrayList<Integer> quantities = new ArrayList<Integer>();
        // HARUS BIKIN OBJECTNYA DULU, SUPAYA ADA TEMPATNYA, BARU DI ADD KE ARRAYLIST
        int valid = -1;
        date today_date = null;

        while(valid!=1 && addorder==0){
            System.out.println("Enter today's date : (use number format) ");
            System.out.print("Date    = ");
            int check_date = scanInt.nextInt();
            System.out.print("Month   = ");
            int check_month = scanInt.nextInt();
            System.out.print("Year    = ");
            int check_year = scanInt.nextInt();
            date mydate = new date(check_date, check_month,check_year);
            if (mydate.isvalidDate()==1 && mydate.isvalidMonth()==1 && mydate.isvalidYear()==1){
                today_date = mydate;
                valid=1;
            }else if(mydate.isvalidYear()!=1){
                System.out.println("Invalid input of year.");
            }else if (mydate.isvalidDate()!=1){
                System.out.println("Invalid input of date");
            }else if(mydate.isvalidMonth()!=1){
                System.out.println("Invalid input of month");
            }
        }

        String checkItemID = "";
        boolean itemIDFound = false;
        int itemPosition = -1;
        System.out.println("What would you like to order? (Enter item ID)");
        checkItemID = scanStr.nextLine();
        Vendor selectedVendor=mgmven.vendors.get(position);
        ArrayList<Item> vendorMenu = selectedVendor.getMenus();

        //check itemID yang dimasuki user ada atau gak
        for (int i = 0; i < vendorMenu.size(); i++) {
            if (vendorMenu.get(i).getItem_id().equals(checkItemID)){
                itemIDFound = true;
                itemPosition = i;
            }
        }
        if (itemIDFound){
            System.out.println("Enter Quantity for " + vendorMenu.get(itemPosition).getName() + ":");
            int quantity = scanInt.nextInt();
            //quantities.add(quantity);
            Customer currentCust = this.customers.get(current);
            Item chosenItem = vendorMenu.get(itemPosition);
            //selectedItems.add(chosenItem);
            if(addorder==0){
                newOrder = new Order(currentCust, selectedVendor, today_date, addorder,serviceType);
                //System.out.println("sudah terbuat");

            }
            newOrder.addOrderDetail(chosenItem,quantity);
        }
    }

     */
    public void viewOrderList(int pos){
        for (int i = 0; i < customers.get(pos).getCustomerOrder().size(); i++) {
            customers.get(pos).getCustomerOrder().get(i).showOrderDetails();
        }
        if(customers.get(pos).getCustomerOrder().isEmpty()){
            System.out.println("You don't have any order in process.");
        }
    }

    public void cancelOrder(ManageVend mgmven, int pos) {
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        int orderPosition = -1;
        int choice = -1;
        int enterid = -1;
        int pendingCount = 0;

        for (int i = 0; i < customers.get(pos).getCustomerOrder().size(); i++) {
            if (customers.get(pos).getCustomerOrder().get(i).getStatusOrder().equalsIgnoreCase("pending")) {
                customers.get(pos).getCustomerOrder().get(i).showOrderDetails();
                pendingCount++;
            }
        }
        if (pendingCount == 0) {
            System.out.println("Sorry, you can only cancel your order if the status is still \"pending\".");
        } else {
            boolean orderFound = false;
            while (enterid != 1) {
                System.out.println("Which order you want to cancel ?\nEnter order ID : ");
                String order_id = scanStr.nextLine();
                for (int i = 0; i < customers.get(pos).getCustomerOrder().size(); i++) {
                    if (customers.get(pos).getCustomerOrder().get(i).getOrder_id().equals(order_id)) {
                        orderFound = true;
                        orderPosition = i;
                    }
                }
                if (orderFound) {
                    System.out.println("Are you sure want to cancel this order ? ");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    choice = scanInt.nextInt();
                    if (choice == 1) {
                        mgmven.vendors.get(position).getReceivedOrders().remove(customers.get(pos).getCustomerOrder().get(orderPosition));
                        double refundAmount = customers.get(pos).getCustomerOrder().get(orderPosition).getTotal_price();
                        // refund balance
                        refundBalance(pos,refundAmount);
                        customers.get(pos).getCustomerOrder().remove(customers.get(pos).getCustomerOrder().get(orderPosition));
                        System.out.println("Your order has been cancelled.");
                        System.out.println("The amount " + refundAmount + " you previously paid for your order has been refunded to your account.");
                        enterid = 1;
                    } else if (choice == 2) {
                        System.out.println("Your order is not cancelled.");
                    }
                } else {
                    System.out.println("The order ID you entered could not be found.");
                    System.out.println("Do you want to enter another ID?\n1. Yes\n2. No");
                    choice = scanInt.nextInt();
                    if (choice == 1) {
                        enterid = -1;
                    } else if (choice == 2) {
                        System.out.println("Back to the Menu...");
                        enterid = 1;
                    }
                }
            }
        }
    }
    public void viewOrderHist(int pos) {
        for (int i = 0; i < customers.get(pos).getHistoryOrderCust().size(); i++) {
            customers.get(pos).getHistoryOrderCust().get(i).showOrderDetails();
        }
    }

    public void giveDelRunReview(int pos, Order order, ManageDelRun mgmdelrun){
        int ratingDelRun =  0;
        int checkrating = 0;
        String descDelRun = "";
        int cont = 0;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        while(cont!=-1) {

            System.out.println("DELIVERY RUNNER REVIEW");
            System.out.println("Please provide a review for this driver : ");
            System.out.println("Enter rating (1-5): ");
            checkrating = scanInt.nextInt();
            if (checkrating >= 1 && checkrating <= 5) {
                ratingDelRun = checkrating;

            } else {
                System.out.println("Invalid input for rating (only accept number 1 - 5).");
                cont = -1;
            }
            System.out.println("Enter your review here : ");
            descDelRun = scanStr.nextLine();

            ReviewDelrun newReview = new ReviewDelrun(customers.get(pos).getUserID(), order.getDriver_id(), ratingDelRun, descDelRun);

            String findDelrunID = order.getDriver_id();
            int delrunPos = mgmdelrun.findDelrunIndex(findDelrunID);

            if (delrunPos != -1) {
                customers.get(pos).addDelrunReview(newReview);
                mgmdelrun.drivers.get(delrunPos).addReview(newReview);
                System.out.println("Your review has been submitted.\n");
                cont = -1;
            } else {
                System.out.println("Delivery runner ID not found.");
            }
        }
    }
    public void giveVendorReview(int pos, Order order, ManageVend mgmven){
        int ratingVen = 0;
        int checkrating =0;
        String descVen = "";
        int cont = 0;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        while(cont!=-1) {

            System.out.println("VENDOR REVIEW");
            System.out.println("Please provide a review for this vendor : ");
            System.out.println("Enter rating (1-5): ");
            checkrating = scanInt.nextInt();
            if (checkrating>=1 && checkrating<=5){
                ratingVen = checkrating;
            }else{
                System.out.println("Invalid input for rating. (only accept number 1-5)");
                cont=-1;
            }
            System.out.println("Enter your review here : ");
            descVen = scanStr.nextLine();
            ReviewVendor newReview = new ReviewVendor(customers.get(pos).getUserID(), order.getVendor().getUserID(), ratingVen, descVen);
            // find vendor position
            String findVendorID = order.getVendor().getUserID();
            int vendorPos = mgmven.findVendorIndex(findVendorID);

            if (vendorPos != -1) {
                // add the review to customer and vendor's lists.
                customers.get(pos).addVendorReview(newReview);
                mgmven.vendors.get(vendorPos).addReview(newReview);
                System.out.println("Your review has been submitted.\n");
                cont = -1;
            } else {
                System.out.println("Vendor ID is not found");
            }
        }
    }
    public void reviewNotification (int pos, ManageVend mgmven, ManageDelRun mgmdelrun) {
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        Order lastOrder = null;
        int unreviewedCount = 0;
        int wantReview = 0;

        // find the last completed order
        for (int i = customers.get(pos).getHistoryOrderCust().size() - 1; i >= 0; i--) {
            Order order = customers.get(pos).getHistoryOrderCust().get(i);
            if (order.getStatusOrder().equalsIgnoreCase("Completed") && !order.isReviewed()) {
                lastOrder = order;
                break; //if the last one is completed, exit the loop
            }
        }
        // display last completed order
        if (lastOrder != null) {
            System.out.println("Your last completed order details :");
            lastOrder.showOrderDetails();
            System.out.println("Would you like to give a review for your last order?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            wantReview = scanInt.nextInt();
            if (wantReview == 1) {
                if (lastOrder.getServiceType() == 1 || lastOrder.getServiceType() == 2) {
                    // if not delivery, only give review for vendor
                    giveVendorReview(pos, lastOrder, mgmven);
                    lastOrder.setReviewed(true);
                } else if (lastOrder.getServiceType() == 3) {
                    giveVendorReview(pos, lastOrder, mgmven);
                    giveDelRunReview(pos, lastOrder, mgmdelrun);
                    lastOrder.setReviewed(true);
                }
            } else if (wantReview == 2) {
                return;
            }
        } else {
            // when no completed order found, notification will be empty
            return;
        }
    }
    public void viewHistoryTransaction(int pos){ //TAMBAH INI
        for (int i = 0; i < customers.get(pos).getHistoryTransaction().size(); i++) {
            customers.get(pos).viewHistoryTransationCust();
        }
    }
    public void checkDeclinedOrder(ManageVend mgmven, int pos){
        if (done!=1){
            for (int i = 0; i < mgmven.vendors.size(); i++) {
                for (int j = 0; j < mgmven.vendors.get(i).getReceivedOrders().size(); j++) {
                    if (mgmven.vendors.get(i).getReceivedOrders().get(j).getCustomer().getUserID().equalsIgnoreCase(customers.get(pos).getUserID())) {
                        if (mgmven.vendors.get(i).getReceivedOrders().get(j).checkDecline_count() == 1) {
                            System.out.println("Couldn't find a driver to deliver your order.");
                            System.out.println("You should take away or dine in the order");
                            double del_fee_refund = mgmven.vendors.get(i).getReceivedOrders().get(j).getDelFee();
                            System.out.println("We will refund your delivery fee to you balance");
                            customers.get(pos).refundCust(del_fee_refund);
                            System.out.println("Current balance = " + customers.get(pos).getBalance());
                            mgmven.vendors.get(i).getReceivedOrders().get(j).resetcheckDecline_count();
                            done = 1;
                        }
                    }
                }
            }
        }
    }
}



