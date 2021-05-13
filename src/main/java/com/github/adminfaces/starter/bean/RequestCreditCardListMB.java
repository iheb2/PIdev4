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
import com.github.adminfaces.starter.model.RequestCreditCard;
import com.github.adminfaces.starter.service.RequestCreditCardService;
import com.github.adminfaces.template.exception.BusinessException;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class RequestCreditCardListMB implements Serializable {

    @Inject
    RequestCreditCardService requestService;

    Long id;

    LazyDataModel<RequestCreditCard> cars;

    Filter<RequestCreditCard> filter = new Filter<>(new RequestCreditCard());

    List<RequestCreditCard> selectedCars; //cars selected in checkbox column

    List<RequestCreditCard> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<RequestCreditCard>() {
            @Override
            public List<RequestCreditCard> load(int first, int pageSize,
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
                List<RequestCreditCard> list = requestService.paginate(filter);
                setRowCount((int) requestService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public RequestCreditCard getRowData(String key) {
                return requestService.findById_request(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<RequestCreditCard>(new RequestCreditCard());
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
        for (RequestCreditCard selectedCar : selectedCars) {
            numCars++;
            requestService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<RequestCreditCard> getSelectedCars() {
        return selectedCars;
    }

    public List<RequestCreditCard> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<RequestCreditCard> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<RequestCreditCard> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<RequestCreditCard> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<RequestCreditCard> cars) {
        this.cars = cars;
    }

    public Filter<RequestCreditCard> getFilter() {
        return filter;
    }

    public void setFilter(Filter<RequestCreditCard> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
