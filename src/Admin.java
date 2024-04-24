
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements Serializable{
    transient Scanner scanInt = new Scanner(System.in);
    transient Scanner scanStr = new Scanner(System.in);

    ManageVend mgmven;
    ManageCust mgmcust;
    ManageDelRun mgmdelrun;

    ArrayList<Admin> admins = new ArrayList<>();
    public void saveOrderID(ManageVend mgmven){
        try{
            File f = new File("staticOrder.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));

        }catch(IOException ex){
            System.out.println(ex.getMessage().toString());
        }
    }
    public Admin(){
        super();
        //close all lines below if you already have the existed file "MyObject1.bin"
         //admins.add(new Admin("A01", "Admin01_", "Anna Admin", "APU Student Centre 4th floor", "0123456789", "anna@admin.apu.my"));
         //mgmven = new ManageVend();
         //mgmcust = new ManageCust();
         //mgmdelrun = new ManageDelRun();
    }
    public Admin (String user_id,String password, String name, String address, String phone_num, String email_add){
        super(user_id,password,name,address,phone_num,email_add);
    }
    public String toString(){
        return super.toString();
    }

    public void UserRegistration() {
        int opt=-1;
        while(opt!=4) {
            System.out.println("=================== NEW USER REGISTRATION ==================");
            System.out.println("1. Register New Vendor");
            System.out.println("2. Register New Customer");
            System.out.println("3. Register New Delivery Runner");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice = ");
            opt = scanInt.nextInt();
            if (opt == 1) {
                mgmven.insert_data();
            } else if (opt == 2) {
                mgmcust.insert_data();
            } else if (opt == 3) {
                mgmdelrun.insert_data();
            } else if (opt == 4){
                System.out.println("Back to Admin Menu...");
            } else {
                System.out.println("Please enter the correct input [1-4].");
            }
        }
    }
    public void VendorManagement(){
        int opt=-1;
        while(opt!=4) {
            System.out.println("============= VENDOR MANAGEMENT================");
            System.out.println("1. Vendor List and Details");
            System.out.println("2. Update Vendor Data");
            System.out.println("3. Delete Vendor Data");
            System.out.println("4. Back");
            System.out.print("Enter you choice = ");
            opt = scanInt.nextInt();
            if (opt == 1) {
                mgmven.printVendorDetail();
            } else if (opt == 2) {
                mgmven.update_data();
            } else if (opt == 3) {
                mgmven.delete_data();
            } else if (opt == 4) {
                System.out.println("Back to Admin Menu...");
            } else{
                System.out.println("Please enter the correct input [1-4].");
            }
        }
    }
    public void CustomerManagement(){
        int opt=-1;
        while(opt!=4) {
            System.out.println("============= CUSTOMER MANAGEMENT================");
            System.out.println("1. Customer List and Details");
            System.out.println("2. Update Customer Data");
            System.out.println("3. Delete Customer Data");
            System.out.println("4. Back");
            System.out.print("Enter your choice = ");
            opt = scanInt.nextInt();
            if (opt == 1) {
                mgmcust.printCustomer();
            } else if (opt == 2) {
                mgmcust.update_data();
            } else if (opt == 3) {
                mgmcust.delete_data();
            } else if (opt == 4){
                System.out.println("Back to Admin Menu...");
            } else{
                System.out.println("Please enter the correct input [1-4].");
            }
        }
    }
    public void DeliveryRunnerManagement(){
        int opt = -1;
        while(opt!=4) {
            System.out.println("=============Delivery Runner MANAGEMENT================");
            System.out.println("1. Delivery Runner List and Details");
            System.out.println("2. Update Delivery Runner Data");
            System.out.println("3. Delete Delivery Runner Data");
            System.out.println("4. Back");
            System.out.print("Enter your choice = ");
            opt = scanInt.nextInt();
            if (opt == 1) {
                mgmdelrun.printDelRun();
            } else if (opt == 2) {
                mgmdelrun.update_data();
            } else if (opt == 3) {
                mgmdelrun.delete_data();
            } else if (opt == 4){
                System.out.println("Back to Admin Menu...");
            } else {
                System.out.println("Please enter the correct input [1-4].");
            }
        }
    }
    public void TopUpCustomerCredit(){
        int opt = -1;
        int accorrej=-1;
        String accuser="";
        System.out.println("Showing list of pending customer...");
        int ctr = mgmcust.pendingCount();
        if (ctr>=1){
            mgmcust.showPendingTopUp();
            //Choose which customer's request you want to accept
            //do not accept all pending request at the same time
            System.out.print("Enter User ID here = ");
            accuser = scanStr.nextLine();
            int found = mgmcust.checkAccCust(accuser);
            if(found>-1){
                System.out.println("1. Accept customer");
                System.out.println("2. Reject Customer");
                System.out.print("Do you want to accept or reject "+accuser+"? [1/2] = ");
                accorrej = scanInt.nextInt();
                if(accorrej==1){
                    System.out.println("Accepting request for user "+accuser+"...");
                    mgmcust.acceptReqAdmin(found);
                    System.out.println("Request has been accepted");
                } else if (accorrej==2){
                    System.out.println("Rejecting request for user "+accuser+"...");
                    mgmcust.rejectReqAdmin(found);
                    System.out.println("Request has been rejected");
                }
            }else if (found==-1){
                System.out.println("This customer does not have any request to top-up their balance.");
            }
        }else{
            System.out.println("No request in topup Customer ");
        }
    }
    public void GenerateTransactionReceipt(ManageCust mgmcust){
        for (int i = 0; i < mgmcust.customers.size(); i++) {
            for (int j = 0; j < mgmcust.customers.get(i).getHistorytopup().size(); j++) {
                if (mgmcust.customers.get(i).getHistorytopup().get(j).getStatus().equalsIgnoreCase("accepted")){
                    mgmcust.customers.get(i).getHistorytopup().get(j).showTopUp();
                    mgmcust.customers.get(i).getHistorytopup().get(j).setStatus("Successfully added to balance");
                }
            }

        }
    }
    public void SendReceiptToCustomer(){
        System.out.println("Sending receipt to customer...");
    }
    public void LoginAdmin(){
        System.out.println("======================== ADMIN LOGIN =======================");
        int position = -1;
        int correctuserid = -1;
        int correctpw = -1;
        while(correctuserid != 1) {
            System.out.print("User ID  = ");
            String user_id = scanStr.nextLine();
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getUserID().equals(user_id)) {
                    position = i;
                    while (correctpw != 1) {
                        System.out.print("Password = ");
                        String password = scanStr.nextLine();
                        if (admins.get(position).getPassword().equals(password)) {
                            int opt = -1;
                            while (opt != 8) {
                                System.out.println("======================== ADMIN MENU ========================");
                                System.out.println("1. User Registration");
                                System.out.println("2. Vendor Management");
                                System.out.println("3. Customer Management");
                                System.out.println("4. DelRun Runner Management");
                                System.out.println("5. Accept Request Top-Up Customer Credit");
                                System.out.println("6. Generate Transaction Receipt");
                                System.out.println("7. Send Receipt to Customer");
                                System.out.println("8. Back to Main Menu");
                                System.out.println("Enter your choice = ");
                                opt = scanInt.nextInt();
                                if (opt == 1) {
                                    UserRegistration();
                                } else if (opt == 2) {
                                    VendorManagement();
                                } else if (opt == 3) {
                                    CustomerManagement();
                                } else if (opt == 4) {
                                    DeliveryRunnerManagement();
                                } else if (opt == 5) {
                                    TopUpCustomerCredit();
                                } else if (opt == 6) {
                                    GenerateTransactionReceipt(mgmcust);
                                    //GenerateTransactionReceipt();
                                } else if (opt == 7) {
                                    SendReceiptToCustomer();
                                } else if (opt == 8) {
                                    System.out.println("You have been logged out as an admin");
                                } else {
                                    System.out.println("Invalid input [please input only number 1-8]");
                                }
                                correctpw=1;
                            }
                        } else {
                            correctpw = -1;
                            System.out.println("Incorrect password. Please enter the correct password.");
                        }
                    }
                } else {
                    correctuserid = -1;
                    System.out.println("Incorrect User ID. Please enter the correct User ID.");
                }
            }correctuserid=1;
        }
    }
    public void LoginVendor(){
        int optven=-1;
        int optmod = -1;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        System.out.println("======================= VENDOR LOGIN =======================");
        System.out.print("User ID  = ");
        String user_id = scanStr.nextLine();
        System.out.print("Password = ");
        String password = scanStr.nextLine();
        int found = mgmven.checkLogin(user_id,password);
        if (found!=-1) {
            mgmven.viewPendingOrderList(found);
            while(optven != 10){
                System.out.println("======================== VENDOR MENU ========================");
                System.out.println("1. View profile");
                System.out.println("2. View list of items");
                System.out.println("3. Modify Item(insert, update, delete)");
                System.out.println("4. Accept/Cancel Order");
                System.out.println("5. Update Order Status");
                System.out.println("6. Check Order History");
                System.out.println("7. Read Customer Review");
                System.out.println("8. Revenue Dashboard");
                System.out.println("9. View Received Order");
                System.out.println("10. Back to Main Menu");
                System.out.print("Enter your choice = ");

                optven = scanInt.nextInt();
                if (optven == 1){
                    mgmven.viewProfile(found);
                }else if (optven == 2) {
                    mgmven.viewItemList();
                } else if (optven == 3) {
                    while (optmod != 4) {
                        System.out.println("1. Insert new item");
                        System.out.println("2. Update current item");
                        System.out.println("3. Delete current item");
                        System.out.println("4. Back");
                        System.out.println("Enter your choice = ");
                        optmod = scanInt.nextInt();
                        if (optmod == 1) {
                            mgmven.insertItem();
                        } else if (optmod == 2) {
                            mgmven.updateItem();
                        } else if (optmod == 3) {
                            mgmven.deleteItem();
                        } else if (optmod == 4){
                            System.out.println("Back to Vendor Menu...");
                        } else{
                            System.out.println("Invalid input [please input only number 1-4]");
                        }
                    }
                }else if (optven == 4) {
                    mgmven.viewPendingOrderList(found);
                    System.out.println("Do you want to accept/reject order?");
                    System.out.println("1. Accept");
                    System.out.println("2. Reject");
                    int accrej = scanInt.nextInt();
                    if (accrej==1){
                        mgmven.venAccOrder(mgmcust);
                    }else if (accrej==2){
                        mgmven.venRejectOrder(mgmcust);
                    }else{
                        System.out.println("Invalid input. Only accept input number 1 and 2");
                    }
                } else if (optven == 5) {
                    System.out.println("============= LIST OF ORDERS ==============");
                    mgmven.viewOrderList(found);
                    System.out.print("Which order you want to update its status?");
                    String updateSt = "";
                    updateSt = scanStr.nextLine();
                    int foundorder = mgmven.findOrderIdtoUpdate(found,updateSt);
                    if (foundorder!=-1){
                        mgmven.updateStatusOrder(found,foundorder,mgmcust);
                    }else{
                        System.out.println("Order ID cannot be found.");
                    }
                } else if (optven == 6) {
                    mgmven.viewHistoryOrder(found);
                } else if (optven == 7) {
                    mgmven.seeReview(found);
                } else if (optven == 8) {
                    mgmven.revenueDashboard(found);
                } else if (optven == 9) {
                    mgmven.viewOrderList(found);
                } else if (optven == 10){
                    System.out.println("You have been logged out as Vendor...");
                } else {
                    System.out.println("Invalid input [please input only number 1-10]");
                }
            }
        }else {
            System.out.println("Please check again your vendor ID and password");
        }
    }
    public void LoginCustomer(){
        System.out.println("==================== CUSTOMER LOGIN ===================");
        int optcust=-1;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        System.out.print("User ID  = ");
        String user_id = scanStr.nextLine();
        System.out.print("Password = ");
        String password = scanStr.nextLine();
        int found = mgmcust.checkLogin(user_id,password);
        if (found!=-1){
            while(optcust != 11){
                System.out.println("Hi "+user_id);
                // give notification about completed current orders
                // prompt them to give review
                //checking if order under this cust got decline more than 5 times
                mgmcust.printReceipt(found);
                mgmcust.checkDeclinedOrder(mgmven,found);
                if(!mgmcust.customers.get(found).getHistoryOrderCust().isEmpty()){
                    mgmcust.reviewNotification(found, mgmven, mgmdelrun);
                }
                System.out.println("=================== CUSTOMER MENU ====================");
                System.out.println("1. View Profile");
                System.out.println("2. Top-up Credit");
                System.out.println("3. Check Balance");
                System.out.println("4. View Menu and Place Order");
                System.out.println("5. Cancel Order");
                System.out.println("6. Check Order Status");
                System.out.println("7. Check Order History");
                System.out.println("8. Check Transaction History");
                System.out.println("9. Provide review for your completed order");
                System.out.println("10. Reorder using order history");
                System.out.println("11. Back to Main Menu");
                System.out.println("Enter your choice = ");
                optcust = scanInt.nextInt();
                if(optcust == 1) {
                    mgmcust.viewProfile(found);
                }else if (optcust==2){
                    mgmcust.topupCredit(found);
                }else if (optcust==3){
                    mgmcust.showBalance(found);
                }else if (optcust==4){
                    mgmcust.viewAndPlace(mgmven, found);
                }else if (optcust == 5){
                    mgmcust.cancelOrder(mgmven, found);
                }else if (optcust == 6){
                    mgmcust.viewOrderList(found);
                }else if (optcust == 7){
                    mgmcust.viewOrderHist(found);
                }else if(optcust==8){
                    mgmcust.viewHistoryTransaction(found); //TAMBAH INI
                }else if(optcust==9){
                    mgmcust.reviewNotification(found,mgmven,mgmdelrun);
                }else if(optcust==10){
                    mgmcust.reorderByHistory(mgmven,found);
                }else if(optcust == 11){
                    System.out.println("You have been logged out as a Customer...");
                }else {
                    System.out.println("Invalid input [please input only number 1-11]");
                }
            }
        }else{
            System.out.println("Please check again your customer ID and password.");
        }
    }
    public void LoginDriver(){
        int optdriver=-1;
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        System.out.println("================== DELIVERY RUNNER LOGIN =================");
        System.out.print("User ID  = ");
        String user_id = scanStr.nextLine();
        System.out.print("Password = ");
        String password = scanStr.nextLine();
        int found = mgmdelrun.checkLogin(user_id,password);
        if (found!=-1){
            while(optdriver!=7) {
                System.out.println("Welcome! Driver " + user_id);
                System.out.println("====================== DELIVERY RUNNER MENU ==================");
                System.out.println("1. View Profile");
                System.out.println("2. View task"); // didlmnya accept or decline task
                System.out.println("3. Update task status");
                System.out.println("4. Check task history");
                System.out.println("5. Read customer review");
                System.out.println("6. See Revenue Dashboard");
                System.out.println("7. Back to Main Menu");
                System.out.println("Enter your choice = ");
                optdriver = scanInt.nextInt();
                if (optdriver == 1){
                    mgmdelrun.viewProfile(found);
                } else if (optdriver == 2) {
                    mgmdelrun.viewTask(mgmven);
                    int ctrTask = mgmdelrun.countTask(mgmven);
                    if (ctrTask>1){
                        System.out.println("Do you want accept/ reject the task?");
                        System.out.println("1. Accept");
                        System.out.println("2. Reject");
                        int optdelrun = scanInt.nextInt();
                        if (optdelrun == 1) {
                            mgmdelrun.acceptTask(mgmven,user_id);
                        } else if (optdelrun == 2) {
                            mgmdelrun.declineTask(mgmven);
                        } else {
                            System.out.println("Invalid input. Only accept input 1 and 2");
                        }
                    }
                } else if (optdriver == 3) {
                    mgmdelrun.updateStatusToComplete(mgmven,mgmcust,found);
                } else if (optdriver == 4) {
                    mgmdelrun.viewTaskHistory(found);
                } else if (optdriver == 5) {
                    mgmdelrun.seeReview(found);
                } else if (optdriver == 6) {
                    mgmdelrun.revenueDashboard(found);
                } else if (optdriver == 7){
                    System.out.println("You have been logged out as a Delivery Runner... ");
                } else{
                    System.out.println("Invalid input [please input only number 1-7]");
                }
            }
        }else{
            System.out.println("Please check again your Driver ID and password.");
        }

    }
    // urutan methods ga masalah letaknya
    public void MainMenu(){
        int menu = -1;

        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        while(menu != 0){
            System.out.println("LOGIN AS:");
            System.out.println("1. Admin");
            System.out.println("2. Vendor");
            System.out.println("3. Customer");
            System.out.println("4. DelRun Runner ");
            System.out.println("0. Exit");
            System.out.println("Enter your choice = ");
            menu = scanInt.nextInt();

            if(menu == 1){ // ADMIN
                LoginAdmin();
            }else if(menu == 2){ // VENDOR
                LoginVendor();
            }else if(menu == 3){ // CUSTOMER
                LoginCustomer();
            }else if(menu == 4){ // DRIVER
                LoginDriver();
            }else if(menu == 0){
                System.out.println("You're quitting the program...");
            }else{
                System.out.println("Invalid input");
            }
        }
    }
}

