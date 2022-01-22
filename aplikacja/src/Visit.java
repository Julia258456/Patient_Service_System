import java.sql.Timestamp;

/**
 * The class which represents all the data about a user's visit.
 */
public class Visit {

    private int visitId;
    private int patientId;
    private int orthodontistId;
    private int userPatientId;
    private int userOrthodontistId;
    private Timestamp visitDate;
    private String visitComment;

    /**
     * The Constructor.
     */
    public Visit() {}

    /**
     * The Constructor which creates an object using 6 parameters (without visitId).
     * @param patientId
     * @param orthodontistId
     * @param userPatientId
     * @param userOrthodontistId
     * @param visitDate
     * @param visitComment
     */
    public Visit(int patientId, int orthodontistId, int userPatientId, int userOrthodontistId, Timestamp visitDate, String visitComment) {
        this.patientId = patientId;
        this.orthodontistId = orthodontistId;
        this.userPatientId = userPatientId;
        this.userOrthodontistId = userOrthodontistId;
        this.visitDate = visitDate;
        this.visitComment = visitComment;
    }

    /**
     * The Constructor which creates an object using 7 parameters (including visitId).
     * @param visitId
     * @param patientId
     * @param orthodontistId
     * @param userPatientId
     * @param userOrthodontistId
     * @param visitDate
     * @param visitComment
     */
    public Visit(int visitId, int patientId, int orthodontistId, int userPatientId, int userOrthodontistId, Timestamp visitDate, String visitComment) {
        this.visitId = visitId;
        this.patientId = patientId;
        this.orthodontistId = orthodontistId;
        this.userPatientId = userPatientId;
        this.userOrthodontistId = userOrthodontistId;
        this.visitDate = visitDate;
        this.visitComment = visitComment;
    }

    /**
     * The Constructor which copies all the data of another Visit object.
     * @param visit
     */
    public Visit(Visit visit){
        this.visitId = visit.getVisitId();
        this.patientId = visit.getPatientId();
        this.orthodontistId = visit.getOrthodontistId();
        this.userPatientId = visit.getUserPatientId();
        this.userOrthodontistId = visit.getUserOrthodontistId();
        this.visitDate = visit.getVisitDate();
        this.visitComment = visit.getVisitComment();
    }

    /**
     * The method to access private field visitId.
     */
    public int getVisitId() {
        return visitId;
    }

    /**
     * The method to access private field patientId.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * The method to access private field orthodontistId.
     */
    public int getOrthodontistId() {
        return orthodontistId;
    }

    /**
     * The method to set private field visitId.
     * @param visitId
     */
    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    /**
     * The method to set private field patientId.
     * @param patientId
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * The method to set private field orthodontistId.
     * @param orthodontistId
     */
    public void setOrthodontistId(int orthodontistId) {
        this.orthodontistId = orthodontistId;
    }

    /**
     * The method to access private field userPatientId.
     */
    public int getUserPatientId() {
        return userPatientId;
    }

    /**
     * The method to set private field userPatientId.
     * @param userPatientId
     */
    public void setUserPatientId(int userPatientId) {
        this.userPatientId = userPatientId;
    }

    /**
     * The method to access private field userOrthodontistId.
     */
    public int getUserOrthodontistId() {
        return userOrthodontistId;
    }

    /**
     * The method to set private field userOrthodontistId.
     * @param userOrthodontistId
     */
    public void setUserOrthodontistId(int userOrthodontistId) {
        this.userOrthodontistId = userOrthodontistId;
    }

    /**
     * The method to access private field visitDate.
     */
    public Timestamp getVisitDate() {
        return visitDate;
    }

    /**
     * The method to set private field visitDate.
     * @param visitDate
     */
    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * The method to access private field visitComment.
     */
    public String getVisitComment() {
        return visitComment;
    }

    /**
     * The method to set private field visitComment.
     * @param visitComment
     */
    public void setVisitComment(String visitComment) {
        this.visitComment = visitComment;
    }
}
