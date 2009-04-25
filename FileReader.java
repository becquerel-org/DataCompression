import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.BufferedInputStream;

class FindIntput {
	static InputStream getInput(String[] args) throws IOException {
		if (args.length==0) {
			BufferedInputStream buff = new BufferedInputStream(System.in);
			buff.mark(10000000);
			return buff;
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
			return new FileInputStream(f);
		}
	}
}
