import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.List;

public class GUI{
    Frame frame;
    MenuBar menu;
    Menu fileMenu, optionsMenu, helpMenu;
    Panel upperPanel, centralPanel, lowerPanel;
    Button[] buttons = new Button[10];
    static String enteredUsername;
    static String enteredPassword;
    int numberOfAttempts = 0;
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)(screenDimension.width * 0.7);
    int screenHeight = (int)(screenDimension.height * 0.7);
    Image icon = Toolkit.getDefaultToolkit().getImage(".//resources//icon.JPG");
    User user = new User();

    /**
     * The basic Constructor which creates basic frame of Main menu.
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
        createMenu();

        fileMenu = new Menu("File");
        optionsMenu = new Menu("Options");
        helpMenu = new Menu("Help");
    }

    /**
     * The method which creates Buttons in main frame for Orthodontist
     */
    private void createButtonsOrthodontist(){
        buttons[0] = new Button("My visits");
        buttons[1] = new Button("My mailbox");
        buttons[2] = new Button("My profile");
        buttons[3] = new Button("Log out");
        buttons[4] = new Button("Exit the program");

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
     * The method which creates Buttons in main frame for Patient
     */
    private void createButtonsPatient(){
        buttons[0] = new Button("My visits");
        buttons[1] = new Button("My mailbox");
        buttons[2] = new Button("My profile");
        buttons[3] = new Button("Log out");
        buttons[4] = new Button("Exit the program");

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
     * The method which creates Buttons in main frame for Developer
     */
    private void createButtonsDeveloper(){
        buttons[0] = new Button("Find user in database");
        buttons[1] = new Button("Edit users");
        buttons[2] = new Button("My mailbox");
        buttons[3] = new Button("My profile");
        buttons[4] = new Button("Log out");
        buttons[5] = new Button("Exit the program");

        buttons[0].addActionListener(e -> {
            frame.dispose();
            findUserScreen();
        });

        buttons[4].addActionListener(e -> {
            frame.dispose();
            loginScreen();
        });
        buttons[5].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /**
     * The method which creates Panels in main frame
     */
    private void createPanels(){
        upperPanel = new Panel();
        lowerPanel = new Panel();
        centralPanel = new Panel();
    }

    /**
     * The method which creates Menu in main frame
     */
    private void createMenu(){
        fileMenu = new Menu("File");
        helpMenu = new Menu("Help");
        optionsMenu = new Menu("Options");
    }

    /**
     * The method which adds Menu Bars in main frame
     */
    private void addMenuBars(){
        menu.add(fileMenu);
        menu.add(optionsMenu);
        menu.add(helpMenu);
    }

    /**
     * The method which configures Menu Bars in main frame
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
     * The method which is responsible for finding the user by e-mail address
     */
    public void findUserScreen(){
        enteredUsername = "null";
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        User user = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
        List<User> list = DataBaseHandlingClass.SearchForPatientsOfOrthodontist(connection, user);

        Frame findUserFrame = new Frame("Find user by his email adress");

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label labelMail = new Label("e-mail address: ");
        Label emailStatement = new Label("Please enter the user's email address credentials to find the user");

        TextField emailField = new TextField(20);

        Button loginButton = new Button("Find User");
        ActionListener action = e -> {
            numberOfAttempts++;
            try {
                enteredUsername = emailField.getText();
                User userToBeFound = new User();

                boolean userFound = false;
                for(User userToFind: list){
                    if(userToFind.getUserEmail().equals(enteredUsername)){
                        System.out.println(userToFind.getUserEmail());
                        userFound = true;
                        userToBeFound = userToFind;
                    }
                }
                if(userFound){
                    emailStatement.setText("You have found user: " + userToBeFound.getUserLogin());
                    System.out.println("You have found user: " + userToBeFound.getUserLogin());
                }
                else {
                    emailStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                    System.out.println("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            }
            catch(Exception exception){
                emailStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                System.out.println("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
            }
        };

        emailField.addActionListener(action);
        loginButton.addActionListener(action);

        findUserPanel.add(labelMail);
        findUserPanel.add(emailField);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findUserPanel.add(loginButton);

        Button exitButton = new Button("Return to main menu");
        exitButton.addActionListener(e -> {
            findUserFrame.setVisible(false);
            findUserFrame.dispose();
            numberOfAttempts = 0;
            runGUIDeveloper();
        });
        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        findPanelMessage.add(emailStatement);
        findPanelMessage.add(exitButton);

        findUserFrame.setLocation((screenWidth/4),(screenHeight/2));
        findUserFrame.setIconImage(icon);
        findUserFrame.add(findUserPanel, BorderLayout.CENTER);
        findUserFrame.add(findPanelMessage, BorderLayout.SOUTH);
        findUserFrame.add(welcomePanel,BorderLayout.NORTH);
        findUserFrame.setSize((screenWidth),(screenHeight/2));
        findUserFrame.setVisible(true);
        findUserFrame.setResizable(false);
        findUserFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                findUserFrame.setVisible(false);
                findUserFrame.dispose();
                if (user == null)
                    System.exit(0);
            }

        });
    }

    /**
     * The method which is responsible for login screen
     */
    public void loginScreen() {
        enteredUsername = "null";
        enteredPassword = "null";

        Frame loginFrame = new Frame("Login Screen");

        Panel welcomePanel = new Panel();
        Panel loginPanelMessage = new Panel();
        Panel loginPanel = new Panel(new GridBagLayout());

        Label labelUsername = new Label("Username: ");
        Label labelPassword = new Label("Password: ");
        Label loginStatement = new Label("Please enter your credentials");
        Label welcomeLabel = new Label("Patient Service System - Orthodontic office");

        TextField usernameField = new TextField(20);
        TextField passwordField = new TextField(20);

        Button loginButton = new Button("Login");

        ActionListener action = e -> {
            try {
                enteredUsername = usernameField.getText();
                enteredPassword = passwordField.getText();
                numberOfAttempts++;
                Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
                User user = DataBaseHandlingClass.LogInUser(connection, enteredUsername, enteredPassword);

                if(user == null){
                    loginStatement.setText("Your data is wrong (Attempt: " + numberOfAttempts + ")");
                    System.out.println("Your data is wrong (Attempt: " + numberOfAttempts + ")");
                }
                else if (user.getUserPermissionsLevel()==0){
                    numberOfAttempts = 0;
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    runGUIPatient();
                }
                else if(user.getUserPermissionsLevel()==1){
                    numberOfAttempts = 0;
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    runGUIOrthodontist();
                }
                else if(user.getUserPermissionsLevel()==2){
                    numberOfAttempts = 0;
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    runGUIDeveloper();
                }
            }
            catch(Exception exception){
                loginStatement.setText("Your data is wrong (Attempt: " + numberOfAttempts + ")");
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
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.add(loginButton);

        loginPanelMessage.add(loginStatement, BorderLayout.NORTH);
        loginPanelMessage.setVisible(true);

        loginFrame.setLocation((screenWidth/2),(screenHeight/2));
        loginFrame.setIconImage(icon);
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
                if (user == null)
                    System.exit(0);
            }

        });
    }

    /**
     * The method which is responsible for running main frame of GUI for Orthodontist
     */
    public void runGUIOrthodontist() {
        createButtonsOrthodontist();
        addMenuBars();
        configureMenuBars();
        addPanels();

        frame.setIconImage(icon);
        frame.setLocation((screenWidth/5),(screenHeight/4));
        frame.setSize(screenWidth,screenHeight);
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        for(int i=0;i<5;i++)
            centralPanel.add(buttons[i]);
    }

    /**
     * The method which is responsible for running main frame of GUI for Patient
     */
    public void runGUIPatient() {
        createButtonsPatient();
        addMenuBars();
        configureMenuBars();
        addPanels();

        frame.setIconImage(icon);
        frame.setLocation((screenWidth/5),(screenHeight/4));
        frame.setSize(screenWidth,screenHeight);
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        for(int i=0;i<5;i++)
            centralPanel.add(buttons[i]);
    }

    /**
     * The method which is responsible for running main frame of GUI for Developer
     */
    public void runGUIDeveloper() {
        createButtonsDeveloper();
        addMenuBars();
        configureMenuBars();
        addPanels();

        frame.setIconImage(icon);
        frame.setLocation((screenWidth/5),(screenHeight/4));
        frame.setSize(screenWidth,screenHeight);
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        for(int i=0;i<6;i++)
            centralPanel.add(buttons[i]);
    }
}
