package mediaitems.sources.api;

import java.io.IOException;
import java.io.OutputStream;

/**
 * T Type of Media Resource e.g. File
 * 
 * @author lm
 *
 * @param <T>
 */
public interface ContentHandle {

		public String getLocationIdentifier() throws IOException;
	
		OutputStream newOutputStream() throws IOException;
		
		ContentDescription getDescription() throws IOException;
	
		public byte[] createThumb() throws IOException;
}
