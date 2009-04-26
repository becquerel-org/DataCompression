package DataCompression;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.FileOutputStream;

class FileReader {
	static File getFile(String[] args) throws IOException {
		if (args.length==0) {
			File temp = File.createTempFile("inputbuffer", ".buff");
			temp.deleteOnExit();
			FileOutputStream out = new FileOutputStream(temp);
			int len;
			int buffsize=1024;
			byte[] buff = new byte[buffsize];
			len = System.in.read(buff,0,buffsize);
			while (len==buffsize) {
				out.write(buff,0,buffsize);
				len = System.in.read(buff,0,buffsize);
			}
			if (!(len==-1)) {
				out.write(buff,0,len);
			}
			out.close();
			return temp;
		} else {
			File f=new File(args[0]);
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
