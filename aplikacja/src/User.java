/**
 * The class which represents all the data of a user of the application.
 */
public class User {

    // private fields of User containing all of credentials
    private int userId;
    private String userLogin = "null";
    private String userPassword = "null";
    private String userName = "null";
    private String userSurname = "null";
    private String userTelephoneNumber = "null";
    private String userAddress = "null";
    private String userEmail = "null";
    private int userPermissionsLevel = 0; // 0 - patient, 1 - orthodontist, 2 - dev

    /**
     * The Constructor.
     */
    public User() {
    }

    /**
     * The Constructor which copies all the data of another User object.
     * @param user
     */
    public User(User user){
        this.userId = user.getUserId();
        this.userLogin = user.getUserLogin();
        this.userPassword = user.getUserPassword();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.userTelephoneNumber = user.getUserTelephoneNumber();
        this.userAddress = user.getUserAddress();
        this.userEmail = user.getUserEmail();
        this.userPermissionsLevel = user.getUserPermissionsLevel();
    }

    /**
     * The Constructor which creates an object using 8 parameters (without password).
     * @param userId
     * @param userLogin
     * @param userName
     * @param userSurname
     * @param userTelephoneNumber
     * @param userAddress
     * @param userEmail
     * @param userPermissionsLevel
     */
    public User(int userId, String userLogin, String userName, String userSurname,
                String userTelephoneNumber, String userAddress, String userEmail, int userPermissionsLevel){
        this.userId = userId;
        this.userLogin = userLogin;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userTelephoneNumber = userTelephoneNumber;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userPermissionsLevel = userPermissionsLevel;
    };

    /**
     * The Constructor which creates an object using 9 parameters (including password).
     * @param userId
     * @param userLogin
     * @param userPassword
     * @param userName
     * @param userSurname
     * @param userTelephoneNumber
     * @param userAddress
     * @param userEmail
     * @param userPermissionsLevel
     */
    public User(int userId, String userLogin, String userPassword, String userName, String userSurname,
                String userTelephoneNumber, String userAddress, String userEmail, int userPermissionsLevel){
        this.userId = userId;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userTelephoneNumber = userTelephoneNumber;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userPermissionsLevel = userPermissionsLevel;
    };

    /**
     * The method to access private field userId.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * The method to access private field userLogin.
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * The method to access private field userPassword.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * The method to access private field userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The method to access private field userSurname.
     */
    public String getUserSurname() {
        return userSurname;
    }

    /**
     * The method to access private field userTelephoneNumber.
     */
    public String getUserTelephoneNumber() {
        return userTelephoneNumber;
    }

    /**
     * The method to access private field userAddress.
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * The method to access private field userEmail.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * The method to access private field userPermissionsLevel.
     */
    public int getUserPermissionsLevel() {
        return userPermissionsLevel;
    }

    /**
     * The method to set private field userId.
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * The method to set private field userLogin.
     * @param userLogin
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * The method to set private field userPassword.
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * The method to set private field userName.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * The method to set private field userSurname.
     * @param userSurname
     */
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    /**
     * The method to set private field userTelephoneNumber.
     * @param userTelephoneNumber
     */
    public void setUserTelephoneNumber(String userTelephoneNumber) {
        this.userTelephoneNumber = userTelephoneNumber;
    }

    /**
     * The method to set private field userAddress.
     * @param userAddress
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * The method to set private field userEmail.
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * The method to set private field userPermissionsLevel.
     * @param userPermissionsLevel
     */
    public void setUserPermissionsLevel(int userPermissionsLevel) {
        this.userPermissionsLevel = userPermissionsLevel;
    }

}
