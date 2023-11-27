package de.orat.math.sparsematrix;

import java.util.List;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseStringColumnVector {
    
    private ColumnVectorSparsity sparsity;
    private List<String> data;
    
    public SparseStringColumnVector(StringVector vector){
        //TODO
    }
    
    public SparseStringColumnVector(ColumnVectorSparsity sparsity, StringVector vector){
        this.sparsity = sparsity;
        //TODO
    }
    
    public boolean contains(String value){
        return data.contains(value);
    }
}
