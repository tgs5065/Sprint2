package Controller;



import java.io.File;
import Model.Inventory;
import Model.Item;
import View.RunProgram;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadFile {
    private File file;
    private Inventory inv;
    private Scanner in;
    private Scanner sc;
    
    public LoadFile() throws FileNotFoundException, IOException{
        in = new Scanner(System.in);
        System.out.print("Please enter the name of the file you wish to open: ");
        file = new File(in.next() + ".txt");
        sc = new Scanner(new FileReader(file));
        inv = new Inventory();
        loadData();
        sc.close();
        RunProgram.run.addInv(inv);
    }
    
    public void loadData() throws IOException{
        if(sc.nextLine().equals("inv%")){
            inv.setName(sc.nextLine());
            if(sc.nextLine().equals("item%")){
                Item item = newItem();
                inv.addItem(item);
            }
        }
    }
    
    public Item newItem(){
        Item item = new Item();
        item.setName(sc.nextLine());
        if(sc.nextLine().equals("att%")){
            item.addAttribute(sc.nextLine());
        }
        return item;
    }

    public Inventory getInv() {
        return inv;
    }
}
