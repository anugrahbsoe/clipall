package test;

import dev.clipall.business.ClipboardLogic;
import dev.clipall.business.GenericLogic;
import dev.clipall.business.HotKeyLogic;
import dev.clipall.business.systray.DefaultSystemTray;
import dev.clipall.view.SearchFrame;
import dev.utils.log.Logger;

/**
 *
 * @author erol
 */
public class LogicTest {

    public static void main(String[] args){
        
        GenericLogic.getInstance().initAppSettings();
        HotKeyLogic.getInstance().initializeHotKeyListener(SearchFrame.getInstance().getTitle());
        ClipboardLogic.getInstance().startToListen();
        DefaultSystemTray.getInstance().run();

        Logger.getLogger().debug("listening...", LogicTest.class);
        System.out.println("listening...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
