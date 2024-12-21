package main.telebotbank.model;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data @NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "telegram_id")
    private Long telegramId;

    private Integer stateId;

    @Column(nullable = false, name = "first_name")
    @NotNull(message = "first name can not be null")
   private String firstName;


    @Column(nullable = false, name = "last_name")
    @NotNull(message = "last name can not be null")
    private String lastName;

    @Column(nullable = false, name = "phone_number")
    @NotNull(message = "phone number can not be null")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "phone number must be valid")
    private String number;

    @Column(nullable = false)
    @NotNull(message = "email can not be null")
    private String email;

    @Column(nullable = false, updatable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private UsersRole role;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

   public Users (Long telegramId, Integer state){
        this.telegramId = telegramId;
        this.stateId= state;
   }

   public Users (Long telegramId, Integer stateId, UsersRole role){
        this.telegramId = telegramId;
        this.stateId = stateId;
        this.role = role;
   }




}
