package com.app.rest.assembler;

import com.app.rest.entity.Question;
import com.app.rest.model.QuestionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

//https://howtodoinjava.com/spring5/hateoas/spring-hateoas-tutorial/
public class QuestionModelAssembler extends RepresentationModelAssemblerSupport<Question, QuestionModel> {

    public QuestionModelAssembler(Class<?> controllerClass, Class<QuestionModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public QuestionModel toModel(Question entity) {
        QuestionModel questionModel = instantiateModel(entity);

        //questionModel.add(linkTo)
        return null;
    }
}
