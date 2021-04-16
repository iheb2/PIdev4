package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.model.Info;
import tn.esprit.spring.model.User;
import tn.esprit.spring.repo.InfoRepository;
import tn.esprit.spring.repo.UserRepository;



@Service
public class UserServicempl implements IUserService{
	@Autowired
	UserRepository userR;
	@Override
	public  User getUser(String id ){ return userR.findById(Long.parseLong(id)).orElse(null);}

}
