package DataCompression.util;

import DataCompression.util.MatrixIndexOutOfBoundsException;
import DataCompression.util.DimensionException;

/**
 * Simple Matrix class
 */
public class Matrix {
	private double[][] data;

	private int rows;
	private int columns;


	// Constructors
	
	public Matrix(int rows, int columns) {
		init(rows,columns);
	}

	public Matrix(int n) {
		init(n,n);
	}

	public Matrix(double[][] raw) {
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

	public double at(int row, int column) throws MatrixIndexOutOfBoundsException {
		try {
		return data[row][column];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new MatrixIndexOutOfBoundsException(e.toString());
		}
	}

	public void set(int row, int column, double value) throws MatrixIndexOutOfBoundsException {
		try {
		data[row][column]=value;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new MatrixIndexOutOfBoundsException(e.toString());
		}
	}

	public Matrix transpose() {
		Matrix ret = new Matrix(columns, rows);
		for (int i=0; i<rows; i++) {
			for (int j=0;j<columns;j++) {
				ret.set(j,i,data[i][j]);
			}
		}
		return ret;
	}

	public Matrix multiply(Matrix oth) throws DimensionException {
		if (columns!=oth.rows) {
			throw new DimensionException(DimensionException.multiplicationError(rows,columns,oth.rows,oth.columns));
		}

		Matrix ret = new Matrix(rows,oth.columns);
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

	public boolean equals(Matrix oth) {
		if ((rows!=oth.rows) && (columns!=oth.columns))
			return false;
		return data.equals(oth.data);
	}


}
