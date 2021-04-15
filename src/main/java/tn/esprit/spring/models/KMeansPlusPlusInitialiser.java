package tn.esprit.spring.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tn.esprit.spring.services.Initialiser;

public class KMeansPlusPlusInitialiser implements Initialiser {

	@Override
	public List<DataPoint> createInitialCentroids(int k, List<DataPoint> points) {

		List<DataPoint> centroids = new ArrayList<>();

		Random generator = new Random();
		int randomInt = generator.nextInt(points.size());
		DataPoint point = points.get(randomInt);
		centroids.add(point);

		while (centroids.size() < k) {

			double random = Math.random();
			double cumulativeProbability = 0.0;

			double cost = points.parallelStream().map(p -> InitialiserUtils.getMinimumDistanceSquared(p, centroids))
					.reduce(0.0, (a, b) -> a + b);

			for (int i = 0; i < points.size(); i++) {

				double probability = InitialiserUtils.getProbability(points.get(i), centroids, cost);

				cumulativeProbability += probability;

				if (random <= cumulativeProbability) {
					centroids.add(points.get(i));
					break;
				}
			}

		}

		return centroids;
	}

}
