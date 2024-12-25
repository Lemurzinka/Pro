package main.loginandgeo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import main.loginandgeo.dto.AccountDTO;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;


    private String email;
    private String name;
    private String pictureUrl;

    public Account() {}

    public Account(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static Account of(String email, String name, String pictureUrl) {
        return new Account(email, name, pictureUrl);
    }

    public AccountDTO toDTO() {
        return new AccountDTO(email, name, pictureUrl);
    }

    public static Account fromDTO(AccountDTO dto) {
        return new Account(dto.getEmail(), dto.getName(), dto.getPictureUrl());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
