package com.app.tutorial.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;

    private long seq;
}
