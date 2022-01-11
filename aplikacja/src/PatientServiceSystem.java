/**
 * the main class that runs the program
 */
public class PatientServiceSystem {

    /**
     * The main static method
     * @param args are not being used
     */
    public static void main(String [] args){

        GUI gui = new GUI();
        gui.configureMenuBars();
        gui.loginScreen();

    }
}
