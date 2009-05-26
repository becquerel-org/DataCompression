package DataCompression.util;

import java.io.IOException;

interface ObjectReader<T> {
	T next() throws IOException;
}
