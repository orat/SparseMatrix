package de.orat.math.sparsematrix;

/**
 * https://introcs.cs.princeton.edu/java/44st/SparseMatrix.java.html
 * 
 * https://spark.apache.org/docs/1.4.0/api/java/org/apache/spark/mllib/linalg/SparseMatrix.html
 * 
 * https://www.javatpoint.com/sparse-matrix
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseStringMatrix implements iStringMatrix {
    
    /**
     * 
     * @param m alle "0"-Komponenten als sparse werten
     */
    public SparseStringMatrix(String[][] m){
    
    }

    @Override
    public String[][] copyMatrix() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
