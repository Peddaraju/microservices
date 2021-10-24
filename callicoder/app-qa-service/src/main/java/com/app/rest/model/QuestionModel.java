package com.app.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "actor")
@Relation(collectionRelation = "actors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionModel extends RepresentationModel<QuestionModel> {
    private Long id;
    private String title;
    private String description;
}
