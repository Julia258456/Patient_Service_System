import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.List;

/**
 * The main class which represents graphical user interface
 */
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

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            myVisitsOrthodontist();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            myMailboxOrthodontist();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            myProfileOrthodontist();
        });

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

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            myVisitsPatient();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            myMailboxPatient();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            myProfilePatient();
        });

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
        buttons[2] = new Button("My profile");
        buttons[3] = new Button("Log out");
        buttons[4] = new Button("Exit the program");

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            findUserScreen();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            editUsers();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            myProfileDeveloper();
        });

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
    public void addMenuBars(){
        menu.add(fileMenu);
        menu.add(optionsMenu);
        menu.add(helpMenu);
    }

    /**
     * The method which configures Menu Bars in main frame
     */
    public void configureMenuBars(){
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
     * The method which is responsible for adding exit buttons to GUI
     */
    public void addExitButton(){
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.dispose();
            frame.setLayout(null);
            runGUIDeveloper();

        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for finding the user by e-mail address
     */
    public void findUserScreen(){
        enteredUsername = "null";
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        User user = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
        assert user != null;
        List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, user);

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label usernameLabel = new Label("Username: ");
        Label userStatement = new Label("Please enter the username to see user's credentials");

        TextField userField = new TextField(20);

        Button loginButton = new Button("Find User");
        ActionListener action = e -> {
            numberOfAttempts++;
            try {
                enteredUsername = userField.getText();
                User userToBeFound = new User();

                boolean userFound = false;
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userFound = true;
                        userToBeFound = userToFind;
                    }
                }
                if(userFound){
                    userStatement.setText("You have found user: " + userToBeFound.getUserName() + " " + userToBeFound.getUserSurname() + ", with id: " + userToBeFound.getUserId());
                }
                else {
                    userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                    System.out.println("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
                userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                System.out.println("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
            }
        };

        userField.addActionListener(action);
        loginButton.addActionListener(action);

        findUserPanel.add(usernameLabel);
        findUserPanel.add(userField);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findUserPanel.add(loginButton);

        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        findPanelMessage.add(userStatement);

        frame.removeAll();
        frame.add(findUserPanel, BorderLayout.NORTH);
        frame.add(findPanelMessage, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH);
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);

    }

    /**
     * The method
     */
    public void editUsers(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myMailboxPatient(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myMailboxOrthodontist(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myProfileDeveloper(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myProfilePatient(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myProfileOrthodontist(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myVisitsPatient(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method
     */
    public void myVisitsOrthodontist(){
        addExitButton();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
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
        frame.removeAll();
        createButtonsOrthodontist();
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
        frame.removeAll();
        createButtonsPatient();
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
        frame.removeAll();
        createButtonsDeveloper();
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
}
