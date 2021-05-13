package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;

import com.github.adminfaces.starter.model.Report;
import com.github.adminfaces.starter.service.ReportService;
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
public class ReportListMB implements Serializable {

    @Inject
    ReportService carService;

    Long id;

    LazyDataModel<Report> cars;

    Filter<Report> filter = new Filter<>(new Report());

    List<Report> selectedCars; //cars selected in checkbox column

    List<Report> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<Report>() {
            @Override
            public List<Report> load(int first, int pageSize,
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
                List<Report> list = carService.paginate(filter);
                setRowCount((int) carService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public Report getRowData(String key) {
                return carService.findById(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<Report>(new Report());
    }

    public List<String> completeModel(String query) {
        List<String> result = carService.getModels(query);
        return result;
    }

    public void findCarById(Long id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedCars.add(carService.findById(id));
    }

    public void delete() {
        int numCars = 0;
        for (Report selectedCar : selectedCars) {
            numCars++;
            carService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<Report> getSelectedCars() {
        return selectedCars;
    }

    public List<Report> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Report> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<Report> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<Report> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<Report> cars) {
        this.cars = cars;
    }

    public Filter<Report> getFilter() {
        return filter;
    }

    public void setFilter(Filter<Report> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
