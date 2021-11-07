package com.app.rest.resources;

import com.app.rest.entity.Question;
import com.app.rest.exception.ResourceNotFoundException;
import com.app.rest.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class QuestionResource {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        List<Question> questionPage = questionRepository.findAll();
        if (questionPage.isEmpty()) {
            log.error("Question not found id "+questionPage.size());
            throw new ResourceNotFoundException("Question not found id "+questionPage.size());
        }
        return questionPage;
    }

    @GetMapping("/questions/pageable")
    public Page<Question> getQuestions(Pageable pageable) {

        Page<Question> questionPage = questionRepository.findAll(pageable);
        if (questionPage.isEmpty()) {
            log.error("Question not found id "+questionPage.getNumberOfElements());
            throw new ResourceNotFoundException("Question not found id "+questionPage.getNumberOfElements());
        }
        return questionPage;
    }

    @GetMapping("/questions/heteoas")
    public EntityModel<List<Question>> getQuestionsWithURLs() {
        List<Question> questionPage = questionRepository.findAll();
        if (questionPage.isEmpty()) {
            log.error("Question not found id "+questionPage.size());
            throw new ResourceNotFoundException("Question not found id "+questionPage.size());
        }
        return EntityModel.of(questionPage);
    }

    @GetMapping("/questions/{questionId}")
    public EntityModel<Question> getQuestionsById(@PathVariable Long questionId) {
        Optional<Question> questionPage = questionRepository.findById(questionId);
        if (questionPage.isEmpty()) {
            throw new ResourceNotFoundException("Question not found id ");
        }

        EntityModel<Question> questionEntityModel = EntityModel.of(questionPage.get());
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllQuestions());
        questionEntityModel.add(webMvcLinkBuilder.withRel("all-questions"));

        return questionEntityModel;
    }

    @PostMapping("/questions")
    private Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestions(@PathVariable Long questionId,
                                    @Valid @RequestBody Question questionDto) {
        /*Optional<Question> questionData = questionRepository.findById(questionId);
        if(questionData.isPresent()) {
            Question _question = questionData.get();
            _question.setTitle(questionDto.getTitle());
            _question.setDescription(questionDto.getDescription());
            return questionRepository.save(_question);
        } else {
            throw new ResourceNotFoundException("Question not found id "+questionId);
        }*/

        return questionRepository.findById(questionId)
                .map(
                        question -> {
                            question.setTitle(questionDto.getTitle());
                            question.setDescription(questionDto.getDescription());
                            return questionRepository.save(question);
                        }
                ).orElseThrow(
                        ()  -> new ResourceNotFoundException("Question not found id" + questionId)
                );
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(
                        question -> {
                            questionRepository.delete(question);
                            return ResponseEntity.ok().build();
                        }
                ).orElseThrow(
                        () -> {
                            log.info("Question id not found:"+questionId);
                            throw new ResourceNotFoundException("question not found");
                        }
                );
    }
}
