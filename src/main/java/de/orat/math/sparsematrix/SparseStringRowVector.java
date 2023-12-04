package de.orat.math.sparsematrix;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseStringRowVector extends SparseStringVector {

    protected RowVectorSparsity sparsity;
    
    public SparseStringRowVector(RowVectorSparsity sparsity, String[] data){
        this.sparsity = sparsity;
        this.data = data;
    }
    
    @Override
    public String[][] toArr() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public iStringMatrix transpose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
