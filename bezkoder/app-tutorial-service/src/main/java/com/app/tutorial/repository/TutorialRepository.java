package com.app.tutorial.repository;

import com.app.tutorial.model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
public interface TutorialRepository extends MongoRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
