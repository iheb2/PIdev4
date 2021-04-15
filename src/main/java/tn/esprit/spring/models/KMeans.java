package tn.esprit.spring.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.services.Initialiser;

public class KMeans {

	public final int k;
	public final List<Cluster> clusters;
	public final List<DataPoint> points;

	public KMeans(int k, List<DataPoint> points, Initialiser initialiser) {

		if (k < 2) {
			throw new IllegalArgumentException("K should be >= 2. Actual value: " + k);
		}

		this.k = k;
		this.clusters = new ArrayList<>();
		this.points = points;

		List<DataPoint> initialCentroids = createInitialCentroids(initialiser);

		for (int i = 0; i < k; i++) {
			Cluster c = new Cluster(initialCentroids.get(i));
			clusters.add(c);
		}

		assignObservationsToCluster();

		boolean hasNotConverged = true;

		while (hasNotConverged) {

			List<Set<DataPoint>> datapointsPerClusterBefore = getCopyOfDatapointsPerCluster();

			recalculateCentroids();
			assignObservationsToCluster();

			List<Set<DataPoint>> datapointsPerClusterAfter = getCopyOfDatapointsPerCluster();

			if (datapointsPerClusterBefore.equals(datapointsPerClusterAfter)) {
				hasNotConverged = false;
			}

		}
	}

	public List<Set<DataPoint>> getCopyOfDatapointsPerCluster() {

		List<Set<DataPoint>> result = new ArrayList<>();

		for (int i = 0; i < clusters.size(); i++) {
			Set<DataPoint> dataPoints = new HashSet<>();
			for (DataPoint p : clusters.get(i).getDataPoints()) {
				dataPoints.add(p.copy());
			}
			result.add(dataPoints);
		}

		return result;
	}

	public void recalculateCentroids() {
		for (Cluster c : clusters) {
			c.recalculateCentroid(points.get(0).getComponents().size());
		}
	}

	public void assignObservationsToCluster() {

		for (Cluster c : clusters) {
			c.getDataPoints().clear();
		}

		for (int i = 0; i < points.size(); i++) {
			assignObservationToCluster(points.get(i));
		}

	}

	public void assignObservationToCluster(DataPoint point) {

		Cluster c = clusters.get(0);
		double distance = point.distanceTo(c.getCentroid());

		for (int j = 0; j < clusters.size(); j++) {
			double newDistance = point.distanceTo(clusters.get(j).getCentroid());
			if (newDistance < distance) {
				distance = newDistance;
				c = clusters.get(j);
			}
		}

		c.addDataPointToCluster(point);
	}

	public List<DataPoint> createInitialCentroids(Initialiser initialiser) {
		return initialiser.createInitialCentroids(k, points);
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public int getK() {
		return k;
	}

	public List<DataPoint> getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return clusters.toString();
	}
}
