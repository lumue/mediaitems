package mediaitems.sources.filesystem.local;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FileChecksumBuilder {

	private MessageDigest messageDigest;


	public FileChecksumBuilder() {
		this("MD5");
	}

	public FileChecksumBuilder(String algorithm) {
		super();
		initMessageDigest(algorithm);
	}

	String buildChecksum(Path path) throws IOException {
		int blocksize = 1024;
		return buildChecksum(path, blocksize);
	}

	String buildChecksum(Path path, int blocksize) throws IOException {
		byte[] dataBytes = new byte[blocksize];
		FileInputStream fis = new FileInputStream(path.toFile());
		int nread = 0;

		while ((nread = fis.read(dataBytes)) != -1) {
			messageDigest.update(dataBytes, 0, nread);
		}

		fis.close();

		byte[] mdbytes = messageDigest.digest();

		// convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}

	private void initMessageDigest(String algorithm) {

		try {
			this.messageDigest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
