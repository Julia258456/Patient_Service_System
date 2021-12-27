import java.sql.Timestamp;

public class Wizyta {

    private int idWizyty;
    private int pacjent;
    private int ortodonta;
    private int userIdPacjenta;
    private int userIdOrtodonty;
    private Timestamp dataWizyty;
    private String komentarz;

    public Wizyta() {}

    public Wizyta(int idWizyty, int pacjent, int ortodonta, int userIdPacjenta, int userIdOrtodonty, Timestamp dataWizyty, String komentarz) {
        this.idWizyty = idWizyty;
        this.pacjent = pacjent;
        this.ortodonta = ortodonta;
        this.userIdPacjenta = userIdPacjenta;
        this.userIdOrtodonty = userIdOrtodonty;
        this.dataWizyty = dataWizyty;
        this.komentarz = komentarz;
    }

    public int getIdWizyty() {
        return idWizyty;
    }

    public int getPacjent() {
        return pacjent;
    }

    public int getOrtodonta() {
        return ortodonta;
    }

    public void setIdWizyty(int idWizyty) {
        this.idWizyty = idWizyty;
    }

    public void setPacjent(int pacjent) {
        this.pacjent = pacjent;
    }

    public void setOrtodonta(int ortodonta) {
        this.ortodonta = ortodonta;
    }

    public int getUserIdPacjenta() {
        return userIdPacjenta;
    }

    public void setUserIdPacjenta(int userIdPacjenta) {
        this.userIdPacjenta = userIdPacjenta;
    }

    public int getUserIdOrtodonty() {
        return userIdOrtodonty;
    }

    public void setUserIdOrtodonty(int userIdOrtodonty) {
        this.userIdOrtodonty = userIdOrtodonty;
    }

    public Timestamp getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(Timestamp dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz = komentarz;
    }
}
