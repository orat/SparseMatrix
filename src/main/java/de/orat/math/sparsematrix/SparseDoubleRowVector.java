package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseDoubleRowVector extends SparseDoubleVector {

    protected RowVectorSparsity sparsity;
    
    public SparseDoubleRowVector(RowVectorSparsity sparsity, double[] data){
        this.sparsity = sparsity;
        this.data = data;
    }
    @Override
    public double[][] toMatrix() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SparseDoubleColumnVector transpose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
