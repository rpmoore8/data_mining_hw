// The Homework3 class that contains methods to be implemented in HW3
// Your name here

public class Homework3
{
    // This method takes two int arrays (as number vectors) and returns the
    // Euclidean distance between the two vectors.
    // Do not make any changes to this method!
    public double euclidean(double[] A, double[] B)
    {
        int n = A.length;
        double dist = 0;

        for (int i = 0; i < n; i++)
            dist += (A[i] - B[i]) * (A[i] - B[i]);

        return Math.sqrt(dist);
    }

    // This method takes as parameter an integer for the number of clusters, a
    // 2D double array for the cluster centroids, and a 2D double array for the
    // data set that contains test samples. It returns a 1D int array that contains
    // the cluster indices for all samples.
    public int[] assignSamples(int k, double[][] centroids, double[][] dataset)
    {
        int[] assignments = new int[dataset.length];
        
        for(int i = 0; i < dataset.length; i++) {
            double length;
            double shortest = Double.MAX_VALUE;
            for(int j = 0; j < k; j++) {
                length = euclidean(centroids[j], dataset[i]);
                if(length < shortest) {
                    shortest = length;
                    assignments[i] = j;
                }
            }
        }
        return assignments;
    }

    // This method takes as parameter an integer for the number of clusters, a 1D
    // int array that contains the cluster indices for all samples, and a 2D
    // double array for the data set that contains test samples. It returns a
    // 2D double array that contains the updated cluster centroids.
    public double[][] updateCentroids(int k, int[] clusters, double[][] dataset)
    {
        double[][] centroids = new double[k][dataset[0].length];
        int[] totalSamples = new int[k];
        double[][] sums = new double[k][dataset[0].length];
        
        for(int i = 0; i < dataset.length; i++) {
            totalSamples[clusters[i]]++;
            
            for(int j = 0; j < dataset[0].length; j++) {
                sums[clusters[i]][j] += dataset[i][j];
            }
        }
        
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < dataset[0].length; j++) {
                centroids[i][j] = sums[i][j]/totalSamples[i];
            }
        }

        return centroids;
    }

    // This method takes as parameter an int array that contains the cluster indicies
    // for all samples, a 2D double array for the cluster centroids, and a 2D double
    // array for the data set that contains test samples. It returns the sum of squared
    // error (SSE) for the clusters.
    public double calculateSSE(int[] clusters, double[][] centroids, double[][] dataset)
    {
        double SSE = 0.0;
        
        for(int i = 0; i < dataset.length; i++) {
            
            for(int j = 0; j < dataset[0].length; j++) {
                SSE += (centroids[clusters[i]][j] - dataset[i][j]) * (centroids[clusters[i]][j] - dataset[i][j]);
            }
        }

        return SSE;
    }

    // This method implements the k-means clustering algorithm. It takes an integer
    // for the number of clusters, a 2D double array for the cluster centroids, and
    // a 2D double array for the data set that contains test samples. It returns the
    // final SSE after the clustering process converges.
    // NOTE: This method should leverage the above methods in order to implement
    // the algorithm. It should NOT re-implement any functionalities that have been
    // implemented in the above methods.
    public double kMeans(int k, double[][] centroids, double[][] dataset)
    {
        double SSE = Double.MAX_VALUE;
        int[] clusters = assignSamples(k, centroids, dataset);
        Boolean converged = false;
        
        while(!converged) {
            centroids = updateCentroids(k, clusters, dataset);
            clusters = assignSamples(k, centroids, dataset);
            double tempSSE = calculateSSE(clusters, centroids, dataset);
            if(tempSSE < SSE) {
                SSE = tempSSE;
            }
            else {
                converged = true;
            } 
        }

        return SSE;
    }
}
