package main.telebotbank.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Date;

@jakarta.persistence.Entity
@Data @NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "telegram_id", unique = true)
    private Long telegramId;

    private Integer stateId;

    @Column(nullable = false, name = "first_name")
    @NotNull(message = "first name can not be null")
   private String firstName;


    @Column(nullable = false, name = "last_name")
    @NotNull(message = "last name can not be null")
    private String lastName;

    @Column(nullable = false, name = "phone_number", unique = true)
    @NotNull(message = "phone number can not be null")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "phone number must be valid")
    private String number;

    @Column(nullable = false, unique = true)
    @NotNull(message = "email can not be null")
    private String email;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsersRole role = UsersRole.USER;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
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
