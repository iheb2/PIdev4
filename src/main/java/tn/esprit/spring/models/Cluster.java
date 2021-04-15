package tn.esprit.spring.models;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cluster {

    private DataPoint centroid;
    private final Set<DataPoint> dataPoints;

    public Cluster(DataPoint centroid, Set<DataPoint> dataPoints) {
        this.centroid = centroid;
        this.dataPoints = dataPoints;
    }

    public Cluster(DataPoint centroid) {
        this.centroid = centroid;
        this.dataPoints = new HashSet<>();
    }

    public DataPoint getCentroid() {
        return centroid;
    }

    public void setCentroid(DataPoint centroid) {
        this.centroid = centroid;
    }

    public Set<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void addDataPointToCluster(DataPoint point) {
        dataPoints.add(point);
    }

    public void recalculateCentroid(int componentNumber) {

        List<Double> identityComponents = new ArrayList<>();

        for (int i = 0; i < componentNumber; i++) {
            identityComponents.add(0.0);
        }

        DataPoint identity = new DataPoint(identityComponents);

        DataPoint p = dataPoints.parallelStream().reduce(identity, DataPoint::add);
        DataPoint newCentroid = p.divideComponentsBy(dataPoints.size());
        setCentroid(newCentroid);
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "centroid=" + centroid +
                ", dataPoints=" + dataPoints +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cluster)) {
            return false;
        }
        Cluster cluster = (Cluster) o;

        return dataPoints.equals(cluster.getDataPoints());
    }

    @Override
    public int hashCode() {
        int result = centroid.hashCode();
        result = 31 * result + dataPoints.hashCode();
        return result;
    }
}
