package test;

import dev.clipall.business.autostart.AutoStart;
import java.io.File;

/**
 *
 * @author Erol Hira
 */
public class RegistryTest {

    public RegistryTest(){
        test();
    }

    public static void main(String args[]){
        new RegistryTest();
    }

    private void test() {

        boolean autoStart = AutoStart.getInstance().autoStartEnabled();
        System.out.println("autostart: " + autoStart);

        AutoStart.getInstance().setAutoStart(false);
        autoStart = AutoStart.getInstance().autoStartEnabled();
        System.out.println("autostart: " + autoStart);

        String currentDir = new File(".").getAbsolutePath();
        System.out.println("currentDir: " + currentDir);
    }
}
