import java.awt.*;
import java.awt.event.*;

public class GUI{
    Frame ramka;
    MenuBar menu;
    Menu plikMenu, opcjeMenu, pomocMenu;
    Panel panelGorny, panelSrodkowy, panelDolny;
    Label etykietaObramowaniePrzycisku, etykietaWcisnietyPrzycisk;
    Button[] przyciski = new Button[10];
    static String username;
    static String password;
    int logginsAttempt = 0;
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)(screenDimension.width * 0.7);
    int screenHeight = (int)(screenDimension.height * 0.7);

    public GUI() {
        ramka = new Frame("Menu główne");
        ramka.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit( 0 );
                    }
                }
        );
        menu = new MenuBar();
        stworzPanele();
        stworzEtykiety();
        stworzPrzyciski();
        stworzMenu();

        plikMenu = new Menu("Plik");
        opcjeMenu = new Menu("Opcje");
        pomocMenu = new Menu("Pomoc");
    }

    private void stworzEtykiety(){
        etykietaObramowaniePrzycisku = new Label();
        etykietaWcisnietyPrzycisk = new Label();
    }

    private void stworzPrzyciski(){
        przyciski[0] = new Button("Wyszukaj pacjenta z bazy danych");
        przyciski[0].setActionCommand("Akcja przycisk1");
        przyciski[1] = new Button("Skrzynka pocztowa");
        przyciski[1].setActionCommand("Akcja przycisk2");
        przyciski[2] = new Button("Generuj raport");
        przyciski[2].setActionCommand("Akcja przycisk3");
        przyciski[3] = new Button("Wyloguj się");
        przyciski[3].setActionCommand("Akcja przycisk4");
        przyciski[4] = new Button("Wyjdź z programu");
        przyciski[4].setActionCommand("Akcja przycisk5");


        przyciski[3].addActionListener(e -> {
            ramka.dispose();
            ekranLogowania();
        });
        przyciski[4].addActionListener(e -> {
            ramka.dispose();
            System.exit(0);
        });
    }

    private void stworzPanele(){
        panelGorny = new Panel();
        panelDolny = new Panel();
        panelSrodkowy = new Panel();
    }

    private void stworzMenu(){
        plikMenu = new Menu("Plik");
        pomocMenu = new Menu("Pomoc");
        opcjeMenu = new Menu("Opcje");
    }

    private void dodajBelkiMenu(){
        menu.add(plikMenu);
        menu.add(opcjeMenu);
        menu.add(pomocMenu);
    }

    private void dodajDoBelekMenu(){
        plikMenu.add(new MenuItem("Wczytaj Plik"));
        MenuItem defaultItem = new MenuItem("Wyjdź");
        defaultItem.addActionListener(e -> {
            ramka.dispose();
            ramka.setVisible(false);
            System.exit(0);
        });
        plikMenu.add(defaultItem);
        opcjeMenu.add(new MenuItem("Otworz ustawienia"));
        opcjeMenu.add(new MenuItem("Zresetuj interfejs graficzny"));

        pomocMenu.add(new MenuItem("Twoje konto"));
        pomocMenu.add(new MenuItem("Pomoc"));
        pomocMenu.add(new MenuItem("O programie"));
    }

    private void dodajPanele(){
        GridLayout layout = new GridLayout(6,6,10,10);
        panelGorny.setLayout(layout);
        panelSrodkowy.setLayout(layout);
        panelDolny.setLayout(layout);
        ramka.add(panelGorny, BorderLayout.NORTH);
        ramka.add(panelSrodkowy, BorderLayout.CENTER);
        ramka.add(panelDolny, BorderLayout.SOUTH);
        ramka.setVisible(true);
    }

    private void dodajEtykiety(){

    }

    private void obslugaOkno(){

    }

    public void ekranLogowania() {
        username = "null";
        password = "null";

        Frame ramkaLogowanie = new Frame("Menu logowania");

        Panel welcomePanel = new Panel();
        Panel panelLogowaniaKomunikat = new Panel();
        Panel panelLogowania = new Panel(new GridBagLayout());

        Label labelUzytkownik = new Label("Nazwa użytkownika: ");
        Label komunikatLogowania = new Label("Proszę wprowadź swoje dane do logowania");
        Label welcomeLabel = new Label("System Obsługi Pacjenta - Gabinet ortodontyczny");
        Label labelHaslo = new Label("Hasło: ");
        Label gap = new Label("    ");

        TextField poleNazwaUzytkownika = new TextField(20);
        TextField poleHasloUzytkownika = new TextField(20);

        Button przyciskLogowania = new Button("Login");

        ActionListener action = e -> {
            username = poleNazwaUzytkownika.getText();
            password = poleHasloUzytkownika.getText();
            logginsAttempt++;
            if (username.equals("admin") && password.equals("admin")) {
                ramkaLogowanie.setVisible(false);
                ramkaLogowanie.dispose();
                uruchomGui();
            }
            else {
                komunikatLogowania.setText("      Twoje dane są błędne (Próba: " + logginsAttempt + ")");
                System.out.println("Twoje dane są błędne (Próba: " + logginsAttempt + ")");
            }
        };

        welcomePanel.add(welcomeLabel);

        poleHasloUzytkownika.addActionListener(action);
        poleNazwaUzytkownika.addActionListener(action);
        przyciskLogowania.addActionListener(action);

        panelLogowania.add(labelUzytkownik);
        panelLogowania.add(poleNazwaUzytkownika);
        panelLogowania.add(labelHaslo);
        panelLogowania.add(poleHasloUzytkownika);
        panelLogowania.add(gap);
        panelLogowania.add(przyciskLogowania);

        panelLogowaniaKomunikat.add(komunikatLogowania, BorderLayout.NORTH);
        panelLogowaniaKomunikat.setVisible(true);

        ramkaLogowanie.setBackground(Color.darkGray);
        ramkaLogowanie.add(panelLogowania, BorderLayout.CENTER);
        ramkaLogowanie.add(panelLogowaniaKomunikat, BorderLayout.SOUTH);
        ramkaLogowanie.add(welcomePanel,BorderLayout.NORTH);
        ramkaLogowanie.setSize((int)(screenWidth/1.7),(screenHeight/3));
        ramkaLogowanie.setVisible(true);
        ramkaLogowanie.setResizable(false);
        ramkaLogowanie.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                ramkaLogowanie.setVisible(false);
                ramkaLogowanie.dispose();
                if (!username.equals("admin") && !password.equals("admin"))
                    System.exit(0);
            }

    });
    }

    public void uruchomGui() {
        obslugaOkno();
        dodajBelkiMenu();
        dodajDoBelekMenu();
        dodajPanele();
        dodajEtykiety();

        ramka.setSize(screenWidth,screenHeight);
        ramka.setBackground(Color.darkGray);
        ramka.setMenuBar(menu);
        ramka.setResizable(false);
        for(int i=0;i<5;i++)
            panelSrodkowy.add(przyciski[i]);
    }
}
