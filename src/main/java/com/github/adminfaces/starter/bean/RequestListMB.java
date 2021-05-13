package com.github.adminfaces.starter.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.adminfaces.starter.infra.model.Filter;


import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.repository.RequestRepository;
import com.github.adminfaces.starter.service.IRequestService;
import com.github.adminfaces.starter.service.RequestService;
import com.github.adminfaces.template.exception.BusinessException;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartOptions;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.adminfaces.starter.util.Utils.addDetailMessage;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class RequestListMB implements Serializable {

    @Inject
    RequestService requestService;
    IRequestService requestC;
    Request req;

    Long id;

    LazyDataModel<Request> cars;

    Filter<Request> filter = new Filter<>(new Request());

    List<Request> selectedCars; //cars selected in checkbox column

    List<Request> filteredValue;// datatable filteredValue attribute (column filters)

    @PostConstruct
    public void initDataModel() {
        cars = new LazyDataModel<Request>() {
            @Override
            public List<Request> load(int first, int pageSize,
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
                List<Request> list = requestService.paginate(filter);
                setRowCount((int) requestService.count(filter));
                return list;
            }

            @Override
            public int getRowCount() {
                return super.getRowCount();
            }

            @Override
            public Request getRowData(String key) {
                return requestService.findById_request(new Long(key));
            }
        };
    }

    public void clear() {
        filter = new Filter<Request>(new Request());
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
        for (Request selectedCar : selectedCars) {
            numCars++;
            requestService.remove(selectedCar);
        }
        selectedCars.clear();
        addDetailMessage(numCars + " cars deleted successfully!");
    }

    public List<Request> getSelectedCars() {
        return selectedCars;
    }

    public List<Request> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Request> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public void setSelectedCars(List<Request> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public LazyDataModel<Request> getCars() {
        return cars;
    }

    public void setCars(LazyDataModel<Request> cars) {
        this.cars = cars;
    }

    public Filter<Request> getFilter() {
        return filter;
    }

    public void setFilter(Filter<Request> filter) {
        this.filter = filter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	private PieChartModel pieModel;
    
    public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}


	 
	public PieChartModel createPieModel(float projectcost) {
	        pieModel = new PieChartModel();
	          
	    	
	        Map<Double, Double> MA = new HashMap<Double, Double>(); 
	    	MA =requestC.StatisticCreatedPerMonth(2021); 
		    ChartData data = new ChartData();

	        PieChartDataSet dataSet = new PieChartDataSet();
	        List<Number> values = new ArrayList<>();
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	        values.add(1);
	      
	        for (Map.Entry mapentry : MA.entrySet()) {
	           /* System.out.println("clÃ©: "+mapentry.getKey() 
	                               + " | valeur: " + mapentry.getValue());*/
	        	
	        	if((double)mapentry.getKey()==1) {
	        		values.remove(0);
	        		values.add(0, (Number)mapentry.getValue());
	        		
	        	}
	        	else if((double)mapentry.getKey()==2) {
	        		values.remove(1);
	        		values.add(1, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==3) {
	        		values.remove(2);
	        		values.add(2, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==4) {
	        		values.remove(3);
	        		values.add(3, (Number)mapentry.getValue());
	        		}
	        	else if((double)mapentry.getKey()==5) {
	        		values.remove(4);
	        		values.add(4, (Number)mapentry.getValue());
	        	//	values.add((Number) mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==6) {
	        		values.remove(5);
	        		values.add(5, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==7) {
	        		values.remove(6);
	        		values.add(6, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==8) {
	        		values.remove(7);
	        		values.add(7, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==9) {
	        		values.remove(8);
	        		values.add(8, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==10) {
	        		values.remove(9);
	        		values.add(9, (Number)mapentry.getValue());
	        	}
	        	else if((double)mapentry.getKey()==11) {
	        		values.remove(10);
	        		values.add(10, (Number)mapentry.getValue());
	        		}
	        	else if((double)mapentry.getKey()==12) {
	        		values.remove(11);
	        		values.add(11, (Number)mapentry.getValue());
	        	}
	        	
	         }
 
	       

	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(255, 99, 132, 0.2)");
	        bgColor.add("rgba(255, 159, 64, 0.2)");
	        bgColor.add("rgba(255, 205, 86, 0.2)");
	        bgColor.add("rgba(75, 192, 192, 0.2)");
	        bgColor.add("rgba(54, 162, 235, 0.2)");
	        bgColor.add("rgba(153, 102, 255, 0.2)");
	        bgColor.add("rgba(201, 203, 207, 0.2)");

	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(201, 203, 207)");

	        data.addChartDataSet(dataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("January");
	        labels.add("February");
	        labels.add("March");
	        labels.add("April");
	        labels.add("May");
	        labels.add("June");
	        labels.add("July");
	        labels.add("August");
	        labels.add("September");
	        labels.add("October");
	        labels.add("November");
	        labels.add("December");
	        data.setLabels(labels);
	        
	        //Options
	        PieChartOptions options = new PieChartOptions();
	        

	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Statistic created Requests by month for this year ");
	        options.setTitle(title);

	        pieModel.setData((Map<String, Number>) data);
	        
	        return pieModel;
	    }
}
