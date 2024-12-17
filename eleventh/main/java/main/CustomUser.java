package main;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;

private String email;
private String phone;
private String address;

    public CustomUser(String login, String password, String email, String phone, String address) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
