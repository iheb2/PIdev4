package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.ExportPDF.ExportPdf;
import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.service.AgencyService;
import com.github.adminfaces.starter.service.IAgencyService;
import com.github.adminfaces.template.exception.BusinessException;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


import static com.github.adminfaces.starter.util.Utils.addDetailMessage;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class AgencyListMB implements Serializable {

    @Inject
    AgencyService carService;
    IAgencyService agencyservice;

    Long id;

    LazyDataModel<Agency> cars;

    Filter<Agency> filter = new Filter<>(new Agency());

    List<Agency> selectedCars; //cars selected in checkbox column

    List<Agency> filteredValue;// datatable filteredValue attribute (column filters)
    
    
    

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<Agency>() {
            @Override
            public List<Agency> load(int first, int pageSize,
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
                List<Agency> list = carService.paginate(filter);
                setRowCount((int) carService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public Agency getRowData(String key) {
                return carService.findById_agency(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<Agency>(new Agency());
    }

    public List<String> completeModel(String query) {
        List<String> result = carService.getModels(query);
        return result;
    }

    public void findCarById(Long id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedCars.add(carService.findById_agency(id));
    }

    public void delete() {
        int numCars = 0;
        for (Agency selectedCar : selectedCars) {
            numCars++;
            carService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<Agency> getSelectedCars() {
        return selectedCars;
    }

    public List<Agency> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Agency> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<Agency> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<Agency> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<Agency> cars) {
        this.cars = cars;
    }

    public Filter<Agency> getFilter() {
        return filter;
    }

    public void setFilter(Filter<Agency> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
