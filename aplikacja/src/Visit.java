import java.sql.Timestamp;

public class Visit {

    private int visitId;
    private int patientId;
    private int orthodontistId;
    private int userPatientId;
    private int userOrthodontistId;
    private Timestamp visitDate;
    private String visitComment;

    public Visit() {}

    public Visit(int visitId, int patientId, int orthodontistId, int userPatientId, int userOrthodontistId, Timestamp visitDate, String visitComment) {
        this.visitId = visitId;
        this.patientId = patientId;
        this.orthodontistId = orthodontistId;
        this.userPatientId = userPatientId;
        this.userOrthodontistId = userOrthodontistId;
        this.visitDate = visitDate;
        this.visitComment = visitComment;
    }

    public int getVisitId() {
        return visitId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getOrthodontistId() {
        return orthodontistId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setOrthodontistId(int orthodontistId) {
        this.orthodontistId = orthodontistId;
    }

    public int getUserPatientId() {
        return userPatientId;
    }

    public void setUserPatientId(int userPatientId) {
        this.userPatientId = userPatientId;
    }

    public int getUserOrthodontistId() {
        return userOrthodontistId;
    }

    public void setUserOrthodontistId(int userOrthodontistId) {
        this.userOrthodontistId = userOrthodontistId;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitComment() {
        return visitComment;
    }

    public void setVisitComment(String visitComment) {
        this.visitComment = visitComment;
    }
}
