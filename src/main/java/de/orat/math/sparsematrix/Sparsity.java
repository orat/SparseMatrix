package de.orat.math.sparsematrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 * 
 * TODO
 * ins package in eines von dem GeometricAlgebra
 * abhängt verschieben...
 */
public class Sparsity {
    
    final int n_row;
    final int n_col;
    final int[] colind; // cumulative count of non zeros for each column, started with 0; length == number of columns +1 
    final int[] row;    // rowindex for each non zero value, row.length == number of non zeros
    
    public Sparsity(int n_row, int n_col, int[] colind, int[] row){
        this.n_row = n_row;
        this.n_col = n_col;
        this.colind = colind;
        this.row = row;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        // rows
        sb.append("n_row=");
        sb.append(String.valueOf(n_row));
        sb.append("\n");
        
        // cols
        sb.append("n_col=");
        sb.append(String.valueOf(n_col));
        sb.append("\n");
        
        // colind
        sb.append("colind=[");
        for (int i=0;i<colind.length-1;i++){
            sb.append(String.valueOf(colind[i]));
            sb.append(",");
        }
        sb.append(String.valueOf(colind[colind.length-1]));
        sb.append("]\n");
        
        // row
        sb.append("row=[");
        for (int i=0;i<row.length-1;i++){
            sb.append(String.valueOf(row[i]));
            sb.append(",");
        }
        sb.append(String.valueOf(row[row.length-1]));
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
        return row;
    }
    
    /**
     * Werte die exakt 0 sind als strukturell 0 ansehen und entsprechend
     * ein sparsity objekt erzeugen.
     * 
     * @param m 
     */
    public Sparsity(double[][] m){
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
        row = rowList.stream().mapToInt(d -> d).toArray();
    }
    
    // not yet tested
    // geht das nicht mit einer effizienteren Implementierung, die diese arrays
    // nicht anlegt, bzw. nur bei Bedarf?
    // Also eine Dense class die das effizienter implementiert
    // aber dann brauche ich vermutlich ein Sparsity-Interface da ja Dense von
    // Sparsity erben muss ich aber die Datenstrukturen von Spasity gar nicht will
    //TODO
    public static Sparsity dense(int nrow, int ncol){
        int[] colind = new int[ncol+1];
        int[] row = new int[nrow*ncol];
        //TODO
        // array-values richtig auffüllen
        return new Sparsity(nrow, ncol, colind, row);
    }
}
