package com.github.adminfaces.starter.model;

import java.util.List;

public class InitialiserUtils {

	private InitialiserUtils() {
	}

	public static double getMinimumDistanceSquared(DataPoint point, List<DataPoint> centroidsAvailable) {

		double distance = Double.MAX_VALUE;

		for (int i = 0; i < centroidsAvailable.size(); i++) {

			double newDistance = point.distanceTo(centroidsAvailable.get(i));
			if (newDistance < distance) {
				distance = newDistance;
			}
		}

		return Math.pow(distance, 2.0);
	}

	public static int getIndexOfMinimumDistanceSquared(DataPoint point, List<DataPoint> centroidsAvailable) {

		double distance = Double.MAX_VALUE;
		int index = -1;

		for (int i = 0; i < centroidsAvailable.size(); i++) {

			double newDistance = point.distanceTo(centroidsAvailable.get(i));
			if (newDistance < distance) {
				distance = newDistance;
				index = i;
			}
		}

		return index;
	}

	public static double getProbability(DataPoint point, List<DataPoint> centroidsAvailable, double cost) {

		double distance = getMinimumDistanceSquared(point, centroidsAvailable);

		return distance / cost;
	}

	public static double getWeightProbability(int index, int[] weightVector, double totalCost) {
		return weightVector[index] / totalCost;
	}

	public static int sum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
}
