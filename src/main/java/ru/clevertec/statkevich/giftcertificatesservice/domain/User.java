package ru.clevertec.statkevich.giftcertificatesservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * User is the main entity of application.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    /**
     * Email of user
     */
    @Column(name = "email")
    private String email;

    /**
     * Full name of user
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * User password
     */
    @Column(name = "password")
    private String password;

}
