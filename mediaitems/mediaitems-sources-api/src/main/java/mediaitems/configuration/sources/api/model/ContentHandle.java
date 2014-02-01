package mediaitems.configuration.sources.api.model;

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
