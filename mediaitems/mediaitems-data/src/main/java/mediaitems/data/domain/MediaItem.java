package mediaitems.data.domain;

import org.springframework.data.annotation.Id;

public class MediaItem {
	
	@Id
	private String key;
	
	private MediaType mediaType;
	
	private ContentLocation contentLocation;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

}
