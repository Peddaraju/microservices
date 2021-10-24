package com.app.rest.resources;

import com.app.rest.exception.ResourceNotFoundException;
import com.app.rest.model.Question;
import com.app.rest.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class QuestionResource {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public Page<Question> getQuestions(Pageable pageable) {

        Page<Question> questionPage = questionRepository.findAll(pageable);
        if (questionPage.isEmpty()) {
            log.error("Question not found id "+questionPage.getNumberOfElements());
            throw new ResourceNotFoundException("Question not found id "+questionPage.getNumberOfElements());
        }
        return questionPage;
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
}
