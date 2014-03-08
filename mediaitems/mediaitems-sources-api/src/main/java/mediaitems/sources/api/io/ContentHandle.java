package mediaitems.sources.api.io;

import java.io.IOException;
import java.net.URI;

/**
 * 
 * 
 * @author lm
 * 
 * 
 */
public interface ContentHandle {

	public URI getLocationIdentifier() throws IOException;

}
