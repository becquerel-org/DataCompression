package DataCompression.util;

import DataCompression.util.DimensionException;
import java.text.DecimalFormat;

/**
 * Simple Matrix class
 */
public class DoubleMatrix {
	private double[][] data;

	private int rows;
	private int columns;


	// Constructors
	
	public DoubleMatrix(int rows, int columns) {
		init(rows,columns);
	}

	public DoubleMatrix(int n) {
		init(n,n);
	}

	public DoubleMatrix(double[][] raw) {
		init(raw.length, columns=raw[0].length);

		for (int i=0; i<rows; i++) {
			for (int j=0;j<columns;j++) {
				data[i][j]=raw[i][j];
			}
		}
	}

	private void init(int rows, int columns) {
		data = new double[rows][columns];
		this.rows=rows;
		this.columns=columns;
	}

	// Accessors
	
	public int rows() {
		return this.rows;
	}

	public int columns() {
		return this.columns;
	}
	

	// Methods

	public double at(int row, int column) throws IndexOutOfBoundsException {
		return data[row][column];
	}

	public void set(int row, int column, double value) throws IndexOutOfBoundsException {
		data[row][column]=value;
	}

	public DoubleMatrix transpose() {
		DoubleMatrix ret = new DoubleMatrix(columns, rows);
		for (int i=0; i<rows; i++) {
			for (int j=0;j<columns;j++) {
				ret.set(j,i,data[i][j]);
			}
		}
		return ret;
	}

	public DoubleMatrix multiply(DoubleMatrix oth) throws DimensionException {
		if (columns!=oth.rows) {
			throw new DimensionException(DimensionException.multiplicationError(rows,columns,oth.rows,oth.columns));
		}

		DoubleMatrix ret = new DoubleMatrix(rows,oth.columns);
		for (int i=0; i<rows; i++) {
			for (int j=0; j<oth.columns;j++) {
				double val=0;
				for (int k=0;k<columns; k++) {
					val+=data[i][k]*oth.data[k][j];
				}
				ret.data[i][j]=val;
			}
		}
		return ret;
	}

	public RowVector multiply(ColumnVector oth) throws DimensionException {
		if (columns!=oth.size()) {
			throw new DimensionException("Cannot multiply " + rows + " times " +
					columns + " matrix with vector of size " + oth.size());
		}

		RowVector ret = new RowVector(rows);
		for (int i=0; i<rows; i++) {
			double val=0;
			for (int k=0;k<columns; k++) {
				val+=data[i][k]*oth.data[k];
			}
			ret.data[i]=val;
		}

		return ret;
	}

	

	public boolean equals(DoubleMatrix oth) {
		if ((rows!=oth.rows) && (columns!=oth.columns))
			return false;
		return data.equals(oth.data);
	}

	public String toString() {
		return toString(null);
	}

	public String toString(DecimalFormat format) {
		String ret = "";
		for (int i=0; i<rows; i++) {
			for (int j=0;j<columns-1;j++) {
				if (format!=null) {
					ret += format.format(data[i][j]) + ", ";
				} else {
					ret += data[i][j] + ", ";
				}
			}
			ret+= data[i][columns-1] + "\n";
		}
		return ret;
	}

}
