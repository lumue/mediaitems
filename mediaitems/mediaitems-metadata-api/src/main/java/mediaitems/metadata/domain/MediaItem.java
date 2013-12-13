package mediaitems.metadata.domain;

public interface MediaItem {

	public interface MediaItemBuilder<T extends MediaItem> extends Builder<T>{
		MediaItemBuilder<T> setName(String name);
		MediaItemBuilder<T> setMediaType(MediaType mediaType);
		MediaItemBuilder<T> setContentLocation(ContentLocation location);
	}
	
	public String getKey();

	public MediaType getMediaType();

	public ContentLocation getContentLocation();
	
	public String getName();

	public void setName(String name);

}
