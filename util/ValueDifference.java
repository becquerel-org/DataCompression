package DataCompression.util;

/**
Take to objects of the same type and rate the difference as double
*/
public interface ValueDifference<T> {
	double valueDifference(T a, T b);
}
