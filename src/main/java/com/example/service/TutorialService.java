package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Tutorial;
import com.example.repository.TutorialRepository;

@Service
public class TutorialService {
	@Autowired
	private TutorialRepository tutorialRepository;

	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		List<Tutorial> all = new ArrayList<>();
		tutorialRepository.findAll().forEach(all::add);
		if (all.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	public ResponseEntity<Tutorial> getTutorialById(int id) {
		Optional<Tutorial> tdata = tutorialRepository.findById(id);
		if (tdata.isPresent())
			return new ResponseEntity<>(tdata.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Tutorial>> getTutorialByTitle(String title) {
		List<Tutorial> tdata = tutorialRepository.findByTitle(title);
		if (tdata.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(tdata, HttpStatus.OK);
	}

	public ResponseEntity<List<Tutorial>> getTutorialByPublished(boolean pub) {
		List<Tutorial> ts = tutorialRepository.findByPublished(pub);
		if (ts.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(ts, HttpStatus.OK);
	}

	public ResponseEntity<Tutorial> createTutorial(Tutorial tut) {
		Tutorial _tutorial = tutorialRepository.save(tut);
		return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	}

	public ResponseEntity<Tutorial> updateTutorial(int id, Tutorial tut) {
		Optional<Tutorial> tdata = tutorialRepository.findById(id);
		if (tdata.isPresent()) {
			Tutorial _t = tdata.get();
			_t.setTitle(tut.getTitle());
			_t.setDescription(tut.getDescription());
			_t.setPublished(tut.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_t), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<HttpStatus> deleteTutorialById(int id) {
		tutorialRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<HttpStatus> deleteAllTutorial() {
		tutorialRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
