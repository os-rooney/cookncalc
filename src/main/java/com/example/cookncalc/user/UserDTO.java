package com.example.cookncalc.user;

public class UserDTO {

    private Long id;
    private String username;
    private boolean admin;

    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.username = user.getUsername();
        userDTO.admin = user.isAdmin();
        return userDTO;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

