// The Homework1 class that contains methods to be implemented in HW1
// Your name here

public class Homework1 
{
    // This method takes two 2-D int arrays (as int matrices) and returns the 
    // product of the two matrices in a third matrix.
    public int[][] matrixMultiplication(int[][] A, int[][] B)
    {
    	// Assumes matrices have correct dimensions. Otherwise, check here.


    	// Product matrix to be calculated, initialized as all zeros.
    	int[][] product = new int[A.length][B[0].length];

        for(int row_A = 0; row_A < A.length; row_A++) {
            for(int col_B = 0; col_B < B[0].length; col_B++) {
        	for(int col_A = 0; col_A < A[0].length; col_A++) {
                    product[row_A][col_B] += A[row_A][col_A] * B[col_A][col_B];
                }
            }
        }
        
        return product;
    }
    
    // This method takes two int arrays (as binary vectors) and returns the 
    // Jaccard coefficient between the two vectors.
    public double jaccard(int[] A, int[] B)
    {
        int isect = 0;
        int union = 0;
        
        for(int i = 0; i < A.length; i ++) {
            if(A[i] == 0) {
                if(B[i] != 0) {
                    union++;
                }
            }
            else {
                if(B[i] == 0) {
                    union++;
                }
                else {
                    isect++;
                }
            }
        }      
        union += isect;
        return (double)isect/union;
    }
    
    // This method takes two int arrays (as number vectors) and returns the 
    // angle (in degrees) between the two vectors using Cosine similarity.
    public double cosineSimilarity(int[] A, int[] B)
    {
        int dotProd = 0;
        double magA = 0;
        double magB = 0;
        
        for(int i = 0; i < A.length; i++) {
            dotProd += A[i] * B[i];
            magA += A[i] * A[i];
            magB += B[i] * B[i];
        }
        magA = Math.sqrt(magA);
        magB = Math.sqrt(magB);
        
        return Math.toDegrees(Math.acos(dotProd / (magA * magB)));
    }
    
    // This method takes two int arrays (as number vectors) and returns the 
    // Euclidean distance between the two vectors.
    public double euclidean(int[] A, int[] B)
    {
        double distance = 0;
        for(int i = 0; i < A.length; i++) {
            distance += Math.pow(A[i] - B[i], 2);
        }
        return Math.sqrt(distance);
    }    
}
