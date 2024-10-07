package Model;
/**
 This class represents an user.
 */
public class Users {
    private int userId;
    private String username;
    private String password;

    public Users (int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /** Gets User ID
     * @return userId */
    public int getUserId() {
        return userId;
    }

    /** Sets User ID
     * @param userId */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Gets Username
     * @return username */
    public String getUsername() {
        return username;
    }

    /** Sets Username
     * @param username */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Gets Password
     * @return password*/
    public String getPassword() {
        return password;
    }

    /** Sets Password
     * @param password */
    public void setPassword(String password) {
        this.password = password;
    }
}