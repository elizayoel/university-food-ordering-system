import java.util.Scanner;

import java.io.Serializable;
public abstract class User implements Serializable {
    transient Scanner scanInt = new Scanner(System.in);
    transient Scanner scanStr = new Scanner(System.in);
    protected String user_id, password, name, address, phone_num, email_add;
    public User (){
        user_id = "";
        password = "";
        name = "";
        address = "";
        phone_num = "";
        email_add = "";
    }
    public User(String user_id,String password, String name, String address, String phone_num, String email_add){
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.email_add = email_add;
    }

    public void insert(String user_id){
        this.user_id=user_id;
        int wrongow=-1;
        String checkpassword="";
        boolean isUserIDTaken = true;
        boolean isPasswordCorrect = false;
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
                System.out.println("password meets the requirements.");
                password = checkpassword;
                isPasswordCorrect=true;
            }
        }
        System.out.println("HI!"+user_id);
        System.out.println("Enter other details for your registration.");
        System.out.println("Enter name : ");
        name = scanStr.nextLine();
        System.out.println("Enter address : ");
        address = scanStr.nextLine();
        System.out.println("Enter phone number : ");
        phone_num = scanStr.nextLine();
        System.out.println("Enter email address :");
        email_add = scanStr.nextLine();
        //User newUser = new User(user_id,password,name,address,phone_num,email_add);
        //users.add(newUser);

    }
    public void update(int opt){
        if (scanInt == null) {
            scanInt = new Scanner(System.in);
        }
        if (scanStr== null) {
            scanStr = new Scanner(System.in);
        }
        if (opt == 1) {
            String checkpassword = "";
            boolean isPasswordCorrect = false;
            while (isPasswordCorrect != true) {
                int jLower = 0, jUpper = 0, jDigit = 0, jSymbol = 0;
                System.out.println("Enter the new password = ");
                checkpassword = scanStr.nextLine();
                for (int i = 0; i < checkpassword.length(); i++) {
                    if (checkpassword.charAt(i) >= 'a' && checkpassword.charAt(i) <= 'z') jLower++;
                    else if (checkpassword.charAt(i) >= 'A' && checkpassword.charAt(i) <= 'Z') jUpper++;
                    else if (checkpassword.charAt(i) >= '0' && checkpassword.charAt(i) <= '9') jDigit++;
                    else jSymbol++;
                }
                if (jLower == 0) {
                    System.out.println("Password must contain lowercase.");
                    isPasswordCorrect = false;
                }
                if (jUpper == 0) {
                    System.out.println("Password must contain uppercase.");
                    isPasswordCorrect = false;
                }
                if (jDigit == 0) {
                    System.out.println("Password must contain number.");
                    isPasswordCorrect = false;
                }
                if (jSymbol == 0) {
                    System.out.println("Password must contain symbol.");
                    isPasswordCorrect = false;
                } else {
                    System.out.println("Password meets the requirements.");
                    password = checkpassword;
                    isPasswordCorrect = true;
                    System.out.println("User's Password has been updated.");
                }
            }
        }else if(opt==2){
            System.out.println("Enter your new name = ");
            String newname=scanStr.nextLine();
            name = newname;
            System.out.println("User's name has been updated.");
        }else if(opt==3){
            System.out.println("Enter your new address = ");
            String newadr=scanStr.nextLine();
            address = newadr;
            System.out.println("User's address has been updated.");
        }else if(opt==4){
            System.out.println("Enter your new phone number = ");
            String newphone=scanStr.nextLine();
            phone_num = newphone;
            System.out.println("User's phone number has been updated.");
        }else if(opt==5){
            System.out.println("Enter your new email address = ");
            String newemail=scanStr.nextLine();
            email_add = newemail;
            System.out.println("User's email address has been updated.");
        }else if (opt==6){

        }
    }


    public String getUserID() {
        return user_id;
    }
    public void setUserID(String user_id) {
        this.user_id = user_id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNum() {
        return phone_num;
    }
    public void setPhoneNum(String phone_num) {
        this.phone_num = phone_num;
    }
    public String getEmailAdd() {
        return email_add;
    }
    public void setEmailAdd(String email_add) {
        this.email_add = email_add;
    }
    public String toString(){
        return  "User ID\t\t\t: " + user_id + "\n" +
                "Password\t\t: " + password + "\n" +
                "Name\t\t\t: " + name + "\n" +
                "Address\t\t\t: " + address + "\n" +
                "Phone number\t: " + phone_num + "\n" +
                "Email address\t: " + email_add + "\n";
    }
}

