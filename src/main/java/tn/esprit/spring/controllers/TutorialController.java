package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.models.Notification;
import tn.esprit.spring.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

  @Autowired
  TutorialRepository tutorialRepository;

  private Sort.Direction getSortDirection(String direction) {
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }

    return Sort.Direction.ASC;
  }

  @GetMapping("/sortedNotificationsbymessage")
  public ResponseEntity<List<Notification>> getAllTutorials(@RequestParam(defaultValue = "message,asc") String[] sort) {

    try {
      List<Order> orders = new ArrayList<Order>();

      if (sort[0].contains(",")) {
        // will sort more than 2 fields
        // sortOrder="field, direction"
        for (String sortOrder : sort) {
          String[] _sort = sortOrder.split(",");
          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
        }
      } else {
        // sort=[field, direction]
        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
      }

      List<Notification> tutorials = tutorialRepository.findAll(Sort.by(orders));

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/AllNotificationsPagination")
  public ResponseEntity<Map<String, Object>> getAllTutorials(
        @RequestParam(required = false) String message,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size
      ) {

    try {
      List<Notification> tutorials = new ArrayList<Notification>();
      Pageable paging = PageRequest.of(page, size);
      
      Page<Notification> pageTuts;
      if (message == null)
        pageTuts = tutorialRepository.findAll(paging);
      else
        pageTuts = tutorialRepository.findByMessageContaining(message, paging);

      tutorials = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("tutorials", tutorials);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  @GetMapping("/tutorials/hello")
  public ResponseEntity<Map<String, Object>> findByMessage(
		 // @RequestParam(required = false) String message,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size
      ) {
    try {      
    	String	message="hello";
      List<Notification> tutorials = new ArrayList<Notification>();
      Pageable paging = PageRequest.of(page, size);
      
      Page<Notification> pageTuts = tutorialRepository.findByMessage(message, paging);
      tutorials = pageTuts.getContent();
            
      Map<String, Object> response = new HashMap<>();
      response.put("tutorials", tutorials);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  
/*
  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Notification> getTutorialById(@PathVariable("id") long id) {
    Optional<Notification> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }*/
/*
  @PostMapping("/tutorials")
  public ResponseEntity<Notification> createTutorial(@RequestBody Notification tutorial) {
    try {
    	Notification _tutorial = tutorialRepository.save(new Notification(tutorial.getTitle(), tutorial.getDescription(), false));
      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Notification> updateTutorial(@PathVariable("id") long id, @RequestBody Notification tutorial) {
    Optional<Notification> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
    	Notification _tutorial = tutorialData.get();
      _tutorial.setTitle(tutorial.getTitle());
      _tutorial.setDescription(tutorial.getDescription());
      _tutorial.setPublished(tutorial.isPublished());
      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
*/
  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
      tutorialRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      tutorialRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
