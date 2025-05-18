package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	// custom query method
	List<Tutorial> findByPublished(boolean published);

	List<Tutorial> findByTitle(String title);

}
