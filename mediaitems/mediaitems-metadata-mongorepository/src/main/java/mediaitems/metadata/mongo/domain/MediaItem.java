package mediaitems.metadata.mongo.domain;

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

	public static class MongoMediaItemBuilder implements  MediaItemBuilder<mediaitems.metadata.domain.MediaItem>
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

		@Override
		public MongoMediaItemBuilder setName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public MongoMediaItemBuilder setMediaType(MediaType mediaType) {
			this.mediaType = mediaType;
			return this;
		}

		@Override
		public MongoMediaItemBuilder setContentLocation(String location) {
			this.contentLocation=new ContentLocation(location);
			return this;
		}

		
		
		
	}
}
