package mediaitems.configuration.sources.api.model;

import java.net.URI;

public interface ContentSource {

	public String getId();

	public String getName();

	public void setName(String name);

	public URI getUrl();

}
