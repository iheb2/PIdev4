package com.github.adminfaces.starter.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomInitialiser implements Initialiser {

	@Override
	public List<DataPoint> createInitialCentroids(int k, List<DataPoint> points) {
		Random generator = new Random();
		List<DataPoint> list = new ArrayList<>();

		while (list.size() < k) {
			int i = generator.nextInt(points.size());
			list.add(points.get(i));
		}

		return list;
	}
}
