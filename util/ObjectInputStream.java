package DataCompression.util;

import java.io.IOException;

/**
Interface for a readable stream of objects. Like ObjectInput but with type safty.
*/
public interface ObjectInputStream<T> {
	boolean hasNext();
	T readNext() throws IOException;
}
