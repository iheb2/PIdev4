package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.model.StateT;
import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.repository.TransactionRepository;
import com.github.adminfaces.starter.service.CarService;
import com.github.adminfaces.starter.service.TransactionService1;
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
public class TransactionListMB1 implements Serializable {

    @Inject
    TransactionService1 carService;
    @Inject
	TransactionRepository rep;
    Long id;

    LazyDataModel<Transaction> cars;

    Filter<Transaction> filter = new Filter<>(new Transaction());

    List<Transaction> selectedCars; //cars selected in checkbox column

    List<Transaction> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<Transaction>() {
            @Override
            public List<Transaction> load(int first, int pageSize,
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
                List<Transaction> list = carService.paginate(filter);
                setRowCount((int) carService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public Transaction getRowData(String key) {
                return carService.findById(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<Transaction>(new Transaction());
    }

    public List<StateT> completeModel(String query) {
        List<StateT> result = carService.getModels(query);
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
        for (Transaction selectedCar : selectedCars) {
            numCars++;
            carService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<Transaction> getSelectedCars() {
        return selectedCars;
    }

    public List<Transaction> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Transaction> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<Transaction> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<Transaction> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<Transaction> cars) {
        this.cars = cars;
    }

    public Filter<Transaction> getFilter() {
        return filter;
    }

    public void setFilter(Filter<Transaction> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
