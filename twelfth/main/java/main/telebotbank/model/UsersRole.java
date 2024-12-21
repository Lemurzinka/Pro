package main.telebotbank.model;

public enum UsersRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}

