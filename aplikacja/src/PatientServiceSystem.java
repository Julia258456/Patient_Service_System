import com.sun.scenario.effect.ImageData;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PatientServiceSystem {

    /**
     * The main method
     * @param args are not being used
     */
    public static void main(String [] args){

        GUI gui = new GUI();
        gui.configureMenuBars();
        gui.loginScreen();

    }
}
