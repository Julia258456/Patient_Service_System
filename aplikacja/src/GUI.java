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
    Panel centralPanel;
    Button[] buttons = new Button[10];
    static String enteredUsername;
    static String enteredPassword;
    int numberOfAttempts = 0;
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)(screenDimension.width * 0.7);
    int screenHeight = (int)(screenDimension.height * 0.7);
    Image icon = Toolkit.getDefaultToolkit().getImage(".//resources//icon.JPG");
    User user = new User();
    User loggedUser = new User();
    User userToEdit = new User();

    /**
     * The basic Constructor which creates basic frame of Main menu.
     */
    public GUI() {
        frame = new Frame("Patient Service System - Orthodontic office");
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

        fileMenu = new Menu("File");
        optionsMenu = new Menu("Options");
        helpMenu = new Menu("Help");
    }

    /**
     * The method which creates Buttons in main frame for Orthodontist
     */
    private void createButtonsOrthodontist(){
        buttons[0] = new Button("My visits");
        buttons[0].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[1] = new Button("My mailbox");
        buttons[1].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[2] = new Button("My profile");
        buttons[2].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[3] = new Button("Log out");
        buttons[3].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));
        buttons[4] = new Button("Exit the program");
        buttons[4].setBackground(Color.getHSBColor(178.8f,102.2f,85.1f));

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
        buttons[0].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[1] = new Button("My mailbox");
        buttons[1].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[2] = new Button("My profile");
        buttons[2].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[3] = new Button("Log out");
        buttons[3].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));
        buttons[4] = new Button("Exit the program");
        buttons[4].setBackground(Color.getHSBColor(137.4f,101.1f,85.1f));

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
        buttons[0].setBackground(Color.lightGray);
        buttons[1] = new Button("Edit users");
        buttons[1].setBackground(Color.lightGray);
        buttons[2] = new Button("Add user");
        buttons[2].setBackground(Color.lightGray);
        buttons[3] = new Button("Delete user");
        buttons[3].setBackground(Color.lightGray);
        buttons[4] = new Button("My profile");
        buttons[4].setBackground(Color.lightGray);
        buttons[5] = new Button("Log out");
        buttons[5].setBackground(Color.lightGray);
        buttons[6] = new Button("Exit the program");
        buttons[6].setBackground(Color.lightGray);

        buttons[0].addActionListener(e -> {
            frame.removeAll();
            findUser();
        });

        buttons[1].addActionListener(e -> {
            frame.removeAll();
            editUsers();
        });

        buttons[2].addActionListener(e -> {
            frame.removeAll();
            addUser();
        });

        buttons[3].addActionListener(e -> {
            frame.removeAll();
            deleteUser();
        });

        buttons[4].addActionListener(e -> {
            frame.removeAll();
            myProfileDeveloper();
        });

        buttons[5].addActionListener(e -> {
            frame.dispose();
            loginScreen();
        });
        buttons[6].addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    /**
     * The method which creates Panels in main frame
     */
    private void createPanels(){
        centralPanel = new Panel();
    }

    /**
     * The method which configures Menu Bars in main frame
     */
    public void configureMenuBars(){
        fileMenu = new Menu("File");
        helpMenu = new Menu("Help");
        optionsMenu = new Menu("Options");

        menu.add(fileMenu);
        menu.add(optionsMenu);
        menu.add(helpMenu);

        MenuItem loadFileItem = new MenuItem("Load file");
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem resetTheGraphicalInterface = new MenuItem("Reset the graphical interface (Log out)");
        MenuItem openSettingsItem = new MenuItem("Open settings");
        MenuItem yourAccountItem = new MenuItem("Your account");
        MenuItem helpItem = new MenuItem("Help");
        MenuItem gettingStartedItem = new MenuItem("Getting started");
        MenuItem checkForUpdatesItem = new MenuItem("Check for updates");
        MenuItem aboutItem = new MenuItem("About");

        loadFileItem.addActionListener(e -> {});
        exitMenuItem.addActionListener(e -> {
            frame.dispose();
            frame.setVisible(false);
            System.exit(0);
        });

        fileMenu.add(loadFileItem);
        fileMenu.add(exitMenuItem);

        resetTheGraphicalInterface.addActionListener(e -> {
            frame.dispose();
            frame.setLayout(null);
            loginScreen();
        });
        openSettingsItem.addActionListener(e -> {});

        optionsMenu.add(resetTheGraphicalInterface);
        optionsMenu.add(openSettingsItem);

        yourAccountItem.addActionListener(e -> {});
        helpItem.addActionListener(e -> {});
        gettingStartedItem.addActionListener(e -> {});
        checkForUpdatesItem.addActionListener(e -> {});
        aboutItem.addActionListener(e -> {});

        helpMenu.add(yourAccountItem);
        helpMenu.add(helpItem);
        helpMenu.add(gettingStartedItem);
        helpMenu.add(checkForUpdatesItem);
        helpMenu.add(aboutItem);
    }

    /**
     * The method which adds Panels in main frame
     */
    private void addPanels(){
        frame.add(centralPanel);
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding exit button for developer to GUI
     */
    public void addExitButtonDeveloper(){
        numberOfAttempts = 0;
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setLayout(null);
            runGUIDeveloper();
            frame.setVisible(true);
        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for adding exit button for orthodontist to GUI
     */
    public void addExitButtonOrthodontist(){
        numberOfAttempts = 0;
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setLayout(null);
            runGUIOrthodontist();
            frame.setVisible(true);
        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for adding exit button for patient to GUI
     */
    public void addExitButtonPatient(){
        numberOfAttempts = 0;
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to main menu");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setLayout(null);
            runGUIPatient();
            frame.setVisible(true);
        });
        frame.add(editUserPanel);
    }

    /**
     * The method which is responsible for finding the user by e-mail address
     */
    public void findUser(){
        enteredUsername = "null";
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        User user2 = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
        assert user2 != null;
        List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, user2);

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label usernameLabel = new Label("Username: ");
        Label userStatement = new Label("Please enter the username to see user's credentials");

        TextField userField = new TextField(20);

        Button loginButton = new Button("Find user");
        ActionListener action = e -> {
            numberOfAttempts++;
            try {
                enteredUsername = userField.getText();

                boolean userFound = false;
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userFound = true;
                        userToEdit = userToFind;
                    }
                }
                if(userFound){
                    userStatement.setText("You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with id: " + userToEdit.getUserId());

                }
                else {
                    userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
                userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
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
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding new user to the database
     */
    public void addUser(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("When adding a user, remember to fill in all data, and prohibit creating duplicates, otherwise adding will fail");
        editUserPanel.add(info);
        Label username = new Label("Username: ");
        TextField usernameTextField = new TextField();
        Label password = new Label("Password: ");
        TextField passwordTextField = new TextField();
        Label name = new Label("Name: ");
        TextField nameTextField = new TextField();
        Label surname = new Label("Surname: ");
        TextField surnameTextField = new TextField();
        Label mobileNumber = new Label("Mobile number: ");
        TextField mobileNumberTextField = new TextField();
        Label mail = new Label("E-mail: ");
        TextField mailTextField = new TextField();
        Label level = new Label("Permission level (0,1,2): ");
        TextField levelTextField = new TextField();

        editUserPanel.add(username);
        editUserPanel.add(usernameTextField);
        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);
        editUserPanel.add(level);
        editUserPanel.add(levelTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Add new user to database");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(prompt);
        editUserPanel.add(editUserButton);

        editUserButton.addActionListener(e -> {

            try {
                if( Integer.parseInt(levelTextField.getText())>3 || Integer.parseInt(levelTextField.getText())<0 )
                    throw new Exception();

                if (!levelTextField.getText().isEmpty() && !mailTextField.getText().isEmpty() && !mobileNumberTextField.getText().isEmpty() && !surnameTextField.getText().isEmpty() &&
                        !nameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !usernameTextField.getText().isEmpty()) {


                    Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
                    User admin = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
                    assert admin != null;
                    List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, admin);
                    assert list != null;
                    for(User userToCheck: list) {
                        if(usernameTextField.getText().equals(userToCheck.getUserName()))
                            throw new Exception();
                    }

                    User adminOrthodontist = DataBaseHandlingClass.LogInUser(connection, "adminortodonta", "admin");
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserLogin(usernameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserPermissionsLevel(Integer.parseInt(levelTextField.getText()));
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());


                    if (userToEdit.getUserPermissionsLevel() == 0) {
                        DataBaseHandlingClass.AddNewPatientToDB(connection, admin, userToAdd, adminOrthodontist);
                    } else if (userToEdit.getUserPermissionsLevel() == 1) {
                        try {
                            if (userToAdd.getUserLogin().equals("adminortodonta")) // double check
                                throw new Exception();
                            DataBaseHandlingClass.AddNewOrthodontistToDB(connection, admin, userToAdd);
                        } catch (Exception exception) {
                            System.out.println("Exception caught!");
                        }
                    } else if (userToEdit.getUserPermissionsLevel() == 2) {
                        try {
                            if (userToAdd.getUserLogin().equals("admin"))
                                throw new Exception();
                            DataBaseHandlingClass.AddNewAdministratorToDB(connection, admin, userToAdd);
                        } catch (Exception exception) {
                            System.out.println("Exception caught!");
                        }
                    }
                    prompt.setText("The user: " + userToAdd.getUserLogin() + ", has been successfully added to the database");
                } else
                    prompt.setText("There was an error adding the user, please check the entered data and try again...");

            }
            catch (Exception exception){
                prompt.setText("A problem has arisen while adding a new user to database");
                System.out.println("A problem has arisen while adding a new user to database");
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding user from the database
     */
    public void deleteUser(){
        enteredUsername = "null";
        Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
        User admin = DataBaseHandlingClass.LogInUser(connection, "admin", "admin");
        assert admin != null;
        List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, admin);

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label usernameLabel = new Label("Username: ");
        Label userStatement = new Label("Please enter the username to see user's credentials");

        TextField userField = new TextField(20);

        Button loginButton = new Button("Find user");
        ActionListener action = e -> {
            numberOfAttempts++;
            try {
                enteredUsername = userField.getText();

                boolean userFound = false;
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userFound = true;
                        userToEdit = userToFind;
                    }
                }
                if(userFound){
                    userStatement.setText("You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with id: " + userToEdit.getUserId());

                }
                else {
                    userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
                userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
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

        Panel deletePanel = new Panel();
        Label deleteInfo = new Label("Are you sure you want to delete this user? If yes press the button below...");
        Button deleteButton = new Button("Delete user");
        deleteButton.addActionListener(e -> {
            try {
                if (userToEdit.getUserLogin().equals("admin") || userToEdit.getUserLogin().equals("adminortodonta"))
                    throw new Exception();

                if (userToEdit.getUserPermissionsLevel() == 0) {
                    DataBaseHandlingClass.RemovePatientFromDB(connection, admin, userToEdit);
                    deleteInfo.setText("Deletion of patient: " + userToEdit.getUserLogin() + ", was successful");
                    userToEdit = null;
                } else if (userToEdit.getUserPermissionsLevel() == 1) {
                    User adminOrthodontist = DataBaseHandlingClass.LogInUser(connection, "adminortodonta", "admin");
                    deleteInfo.setText("Deletion of orthodontist: " + userToEdit.getUserLogin() + ", was successful");
                    DataBaseHandlingClass.RemoveOrthodontistFromDB(connection, admin, userToEdit, adminOrthodontist);
                    userToEdit = null;
                } else if (userToEdit.getUserPermissionsLevel() == 2) {
                    try {
                        if (userToEdit.getUserLogin().equals(admin.getUserLogin()))
                            throw new Exception();
                        DataBaseHandlingClass.RemoveAdministratorFromDB(connection, admin, userToEdit);
                        deleteInfo.setText("Deletion of developer: " + userToEdit.getUserLogin() + ", was successful");
                        userToEdit = null;
                    } catch (Exception exception) {
                        System.out.println("Exception caught! a problem has arisen while deleting user from the database");
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!");
            }

        });
        deletePanel.add(deleteInfo);
        deletePanel.add(deleteButton);
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));


        frame.removeAll();
        frame.add(findUserPanel, BorderLayout.NORTH);
        frame.add(findPanelMessage, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH);
        frame.add(deletePanel);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for editing the user's credentials
     */
    public void editUsers(){
        frame.removeAll();
        userToEdit = null;
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
        Button loginButton = new Button("Find user");
        Panel editUserPanel = new Panel();
        Label info = new Label("Please first find the user in database, then enter all of the following data to edit the user's credentials...");
        editUserPanel.add(info);
        Label username = new Label("New username: ");
        TextField usernameTextField = new TextField("null");
        Label password = new Label("New password: ");
        TextField passwordTextField = new TextField("null");
        Label name = new Label("New user's name: ");
        TextField nameTextField = new TextField("null");
        Label surname = new Label("New user's surname: ");
        TextField surnameTextField = new TextField("null");
        Label mobileNumber = new Label("New user's mobile number: ");
        TextField mobileNumberTextField = new TextField("null");
        Label mail = new Label("New user's mail: ");
        TextField mailTextField = new TextField("null");
        Label address = new Label("New user's address: ");
        TextField addressTextField = new TextField("null");
        Label level = new Label("New user's level of permissions: ");
        TextField levelTextField = new TextField("null");

        ActionListener action = e -> {
            numberOfAttempts++;
            try {
                enteredUsername = userField.getText();

                boolean userFound = false;
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userFound = true;
                        userToEdit = userToFind;
                        enteredUsername = userField.getText();
                        usernameTextField.setText(userToEdit.getUserLogin());
                        passwordTextField.setText(userToEdit.getUserPassword());
                        nameTextField.setText(userToEdit.getUserName());
                        surnameTextField.setText(userToEdit.getUserSurname());
                        mobileNumberTextField.setText(userToEdit.getUserTelephoneNumber());
                        mailTextField.setText(userToEdit.getUserEmail());
                        levelTextField.setText(Integer.toString(userToEdit.getUserId()));
                        addressTextField.setText(userToEdit.getUserAddress());
                    }
                }
                if(userFound){
                    userStatement.setText("You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with id: " + userToEdit.getUserId());

                }
                else {
                    userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
                userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
            }
        };

        userField.addActionListener(action);
        loginButton.addActionListener(action);

        findUserPanel.add(usernameLabel);
        findUserPanel.add(userField);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        findPanelMessage.add(userStatement);

        editUserPanel.add(username);
        editUserPanel.add(usernameTextField);
        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(level);
        editUserPanel.add(levelTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit user");
        editUserPanel.add(loginButton);
        Label prompt = new Label("Please before editing the user find him in the database");
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            try {
                if (userToEdit != null) {
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserLogin(usernameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserPermissionsLevel(Integer.parseInt(levelTextField.getText()));
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(userToEdit.getUserId());
                    userToAdd.setUserAddress(userToEdit.getUserAddress());
                    DataBaseHandlingClass.EditUserInfoInDB(connection, userToEdit, userToAdd);
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
        });

        frame.add(findUserPanel, BorderLayout.NORTH);
        frame.add(findPanelMessage, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH);
        frame.add(editUserPanel, BorderLayout.SOUTH);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for patient's mailbox
     */
    public void myMailboxPatient(){
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for orthodontist's mailbox
     */
    public void myMailboxOrthodontist(){
        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing developer's credentials
     */
    public void myProfileDeveloper(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("If u wish to edit your credentials, please change them and enter the button below");
        editUserPanel.add(info);
        Label address = new Label("Your address: ");
        TextField addressTextField = new TextField(loggedUser.getUserAddress());
        Label password = new Label("Your password: ");
        TextField passwordTextField = new TextField(loggedUser.getUserPassword());
        Label name = new Label("Your name: ");
        TextField nameTextField = new TextField(loggedUser.getUserName());
        Label surname = new Label("Your surname: ");
        TextField surnameTextField = new TextField(loggedUser.getUserSurname());
        Label mobileNumber = new Label("Your mobile number: ");
        TextField mobileNumberTextField = new TextField(loggedUser.getUserTelephoneNumber());
        Label mail = new Label("Your mail: ");
        TextField mailTextField = new TextField(loggedUser.getUserEmail());

        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit my data");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(prompt);
        editUserPanel.add(editUserButton);

        editUserButton.addActionListener(e -> {
            try {
                if (userToEdit != null) {
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(userToEdit.getUserId());
                    Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
                    DataBaseHandlingClass.EditUserInfoInDB(connection, userToEdit, userToAdd);
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing patient's credentials
     */
    public void myProfilePatient(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("If u wish to edit your credentials, please change them and enter the button below");
        editUserPanel.add(info);
        Label address = new Label("Your address: ");
        TextField addressTextField = new TextField(loggedUser.getUserAddress());
        Label password = new Label("Your password: ");
        TextField passwordTextField = new TextField(loggedUser.getUserPassword());
        Label name = new Label("Your name: ");
        TextField nameTextField = new TextField(loggedUser.getUserName());
        Label surname = new Label("Your surname: ");
        TextField surnameTextField = new TextField(loggedUser.getUserSurname());
        Label mobileNumber = new Label("Your mobile number: ");
        TextField mobileNumberTextField = new TextField(loggedUser.getUserTelephoneNumber());
        Label mail = new Label("Your mail: ");
        TextField mailTextField = new TextField(loggedUser.getUserEmail());

        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit my data");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            try {
                if (userToEdit != null) {
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(userToEdit.getUserId());
                    Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
                    DataBaseHandlingClass.EditUserInfoInDB(connection, userToEdit, userToAdd);
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing orthodontist's credentials
     */
    public void myProfileOrthodontist(){
        frame.removeAll();

        Panel editUserPanel = new Panel();
        Label info = new Label("If u wish to edit your credentials, please change them and enter the button below");
        editUserPanel.add(info);
        Label address = new Label("Your address: ");
        TextField addressTextField = new TextField(loggedUser.getUserAddress());
        Label password = new Label("Your password: ");
        TextField passwordTextField = new TextField(loggedUser.getUserPassword());
        Label name = new Label("Your name: ");
        TextField nameTextField = new TextField(loggedUser.getUserName());
        Label surname = new Label("Your surname: ");
        TextField surnameTextField = new TextField(loggedUser.getUserSurname());
        Label mobileNumber = new Label("Your mobile number: ");
        TextField mobileNumberTextField = new TextField(loggedUser.getUserTelephoneNumber());
        Label mail = new Label("Your mail: ");
        TextField mailTextField = new TextField(loggedUser.getUserEmail());

        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));

        Button editUserButton = new Button("Edit my data");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            try {
                if (userToEdit != null) {
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(userToEdit.getUserId());
                    Connection connection = DataBaseHandlingClass.StartConnectionWithDB();
                    DataBaseHandlingClass.EditUserInfoInDB(connection, userToEdit, userToAdd);
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            }
            catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing patient's visits
     */
    public void myVisitsPatient(){
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method is responsible for viewing and editing orthodontist's visits
     */
    public void myVisitsOrthodontist(){
        addExitButtonOrthodontist();
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
                assert user != null;
                User admin = DataBaseHandlingClass.LogInUser(connection,"admin", "admin");
                assert admin != null;
                List<User> list = DataBaseHandlingClass.SearchForAllUsers(connection, admin);

                assert list != null;
                for(User userToFind: list) {
                    if (userToFind.getUserLogin().equals(enteredUsername)){
                        loggedUser.setUserId(userToFind.getUserId());
                        loggedUser.setUserLogin(userToFind.getUserLogin());
                        loggedUser.setUserEmail(userToFind.getUserEmail());
                        loggedUser.setUserName(userToFind.getUserName());
                        loggedUser.setUserSurname(userToFind.getUserSurname());
                        loggedUser.setUserTelephoneNumber(userToFind.getUserTelephoneNumber());
                        loggedUser.setUserPermissionsLevel(userToFind.getUserPermissionsLevel());
                    }
                }

                if (user.getUserPermissionsLevel()==0){
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
                loginStatement.setText("Your input is wrong (Attempt: " + numberOfAttempts + ")");
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
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
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
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
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
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        for(int i=0;i<7;i++)
            centralPanel.add(buttons[i]);
    }
}
