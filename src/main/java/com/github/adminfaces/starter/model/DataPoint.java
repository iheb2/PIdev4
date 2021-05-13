package com.github.adminfaces.starter.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataPoint {

    private final List<Double> components;

    public DataPoint(List<Double> components) {
        this.components = components;
    }

    public DataPoint(Double... components) {
        this.components = new ArrayList<>(Arrays.asList(components));
    }

    public List<Double> getComponents() {
        return components;
    }

    public DataPoint() {
		super();

    	components=null;
	}

	public DataPoint add(DataPoint other) {
        List<Double> comps = new ArrayList<>();

        for (int i = 0; i < components.size(); i++) {
            comps.add(components.get(i) + other.components.get(i));
        }

        return new DataPoint(comps);
    }

    public DataPoint divideComponentsBy(double n) {
        List<Double> comps = new ArrayList<>();

        for (int i = 0; i < components.size(); i++) {
            comps.add(i, components.get(i) / n);
        }

        return new DataPoint(comps);
    }

    public double distanceTo(DataPoint other) {

        double sum = 0;

        for (int i = 0; i < components.size(); i++) {
            sum += Math.pow(components.get(i) - other.components.get(i), 2.0);
        }

        return Math.sqrt(sum);
    }

    public DataPoint copy() {
        List<Double> comps = new ArrayList<>();

        for (int i = 0; i < components.size(); i++) {
            comps.add(i, components.get(i));
        }

        return new DataPoint(comps);
    }

    @Override
    public String toString() {
        //return "DataPoint{"  + components +'}';
    	return ""+components+"" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPoint dataPoint = (DataPoint) o;

        return components.equals(dataPoint.components);
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }
}
