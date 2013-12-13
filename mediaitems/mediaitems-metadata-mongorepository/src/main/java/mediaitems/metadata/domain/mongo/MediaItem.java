package mediaitems.metadata.domain.mongo;

import mediaitems.metadata.domain.Builder;
import mediaitems.metadata.domain.MediaType;

import org.springframework.data.annotation.Id;

public class MediaItem implements mediaitems.metadata.domain.MediaItem{
	
	@Id
	private String key;
	
	private String name;
	
	MediaItem(String name, MediaType mediaType, ContentLocation contentLocation) {
		super();
		this.name = name;
		this.mediaType=mediaType;
		this.contentLocation=contentLocation;
	}

	private MediaType mediaType;
	
	private ContentLocation contentLocation;

	public String getKey() {
		return key;
	}

	@Override
	public MediaType getMediaType() {
		return mediaType;
	}

	@Override
	public ContentLocation getContentLocation() {
		return contentLocation;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public static class MongoMediaItemBuilder implements  Builder<mediaitems.metadata.domain.MediaItem>
	{


		private String name;
		private MediaType mediaType;
		private ContentLocation contentLocation;

		@Override
		public MediaItem build() {
			return new MediaItem(this.name ,
			this.mediaType,
			this.contentLocation);

		}

		public MongoMediaItemBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public MongoMediaItemBuilder setMediaType(MediaType mediaType) {
			this.mediaType = mediaType;
			return this;
		}

		public MongoMediaItemBuilder setContentLocation(ContentLocation contentLocation) {
			this.contentLocation = contentLocation;
			return this;
		}
		
		
		
	}
}
