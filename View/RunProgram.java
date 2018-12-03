package View;

import Controller.Program;
import java.io.IOException;

public class RunProgram
{
    public static Program run;
    
    public static void main(String[] args) throws IOException{
        run = new Program();
        run.mainMenu();
    }
}
