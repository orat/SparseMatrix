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
    
    // very slow implementation
    /**
     * 
     * @param rowIndex
     * @return -1 if the given index is structural zero
     * @throws IllegalArgumentException if the given index is out of the columns vector length
     */
    public int determineIndexOfRow(int rowIndex){
        if (rowIndex >= getn_row()) throw new IllegalArgumentException("row =="+String.valueOf(rowIndex)+
                " is >= nrow="+String.valueOf(n_row));
        for (int i=0;i<rows.length;i++){
            if (rows[i] == rowIndex) return i;
        }
        return -1;
    }
    
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
    
    public static ColumnVectorSparsity dense(int rows){
        return new ColumnVectorSparsity(rows, createDenseRows(rows));
    }
    protected static int[] createDenseRows(int rows){
        int[] result = new int[rows];
        for (int i=0;i<rows;i++){
            result[i] = i;
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
    
    // oder-Verknüpfung
    // mir scheint rows die in beiden vorkommen erscheinen doppelt im output
    //FIXME
    public ColumnVectorSparsity join(ColumnVectorSparsity sparsity){
        if (sparsity.getn_row() != getn_row()){
            throw new IllegalArgumentException("Sparsity object must have the same count of rows: "+
                    String.valueOf(sparsity.getn_row())+"!="+String.valueOf(getn_row()));
        }
        List<Integer> result = Arrays.stream(getrow())     // IntStream
                         .boxed()             // Stream<Integer>
                         .collect(Collectors.toList());
        List<Integer> rows2 = Arrays.stream(sparsity.getrow())     // IntStream
                         .boxed()             // Stream<Integer>
                         .collect(Collectors.toList());
        rows2.removeAll(result);
        result.addAll(rows2);
        
        int[] rows = result.stream().mapToInt(Integer::intValue).toArray();
        //TODO
        // hier werden sparsity-Objekte erzeugt, diese sollte ich aber cachen
        return new ColumnVectorSparsity(result.size(), rows);
    }
    
    // und-Verknüpfung
    public ColumnVectorSparsity meet(ColumnVectorSparsity sparsity){
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
