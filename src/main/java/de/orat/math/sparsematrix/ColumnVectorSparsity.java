package de.orat.math.sparsematrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MatrixSparsity of a column vector.
 * 
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class ColumnVectorSparsity extends MatrixSparsity {
    
    public ColumnVectorSparsity(int n_row, int[] row){
        super(n_row, 1, new int[]{0,row.length}, row);
    }
    /*public ColumnVectorSparsity(int n_row, int nonZeros, int[] row){
        super(n_row, 1, new int[]{nonZeros}, row);
    }*/
    /**
    * @param vec only the non-zero values define the sparsity
     */
    public ColumnVectorSparsity(double[] vec){
        super(create2dim(vec));
    }
    private static double[][] create2dim(double[] vec){
        double[][] result = new double[vec.length][1];
        for (int i=0;i<vec.length;i++){
            result[i][0] = vec[i];
        }
        return result;
    }
    
    public RowVectorSparsity transpose(){
        // Beginn einer effizienteren Implementierung
        //TODO
        /*int[] new_colind = new int[getn_row()+1];
        for (int i=0;i<getn_row();i++){
            
        }
        return new RowVectorSparsity(getn_col(), new_colind);*/
        
        //TODO
        // sehr ineffiziente Implementierung
        double[] vec = new double[n_row];
        for (int i=0;i<rows.length;i++){
            vec[rows[i]] = 1;
        }
        return new RowVectorSparsity(vec);
    }
    
    public ColumnVectorSparsity intersect(ColumnVectorSparsity sparsity){
        if (sparsity.getn_row() != getn_row()){
            throw new IllegalArgumentException("Sparsity object must have the same count of rows: "+
                    String.valueOf(sparsity.getn_row())+"!="+String.valueOf(getn_row()));
        }
        List<Integer> rows2 = Arrays.stream(sparsity.getrow())     // IntStream
                         .boxed()             // Stream<Integer>
                         .collect(Collectors.toList());
        List<Integer> resultIndices = new ArrayList<>();
        for (int i=0;i<rows.length;i++){
            if (rows2.contains(this.rows[i])){
                resultIndices.add(rows[i]);
            }
        }
        int[] nonzeros = resultIndices.stream().mapToInt(Integer::intValue).toArray();
        //TODO
        // hier werden sparsity-Objekte erzeugt, diese sollte ich aber cachen
        return new ColumnVectorSparsity(getn_row(), nonzeros);
    }
    
    
}
