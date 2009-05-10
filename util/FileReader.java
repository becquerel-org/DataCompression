package DataCompression.util;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.FileOutputStream;

/**
 * Opens the file to work with.
 * Takes filename from command line and checks if file is readable.
 * If no file is given it encapsulates stdin into a temp file.
 */
public class FileReader {
	/**
	 * Tries to open the first argument as file.
	 * If first argument is not given stdin is read into a temporary file.
	 */
	public static File getFile(String[] args) throws IOException {
		if (args.length==0) { // No file name given
			File temp = File.createTempFile("inputbuffer", ".buff");
			temp.deleteOnExit();
			FileOutputStream out = new FileOutputStream(temp);
			int len;
			int buffsize=1024;
			byte[] buff = new byte[buffsize];
			len = System.in.read(buff,0,buffsize);
			while (len!=-1) { // read until no more input is available
				out.write(buff,0,len);
				len = System.in.read(buff,0,buffsize);
			}
			out.close();
			return temp;
		} else { // file name given
			File f=new File(args[0]); // try to open it
			// check whether file exists and can be read
			if (!(f.exists()&&f.canRead())) {
				String error = "File "+f.getName();
				if (!f.exists()) {
					error = error + " does not exist!";
				} else {
						error = error + " cannot be read!";
				}
				throw new IOException(error);
			}
			return f;
		}
	}
}
