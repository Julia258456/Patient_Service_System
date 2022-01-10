import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.List;

/**
 * The main class which represents graphical user interface - GUI
 */
public class GUI{
    Frame frame;
    MenuBar menu;
    Menu fileMenu, optionsMenu, helpMenu;
    Panel centralPanel;
    Button[] buttons = new Button[10];
    static String enteredUsername;
    static String enteredPassword;
    Image icon = Toolkit.getDefaultToolkit().getImage(".//resources//icon.JPG");
    User user = new User();
    User loggedUser = new User();
    User userToEdit = new User();
    int numberOfAttempts = 0;
    int SCREEN_RES = 0;
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)(screenDimension.width * 0.7);
    int screenHeight = (int)(screenDimension.height * 0.7);
    Color colorForOrthodontist = Color.getHSBColor(178.8f,102.2f,85.1f);
    Color colorForPatient = Color.getHSBColor(137.4f,101.1f,85.1f);
    Color colorForDeveloper = Color.darkGray.brighter().brighter();

    /**
     * The Constructor which creates basic frame of main menu.
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
        centralPanel = new Panel();
        fileMenu = new Menu("File");
        optionsMenu = new Menu("Options");
        helpMenu = new Menu("Help");
    }

    /**
     * The method which creates Buttons in main frame for Orthodontist and determines their actions
     */
    private void createButtonsOrthodontist(){
        buttons[0] = new Button("My visits");
        buttons[0].setBackground(colorForOrthodontist);
        buttons[1] = new Button("My mailbox");
        buttons[1].setBackground(colorForOrthodontist);
        buttons[2] = new Button("My profile");
        buttons[2].setBackground(colorForOrthodontist);
        buttons[3] = new Button("My patients");
        buttons[3].setBackground(colorForOrthodontist);
        buttons[4] = new Button("Log out");
        buttons[4].setBackground(colorForOrthodontist);
        buttons[5] = new Button("Exit the program");
        buttons[5].setBackground(colorForOrthodontist);

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
            frame.removeAll();
            myPatients();
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
     * The method which creates Buttons in main frame for Patient and determines their actions
     */
    private void createButtonsPatient(){
        buttons[0] = new Button("My visits");
        buttons[0].setBackground(colorForPatient);
        buttons[1] = new Button("My mailbox");
        buttons[1].setBackground(colorForPatient);
        buttons[2] = new Button("My profile");
        buttons[2].setBackground(colorForPatient);
        buttons[3] = new Button("Log out");
        buttons[3].setBackground(colorForPatient);
        buttons[4] = new Button("Exit the program");
        buttons[4].setBackground(colorForPatient);

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
     * The method which creates Buttons in main frame for Developer and determines their actions
     */
    private void createButtonsDeveloper(){
        buttons[0] = new Button("Find user in database");
        buttons[0].setBackground(colorForDeveloper);
        buttons[1] = new Button("Edit users");
        buttons[1].setBackground(colorForDeveloper);
        buttons[2] = new Button("Add user");
        buttons[2].setBackground(colorForDeveloper);
        buttons[3] = new Button("Delete user");
        buttons[3].setBackground(colorForDeveloper);
        buttons[4] = new Button("My profile");
        buttons[4].setBackground(colorForDeveloper);
        buttons[5] = new Button("Log out");
        buttons[5].setBackground(colorForDeveloper);
        buttons[6] = new Button("Exit the program");
        buttons[6].setBackground(colorForDeveloper);

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
     * The method which adds and configures Menu Bars in main frame
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
        MenuItem resetTheGraphicalInterface = new MenuItem("Reset the graphical user interface (Log out from the app)");
        MenuItem openSettingsItem = new MenuItem("Open settings");
        MenuItem yourAccountItem = new MenuItem("Your account");
        MenuItem helpItem = new MenuItem("Help");
        MenuItem gettingStartedItem = new MenuItem("Getting started");
        MenuItem checkForUpdatesItem = new MenuItem("Check for updates");
        MenuItem aboutItem = new MenuItem("About");

        loadFileItem.addActionListener(e -> {
            JFrame jFrame = new JFrame("Preview of pantomography image");
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            jFrame.setIconImage(icon);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(String.valueOf(fileChooser.getSelectedFile().getAbsoluteFile()));
                jFrame.setVisible(true);
                ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                Panel panel= new Panel();
                panel.add(new JLabel(imageIcon));
                jFrame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
                jFrame.add(panel);
                jFrame.setLocationRelativeTo(null);
            }

        });
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
        openSettingsItem.addActionListener(e -> {
            Frame settingsFrame = new Frame("Settings");
            settingsFrame.setIconImage(icon);
            settingsFrame.setVisible(true);
            Label message = new Label("Resize the window using these options:", Label.CENTER);
            Button normalButton = new Button("Standard (70% screen size)");
            normalButton.addActionListener(e1 -> {
                frame.setSize(screenWidth,screenHeight);
                frame.setLocationRelativeTo(null);
                SCREEN_RES = 0;
                for(int i=0; i<10;i++) {
                    if(buttons[i] == null)
                        break;
                    buttons[i].setMaximumSize(new Dimension((int) (frame.getWidth() / 1.5), frame.getHeight() / 9));
                }
                frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
            });
            Button greaterButton = new Button("Greater (90% screen size)");
            greaterButton.addActionListener(e1 -> {
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                frame.setLocationRelativeTo(null);
                SCREEN_RES = 1;
                for(int i=0; i<10;i++) {
                    if(buttons[i] == null)
                        break;
                    buttons[i].setMaximumSize(new Dimension((int) (frame.getWidth() / 1.5), frame.getHeight() / 9));
                }
                frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
            });
            Button fullscreenButton = new Button("Fullscreen (100% screen size)");
            fullscreenButton.addActionListener(e1 -> {
                frame.setSize(screenDimension.width, screenDimension.height);
                frame.setLocationRelativeTo(null);
                SCREEN_RES = 3;
                for(int i=0; i<10;i++) {
                    if(buttons[i] == null)
                        break;
                    buttons[i].setMaximumSize(new Dimension((int) (frame.getWidth() / 1.5), frame.getHeight() / 9));
                }
                frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
            });
            settingsFrame.add(message);
            settingsFrame.add(normalButton);
            settingsFrame.add(greaterButton);
            settingsFrame.add(fullscreenButton);
            settingsFrame.setLayout(new BoxLayout(settingsFrame, BoxLayout.Y_AXIS));
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            settingsFrame.setLocation(location);
            settingsFrame.setSize(frame.getWidth()/3,frame.getHeight()/3);
            settingsFrame.setVisible(true);
            switch (loggedUser.getUserPermissionsLevel()){
                case 0:
                    normalButton.setBackground(colorForPatient);
                    greaterButton.setBackground(colorForPatient);
                    fullscreenButton.setBackground(colorForPatient);
                    break;
                case 1:
                    normalButton.setBackground(colorForOrthodontist);
                    greaterButton.setBackground(colorForOrthodontist);
                    fullscreenButton.setBackground(colorForOrthodontist);
                    break;
                case 2:
                    normalButton.setBackground(colorForDeveloper);
                    greaterButton.setBackground(colorForDeveloper);
                    fullscreenButton.setBackground(colorForDeveloper);
                    break;
            }
            settingsFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    settingsFrame.setVisible(false);
                    settingsFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });

        optionsMenu.add(resetTheGraphicalInterface);
        optionsMenu.add(openSettingsItem);

        yourAccountItem.addActionListener(e -> {
            if(loggedUser.getUserPermissionsLevel()==0) {
                frame.removeAll();
                myProfilePatient();
            } else if(loggedUser.getUserPermissionsLevel()==1){
                frame.removeAll();
                myProfileOrthodontist();
            } else if(loggedUser.getUserPermissionsLevel()==2){
                frame.removeAll();
                myProfileDeveloper();
            }
        });
        helpItem.addActionListener(e -> {
            Frame helpFrame = new Frame("Help");
            switch (loggedUser.getUserPermissionsLevel()){
                case 0:
                    helpFrame.setBackground(colorForPatient);
                    break;
                case 1:
                    helpFrame.setBackground(colorForOrthodontist);
                    break;
                case 2:
                    helpFrame.setBackground(colorForDeveloper);
                    break;
            }
            helpFrame.setIconImage(icon);
            helpFrame.setVisible(true);
            Label message = new Label("", Label.CENTER);
            if(loggedUser.getUserPermissionsLevel() == 1 || loggedUser.getUserPermissionsLevel() == 0)
                message.setText("For assistance with the application, please contact the administrator in your mailbox");
            else
                message.setText("For assistance with the application, please contact the your employer");
            helpFrame.add(message);
            helpFrame.setLayout(new BoxLayout(helpFrame, BoxLayout.Y_AXIS));
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            helpFrame.setLocation(location);
            helpFrame.setSize(frame.getWidth()/2,frame.getHeight()/6);
            helpFrame.setVisible(true);
            helpFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    helpFrame.setVisible(false);
                    helpFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        gettingStartedItem.addActionListener(e -> {
            JFrame gettingStartedFrame = new JFrame("Getting Started");
            switch (loggedUser.getUserPermissionsLevel()){
                case 0:
                    gettingStartedFrame.setBackground(colorForPatient);
                    break;
                case 1:
                    gettingStartedFrame.setBackground(colorForOrthodontist);
                    break;
                case 2:
                    gettingStartedFrame.setBackground(colorForDeveloper);
                    break;
            }
            gettingStartedFrame.setIconImage(icon);
            gettingStartedFrame.setVisible(true);
            gettingStartedFrame.setResizable(true);
            gettingStartedFrame.setVisible(true);
            File file = new File("./resources/icon.JPG");
            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
            Image image = imageIcon.getImage();
            Image imageScaled = image.getScaledInstance((int)(frame.getWidth()/2.1),(int)(frame.getHeight()/2.3), Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(imageScaled);
            JPanel panel = new JPanel();
            JLabel message = new JLabel("For more instructions please read README.md file", SwingConstants.CENTER);
            JLabel message2 = new JLabel("!!!This file is located in SystemObslugiPacjenta-GabinetOrtodontyczny directory", SwingConstants.CENTER);
            message.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            message2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            panel.add(message);
            panel.add(message2);
            panel.add(new JLabel(imageIcon,SwingConstants.CENTER));
            gettingStartedFrame.add(panel);
            gettingStartedFrame.setSize((int)(frame.getWidth()/1.5),(int)(frame.getHeight()/1.7));
            gettingStartedFrame.setResizable(false);
            gettingStartedFrame.setLocationRelativeTo(null);
            gettingStartedFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    gettingStartedFrame.setVisible(false);
                    gettingStartedFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        checkForUpdatesItem.addActionListener(e -> {
            Frame updateFrame = new Frame("Check for updates");
            switch (loggedUser.getUserPermissionsLevel()){
                case 0:
                    updateFrame.setBackground(colorForPatient);
                    break;
                case 1:
                    updateFrame.setBackground(colorForOrthodontist);
                    break;
                case 2:
                    updateFrame.setBackground(colorForDeveloper);
                    break;
            }

            updateFrame.setIconImage(icon);
            updateFrame.setVisible(true);
            Label message = new Label("You have the latest version installed, current version: 1.00000023", Label.CENTER);
            updateFrame.add(message);
            updateFrame.setLayout(new BoxLayout(updateFrame, BoxLayout.Y_AXIS));
            updateFrame.setResizable(false);
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            updateFrame.setLocation(location);
            updateFrame.setSize(frame.getWidth()/3,frame.getHeight()/6);
            updateFrame.setVisible(true);
            updateFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    updateFrame.setVisible(false);
                    updateFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        aboutItem.addActionListener(e -> {
            Frame aboutFrame = new Frame("About Patient Service System - Orthodontic office");
            switch (loggedUser.getUserPermissionsLevel()){
                case 0:
                    aboutFrame.setBackground(colorForPatient);
                    break;
                case 1:
                    aboutFrame.setBackground(colorForOrthodontist);
                    break;
                case 2:
                    aboutFrame.setBackground(colorForDeveloper);
                    break;
            }
            aboutFrame.setIconImage(icon);
            Panel aboutPanel = new Panel();
            Label message = new Label("Build #OP-4214.2421323, built on January 10, 2022", Label.CENTER);
            Label message2 = new Label("Runtime version: 1.00312313123 amd64", Label.CENTER);
            Label message3 = new Label("Powered by open-source software", Label.CENTER);
            Label message4 = new Label("Copyright © 2022-2042 Orthodontic office systems s.r.o.", Label.CENTER);

            aboutPanel.add(message);
            aboutPanel.add(message2);
            aboutPanel.add(message3);
            aboutPanel.add(message4);
            aboutFrame.setResizable(false);
            Point location = frame.getLocation();
            location.x += frame.getWidth()/3;
            location.y += frame.getHeight()/5;
            aboutFrame.setLocation(location);
            aboutFrame.setSize(frame.getWidth()/3,frame.getHeight()/4);
            aboutPanel.setVisible(true);
            aboutFrame.setVisible(true);
            aboutFrame.add(aboutPanel);
            aboutFrame.setLayout(new BoxLayout(aboutFrame, BoxLayout.Y_AXIS));
            aboutFrame.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    aboutFrame.setVisible(false);
                    aboutFrame.dispose();
                    if (user == null)
                        System.exit(0);
                }

            });
        });
        helpMenu.add(yourAccountItem);
        helpMenu.add(helpItem);
        helpMenu.add(gettingStartedItem);
        helpMenu.add(checkForUpdatesItem);
        helpMenu.add(aboutItem);
    }

    /**
     * The method which adds and edits Panels in main frame
     */
    private void addPanels(){
        frame.add(centralPanel);
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for adding exit button for developer to GUI. Pressing the button
     * causes return to main menu
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
     * The method which is responsible for adding exit button for orthodontist to GUI. Pressing the button
     * causes return to main menu
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
     * The method which is responsible for adding exit button for patient to GUI. Pressing the button
     * causes return to main menu
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

    public void myPatients(){
        frame.removeAll();
        Label infoLabel = new Label("Please select your patient to view his details: ");
        Choice choice = new Choice();
        Panel panelMyPatients = new Panel();
        Button downloadDataButton = new Button("Download data");
        Connection connection = DataBaseHandling.StartConnectionWithDB();
        List<User> userList = DataBaseHandling.SearchForPatientsOfOrthodontist(connection, loggedUser);
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userList != null) {
            for (User user : userList) {
                choice.add(user.getUserLogin());
            }
        }
        if (userList != null && userList.size() == 0) {
            infoLabel.setText("We did not find any patients, please contact the administrator to clarify the problem");
        }

        Label usernameLabel = new Label("Patient username: null");
        Label nameLabel = new Label("Patient name: null");
        Label surnameLabel = new Label("Patient surname: null");
        Label telephoneLabel = new Label("Patient telephone: null");
        Label emailLabel = new Label("Patient e-mail address: null");
        Label addressLabel = new Label("Patient address: null");
        Label downloadInfoLabel = new Label();
        downloadInfoLabel.setBackground(null);

        panelMyPatients.add(infoLabel);
        panelMyPatients.add(choice);
        panelMyPatients.add(usernameLabel);
        panelMyPatients.add(nameLabel);
        panelMyPatients.add(surnameLabel);
        panelMyPatients.add(telephoneLabel);
        panelMyPatients.add(emailLabel);
        panelMyPatients.add(addressLabel);
        panelMyPatients.add(downloadInfoLabel);
        panelMyPatients.add(downloadDataButton);

        downloadDataButton.addActionListener(e -> {
            if (userList != null) {
                for(User user: userList){
                    if(user.getUserLogin().equals(choice.getSelectedItem())){
                        usernameLabel.setText("Patient username: " + user.getUserLogin());
                        nameLabel.setText("Patient name: " + user.getUserName());
                        surnameLabel.setText("Patient surname: " + user.getUserSurname());
                        telephoneLabel.setText("Patient telephone: " + user.getUserTelephoneNumber());
                        emailLabel.setText("Patient e-mail address: " + user.getUserEmail());
                        addressLabel.setText("Patient address: " + user.getUserEmail());
                    }
                }
                downloadInfoLabel.setText("Patient data was successfully downloaded");
                downloadInfoLabel.setBackground(Color.green);
            }
            else{
                downloadInfoLabel.setText("There was a problem with downloading patient's data, please try again");
                downloadInfoLabel.setBackground(Color.red);
            }
        });

        panelMyPatients.setLayout(new BoxLayout(panelMyPatients, BoxLayout.Y_AXIS));
        frame.add(panelMyPatients);
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        addExitButtonOrthodontist();
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for finding the user by e-mail address. It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to find the data.
     */
    public void findUser(){
        enteredUsername = "null";
        Connection connection = DataBaseHandling.StartConnectionWithDB();
        List<User> list = DataBaseHandling.SearchForAllUsers(connection, loggedUser);

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
                if(userFound && userToEdit.getUserPermissionsLevel()==0){
                    List<Visit> visitsToView = DataBaseHandling.SearchForVisitsOfPatient(connection, userToEdit);
                    if(visitsToView!=null) {
                        StringBuilder messageToView = new StringBuilder("You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with DB id: " + userToEdit.getUserId()
                                + ", this user has: " + visitsToView.size() + " visits, which took place on: ");
                        for (int i = 0; i < visitsToView.size(); i++) {
                            messageToView.append(visitsToView.get(i).getVisitDate());
                            if(i==visitsToView.size()-1)
                                messageToView.append(".");
                            else
                                messageToView.append(", ");
                        }
                        userStatement.setText(messageToView.toString());
                    } else {
                        String messageToView = "You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with DB id: " + userToEdit.getUserId() + ", this user has 0 visits";
                        userStatement.setText(messageToView);
                    }
                } else if(userFound && userToEdit.getUserPermissionsLevel()!=0){
                    String messageToView = "You have found user: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with DB id: " + userToEdit.getUserId();
                    userStatement.setText(messageToView);
                } else {
                    userStatement.setText("There is no such user in the database (Attempt: " + numberOfAttempts + ")");
                }
            } catch(Exception exception){
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
     * The method which is responsible for adding new user to the database. It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to change the data.
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
        Label address = new Label("Address: ");
        TextField addressTextField = new TextField();
        Label mobileNumber = new Label("Mobile number: ");
        TextField mobileNumberTextField = new TextField();
        Label mail = new Label("E-mail: ");
        TextField mailTextField = new TextField();
        Label level = new Label("Permission level (0 - Patient, 1 - Orthodontist, 2 - Developer): ");
        TextField levelTextField = new TextField();
        Label optionalOrthodontist = new Label("OPTIONAL [select only when adding a patient] Patient's Orthodontist: ");
        Choice orthodontistToSelect = new Choice();

        Connection connectionList = DataBaseHandling.StartConnectionWithDB();
        List<User> listOfOrthodontist = DataBaseHandling.SearchForAllOrthodontists(connectionList, loggedUser);
        if (listOfOrthodontist != null)
            for (User userToAdd : listOfOrthodontist) {
                orthodontistToSelect.add(userToAdd.getUserLogin());
            }
        try {
            assert connectionList != null;
            connectionList.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        editUserPanel.add(username);
        editUserPanel.add(usernameTextField);
        editUserPanel.add(password);
        editUserPanel.add(passwordTextField);
        editUserPanel.add(name);
        editUserPanel.add(nameTextField);
        editUserPanel.add(surname);
        editUserPanel.add(surnameTextField);
        editUserPanel.add(address);
        editUserPanel.add(addressTextField);
        editUserPanel.add(mobileNumber);
        editUserPanel.add(mobileNumberTextField);
        editUserPanel.add(mail);
        editUserPanel.add(mailTextField);
        editUserPanel.add(level);
        editUserPanel.add(levelTextField);
        editUserPanel.add(optionalOrthodontist);
        editUserPanel.add(orthodontistToSelect);

        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));
        Button editUserButton = new Button("Add new user to database");
        Label prompt = new Label("Please before pressing the button, check the correctness of the data");
        prompt.setBackground(null);
        editUserPanel.add(prompt);
        editUserPanel.add(editUserButton);

        editUserButton.addActionListener(e -> {
            prompt.setBackground(null);
            try {
                Connection connection = DataBaseHandling.StartConnectionWithDB();
                User orthodontistSelected = new User();
                if(listOfOrthodontist != null){
                    for(User userToFind: listOfOrthodontist){
                        if(userToFind.getUserLogin().equals(orthodontistToSelect.getSelectedItem())){
                            orthodontistSelected = userToFind;
                        }
                    }
                }

                if(Integer.parseInt(levelTextField.getText())>3 || Integer.parseInt(levelTextField.getText())<0)
                    throw new Exception();

                if (!levelTextField.getText().isEmpty() && !mailTextField.getText().isEmpty() && !mobileNumberTextField.getText().isEmpty() && !surnameTextField.getText().isEmpty() &&
                        !nameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !usernameTextField.getText().isEmpty() && !addressTextField.getText().isEmpty()) {
                    List<User> list = DataBaseHandling.SearchForAllUsers(connection, loggedUser);
                    if(list!=null){
                        for(User userToCheck: list) {
                            if(usernameTextField.getText().equals(userToCheck.getUserLogin()))
                                throw new Exception("You can't create a user with the same username");
                        }
                    } else
                        throw new Exception("There are no users, a problem has arisen while adding a new user to database");

                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserLogin(usernameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserPermissionsLevel(Integer.parseInt(levelTextField.getText()));
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());

                    if (levelTextField.getText().equals("0")) {
                        DataBaseHandling.AddNewPatientToDB(connection, loggedUser, userToAdd, orthodontistSelected);
                    } else if (levelTextField.getText().equals("1")) {
                        DataBaseHandling.AddNewOrthodontistToDB(connection, loggedUser, userToAdd);
                    } else if (levelTextField.getText().equals("2")) {
                        DataBaseHandling.AddNewAdministratorToDB(connection, loggedUser, userToAdd);
                    }
                    prompt.setText("The user: " + userToAdd.getUserLogin() + ", has been successfully added to the database");
                    prompt.setBackground(Color.green);
                } else {
                    prompt.setText("There was an error adding the user, please check the entered data and try again...");
                    prompt.setBackground(Color.red);
                }

            } catch (Exception exception){
                prompt.setText("You can't create a user with this credentials, please fill them again");
                prompt.setBackground(Color.red);
                System.out.println("A problem has arisen while adding a new user to database " + exception.getMessage());
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for deleting users from the database. It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to change the data.
     */
    public void deleteUser(){
        enteredUsername = "null";
        Connection connectionList = DataBaseHandling.StartConnectionWithDB();
        List<User> list = DataBaseHandling.SearchForAllUsers(connectionList, loggedUser);
        try {
            if (connectionList != null) {
                connectionList.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());

        Label usernameLabel = new Label("List of users available in the database: ");
        usernameLabel.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));
        Label userStatement = new Label("Please select the username from the list, to see user's credentials", Label.CENTER);
        userStatement.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));

        Choice userToSelect = new Choice();
        userToSelect.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));
        if (list != null)
            for (User userToAdd : list) {
                if (!userToAdd.getUserLogin().equals(loggedUser.getUserLogin()))
                    userToSelect.add(userToAdd.getUserLogin());
            }

        Button downloadInfoButton = new Button("Confirm your selection");
        downloadInfoButton.setSize(new Dimension(frame.getWidth(), frame.getHeight()/7));
        findUserPanel.add(usernameLabel);
        findUserPanel.add(userToSelect);
        Label gapLabel = new Label();
        gapLabel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/8));
        findUserPanel.add(gapLabel);
        Choice choice = new Choice();
        Label infoChoice = new Label("OPTIONAL[select only while deleting an orthodontist] Please choose from the list an orthodontist who will take over patients: ");
        findUserPanel.add(infoChoice);
        findUserPanel.add(choice);
        Connection connectionOrthodontist = DataBaseHandling.StartConnectionWithDB();
        List<User> listOfOrthodontist = DataBaseHandling.SearchForAllUsers(connectionOrthodontist, loggedUser);
        try {
            if (connectionOrthodontist != null) {
                connectionOrthodontist.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(listOfOrthodontist != null){
            for(User orthodontist: listOfOrthodontist){
                choice.add(orthodontist.getUserLogin());
            }
        }
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findUserPanel.add(downloadInfoButton);

        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
        findPanelMessage.add(userStatement);

        Panel deletePanel = new Panel();
        Label deleteInfo = new Label("Are you sure you want to permanently delete this user? If yes press the button below", Label.CENTER);
        deleteInfo.setBackground(null);
        deleteInfo.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()/12));
        Button deleteButton = new Button("Delete user");
        deleteButton.setSize(new Dimension(frame.getWidth(), frame.getHeight()/7));

        downloadInfoButton.addActionListener(e -> {
            boolean userFound = false;
            if (list != null)
                for (User userToFind : list) {
                    if (userToFind.getUserLogin().equals(userToSelect.getSelectedItem())) {
                        userToEdit = userToFind;
                        userFound = true;
                    }
                }

            if(userFound){
                userStatement.setText("You have selected: " + userToEdit.getUserName() + " " + userToEdit.getUserSurname() + ", with username: " + userToEdit.getUserName());
            } else {
                userStatement.setText("ERROR, Please select a user from the list first!");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                Connection connection = DataBaseHandling.StartConnectionWithDB();
                deleteInfo.setBackground(null);
                if (userToEdit.getUserPermissionsLevel() == 0) {
                    DataBaseHandling.RemovePatientFromDB(connection, loggedUser, userToEdit);
                    deleteInfo.setText("Deletion of patient: " + userToEdit.getUserLogin() + ", was successful");
                    deleteInfo.setBackground(Color.green);
                } else if (userToEdit.getUserPermissionsLevel() == 1) {
                    User adminOrthodontist = new User();
                    if(list != null){
                        for(User user: list) {
                            if(user.getUserLogin().equals(choice.getSelectedItem()))
                                adminOrthodontist = user;
                        }
                    }
                    DataBaseHandling.RemoveOrthodontistFromDB(connection, loggedUser, userToEdit, adminOrthodontist);
                    deleteInfo.setText("Deletion of orthodontist: " + userToEdit.getUserLogin() + ", was successful");
                    deleteInfo.setBackground(Color.green);
                } else if (userToEdit.getUserPermissionsLevel() == 2) {
                    try {
                        DataBaseHandling.RemoveAdministratorFromDB(connection, loggedUser, userToEdit);
                        deleteInfo.setText("Deletion of developer: " + userToEdit.getUserLogin() + ", was successful");
                        deleteInfo.setBackground(Color.green);
                    } catch (Exception exception) {
                        System.out.println("Exception caught! a problem has arisen while deleting user from the database");
                        deleteInfo.setBackground(Color.red);
                    }
                }
            } catch(Exception exception){
                deleteInfo.setBackground(Color.red);
                deleteInfo.setText("Deletion of user: " + userToEdit.getUserLogin() + ", was not successful!");
                System.out.println("Exception caught! " + exception.getMessage());
            } finally{
                Connection connection = DataBaseHandling.StartConnectionWithDB();
                List <User> listUpdate = DataBaseHandling.SearchForAllUsers(connection, loggedUser);
                if (listUpdate != null) {
                    for (User userToAdd : listUpdate) {
                        if (!userToAdd.getUserLogin().equals(loggedUser.getUserLogin()))
                            userToSelect.add(userToAdd.getUserLogin());
                    }
                    if(userToSelect.getItemCount()>=1)
                        userToSelect.select(0);
                }
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
     * The method which is responsible for editing the user's credentials. It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to change the data.
     */
    public void editUsers() {
        frame.removeAll();
        userToEdit = null;
        enteredUsername = "null";

        Connection connectionFindList = DataBaseHandling.StartConnectionWithDB();
        assert user != null;
        List<User> list = DataBaseHandling.SearchForAllUsers(connectionFindList, loggedUser);
        try {
            if (connectionFindList != null) {
                connectionFindList.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Panel welcomePanel = new Panel();
        Panel findPanelMessage = new Panel();
        Panel findUserPanel = new Panel(new GridBagLayout());
        Label usernameLabel = new Label("List of users available in the database: ");

        Choice userToSelect = new Choice();
        if (list != null)
            for (User userToAdd : list) {
                if (!userToAdd.getUserLogin().equals(loggedUser.getUserLogin()))
                    userToSelect.add(userToAdd.getUserLogin());
            }
        Button loginButton = new Button("Download data");
        Panel editUserPanel = new Panel();
        Label info = new Label("Please first select the user from the database, then click \"download data\" button to see the credentials of selected user");
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

        ActionListener action = e -> {
            enteredUsername = userToSelect.getSelectedItem();
            try {
                assert list != null;
                for(User userToFind: list){
                    if(userToFind.getUserLogin().equals(enteredUsername)){
                        userToEdit = userToFind;
                        usernameTextField.setText(userToEdit.getUserLogin());
                        passwordTextField.setText(userToEdit.getUserPassword());
                        nameTextField.setText(userToEdit.getUserName());
                        surnameTextField.setText(userToEdit.getUserSurname());
                        mobileNumberTextField.setText(userToEdit.getUserTelephoneNumber());
                        mailTextField.setText(userToEdit.getUserEmail());
                        addressTextField.setText(userToEdit.getUserAddress());
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Exception caught!" + exception.getMessage());
            }
        };

        loginButton.addActionListener(action);
        findUserPanel.add(usernameLabel);
        findUserPanel.add(userToSelect);
        findUserPanel.setLayout(new BoxLayout(findUserPanel, BoxLayout.Y_AXIS));
        findPanelMessage.setLayout(new BoxLayout(findPanelMessage, BoxLayout.Y_AXIS));
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
        editUserPanel.setLayout(new BoxLayout(editUserPanel, BoxLayout.Y_AXIS));
        Button editUserButton = new Button("Edit user");
        editUserPanel.add(loginButton);
        Label prompt = new Label("", Label.CENTER);
        editUserPanel.add(editUserButton);
        editUserPanel.add(prompt);

        editUserButton.addActionListener(e -> {
            try {
                Connection connection = DataBaseHandling.StartConnectionWithDB();
                if (userToEdit != null) {
                    User userToAdd = new User();
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserLogin(usernameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(userToEdit.getUserId());
                    userToAdd.setUserAddress(addressTextField.getText());
                    DataBaseHandling.EditUserInfoInDB(connection, userToEdit, userToAdd);
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
     * The method which is responsible for patient's mailbox. It allows the user to send an e-mail to an external
     * address of his Orthodontist and Administrator. It uses MailHandling class methods.
     */
    public void myMailboxPatient(){
        if(loggedUser.getUserPermissionsLevel()==0) {
            Dimension maxDimension = new Dimension(frame.getWidth()/20,frame.getHeight()/20);
            Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/15);
            Label infoList = new Label("Please select a user from the list in order to send an e-mail: ", Label.CENTER);
            infoList.setMaximumSize(maxDimensionWidth);
            Choice selectionList = new Choice();
            selectionList.setMaximumSize(maxDimensionWidth);
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            User orthodontistOfPatient = DataBaseHandling.SearchForOrthodontistOfPatient(connection, loggedUser);
            if (orthodontistOfPatient != null) {
                selectionList.add(orthodontistOfPatient.getUserName() + " " + orthodontistOfPatient.getUserSurname() + ", " + orthodontistOfPatient.getUserEmail());
                selectionList.add("Administration (Please contact us only if you have any problems with the application)");
            } else{
                selectionList.add("Administration (Please contact us only if you have any problems with the application)");
            }

            frame.add(infoList);
            frame.add(selectionList);

            TextField textFieldTopic = new TextField();
            Label textFieldTopicLabel = new Label("e-mail topic:");
            textFieldTopic.setMaximumSize(maxDimensionWidth);
            textFieldTopicLabel.setMaximumSize(maxDimensionWidth);
            frame.add(textFieldTopicLabel);
            frame.add(textFieldTopic);

            TextField textField = new TextField();
            Dimension textFieldDimension = new Dimension(frame.getWidth(), frame.getHeight());
            Label textFieldLabel = new Label("e-mail subject:");
            textFieldLabel.setMaximumSize(maxDimensionWidth);
            textField.setPreferredSize(textFieldDimension);
            frame.add(textFieldLabel);
            frame.add(textField);

            Label sendInfo = new Label("Please check before submitting if a valid user is selected, and the subject and text fields are not empty", Label.CENTER);
            sendInfo.setBackground(null);
            Button sendButton = new Button("Send");
            sendInfo.setMaximumSize(maxDimensionWidth);
            sendButton.setMaximumSize(maxDimension);
            frame.add(sendInfo);
            frame.add(sendButton);

            sendButton.addActionListener(e -> {

                if(textField.getText().isEmpty() || textFieldTopic.getText().isEmpty())
                {
                    sendInfo.setBackground(Color.red);
                    sendInfo.setText("The subject and text fields are empty, please fill them in and try again");
                } else {
                    try {
                        if (orthodontistOfPatient != null && selectionList.getSelectedItem().equals(orthodontistOfPatient.getUserName() + " " + orthodontistOfPatient.getUserSurname() + ", " + orthodontistOfPatient.getUserEmail())) {
                            MailHandling.sendMail(orthodontistOfPatient.getUserEmail(), textFieldTopic.getText(), textField.getText());
                        } else
                            MailHandling.sendMail("gabinetortodontycznypw@gmail.com", textFieldTopic.getText(), textField.getText());
                        sendInfo.setText("The message has been sent successfully!");
                        sendInfo.setBackground(Color.green);
                    } catch (Exception exception){
                        sendInfo.setText("There were problems with sending your e-mail (recipient's address is not vail)");
                        sendInfo.setBackground(Color.red);
                        System.out.println("Exception caught while sending an e-mail");
                    }
                }
            });

        }
        else
        {
            System.err.println("There were problems logging in the correct user");
        }
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for orthodontist's mailbox. It allows the user to send an e-mail to an external
     * address. It uses MailHandling class methods.
     */
    public void myMailboxOrthodontist(){
        if(loggedUser.getUserPermissionsLevel()==1) {
            Dimension maxDimension = new Dimension(frame.getWidth()/20,frame.getHeight()/20);
            Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/15);
            Label infoList = new Label("Please select a user from the list in order to send an e-mail: ", Label.CENTER);
            infoList.setMaximumSize(maxDimensionWidth);
            Choice selectionList = new Choice();
            selectionList.setMaximumSize(maxDimensionWidth);
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<User> listOfPatients = DataBaseHandling.SearchForPatientsOfOrthodontist(connection, loggedUser);
            if (listOfPatients != null) {
                for (User patient : listOfPatients)
                    selectionList.add(patient.getUserName() + " " + patient.getUserSurname() + ", " + patient.getUserEmail());
                selectionList.add("Administration (Please contact us only if you have any problems with the application)");
            } else
                selectionList.add("Administration (Please contact us only if you have any problems with the application)");

            frame.add(infoList);
            frame.add(selectionList);

            TextField textFieldTopic = new TextField();
            Label textFieldTopicLabel = new Label("e-mail topic:");
            textFieldTopic.setMaximumSize(maxDimensionWidth);
            textFieldTopicLabel.setMaximumSize(maxDimensionWidth);
            frame.add(textFieldTopicLabel);
            frame.add(textFieldTopic);

            TextField textField = new TextField();
            Dimension textFieldDimension = new Dimension(frame.getWidth(), frame.getHeight());
            Label textFieldLabel = new Label("e-mail subject:");
            textFieldLabel.setMaximumSize(maxDimensionWidth);
            textField.setPreferredSize(textFieldDimension);
            frame.add(textFieldLabel);
            frame.add(textField);

            Label sendInfo = new Label("Please check before submitting if a valid user is selected, and the subject and text fields are not empty", Label.CENTER);
            sendInfo.setBackground(null);
            Button sendButton = new Button("Send");
            sendInfo.setMaximumSize(maxDimensionWidth);
            sendButton.setMaximumSize(maxDimension);
            frame.add(sendInfo);
            frame.add(sendButton);

            sendButton.addActionListener(e -> {

                if(textField.getText().isEmpty() || textFieldTopic.getText().isEmpty())
                {
                    sendInfo.setBackground(Color.red);
                    sendInfo.setText("The subject and text fields are empty, please fill them in and try again");
                } else {
                    try {
                        boolean userFound = false;
                        User recepient = new User();
                        if (listOfPatients != null) {
                            for(User userToFind: listOfPatients){
                                if(selectionList.getSelectedItem().equals(userToFind.getUserName() + " " + userToFind.getUserSurname() + ", " + userToFind.getUserEmail())){
                                    userFound = true;
                                    recepient.setUserEmail(userToFind.getUserEmail());
                                }
                            }
                        }

                        if (userFound) {
                            MailHandling.sendMail(recepient.getUserEmail(), textFieldTopic.getText(), textField.getText());
                        } else
                            MailHandling.sendMail("gabinetortodontycznypw@gmail.com", textFieldTopic.getText(), textField.getText());
                        sendInfo.setText("The message has been sent successfully!");
                        sendInfo.setBackground(Color.green);
                    } catch (Exception exception){
                        sendInfo.setText("There were problems with sending your e-mail (recipient's address is not vail)");
                        sendInfo.setBackground(Color.red);
                        System.out.println("Exception caught while sending an e-mail");
                    }
                }
            });

        }
        else
            System.err.println("There were problems logging in the correct user");

        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing developer's credentials It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to change the data.
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
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            try {
                loggedUser = DataBaseHandling.LogInUser(connection, loggedUser.getUserLogin(), loggedUser.getUserPassword());
                if (loggedUser != null) {
                    User userToAdd = new User(loggedUser);
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(loggedUser.getUserId());
                    DataBaseHandling.EditUserInfoInDB(connection, loggedUser, userToAdd);
                    loggedUser = userToAdd;
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            } catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonDeveloper();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing patient's credentials It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to change the data.
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
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            try {
                loggedUser = DataBaseHandling.LogInUser(connection, loggedUser.getUserLogin(), loggedUser.getUserPassword());
                if (loggedUser != null) {
                    User userToAdd = new User(loggedUser);
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(loggedUser.getUserId());
                    DataBaseHandling.EditUserInfoInDB(connection, loggedUser, userToAdd);
                    loggedUser = userToAdd;
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            } catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(editUserPanel, BorderLayout.CENTER);
        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for viewing and editing orthodontist's credentials. It works by connecting to a
     * database and calling appropriate methods of DataBaseHandlingClass class to change the data.
     */
    public void myProfileOrthodontist(){
        frame.removeAll();
        Connection connection = DataBaseHandling.StartConnectionWithDB();
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
                loggedUser = DataBaseHandling.LogInUser(connection, loggedUser.getUserLogin(), loggedUser.getUserPassword());
                if (loggedUser != null) {
                    User userToAdd = new User(loggedUser);
                    userToAdd.setUserName(nameTextField.getText());
                    userToAdd.setUserPassword(passwordTextField.getText());
                    userToAdd.setUserSurname(surnameTextField.getText());
                    userToAdd.setUserAddress(addressTextField.getText());
                    userToAdd.setUserEmail(mailTextField.getText());
                    userToAdd.setUserTelephoneNumber(mobileNumberTextField.getText());
                    userToAdd.setUserId(loggedUser.getUserId());
                    DataBaseHandling.EditUserInfoInDB(connection, loggedUser, userToAdd);
                    loggedUser = userToAdd;
                    prompt.setText("The edition was a success");
                } else
                    prompt.setText("There is no such user in the database (Editing is forbidden)");
            } catch(Exception exception){
                System.out.println("Problem has arisen during editing profile");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
        if(loggedUser.getUserPermissionsLevel()==0) {
            Dimension maxDimension = new Dimension(frame.getWidth()/5,frame.getHeight()/13);
            Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/15);
            Label dateInfo = new Label("Please select the date of your visit:");
            Choice visitSelection = new Choice();
            visitSelection.setMaximumSize(maxDimensionWidth);
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<Visit> listOfVisits = DataBaseHandling.SearchForVisitsOfPatient(connection, loggedUser);
            if(listOfVisits != null) {
                for (Visit visit : listOfVisits)
                    visitSelection.add(visit.getVisitDate().toString());
            }
            frame.add(dateInfo);
            frame.add(visitSelection);
            Panel visitInfoPanel = new Panel();
            visitInfoPanel.setPreferredSize(new Dimension(frame.getWidth()/2,frame.getHeight()/2));
            frame.add(visitInfoPanel);
            Button buttonShowDetails = new Button("Show details of the visit");
            buttonShowDetails.setMaximumSize(maxDimension);
            Button generatePDF = new Button("Generate PDF file of the visit");
            generatePDF.setMaximumSize(maxDimension);
            Button makeVisit = new Button("Make a new visit with your Orthodontist");
            makeVisit.setMaximumSize(maxDimension);
            Button refreshVisits = new Button("Refresh your visits");
            refreshVisits.setMaximumSize(maxDimension);
            frame.add(makeVisit);
            frame.add(buttonShowDetails);
            frame.add(generatePDF);
            frame.add(refreshVisits);

            generatePDF.addActionListener(e -> {
                visitInfoPanel.removeAll();
                boolean generateFileCheck;
                Label infoConfirmationLabel = new Label();
                infoConfirmationLabel.setBackground(null);
                try {
                    if (visitSelection.getSelectedItem() == null) {
                        infoConfirmationLabel.setText("Your pdf file have NOT been successfully generated, something went wrong please try again");
                        infoConfirmationLabel.setBackground(Color.red);
                    } else {
                        Visit selectedVisit = new Visit();
                        if (listOfVisits != null) {
                            for (Visit visit : listOfVisits) {
                                if (visitSelection.getSelectedItem().equals(visit.getVisitDate().toString())) {
                                    selectedVisit.setVisitDate(visit.getVisitDate());
                                    selectedVisit.setVisitComment(visit.getVisitComment());
                                    selectedVisit.setVisitId(visit.getVisitId());
                                    selectedVisit.setPatientId(visit.getPatientId());
                                    selectedVisit.setOrthodontistId(visit.getOrthodontistId());
                                    selectedVisit.setUserPatientId(visit.getUserPatientId());
                                    selectedVisit.setUserOrthodontistId(visit.getUserOrthodontistId());
                                }
                            }

                        }


                        // TODO funkcja generująca pdf'a z wybranej wizyty


                        generateFileCheck = selectedVisit.getVisitId() != 3; // TODO zmienic sprawdzenie poprawnosci wygnerowania pdfa

                        if (generateFileCheck) {
                            infoConfirmationLabel.setText("Your pdf file have been successfully generated");
                            infoConfirmationLabel.setBackground(Color.green);
                        } else {
                            infoConfirmationLabel.setText("Your pdf file have NOT been successfully generated, something went wrong please try again");
                            infoConfirmationLabel.setBackground(Color.red);
                        }
                    }
                } catch (Exception exception) {
                    infoConfirmationLabel.setText("Your pdf file have NOT been successfully generated, something went wrong please try again");
                    infoConfirmationLabel.setBackground(Color.red);
                    exception.printStackTrace();
                } finally {
                    visitInfoPanel.add(infoConfirmationLabel);
                    frame.setVisible(true);
                }
            });

            buttonShowDetails.addActionListener(e -> {
                visitInfoPanel.removeAll();
                Label infoLabel1 = new Label();
                infoLabel1.setBackground(null);
                if (listOfVisits != null) {
                    if(visitSelection.getItemCount() == 0) {
                        infoLabel1.setBackground(Color.red);
                        infoLabel1.setText("Please select a visit before viewing it's details. If you cannot see your visits, please contact the administrator!");
                        visitInfoPanel.add(infoLabel1);
                    } else {
                        Visit selectedVisit = new Visit();
                        for (Visit visit : listOfVisits) {
                            if (visitSelection.getSelectedItem().equals(visit.getVisitDate().toString())) {
                                selectedVisit.setVisitDate(visit.getVisitDate());
                                selectedVisit.setVisitComment(visit.getVisitComment());
                                selectedVisit.setVisitId(visit.getVisitId());
                                selectedVisit.setPatientId(visit.getPatientId());
                                selectedVisit.setOrthodontistId(visit.getOrthodontistId());
                                selectedVisit.setUserPatientId(visit.getUserPatientId());
                                selectedVisit.setUserOrthodontistId(visit.getUserOrthodontistId());
                            }
                        }

                        infoLabel1.setText("Visit information from day: " + selectedVisit.getVisitDate() + ", with ID: " + selectedVisit.getVisitId());
                        Label infoLabel2 = new Label("Orthodontist's comment: " + selectedVisit.getVisitComment());
                        Label infoLabel3 = new Label();
                        Button viewPictureButton = new Button("View Pantomography picture from date " + selectedVisit.getVisitDate());
                        viewPictureButton.addActionListener(e1 -> {
                            JFrame jFrame = new JFrame("View of Pantomography picture");
                            jFrame.setResizable(true);
                            jFrame.setVisible(true);
                            File file = new File("./resources/icon.JPG");
                            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                            jFrame.setSize((int)(imageIcon.getIconWidth()/1.5),(int)(imageIcon.getIconHeight()/1.5));
                            Image image = imageIcon.getImage();
                            Image imageScaled = image.getScaledInstance((int)(imageIcon.getIconWidth()/1.5),(int)(imageIcon.getIconHeight()/1.5), Image.SCALE_SMOOTH);
                            imageIcon = new ImageIcon(imageScaled);
                            Panel panel = new Panel();
                            panel.add(new JLabel(imageIcon));
                            jFrame.add(panel);
                            jFrame.setLocationRelativeTo(null);
                        });
                        visitInfoPanel.add(infoLabel1);
                        visitInfoPanel.add(infoLabel2);
                        visitInfoPanel.add(infoLabel3);
                        visitInfoPanel.add(viewPictureButton);
                    }
                } else {
                    infoLabel1.setBackground(Color.red);
                    infoLabel1.setText("If you cannot see your visits, please contact the administrator!");
                    visitInfoPanel.add(infoLabel1);
                }

                visitInfoPanel.setLayout(new BoxLayout(visitInfoPanel, BoxLayout.Y_AXIS));
                frame.setVisible(true);

            });

            makeVisit.addActionListener(e -> {

                visitInfoPanel.removeAll();
                Label labelConfirmation = new Label();
                labelConfirmation.setBackground(null);

                try {
                    Label infoLabel = new Label("Please enter information for your orthodontist regarding the new visit." +
                            " Your orthodontist will be able to plan the visit by himself (if the orthodontist confirms the visit, you will see it in the drop-down list):");
                    TextField textField = new TextField();
                    Button sendButton = new Button("send your inquiry");
                    visitInfoPanel.add(infoLabel);
                    visitInfoPanel.add(textField);
                    visitInfoPanel.add(sendButton);
                    visitInfoPanel.add(labelConfirmation);
                    visitInfoPanel.setLayout(new BoxLayout(visitInfoPanel, BoxLayout.Y_AXIS));
                    frame.setVisible(true);
                    sendButton.addActionListener(e12 -> {
                        Visit visitToSend = new Visit();
                        visitToSend.setVisitComment(textField.getText());
                        visitToSend.setUserPatientId(loggedUser.getUserId()); // TODO Czy to na pewno poprawnie
                        User orthodontistOfPatient = DataBaseHandling.SearchForOrthodontistOfPatient(connection, loggedUser);
                        if (orthodontistOfPatient != null) {
                            visitToSend.setUserOrthodontistId(orthodontistOfPatient.getUserId());
                        }
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        visitToSend.setVisitDate(timestamp);

                        boolean visitCheck = DataBaseHandling.AddNewUpcomingVisitToDB(connection, visitToSend);

                        List<Visit> listOfVisitsUpdate = DataBaseHandling.SearchForVisitsOfPatient(connection, loggedUser);
                        if (listOfVisitsUpdate != null) {
                            for (Visit visit : listOfVisitsUpdate)
                                visitSelection.add(visit.getVisitDate().toString());
                        }

                        if (visitCheck) {
                            labelConfirmation.setText("Your inquiry has been successfully sent");
                            labelConfirmation.setBackground(Color.green);
                        } else {
                            labelConfirmation.setText("Your inquiry has been denied!!!");
                            labelConfirmation.setBackground(Color.red);
                        }
                    });
                } catch(Exception exception){
                    exception.printStackTrace();
                    labelConfirmation.setText("Your inquiry has been denied!!!");
                    labelConfirmation.setBackground(Color.red);
                }
            });

            refreshVisits.addActionListener(e -> {
                List<Visit> listOfVisitsUpdate = DataBaseHandling.SearchForVisitsOfPatient(connection, loggedUser);
                if (listOfVisits != null && listOfVisitsUpdate != null && listOfVisitsUpdate.size() != listOfVisits.size()) {
                    listOfVisits.clear();
                    listOfVisits.addAll(listOfVisitsUpdate);
                }
                frame.removeAll();
                myVisitsPatient();
            });

        } else {
            System.err.println("There were problems logging in the correct user");
        }

        addExitButtonPatient();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    public void addExitButtonForMyVisitsOrthodontist(){
        Panel editUserPanel = new Panel();
        Button exitButton = new Button("Return to my visits");
        exitButton.setBackground(Color.yellow);
        editUserPanel.add(exitButton);
        frame.add(editUserPanel);
        exitButton.addActionListener(e1 -> {
            frame.removeAll();
            myVisitsOrthodontist();
            frame.setVisible(true);
        });
        frame.setVisible(true);
    }

    /**
     * The method is responsible for viewing and editing orthodontist's visits
     */
    public void myVisitsOrthodontist(){
        Dimension maxDimensionWidth = new Dimension(frame.getWidth(), frame.getHeight()/5);
        Button viewVisits = new Button("View your visits");
        Button createVisit = new Button("Create a new visit");
        Button cancelVisit = new Button("Cancel a visit");
        Button editVisit = new Button("Edit your visits");
        Button handleVisits = new Button("Handle upcoming visits");
        createVisit.setPreferredSize(maxDimensionWidth);
        cancelVisit.setPreferredSize(maxDimensionWidth);
        editVisit.setPreferredSize(maxDimensionWidth);
        viewVisits.setPreferredSize(maxDimensionWidth);
        handleVisits.setPreferredSize(maxDimensionWidth);
        Panel panelVisits = new Panel();
        Panel panelVisits2 = new Panel();
        frame.add(panelVisits);
        frame.add(viewVisits);
        frame.add(createVisit);
        frame.add(handleVisits);
        frame.add(cancelVisit);
        frame.add(editVisit);
        frame.add(panelVisits2);

        viewVisits.addActionListener(e -> {
            frame.removeAll();
            Label dateInfo = new Label("Please select date of visit:");
            Choice choice = new Choice();
            Panel panelInfo = new Panel();
            Label info1 = new Label();
            Label info2 = new Label();
            Label info3 = new Label();
            panelInfo.add(info1);
            panelInfo.add(info2);
            panelInfo.add(info3);
            Button viewDetailsButton = new Button("View details of a selected visit");
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<Visit> visitList = DataBaseHandling.SearchForVisitsOfPOrthodontist(connection, loggedUser);
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (visitList != null) {
                for(Visit visit: visitList){
                    choice.add(visit.getVisitDate().toString());
                }
            }

            Button viewPictureButton = new Button("View Pantomography picture");
            viewPictureButton.addActionListener(e11 -> {
                JFrame jFrame = new JFrame("View of Pantomography picture");
                jFrame.setResizable(true);
                jFrame.setVisible(true);
                File file = new File("./resources/icon.JPG"); // TODO WYSWIETLANIE ZDJECIA Z BAZY DANYCH
                ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                jFrame.setSize((int)(imageIcon.getIconWidth()/1.5),(int)(imageIcon.getIconHeight()/1.5));
                Image image = imageIcon.getImage();
                Image imageScaled = image.getScaledInstance((int)(imageIcon.getIconWidth()/1.5),(int)(imageIcon.getIconHeight()/1.5), Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(imageScaled);
                Panel panel = new Panel();
                panel.add(new JLabel(imageIcon));
                jFrame.add(panel);
                jFrame.setLocationRelativeTo(null);
            });
            viewDetailsButton.addActionListener(e1 -> {
                Visit selectedVisit = new Visit();
                if (visitList != null) {
                    for (Visit visit : visitList) {
                        if (choice.getSelectedItem().equals(visit.getVisitDate().toString())) {
                            selectedVisit.setVisitDate(visit.getVisitDate());
                            selectedVisit.setVisitComment(visit.getVisitComment());
                            selectedVisit.setVisitId(visit.getVisitId());
                            selectedVisit.setPatientId(visit.getPatientId());
                            selectedVisit.setOrthodontistId(visit.getOrthodontistId());
                            selectedVisit.setUserPatientId(visit.getUserPatientId());
                            selectedVisit.setUserOrthodontistId(visit.getUserOrthodontistId());
                        }
                    }
                }
                info1.setText("Details about chosen visit:");
                info2.setText("Date of visit: " + selectedVisit.getVisitDate() + ", and ID: " + selectedVisit.getVisitId());
                info3.setText("Visit comment: " + selectedVisit.getVisitComment());


            });
            frame.add(dateInfo);
            frame.add(choice);
            panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
            frame.add(panelInfo);
            frame.add(viewDetailsButton);
            frame.add(viewPictureButton);
            addExitButtonForMyVisitsOrthodontist();
            frame.setVisible(true);
        });

        createVisit.addActionListener(e -> {
            frame.removeAll();
            Label userLabel = new Label("Please select user from the list below: ");
            Choice choice = new Choice();
            Label infoLabel = new Label("Please fill all of below fields, before creating a visit:");
            Label visitComment = new Label("Visit comment: ");
            Label visitDate = new Label("Visit date (in format [year-month-day hour:minute:second]) remember to fill in correctly:");
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            Button createButton = new Button("Create a new Visit");
            Label infoLabel2 = new Label();
            infoLabel2.setBackground(null);
            Panel panel = new Panel();
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<User> userList = DataBaseHandling.SearchForPatientsOfOrthodontist(connection, loggedUser);
            if (userList != null) {
                for(User user: userList)
                    choice.add(user.getUserLogin());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            panel.add(userLabel);
            panel.add(choice);
            panel.add(infoLabel);
            panel.add(visitDate);
            panel.add(textField2);
            panel.add(visitComment);
            panel.add(textField1);
            panel.add(createButton);
            panel.add(infoLabel2);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            frame.add(panel);
            addExitButtonForMyVisitsOrthodontist();

            createButton.addActionListener(e12 -> {
                Connection connectionButton = DataBaseHandling.StartConnectionWithDB();
                Visit visitToAdd = new Visit();

                if (userList != null) {
                    for(User user: userList){
                        if(choice.getSelectedItem().equals(user.getUserLogin())){
                            visitToAdd.setUserPatientId(user.getUserId());
                        }
                    }
                }
                visitToAdd.setUserOrthodontistId(loggedUser.getUserId());
                visitToAdd.setVisitComment(textField1.getText());
                boolean tick = false;
                int Counter = textField2.getText().toCharArray().length;
                if(Counter == 19) {
                    try {
                        Timestamp timestampFromString = Timestamp.valueOf(textField2.getText());
                        visitToAdd.setVisitDate(timestampFromString);
                        tick = DataBaseHandling.AddNewVisitToDB(connectionButton, visitToAdd);
                    } catch (Exception exception){
                        infoLabel2.setBackground(Color.red);
                        infoLabel2.setText("Problems arose when adding a new visit to the base, please try again (check if you entered the date correctly)");
                    }
                } else {
                    infoLabel2.setBackground(Color.red);
                    infoLabel2.setText("Problems arose when adding a new visit to the base, please try again (check if you entered the date correctly)");
                }

                if(tick){
                    infoLabel2.setBackground(Color.green);
                    infoLabel2.setText("New visit has been added to the database successfully");
                }
                else{
                    infoLabel2.setBackground(Color.red);
                    infoLabel2.setText("Problems arose when adding a new visit to the base, please try again (check if you entered the date correctly)");
                }

                try {
                    if (connectionButton != null) {
                        connectionButton.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            });
        });

        handleVisits.addActionListener(e -> {
            frame.removeAll();
            Label userLabel = new Label("Please select a new upcoming visit from your patients: ");
            Choice choice = new Choice();
            Label infoLabel = new Label("Please fill all of below fields, before creating a visit:");
            Label visitComment = new Label("Visit comment: ");
            Label visitDate = new Label("Visit date (in format [year-month-day hour:minute:second]) remember to fill in correctly:");
            TextField textField1 = new TextField();
            TextField textField2 = new TextField();
            Button createButton = new Button("Create a new Visit");
            Label infoLabel2 = new Label();
            infoLabel2.setBackground(null);
            Panel panel = new Panel();
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<Visit> visitList = DataBaseHandling.SearchForVisitsOfPOrthodontist(connection, loggedUser); // TODO FUNKCJA ZWRACAJACA NADCHODZACE WIZYTY
            if (visitList != null) {
                for(Visit visit: visitList)
                    choice.add(visit.getVisitDate().toString() + ", of a patient: " + visit.getUserPatientId());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            panel.add(userLabel);
            panel.add(choice);
            panel.add(infoLabel);
            panel.add(visitDate);
            panel.add(textField2);
            panel.add(visitComment);
            panel.add(textField1);
            panel.add(createButton);
            panel.add(infoLabel2);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            frame.add(panel);
            addExitButtonForMyVisitsOrthodontist();
            createButton.addActionListener(e12 -> {
                Connection connectionButton = DataBaseHandling.StartConnectionWithDB();
                Visit visitToAdd = new Visit();
                if (visitList != null) {
                    for(Visit visit: visitList){
                        if(choice.getSelectedItem().equals(visit.getVisitDate().toString() + ", of a patient: " + visit.getUserPatientId())){
                            visitToAdd.setUserPatientId(user.getUserId());
                        }
                    }
                }
                visitToAdd.setUserOrthodontistId(loggedUser.getUserId());
                visitToAdd.setVisitComment(textField1.getText());
                boolean tick = false;
                int Counter = textField2.getText().toCharArray().length;
                if(Counter == 19) {
                    try {
                        Timestamp timestampFromString = Timestamp.valueOf(textField2.getText());
                        visitToAdd.setVisitDate(timestampFromString);
                        tick = DataBaseHandling.AddNewVisitToDB(connectionButton, visitToAdd);
                    } catch (Exception exception){
                        infoLabel2.setBackground(Color.red);
                        infoLabel2.setText("Problems arose when adding a new visit to the base, please try again (check if you entered the date correctly)");
                    }
                } else {
                    infoLabel2.setBackground(Color.red);
                    infoLabel2.setText("Problems arose when adding a new visit to the base, please try again (check if you entered the date correctly)");
                }

                if(tick){
                    infoLabel2.setBackground(Color.green);
                    infoLabel2.setText("New visit has been added to the database successfully");
                }
                else{
                    infoLabel2.setBackground(Color.red);
                    infoLabel2.setText("Problems arose when adding a new visit to the base, please try again (check if you entered the date correctly)");
                }

                try {
                    if (connectionButton != null) {
                        connectionButton.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            });
        });

        cancelVisit.addActionListener(e -> {
            frame.removeAll();
            Label userLabel = new Label("Please select a visit from the list below: ");
            Choice choice = new Choice();
            Button deleteButton = new Button("Delete visit");
            Label infoLabel = new Label();
            infoLabel.setBackground(null);
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<Visit> visitList = DataBaseHandling.SearchForVisitsOfPOrthodontist(connection, loggedUser);
            if (visitList != null) {
                for(Visit visit: visitList){
                    choice.add(visit.getVisitDate().toString());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            frame.add(userLabel);
            frame.add(choice);
            frame.add(infoLabel );
            frame.add(deleteButton);

            deleteButton.addActionListener(e13 -> {
                Connection connectionButton = DataBaseHandling.StartConnectionWithDB();
                Visit visitToDelete = new Visit();
                if (visitList != null) {
                    for(Visit visit: visitList){
                        if(choice.getSelectedItem().equals(visit.getVisitDate().toString())){
                            visitToDelete = visit;
                            visitList.remove(visit);
                            choice.remove(visit.getVisitDate().toString());
                        }
                    }
                }

                boolean tick = DataBaseHandling.RemoveVisitFromDB(connectionButton, visitToDelete);
                if(tick){
                    infoLabel.setBackground(Color.green);
                    infoLabel.setText("The visit was successfully deleted");
                }
                else{
                    infoLabel.setBackground(Color.red);
                    infoLabel.setText("There was a problem while deleting the visit, please try again");
                }
                try {
                    if (connectionButton != null) {
                        connectionButton.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            addExitButtonForMyVisitsOrthodontist();
        });

        editVisit.addActionListener(e -> {
            frame.removeAll();
            Label dateInfo = new Label("Select a visit date and click View details button, to edit the appointment details:");
            Label editInfo = new Label();
            editInfo.setBackground(null);
            Choice choice = new Choice();
            Panel panelInfo = new Panel();
            Label info = new Label("Details about chosen visit:");
            Label info1 = new Label("Visit date (in format [year-month-day hour:minute:second]) remember to fill in correctly: ");
            TextField textField1 = new TextField("null");
            Label info2 = new Label("Visit comment: ");
            TextField textField2 = new TextField("null");
            Label info3 = new Label("Visit patient id: ");
            TextField textField3 = new TextField("null");
            Label info4 = new Label("Visit orthodontist id: ");
            TextField textField4 = new TextField("null");

            panelInfo.add(info);
            panelInfo.add(info1);
            panelInfo.add(textField1);
            panelInfo.add(info2);
            panelInfo.add(textField2);
            panelInfo.add(info3);
            panelInfo.add(textField3);
            panelInfo.add(info4);
            panelInfo.add(textField4);

            Button downloadDetailsButton = new Button("Download details of a selected visit");
            Button editDetailsButton = new Button("Edit details of a selected visit");
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            List<Visit> visitList = DataBaseHandling.SearchForVisitsOfPOrthodontist(connection, loggedUser);
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (visitList != null) {
                for(Visit visit: visitList){
                    choice.add(visit.getVisitDate().toString());
                }
            }

            downloadDetailsButton.addActionListener(e1 -> {
                Visit selectedVisit = new Visit();
                if (visitList != null) {
                    for (Visit visit : visitList) {
                        if (choice.getSelectedItem().equals(visit.getVisitDate().toString())) {
                            textField1.setText(selectedVisit.getVisitDate().toString());
                            textField2.setText(selectedVisit.getVisitComment());
                            textField3.setText(String.valueOf(selectedVisit.getUserPatientId()));
                            textField4.setText(String.valueOf(selectedVisit.getUserOrthodontistId()));
                        }
                    }
                }
            });

            editDetailsButton.addActionListener(e1 -> {
                boolean tick = true;
                if(textField1.getText().equals("null") || textField2.getText().equals("null") || textField3.getText().equals("null") || textField4.getText().equals("null")){
                    tick = false;
                    editInfo.setBackground(Color.red);
                    editInfo.setText("Problems arose while changing details of the visit, please try again (check if you entered the date correctly)");
                }
                Visit selectedVisit = new Visit();
                if (visitList != null) {
                    for (Visit visit : visitList) {
                        if (choice.getSelectedItem().equals(visit.getVisitDate().toString())) {
                            selectedVisit.setVisitDate(visit.getVisitDate());
                            selectedVisit.setVisitComment(visit.getVisitComment());
                            selectedVisit.setVisitId(visit.getVisitId());
                            selectedVisit.setPatientId(visit.getPatientId());
                            selectedVisit.setOrthodontistId(visit.getOrthodontistId());
                            selectedVisit.setUserPatientId(visit.getUserPatientId());
                            selectedVisit.setUserOrthodontistId(visit.getUserOrthodontistId());
                        }
                    }
                }
                Connection connectionButton = DataBaseHandling.StartConnectionWithDB();

                // TODO FUNKCJA EDYTUJACA WIZYTE

                if(tick){
                    editInfo.setBackground(Color.green);
                    editInfo.setText("Visit with ID: " + selectedVisit.getVisitId() + ", has been successfully edited");
                }
                else{
                    editInfo.setBackground(Color.red);
                    editInfo.setText("Problems arose while changing details of the visit, please try again (check if you entered the date correctly)");
                }
                try {
                    if (connectionButton != null) {
                        connectionButton.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            frame.add(dateInfo);
            frame.add(choice);
            panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
            frame.add(panelInfo);
            frame.add(downloadDetailsButton);
            frame.add(editDetailsButton);
            frame.add(editInfo);
            addExitButtonForMyVisitsOrthodontist();
            frame.setVisible(true);
        });

        addExitButtonOrthodontist();
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setVisible(true);
    }

    /**
     * The method which is responsible for login screen. This method logs the user in by retrieving data from the
     * database and checking their correctness. If the user provides wrong data, an appropriate message will be
     * displayed.
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
        Label loginStatement = new Label("Please enter your credentials", Label.CENTER);
        Label welcomeLabel = new Label("Patient Service System - Orthodontic office");

        JTextField usernameField = new JTextField();
        Dimension dimension = new Dimension();
        dimension.setSize(usernameField.getWidth(),usernameField.getHeight());
        JPasswordField passwordField = new JPasswordField();
        passwordField.setSize(dimension);
        usernameField.setNextFocusableComponent(passwordField);

        Button loginButton = new Button("Login");

        ActionListener action = e -> {
            Connection connection = DataBaseHandling.StartConnectionWithDB();
            try {
                enteredUsername = usernameField.getText();
                enteredPassword = String.valueOf(passwordField.getPassword());
                numberOfAttempts++;
                User user = DataBaseHandling.LogInUser(connection, enteredUsername, enteredPassword);
                assert user != null;
                User admin = DataBaseHandling.LogInUser(connection,"admin", "admin");
                assert admin != null;
                List<User> list = DataBaseHandling.SearchForAllUsers(connection, admin);

                assert list != null;
                for(User userToFind: list) {
                    if (userToFind.getUserLogin().equals(enteredUsername)){
                        loggedUser.setUserId(userToFind.getUserId());
                        loggedUser.setUserPassword(userToFind.getUserPassword());
                        loggedUser.setUserLogin(userToFind.getUserLogin());
                        loggedUser.setUserEmail(userToFind.getUserEmail());
                        loggedUser.setUserName(userToFind.getUserName());
                        loggedUser.setUserSurname(userToFind.getUserSurname());
                        loggedUser.setUserTelephoneNumber(userToFind.getUserTelephoneNumber());
                        loggedUser.setUserPermissionsLevel(userToFind.getUserPermissionsLevel());
                        loggedUser.setUserAddress(userToFind.getUserAddress());
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
                loginStatement.setBackground(Color.red);
                loginStatement.setText("Your input is wrong (Attempt: " + numberOfAttempts + ")");
            }
            finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
        loginFrame.setIconImage(icon);
        loginFrame.add(loginPanel);
        loginFrame.add(loginPanelMessage, BorderLayout.SOUTH);
        loginFrame.add(welcomePanel,BorderLayout.NORTH);
        loginFrame.setSize((int)(screenWidth/1.7),(screenHeight/3));
        loginFrame.setLocationRelativeTo(null);
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
        usernameField.requestFocus();
    }

    /**
     * The method which is responsible for running main frame of GUI for Orthodontist, sets the appropriate buttons and the color of the interface.
     * It also takes care of ensuring that the Orthodontist would not be able to use the options of other users.
     */
    public void runGUIOrthodontist() {
        frame.removeAll();
        createButtonsOrthodontist();
        addPanels();
        frame.setIconImage(icon);
        switch (SCREEN_RES) {
            case 0:
                frame.setSize(screenWidth,screenHeight); // default size
                break;
            case 1:
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                break;
            case 2:
                frame.setSize(screenDimension.width, screenDimension.height);
                break;
        }
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        for(int i=0;i<6;i++) {
            centralPanel.add(Box.createRigidArea(new Dimension(0, frame.getHeight()/22)));
            buttons[i].setMaximumSize(new Dimension((int)(frame.getWidth()/1.5), frame.getHeight()/9));
            centralPanel.add(buttons[i]);
        }
        centralPanel.add(Box.createRigidArea(new Dimension(0, frame.getHeight()/22)));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
    }

    /**
     * The method which is responsible for running main frame of GUI for Patient, sets the appropriate buttons and the color of the interface.
     * It also takes care of ensuring that the patient would not be able to use the options of other users.
     */
    public void runGUIPatient() {
        frame.removeAll();
        createButtonsPatient();
        addPanels();
        frame.setIconImage(icon);
        switch (SCREEN_RES) {
            case 0:
                frame.setSize(screenWidth,screenHeight); // default size
                break;
            case 1:
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                break;
            case 2:
                frame.setSize(screenDimension.width, screenDimension.height);
                break;
        }
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        for(int i=0;i<5;i++) {
            centralPanel.add(Box.createRigidArea(new Dimension(0, frame.getHeight()/22)));
            buttons[i].setMaximumSize(new Dimension((int)(frame.getWidth()/1.5), frame.getHeight()/9));
            centralPanel.add(buttons[i]);
        }
        centralPanel.add(Box.createRigidArea(new Dimension(0, frame.getHeight()/22)));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
    }

    /**
     * The method which is responsible for running main frame of GUI for Developer, sets the appropriate buttons and the color of the interface.
     * It also takes care of ensuring that the Developer would not be able to use the options of other users.
     */
    public void runGUIDeveloper() {
        frame.removeAll();
        createButtonsDeveloper();
        addPanels();
        frame.setIconImage(icon);
        switch (SCREEN_RES) {
            case 0:
                frame.setSize(screenWidth,screenHeight); // default size
                break;
            case 1:
                frame.setSize((int)(screenWidth*1.3),(int)(screenHeight*1.3));
                break;
            case 2:
                frame.setSize(screenDimension.width, screenDimension.height);
                break;
        }
        frame.setMenuBar(menu);
        frame.setResizable(false);
        centralPanel.removeAll();
        for(int i=0;i<7;i++) {
            centralPanel.add(Box.createRigidArea(new Dimension(0, frame.getHeight()/22)));
            buttons[i].setMaximumSize(new Dimension((int)(frame.getWidth()/1.5), frame.getHeight()/9));
            centralPanel.add(buttons[i]);
        }
        centralPanel.add(Box.createRigidArea(new Dimension(0, frame.getHeight()/22)));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
    }
}
