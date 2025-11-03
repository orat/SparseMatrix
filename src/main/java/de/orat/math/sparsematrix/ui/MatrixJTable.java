package de.orat.math.sparsematrix.ui;

import de.orat.math.sparsematrix.DenseStringMatrix;
import javax.swing.JTable;

/**
 * @author Oliver Rettig (Oliver.Rettig@orat.de)
 */
public class MatrixJTable extends JTable {
    
    public MatrixJTable(DenseStringMatrix m){
        super(m.toArr(), determineCols(m));
        setTableHeader(null);
    }
    private static String[] determineCols(DenseStringMatrix m){
        String[] colNames = new String[m.getCols()];
        for (int i=0;i<colNames.length;i++){
            colNames[i] = " ";
        }
        return colNames;
    }
}
