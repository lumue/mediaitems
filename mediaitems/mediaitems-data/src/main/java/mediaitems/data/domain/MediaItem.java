package mediaitems.data.domain;

import org.springframework.data.annotation.Id;

public class MediaItem {
	
	@Id
	private String key;
	
	private String name;
	
	public MediaItem(String name, MediaType document, ContentLocation contentLocation) {
		super();
		this.name = name;
		this.mediaType=document;
		this.contentLocation=contentLocation;
	}

	private MediaType mediaType;
	
	private ContentLocation contentLocation;

	public String getKey() {
		return key;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public ContentLocation getContentLocation() {
		return contentLocation;
	}

	public void setContentLocation(ContentLocation contentLocation) {
		this.contentLocation = contentLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
