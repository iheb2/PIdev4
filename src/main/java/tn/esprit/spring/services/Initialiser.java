package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.models.DataPoint;

public interface Initialiser {

	List<DataPoint> createInitialCentroids(int k, List<DataPoint> points);

}
