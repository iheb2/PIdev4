package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;



import com.github.adminfaces.starter.model.Dab;
import com.github.adminfaces.starter.service.DabService;
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
public class DabListMB implements Serializable {

    @Inject
    DabService carService;

    Long id;

    LazyDataModel<Dab> cars;

    Filter<Dab> filter = new Filter<>(new Dab());

    List<Dab> selectedCars; //cars selected in checkbox column

    List<Dab> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<Dab>() {
            @Override
            public List<Dab> load(int first, int pageSize,
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
                List<Dab> list = carService.paginate(filter);
                setRowCount((int) carService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public Dab getRowData(String key) {
                return carService.findById_dab(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<Dab>(new Dab());
    }

    /*public List<String> completeModel(String query) {
        List<String> result = carService.getModels(query);
        return result;
    }*/

    public void findCarById_dab(Long id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedCars.add(carService.findById_dab(id));
    }

    public void delete() {
        int numCars = 0;
        for (Dab selectedCar : selectedCars) {
            numCars++;
            carService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<Dab> getSelectedCars() {
        return selectedCars;
    }

    public List<Dab> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Dab> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<Dab> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<Dab> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<Dab> cars) {
        this.cars = cars;
    }

    public Filter<Dab> getFilter() {
        return filter;
    }

    public void setFilter(Filter<Dab> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
