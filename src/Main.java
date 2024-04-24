import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // GLOBAL VARIABLES
    transient Scanner scanInt = new Scanner(System.in);
    transient Scanner scanStr = new Scanner(System.in);
    Admin a;
    public Main(){
        a = new Admin();
        loadData();
        a.MainMenu();
        saveData();
    }
    public void saveData()
    {
        try   {
            ObjectOutputStream o = new  ObjectOutputStream(new FileOutputStream("MyObject1.bin"));
            o.writeObject(a);
            o.close();
            System.out.println("data has been saved");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public void loadData()
    {
        try   {
            ObjectInputStream o = new   ObjectInputStream( new FileInputStream("MyObject1.bin"));
            a = (Admin) o.readObject();
            o.close();
        }   catch (ClassNotFoundException e)   {
            System.out.println(e);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    // DEFAULT CONSTRUCTOR MAIN

    // call Main.saveData() in every function you want to immediately save
    public static void main(String[] args) {
        Main mymain = new Main();

    }
}


