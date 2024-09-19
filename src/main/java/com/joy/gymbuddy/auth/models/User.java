package com.joy.gymbuddy.auth.models;

import com.joy.gymbuddy.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User extends BaseEntity {

    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
