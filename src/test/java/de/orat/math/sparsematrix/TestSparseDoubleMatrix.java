package de.orat.math.sparsematrix;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class TestSparseDoubleMatrix {
    
    public TestSparseDoubleMatrix() {
    }

    public boolean equals(int[] a, int[] b){
        if (a.length != b.length) return false;
        for (int i=0;i<a.length;i++){
            if (a[i] != b[i]) return false;
        }
        return true;
    }
    public boolean equals(double[][] a, double[][] b){
        if (a.length != b.length) return false;
        if (a[0].length != b[0].length) return false;
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a[0].length;j++){
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
    
    public boolean equals(String[][] a, String[][] b){
        if (a.length != b.length) return false;
        if (a[0].length != b[0].length) return false;
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a[0].length;j++){
                if (!a[i][j].equals(b[i][j])) return false;
            }
        }
        return true;
    }
    
    @org.junit.jupiter.api.Test
    public void testSparsity() {
        
        // test SparseDoubleMatrix
        
        double[][] a = new double[][]{{7,0,0,  9},
                                      {3,1,0.1,0},
                                      {0,0,4,  0}};
        //System.out.println(SparseDoubleMatrix.toString(a));
        SparseDoubleMatrix sm = new SparseDoubleMatrix(a);
        MatrixSparsity sparsity = sm.getSparsity();
        int[] colind = new int[]{0,2,3,5,6};
        int[] row = new int[]{0,1,1,1,2,0};
        
        assertTrue(equals(sparsity.getcolind(), colind));
        assertTrue(equals(sparsity.getrow(), row));
        
        double[][] b = sm.toMatrix();
        assertTrue(equals(a, b));
       
        
        // isNonZero()
        // vergleich mit dem Inhalt der Matrix a und assertTrue falls Fehlschlag
        //TODO
        for (int r = 0; r < a.length; r++){
            for (int c = 0;c < a[0].length;c++){
                 boolean result = sparsity.isNonZero(r, c);
                 //TODO
                 if (result) {
                     System.out.print("*");
                 } else {
                     System.out.print("_");
                 }
            }
            System.out.println();
        }
       
        
        
        // test SparseStringMatrix
        
        String[][] test = new String[][]{{"1","2","3","4"},
                                         {"5","6","7","8"},
                                         {"9","10","11","12"}};
        
        SparseStringMatrix ssm = new SparseStringMatrix(test);
        String[][] sb = ssm.toArr();
        assertTrue(equals(test, sb));
    }
    
    @org.junit.jupiter.api.Test
    public void testSparsityIsNonZero(){
        System.out.println("----------------------------------------------------- test sparsity.isNonZero()-------");
        double[] values = new double[]{1,0,3,4,0,0,6};
        ColumnVectorSparsity sparsity = new ColumnVectorSparsity(values);
        System.out.println(sparsity.toString());
        for (int i=0;i<values.length;i++){
            if (sparsity.isNonZero(i, 0)){
                    System.out.print("*");
            } else {
                System.out.print("_");
            }
        }
    }
    
    @Test
    public void testSparseDoubleColumnVectorSummation(){
        System.out.println("----------------------------------------------------- test sparse double column vector summation -------");
        
        ColumnVectorSparsity sparsity_a = new ColumnVectorSparsity(7, new int[]{1,2,3});
        SparseDoubleColumnVector a = new SparseDoubleColumnVector(sparsity_a, new double[]{1,2,3});
        System.out.println(a);
        ColumnVectorSparsity sparsity_b = new ColumnVectorSparsity(7, new int[]{3,5,6});
        SparseDoubleColumnVector b = new SparseDoubleColumnVector(sparsity_b, new double[]{4,5,6});
        System.out.println(b);
        SparseDoubleColumnVector result = a.add(b);
        // 0, 1, 2, 7, 0, 5, 6, 0
        // 7x1,4nz
        // colind:  [0,4]
        // row:     [0,2,3,6]
        System.out.println(result);
    }
    
    @Test
    public void testDiagonalMatrixSparsityConcstructor(){
        System.out.println("----------------------------------------------------- test sparse diagonal matrix creation -------");
       
        double[] values = new double[]{1,2,3,4,5};
        MatrixSparsity sparsity = MatrixSparsity.diagonal(values);
        System.out.println(sparsity.toString());
    } 
}
