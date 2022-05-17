package com.example.cookncalc.security.auth;

public class RegistrationDTO {

    /**
     * WARNING This constructor should only be used while testing!
     *
     * @param username the name of the user
     * @param password the password for the user (will also be used for passwordRepeat!)
     */
    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
        this.passwordRepeat = password;
    }

    private final String username;

    private final String password;

    private final String passwordRepeat;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }
}
