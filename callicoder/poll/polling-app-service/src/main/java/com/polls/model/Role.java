package com.polls.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    @NonNull
    private RoleName name;
}
