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

    public User() {
    }

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

    public int getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserTelephoneNumber() {
        return userTelephoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getUserPermissionsLevel() {
        return userPermissionsLevel;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setUserTelephoneNumber(String userTelephoneNumber) {
        this.userTelephoneNumber = userTelephoneNumber;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPermissionsLevel(int userPermissionsLevel) {
        this.userPermissionsLevel = userPermissionsLevel;
    }

}
