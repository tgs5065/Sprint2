package Controller;

import java.util.ArrayList;
import Model.Inventory;
import Model.Item;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Program 
{
    private ArrayList<Inventory> inventories;
    Scanner in = new Scanner(System.in);
    
    public Program() throws IOException
    {
        inventories = new ArrayList<>();
    }
    
    public void newInv() throws IOException
    {
        Inventory inv = new Inventory();
        addInv(inv);
        //menu code
        System.out.print("Please enter the name of your inventory: ");
        inv.setName(in.next());
        //call show inv
        showInv(inventories.size() - 1);
    }
    
    public void addInv(Inventory inv){
        inventories.add(inv);
    }
    
    public void showInv(int num) throws IOException
    {
        int option = -1;
        //print items and give options
        while(option != 0){
            System.out.println("Inventory: " + inventories.get(num).getName());
            System.out.println("\tItems (enter item number to view/edit/delete the item):");
            int selector = 1;
            for(Item item: inventories.get(num).getItems()){
                System.out.println("\t#" + selector + ": " + item.getName());
                selector++;
            }
            System.out.println("\t#" + selector + ": New item");
            selector++;
            System.out.println("\t#" + selector + ": Edit inventory");
            selector++;
            System.out.println("\t#" + selector + ": Delete inventory");
            selector++;
            System.out.println("\t#" + selector + ": Save inventory");
            selector++;
            System.out.println("\t#0: Go back to the previous menu");
            System.out.print("Please enter your choice: ");
            option = in.nextInt();
            
            //if/else to determine choice
            if(option == 0)
                invMenu();
            else if(option < inventories.get(num).getItems().size() + 1)
                showItem(num, option - 1);
            else if(option == inventories.get(num).getItems().size() + 1)
                newItem(num);
            else if(option == inventories.get(num).getItems().size() + 2){
                System.out.print("Please enter the new name of the inventory: ");
                inventories.get(num).setName(in.next());
            }
            else if(option == inventories.get(num).getItems().size() + 3){
                inventories.remove(num);
                invMenu();
            }
            else if(option == inventories.get(num).getItems().size() + 4)
                save(inventories.get(num));
        }
    }
    
    public void newItem(int num) throws IOException
    {
        //prompt for name and call show item
        Item item = new Item();
        inventories.get(num).getItems().add(item);
        //menu code
        System.out.print("Please enter the name of your item: ");
        item.setName(in.next());
        System.out.print("Please enter the quantity of the item: ");
        item.plus(in.nextInt());
        //call show inv
        showItem(num, inventories.get(num).getItems().size() - 1);
    }
    
    public void showItem(int invNum, int itemNum) throws IOException
    {
        int option = -1;
        //print items and give options
        while(option != 0){
            System.out.println("\tItem: " + inventories.get(invNum).getItems().get(itemNum).getName() + " Quantity: " + inventories.get(invNum).getItems().get(itemNum).getQuantity());
            System.out.println("\t\tAttributes (enter attribute number to view/edit/delete the item):");
            int selector = 1;
            for(String att: inventories.get(invNum).getItems().get(itemNum).getAttributes()){
                System.out.println("\t\t#" + selector + ": " + att);
                selector++;
            }
            System.out.println("\t\t#" + selector + ": New attribute");
            selector++;
            System.out.println("\t\t#" + selector + ": Edit item name");
            selector++;
            System.out.println("\t\t#" + selector + ": Add amount to quantity");
            selector++;
            System.out.println("\t\t#" + selector + ": Subtract amount from quantity");
            selector++;
            System.out.println("\t\t#" + selector + ": Delete item");
            selector++;
            System.out.println("\t\t#0: Go back to the previous menu");
            System.out.print("Please enter your choice: ");
            option = in.nextInt();
            
            //if/else to determine choice
            if(option == 0)
                showInv(invNum);
            else if(option < inventories.get(invNum).getItems().get(itemNum).getAttributes().size() + 1)
                showAtt(invNum, itemNum, option - 1);
            else if(option == inventories.get(invNum).getItems().get(itemNum).getAttributes().size() + 1)
                newAtt(invNum, itemNum);
            else if(option == inventories.get(invNum).getItems().get(itemNum).getAttributes().size() + 2){
                System.out.print("Please enter the new name of the item: ");
                inventories.get(invNum).getItems().get(itemNum).setName(in.next());
            }
            else if(option == inventories.get(invNum).getItems().get(itemNum).getAttributes().size() + 3){
                System.out.print("Please enter the amount you wish to add to the quantity: ");
                inventories.get(invNum).getItems().get(itemNum).plus(in.nextInt());
            }
            else if(option == inventories.get(invNum).getItems().get(itemNum).getAttributes().size() + 4){
                System.out.print("Please enter the amount you wish to subtract from the quantity: ");
                inventories.get(invNum).getItems().get(itemNum).minus(in.nextInt());
            }
            else if(option == inventories.get(invNum).getItems().get(itemNum).getAttributes().size() + 5){
                inventories.get(invNum).getItems().remove(itemNum);
                showInv(invNum);
            }
        }
    }
    
    public void newAtt(int invNum, int itemNum) throws IOException{
        //prompt for name and call show item
        String att;
        
        //menu code
        System.out.print("Please enter the attribute description: ");
        att = in.next();
        inventories.get(invNum).getItems().get(itemNum).getAttributes().add(att);
        //call show inv
        showAtt(invNum, itemNum, inventories.get(invNum).getItems().get(itemNum).getAttributes().size()-1);
    }
    
    public void showAtt(int invNum, int itemNum, int attNum) throws IOException{
        int option = -1;
        //print items and give options
        while(option != 0){
            System.out.println("\t\tAttribute: " + inventories.get(invNum).getItems().get(itemNum).getAttributes().get(attNum));
            System.out.println("\t\t\t#1: Edit item");
            System.out.println("\t\t\t#2: Delete item");
            System.out.println("\t\t\t#0: Go back to the previous menu");
            System.out.print("Please enter your choice: ");
            option = in.nextInt();
            
            //if/else to determine choice
            if(option == 0)
                showItem(invNum, itemNum);
            else if(option == 1){
                System.out.print("Please enter the new attribute: ");
                inventories.get(invNum).getItems().get(itemNum).getAttributes().set(attNum, in.next());
            }
            else if(option == 2){
                inventories.get(invNum).getItems().get(itemNum).getAttributes().remove(attNum);
                showInv(invNum);
            }
        }
    }
    
    public void save(Inventory inv) throws FileNotFoundException
    {
    		//print invs and ask to select one to save
        SaveFile save = new SaveFile(inv);
    }
    
    public Inventory load() throws IOException
    {
        LoadFile load = new LoadFile();
        Inventory inv = load.getInv();
        return inv;
    }
    
    public void mainMenu() throws IOException
    {
        int choice = -1;
        
        System.out.println("Welcome to the inventory management system.");
        System.out.println("Please enter your selection based on the following menu.");
        
        while(choice != 4)
        {
            System.out.println("#1: Create a new inventory.");
            System.out.println("#2: Load inventory.");
            System.out.println("#3: Show inventories.");
            System.out.println("#4: Exit program.");

            System.out.print("Enter choice: ");
            choice = in.nextInt();
        
        
            switch(choice)
            {
                case 1:
                    newInv();
                    break;
                case 2:
                    load();
                    break;
                case 3:
                    invMenu();
                    break;
                case 4:
                    System.out.println("The program will now exit.");
                    System.exit(0);
                    break;
                default:
                    System.out.print("Invalid option. Re-enter");
                    choice = in.nextInt();
            }       
        }
        
    }
    
    public void invMenu() throws IOException{
        int choice = -1;
        
        
        while(choice != 0){
            System.out.println("Available inventories:");
            int invNumber = 1;
            for(Inventory inv: inventories){
                System.out.println("#" + (invNumber) + ": " + inv.getName());
                invNumber++;
            }
            System.out.println("#0: Go back to main menu.");

            System.out.print("Please enter your choice: ");
            choice = in.nextInt();
            if(choice == 0)
                mainMenu();
            else if(choice < invNumber && choice != 0){
                showInv(choice - 1);
            }
            else if(choice == (invNumber + 1)){
                mainMenu();
            }
        }
    }
}
