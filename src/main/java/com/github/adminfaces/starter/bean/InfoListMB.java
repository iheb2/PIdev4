package com.github.adminfaces.starter.bean;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.model.Information;
import com.github.adminfaces.starter.repository.InfoRepository;
import com.github.adminfaces.starter.service.InfoService;
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

@Named
@ViewScoped
public class InfoListMB implements Serializable {

	@Inject
	InfoService carService;
	@Inject
	InfoRepository rep;
	Long id;

	LazyDataModel<Information> allCars;

	Filter<Information> filter = new Filter<>(new Information());

	List<Information> selectedCars; // cars selected in checkbox column

	List<Information> filteredValue;// datatable filteredValue attribute (column
								// filters)
	List<Information> infosss;

	public List<Information> getInfosss() {

		infosss = carService.getAllCars();
		return infosss;
	}

	public void setInfosss(List<Information> infosss) {
		this.infosss = infosss;
	}
		
		
	@PostConstruct
	public void initDataModel() {
		allCars = new LazyDataModel<Information>() {
			@Override
			public List<Information> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				com.github.adminfaces.starter.infra.model.SortOrder order = null;
				if (sortOrder != null) {
					order = sortOrder.equals(SortOrder.ASCENDING)
							? com.github.adminfaces.starter.infra.model.SortOrder.ASCENDING
							: sortOrder.equals(SortOrder.DESCENDING)
									? com.github.adminfaces.starter.infra.model.SortOrder.DESCENDING
									: com.github.adminfaces.starter.infra.model.SortOrder.UNSORTED;
				}
				filter.setFirst(first).setPageSize(pageSize).setSortField(sortField).setSortOrder(order)
						.setParams(filters);
				List<Information> list = carService.paginate(filter);
				setRowCount((int) carService.count(filter));
				return list;
			}

			@Override
			public int getRowCount() {
				return super.getRowCount();
			}

			@Override
			public Information getRowData(String key) {
				return carService.findById(new Long(key));
			}
		};
	}
	public void clear() {
		filter = new Filter<Information>(new Information());
	}

	public List<String> completeModel(String query) {
		List<String> result = carService.getModels(query);
		return result;
	}

	public void findCarById(Long id) {
		if (id == null) {
			throw new BusinessException("Provide Information ID to load");
		}
		selectedCars.add(carService.findById(id));
	}

	public void delete() {
		int numCars = 0;
		for (Information selectedCar : selectedCars) {
			numCars++;
			carService.remove(selectedCar);
		}
		selectedCars.clear();
		addDetailMessage(numCars + " cars deleted successfully!");
	}

	public List<Information> getSelectedCars() {
		return selectedCars;
	}

	public List<Information> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<Information> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public void setSelectedCars(List<Information> selectedCars) {
		this.selectedCars = selectedCars;
	}

	public LazyDataModel<Information> getAllCars() {
		return allCars;
	}

	public void setAllCars(LazyDataModel<Information> cars) {
		this.allCars = cars;
	}

	public Filter<Information> getFilter() {
		return filter;
	}

	public void setFilter(Filter<Information> filter) {
		this.filter = filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
