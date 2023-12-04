package de.orat.math.sparsematrix;

import java.util.List;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class SparseStringColumnVector extends SparseStringVector {
    
    private ColumnVectorSparsity sparsity;
    
    public SparseStringColumnVector(ColumnVectorSparsity sparsity, String[] data){
        this.sparsity = sparsity;
        this.data = data;
    }
    
    // ungetested
    public SparseStringColumnVector(String[] m){
        int nonZeros = 0;
        for (int i=0;i<m.length;i++){
            if (!m[i].equals("0")){
                nonZeros++;
            }
        }
        int[] row = new int[nonZeros];
        data = new String[nonZeros];
        int j=0;
        for (int i=0;i<m.length;i++){
            if (!m[i].equals("0")){
                data[j] = m[i];
                row[j] = j++;
            }
        }
        sparsity = new ColumnVectorSparsity(m.length, /*nonZeros,*/ row);
    }
    
    
    public SparseStringColumnVector(StringVector vector){
        //TODO
    }
    
    public SparseStringColumnVector(ColumnVectorSparsity sparsity, StringVector vector){
        this.sparsity = sparsity;
        //TODO
    }
    
    public boolean contains(String value){
        throw new RuntimeException("not yet implemented");
        //return data.contains(value);
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
