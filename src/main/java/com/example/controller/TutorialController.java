package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Tutorial;
import com.example.service.TutorialService;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {
	@Autowired
	private TutorialService tutorialService;

	@GetMapping("/all")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		ResponseEntity<List<Tutorial>> all = tutorialService.getAllTutorials();
		return all;
	}

	@GetMapping("/gettutobyid/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable int id) {
		ResponseEntity<Tutorial> tdata = tutorialService.getTutorialById(id);
		return tdata;
	}

	@PostMapping("/savenewtut")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tut) {
		ResponseEntity<Tutorial> _tutorial = tutorialService.createTutorial(tut);
		return _tutorial;
	}

	@PutMapping("/saveupdatedtut/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable int id, @RequestBody Tutorial tut) {
		ResponseEntity<Tutorial> tdata = tutorialService.updateTutorial(id, tut);
		return tdata;
	}

	@DeleteMapping("/deletetutbyid/{id}")
	public ResponseEntity<HttpStatus> deleteTutorialById(@PathVariable int id) {
		tutorialService.deleteTutorialById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deletealltut")
	public ResponseEntity<HttpStatus> deleteAllTutorial() {
		tutorialService.deleteAllTutorial();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
