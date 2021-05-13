package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;


import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.RequestCheckbook;
import com.github.adminfaces.starter.service.RequestCheckBookService;
import com.github.adminfaces.starter.service.RequestService;
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
public class RequestChequeBookListMB implements Serializable {

    @Inject
    RequestCheckBookService requestService;

    Long id;

    LazyDataModel<RequestCheckbook> cars;

    Filter<RequestCheckbook> filter = new Filter<>(new RequestCheckbook());

    List<RequestCheckbook> selectedCars; //cars selected in checkbox column

    List<RequestCheckbook> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<RequestCheckbook>() {
            @Override
            public List<RequestCheckbook> load(int first, int pageSize,
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
                List<RequestCheckbook> list = requestService.paginate(filter);
                setRowCount((int) requestService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public RequestCheckbook getRowData(String key) {
                return requestService.findById_request(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<RequestCheckbook>(new RequestCheckbook());
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
        for (RequestCheckbook selectedCar : selectedCars) {
            numCars++;
            requestService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<RequestCheckbook> getSelectedCars() {
        return selectedCars;
    }

    public List<RequestCheckbook> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<RequestCheckbook> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<RequestCheckbook> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<RequestCheckbook> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<RequestCheckbook> cars) {
        this.cars = cars;
    }

    public Filter<RequestCheckbook> getFilter() {
        return filter;
    }

    public void setFilter(Filter<RequestCheckbook> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
