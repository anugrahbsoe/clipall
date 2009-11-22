package test;

import java.io.File;



/**
 *
 * @author erol
 */
public class DummyTest {

    public static void main(String[] args){
        String currentDir = new File("").getAbsolutePath();
        System.out.println("currentDir: " + currentDir);
    }
    
}
