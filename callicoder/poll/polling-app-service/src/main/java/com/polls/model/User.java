package com.polls.model;

import com.polls.model.audit.DateAudit;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users", uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "username"
                }),
                @UniqueConstraint(columnNames = {
                        "email"
                })
}
)
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String username;

    @NaturalId
    @Email
    @NonNull
    private String email;

    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
