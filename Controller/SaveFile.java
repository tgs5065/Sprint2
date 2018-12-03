package Controller;

import java.io.File;
import Model.Inventory;
import Model.Item;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFile {
    private File file;
    private Inventory inv;
    private PrintWriter writer;
    private Scanner in;
    
    public SaveFile(Inventory inv) throws FileNotFoundException{
        this.inv = inv;
        in = new Scanner(System.in);
        System.out.print("Please enter the file name you wish to use: ");
        file = new File(in.next() + ".txt");
        writer = new PrintWriter(file);
        writeData();
        writer.close();
    }
    
    public void writeData(){
        writer.println("inv%");
        writer.println(inv.getName());
        ArrayList<Item> items = inv.getItems();
        for(Item item: items){
            writer.println("item%");
            writer.println(item.getName());
            ArrayList<String> attrs = item.getAttributes();
            for(String att: attrs){
                writer.println("att%");
                writer.println(att);
            }
        }
        writer.println("%inv");
    }
}
