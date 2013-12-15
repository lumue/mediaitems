package mediaitems.sources.api;

import java.io.IOException;

public interface ContentSource  {

	public String getId();
	
	public String getName();
	
	public ContentIterable<? extends ContentHandle> list() throws IOException;

}
