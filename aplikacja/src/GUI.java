import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI{

    Frame ramka;
    MenuBar menu;
    Menu plikMenu, opcjeMenu, pomocMenu;
    Panel panelGorny, panelSrodkowy, panelDolny;
    Label etykietaObramowaniePrzycisku, etykietaWcisnietyPrzycisk;
    Button[] przyciski = new Button[3];

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
        przyciski[0] = new Button("Wczytaj Pacjenta");
        przyciski[0].setActionCommand("Akcja przycisk1");
        przyciski[1] = new Button("Przejdź do skrzynki pocztowej");
        przyciski[1].setActionCommand("Akcja przycisk2");
        przyciski[2] = new Button("Wyjdź z programu");
        przyciski[2].setActionCommand("Akcja przycisk3");

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
        plikMenu.add(new MenuItem("Wyjdz"));

        opcjeMenu.add(new MenuItem("Otworz ustawienia"));
        opcjeMenu.add(new MenuItem("Zresetuj interfejs graficzny"));

        pomocMenu.add(new MenuItem("Twoje konto"));
        pomocMenu.add(new MenuItem("Pomoc"));
        pomocMenu.add(new MenuItem("O programie"));
    }

    private void dodajPanele(){
        ramka.add(panelGorny,BorderLayout.NORTH);
        ramka.add(panelSrodkowy,BorderLayout.CENTER);
        ramka.add(panelDolny,BorderLayout.SOUTH);
    }

    private void dodajEtykiety(){

    }

    private void obslugaOkno(){

    }

    public void pokazRamke() {
        ramka.setSize(700,600);
        ramka.setLayout(new FlowLayout());
        ramka.setVisible(true);
        ramka.setBackground(Color.darkGray);
        ramka.setMenuBar(menu);
        ramka.setLayout(new BorderLayout());
        panelGorny.add(przyciski[0]);
        panelSrodkowy.add(przyciski[1]);
        panelDolny.add(przyciski[2]);
        przyciski[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ramka.dispose();
                System.exit(0);
            }
        });

        obslugaOkno();
        dodajBelkiMenu();
        dodajDoBelekMenu();
        dodajPanele();
        dodajEtykiety();
    }

    public void zamkniecieProgramu(){
        ramka.dispose();
        System.exit(0);
    }


}
