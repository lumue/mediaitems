package mediaitems.sources.api;

import java.io.IOException;

public interface ContentSource  {

	public String getId();
	
	public String getName();
	
	public Iterable<ContentHandle> list() throws IOException;

}
