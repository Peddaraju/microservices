package com.app.tutorial.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
/*@Entity                       // H2 database
@Table(name = "tutorial")*/
@Document(collection = "tutorials")
public class Tutorial {

    @Transient
    public static final String SEQUENCE_NAME = "tutorials_sequence";

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private boolean published;


}
