import java.awt.*;
import java.awt.event.*;

public class GUI{
    Frame frame;
    MenuBar menu;
    Menu fileMenu, optionsMenu, helpMenu;
    Panel upperPanel, centralPanel, lowerPanel;
    Button[] buttons = new Button[10];
    static String username;
    static String password;
    int numberOfAttempts = 0;
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)(screenDimension.width * 0.7);
    int screenHeight = (int)(screenDimension.height * 0.7);

    /**
     * The basic Constructor which creates basic frame of Main menu.
     * @return Null
     */
    public GUI() {
        frame = new Frame("Main menu");
        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit( 0 );
                    }
                }
        );
        menu = new MenuBar();
        createPanels();
        createButtons();
        createMenu();

        fileMenu = new Menu("File");
        optionsMenu = new Menu("Options");
        helpMenu = new Menu("Help");
    }

    /**
     * The method which creates Buttons in main frame
     * @return Null
     */
    private void createButtons(){
        buttons[0] = new Button("Search for a patient from the database");
        buttons[0].setActionCommand("Action button 1");
        buttons[1] = new Button("Mailbox");
        buttons[1].setActionCommand("Action button 2");
        buttons[2] = new Button("Generate a report");
        buttons[2].setActionCommand("Action button 3");
        buttons[3] = new Button("Log out");
        buttons[3].setActionCommand("Action button 4");
        buttons[4] = new Button("Exit the program");
        buttons[4].setActionCommand("Action button 5");


        buttons[3].addActionListener(e -> {
            frame.dispose();
            loginScreen();
        });
        buttons[4].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /**
     * The method which creates Panels in main frame
     * @return Null
     */
    private void createPanels(){
        upperPanel = new Panel();
        lowerPanel = new Panel();
        centralPanel = new Panel();
    }

    /**
     * The method which creates Menu in main frame
     * @return Null
     */
    private void createMenu(){
        fileMenu = new Menu("File");
        helpMenu = new Menu("Help");
        optionsMenu = new Menu("Options");
    }

    /**
     * The method which adds Menu Bars in main frame
     * @return Null
     */
    private void addMenuBars(){
        menu.add(fileMenu);
        menu.add(optionsMenu);
        menu.add(helpMenu);
    }

    /**
     * The method which configures Menu Bars in main frame
     * @return Null
     */
    private void configureMenuBars(){
        fileMenu.add(new MenuItem("Load file"));
        MenuItem defaultItem = new MenuItem("Exit");
        defaultItem.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false);
            System.exit(0);
        });
        fileMenu.add(defaultItem);
        optionsMenu.add(new MenuItem("Open settings"));
        optionsMenu.add(new MenuItem("Reset the graphical interface"));

        helpMenu.add(new MenuItem("Your account"));
        helpMenu.add(new MenuItem("Help"));
        helpMenu.add(new MenuItem("Getting started"));
        helpMenu.add(new MenuItem("Check for updates"));
        helpMenu.add(new MenuItem("About"));
    }

    /**
     * The method which adds Panels in main frame
     * @return Null
     */
    private void addPanels(){
        GridLayout layout = new GridLayout(6,6,10,10);
        upperPanel.setLayout(layout);
        centralPanel.setLayout(layout);
        lowerPanel.setLayout(layout);
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for login screen
     * @return Null
     */
    public void loginScreen() {
        username = "null";
        password = "null";

        Frame loginFrame = new Frame("Login Screen");

        Panel welcomePanel = new Panel();
        Panel loginPanelMessage = new Panel();
        Panel loginPanel = new Panel(new GridBagLayout());

        Label labelUsername = new Label("Username: ");
        Label labelPassword = new Label("Password: ");
        Label loginStatement = new Label("Please enter your credentials");
        Label welcomeLabel = new Label("Patient Service System - Orthodontic office");
        Label gap = new Label("    ");

        TextField usernameField = new TextField(20);
        TextField passwordField = new TextField(20);

        Button loginButton = new Button("Login");

        ActionListener action = e -> {
            username = usernameField.getText();
            password = passwordField.getText();
            numberOfAttempts++;
            if (username.equals("admin") && password.equals("admin")) {
                loginFrame.setVisible(false);
                loginFrame.dispose();
                runGUI();
            }
            else {
                loginStatement.setText("      Your data is wrong (Attempt: " + numberOfAttempts + ")");
                System.out.println("Your data is wrong (Attempt: " + numberOfAttempts + ")");
            }
        };

        welcomePanel.add(welcomeLabel);

        passwordField.addActionListener(action);
        usernameField.addActionListener(action);
        loginButton.addActionListener(action);

        loginPanel.add(labelUsername);
        loginPanel.add(usernameField);
        loginPanel.add(labelPassword);
        loginPanel.add(passwordField);
        loginPanel.add(gap);
        loginPanel.add(loginButton);

        loginPanelMessage.add(loginStatement, BorderLayout.NORTH);
        loginPanelMessage.setVisible(true);

        loginFrame.setBackground(Color.darkGray);
        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.add(loginPanelMessage, BorderLayout.SOUTH);
        loginFrame.add(welcomePanel,BorderLayout.NORTH);
        loginFrame.setSize((int)(screenWidth/1.7),(screenHeight/3));
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                loginFrame.setVisible(false);
                loginFrame.dispose();
                if (!username.equals("admin") && !password.equals("admin"))
                    System.exit(0);
            }

    });
    }

    /**
     * The method which is responsible for running main frame of GUI
     * @return Null
     */
    public void runGUI() {
        addMenuBars();
        configureMenuBars();
        addPanels();

        frame.setBackground(Color.darkGray);
        frame.setSize(screenWidth,screenHeight);
        frame.setMenuBar(menu);
        frame.setResizable(false);
        for(int i=0;i<5;i++)
            centralPanel.add(buttons[i]);
    }
}
