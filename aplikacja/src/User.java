public class User {

    // private fields of User containing all of credentials
    private int idUzytkownika;
    private String nazwaUzytkownika;
    private String hasloUzytkownika;
    private String imieUzytkownika;
    private String nazwiskoUzytkownika;
    private String numerTelefonuUzytkownika;
    private String adresUzytkownika;
    private String emailUzytkownika;
    private int poziomUprawnien; // 0 - patient, 1 - orthodontist, 2 - dev

    public User(int idUzytkownika, String nazwaUzytkownika, String hasloUzytkownika, String imieUzytkownika, String nazwiskoUzytkownika,
                String numerTelefonuUzytkownika, String adresUzytkownika, String emailUzytkownika, int poziomUprawnien){
        this.idUzytkownika = idUzytkownika;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.hasloUzytkownika = hasloUzytkownika;
        this.imieUzytkownika = imieUzytkownika;
        this.nazwiskoUzytkownika = nazwiskoUzytkownika;
        this.numerTelefonuUzytkownika = numerTelefonuUzytkownika;
        this.adresUzytkownika = adresUzytkownika;
        this.emailUzytkownika = emailUzytkownika;
        this.poziomUprawnien = poziomUprawnien;
    };

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public String getHasloUzytkownika() {
        return hasloUzytkownika;
    }

    public String getImieUzytkownika() {
        return imieUzytkownika;
    }

    public String getNazwiskoUzytkownika() {
        return nazwiskoUzytkownika;
    }

    public String getNumerTelefonuUzytkownika() {
        return numerTelefonuUzytkownika;
    }

    public String getAdresUzytkownika() {
        return adresUzytkownika;
    }

    public String getEmailUzytkownika() {
        return emailUzytkownika;
    }

    public int getPoziomUprawnien() {
        return poziomUprawnien;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }

    public void setHasloUzytkownika(String hasloUzytkownika) {
        this.hasloUzytkownika = hasloUzytkownika;
    }

    public void setImieUzytkownika(String imieUzytkownika) {
        this.imieUzytkownika = imieUzytkownika;
    }

    public void setNazwiskoUzytkownika(String nazwiskoUzytkownika) {
        this.nazwiskoUzytkownika = nazwiskoUzytkownika;
    }

    public void setNumerTelefonuUzytkownika(String numerTelefonuUzytkownika) {
        this.numerTelefonuUzytkownika = numerTelefonuUzytkownika;
    }

    public void setAdresUzytkownika(String adresUzytkownika) {
        this.adresUzytkownika = adresUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        this.emailUzytkownika = emailUzytkownika;
    }

    public void setPoziomUprawnien(int poziomUprawnien) {
        this.poziomUprawnien = poziomUprawnien;
    }

}
