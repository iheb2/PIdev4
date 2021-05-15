package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.service.RequestCreditService;
import com.github.adminfaces.template.exception.BusinessException;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class RequestCreditListMB implements Serializable {

    @Inject
    RequestCreditService requestService;

    Long id;
    double amount;
    double months;
    
    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getMonths() {
		return months;
	}

	public void setMonths(double months) {
		this.months = months;}
	
	double monthly_payment;
    
    public double getMonthly_payment() {
		return monthly_payment;
	}

	public void setMonthly_payment(double monthly_payment) {
		this.monthly_payment = monthly_payment;
	}
	

    LazyDataModel<RequestCredit> cars;

    Filter<RequestCredit> filter = new Filter<>(new RequestCredit());

    List<RequestCredit> selectedCars; //cars selected in checkbox column

    List<RequestCredit> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<RequestCredit>() {
            @Override
            public List<RequestCredit> load(int first, int pageSize,
                                  String sortField, SortOrder sortOrder,
                                  Map<String, Object> filters) {
                com.github.adminfaces.starter.infra.model.SortOrder order = null;
                if (sortOrder != null) {
                    order = sortOrder.equals(SortOrder.ASCENDING) ? com.github.adminfaces.starter.infra.model.SortOrder.ASCENDING
                            : sortOrder.equals(SortOrder.DESCENDING) ? com.github.adminfaces.starter.infra.model.SortOrder.DESCENDING
                            : com.github.adminfaces.starter.infra.model.SortOrder.UNSORTED;
                }
                filter.setFirst(first).setPageSize(pageSize)
                        .setSortField(sortField).setSortOrder(order)
                        .setParams(filters);
                List<RequestCredit> list = requestService.paginate(filter);
                setRowCount((int) requestService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public RequestCredit getRowData(String key) {
                return requestService.findById_request(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<RequestCredit>(new RequestCredit());
    }

    /*public List<String> completeModel(String query) {
        List<String> result = carService.getModels(query);
        return result;
    }*/

    public void findCarById(Long id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedCars.add(requestService.findById_request(id));
    }

    public void delete() {
        int numCars = 0;
        for (RequestCredit selectedCar : selectedCars) {
            numCars++;
            requestService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<RequestCredit> getSelectedCars() {
        return selectedCars;
    }

    public List<RequestCredit> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<RequestCredit> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<RequestCredit> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<RequestCredit> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<RequestCredit> cars) {
        this.cars = cars;
    }

    public Filter<RequestCredit> getFilter() {
        return filter;
    }

    public void setFilter(Filter<RequestCredit> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
