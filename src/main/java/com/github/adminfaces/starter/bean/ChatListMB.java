package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.Chat;
import com.github.adminfaces.starter.repository.ChatRepository;
import com.github.adminfaces.starter.repository.InfoRepository;
import com.github.adminfaces.starter.service.CarService;
import com.github.adminfaces.starter.service.ChatService;
import com.github.adminfaces.starter.service.IChatMessageService;
import com.github.adminfaces.starter.service.IChatService;
import com.github.adminfaces.template.exception.BusinessException;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class ChatListMB implements Serializable {

    @Inject
    ChatService carService;
	@Inject
	ChatRepository rep;
	@Inject
	IChatMessageService ca;
    Long m;
    String a;
    String b;
    public void agrep(){ca.answermsg(a, m);}
    public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	List<Chat> cars;

    public List<Chat> getCars() {
    	cars =(List<Chat>) rep.findAll();
    			return cars;
	}

	public void setCars(List<Chat> cars) {
		this.cars = cars;
	}

	List<Chat> selectedCars; //cars selected in checkbox column

    List<Chat> filteredValue;// datatable filteredValue attribute (column filters)





    public void findCarById(Long id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedCars.add(carService.findById(id));
    }

    public void delete() {
        int numCars = 0;
        for (Chat selectedCar : selectedCars) {
            numCars++;
            carService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<Chat> getSelectedCars() {
        return selectedCars;
    }

    public List<Chat> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Chat> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<Chat> selectedCars) {
        this.selectedCars = selectedCars;
    }



    public Long getM() {
        return m;
    }

    public void setM(Long id) {
        this.m = id;
    }
}
