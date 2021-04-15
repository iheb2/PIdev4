package tn.esprit.spring.services;


import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.Client;
import tn.esprit.spring.repository.ClientRepository;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

  @Autowired
  ClientRepository repository;
  

  public void save(MultipartFile file) {
    try {
      List<Client> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }


  
  
  
  public ByteArrayInputStream load() {
	    List<Client> tutorials =  (List<Client>) repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
    return in;
  }
}