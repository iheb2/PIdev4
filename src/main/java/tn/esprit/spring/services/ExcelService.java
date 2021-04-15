package tn.esprit.spring.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import tn.esprit.spring.models.Client;
import tn.esprit.spring.repository.ClientRepository;

@Service
public class ExcelService {
  @Autowired
  ClientRepository repository;

  public ByteArrayInputStream load() {
    List<Client> tutorials =  (List<Client>) repository.findAll();
    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    return in;
  }


  public void save(MultipartFile file) {
    try {
      List<Client> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public List<Client> getAllTutorials() {
    return (List<Client>) repository.findAll();
  }
  
  
}