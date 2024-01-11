package de.orat.math.sparsematrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 * 
 * With this format, it is cheap to loop over all the non-zero elements of a 
 * particular column, at constant time per element, but expensive to jump to 
 * access a location (i, j).
 */
public class MatrixSparsity {
    
    final int n_row;
    final int n_col;
    final int[] colind; // cumulative count of non zeros for each column, started with 0; length == number of columns +1 
    final int[] rows;    // rowindex for each non zero value, rows.length == number of non zeros
    
    public MatrixSparsity(int n_row, int n_col, int[] colind, int[] row){
        this.n_row = n_row;
        this.n_col = n_col;
        this.colind = colind;
        this.rows = row;
    }
    
    public static MatrixSparsity dense(int row, int col) {
        return new MatrixSparsity(row, col, createDenseColint(row, col), 
                                     createDenseRow(row, col));
    }
    public boolean isDense(){
        return rows.length == n_row*n_col;
    }
    
    private static int[] createDenseColint(int n_row, int n_col){
        int[] result = new int[n_col+1];
        for (int i=1;i<n_col+1;i++){
            result[i] = result[i-1]+n_row;
        }
        return result;
    }
    
    private static int[] createDenseRow(int n_row, int n_col){
        int[] nonZeroRows = new int[n_row*n_col];
        int i=0;
        for (int col=0;col<n_col;col++){
            for (int row=0;row<n_row;row++){
                nonZeroRows[i++] = row;
            }
        }
        return nonZeroRows;
    }
    
    public static MatrixSparsity diagonal(int size){
        return new MatrixSparsity(size, size, createDiagonalColint(size), createDiagonalRow(size));
    }
    private static int[] createDiagonalColint(int size){
        int[] result = new int[size+1];
        int val=0;
        for (int i=1;i<size+1;i++){
            result[i] = ++val;
        }
        return result;
    }
    private static int[] createDiagonalRow(int size){
        int[] result = new int[size];
        for (int i=0;i<size;i++){
            result[i] = i;
        }
        return result;
    }
    
    /**
     * 
     * @param values for all diagonal elements, zeros are interpreted as structural zeros
     * @return 
     */
    public static MatrixSparsity diagonal(double[] values){
             return new MatrixSparsity(values.length, values.length, 
                     createDiagonalColint(values), createDiagonalRow(values));
    }
    private static int[] createDiagonalColint(double[] values){
        int[] result = new int[values.length+1];
        int val=0;
        for (int i=1;i<values.length+1;i++){
            if (values[i-1] != 0){
                result[i] = ++val;
            }
        }
        return result;
    }
    private static int[] createDiagonalRow(double[] values){
        List<Integer> nonzeros = new ArrayList<>();
        for (int i=0;i<values.length;i++){
            if (values[i] != 0){
                nonzeros.add(i);
            }
        }
        return nonzeros.stream().mapToInt(d -> d).toArray();
    }
    /**
     * scheint zu funktionieren
     * TODO test ist noch zu vervollständigen
     * 
     * @param r row index
     * @param c column index
     * @return true, if the given cell is non zero
     * @throws IllegalArgumentException if the given arguments do not fit to the matrix dimensions
     */
    public boolean isNonZero(int r, int c){
        if (r < 0 || c < 0 || r >= n_row || c >= n_col)
            throw new IllegalArgumentException("row = "+String.valueOf(r)+
                    " or col = "+String.valueOf(c)+" does not fit to sparsity with n_row = "+
                    String.valueOf(n_row)+", n_col = "+String.valueOf(n_col));
        if (colind[c+1] < 1) return false;
        for (int i= colind[c];i< colind[c+1];i++){
            if (rows[i] == r) return true;
        }
        return false;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        // rows
        //sb.append("n_row=");
        //sb.append(String.valueOf(n_row));
        //sb.append("\n");
        
        // cols
        //sb.append("n_col=");
        //sb.append(String.valueOf(n_col));
        //sb.append("\n");
        
        sb.append(String.valueOf(n_row));
        sb.append("x");
        sb.append(String.valueOf(n_col));
        sb.append(",");
        sb.append(String.valueOf(rows.length));
        sb.append("nz");
        sb.append("\n");
        
        // colind
        sb.append("colind:  [");
        for (int i=0;i<colind.length-1;i++){
            sb.append(String.valueOf(colind[i]));
            sb.append(",");
        }
        sb.append(String.valueOf(colind[colind.length-1]));
        sb.append("]\n");
        
        // rows
        sb.append("row:     [");
        for (int i=0;i<rows.length-1;i++){
            sb.append(String.valueOf(rows[i]));
            sb.append(",");
        }
        if(rows.length > 0){
            sb.append(String.valueOf(rows[rows.length-1]));
        }
        sb.append("]\n");
        return sb.toString();
    }
    
    public int getn_row(){
        return n_row;
    }
    public int getn_col(){
        return n_col;
    }
    public int[] getcolind(){
        return colind;
    }
    public int[] getrow(){
        return rows;
    }
    
    /**
     * Werte die exakt 0 sind werden als strukturell 0 ansehen und entsprechend
     * wird das sparsity Objekt erzeugt.
     * 
     * @param m 
     */
    public MatrixSparsity(double[][] m){
        n_row = m.length;
        n_col = m[0].length;
        colind = new int[n_col+1];
        List<Integer> rowList = new ArrayList<>();
        // loop over all columns
        for (int col=0;col<m[0].length;col++){
            colind[col+1] = colind[col];
            // loop over all rows
            for (int i=0;i<m.length;i++){
                if (m[i][col] != 0d){
                    rowList.add(i);
                    colind[col+1]++;
                }
            }
        }
        rows = rowList.stream().mapToInt(d -> d).toArray();
    }
    
    /**
     * "0" wird als structural sparse gewertet.
     * 
     * @param m 
     */
    public MatrixSparsity(String[][] m){
        n_row = m.length;
        n_col = m[0].length;
        colind = new int[n_col+1];
        List<Integer> rowList = new ArrayList<>();
        // loop over all columns
        for (int col=0;col<m[0].length;col++){
            colind[col+1] = colind[col];
            // loop over all rows
            for (int i=0;i<m.length;i++){
                if (!m[i][col].equals("0")){
                    rowList.add(i);
                    colind[col+1]++;
                }
            }
        }
        rows = rowList.stream().mapToInt(d -> d).toArray();
    }
}
