package mediaitems.metadata.domain;

public interface MediaItem {

	
	
	public String getKey();

	public MediaType getMediaType();

	public ContentLocation getContentLocation();
	
	public String getName();

	public void setName(String name);

}
