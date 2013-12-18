package mediaitems.metadata.springdata.domain;

import java.util.ArrayList;
import java.util.Collection;

import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.Tag;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;

public class MediaItem implements mediaitems.metadata.domain.MediaItem {

	@Id
	private String key;

	private String name;

	private Collection<? extends Tag> tagCollection;

	MediaItem(String name, MediaType mediaType,
			ContentLocation contentLocation, Long size, LocalDateTime time) {
		super();
		this.name = name;
		this.mediaType = mediaType;
		this.contentLocation = contentLocation;
		this.size = size;
		this.creationTime = time;
	}

	private final MediaType mediaType;

	private final ContentLocation contentLocation;

	private final LocalDateTime creationTime;

	private final Long size;

	@Override
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

	public static class MongoMediaItemBuilder implements
			MediaItemBuilder<MediaItem> {

		private String name;
		private MediaType mediaType;
		private ContentLocation contentLocation;
		private Long size;
		private LocalDateTime time;

		@Override
		public MediaItem build() {
			return new MediaItem(this.name, this.mediaType,
					this.contentLocation, this.size, this.time);

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
			this.contentLocation = new ContentLocation(location);
			return this;
		}

		@Override
		public MongoMediaItemBuilder setSize(Long size) {
			this.size = size;
			return this;
		}

		@Override
		public MongoMediaItemBuilder setCreationTime(LocalDateTime time) {
			this.time = time;
			return this;
		}

	}

	@Override
	public Iterable<? extends Tag> getTags() {
		return tagCollection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTag(Tag tag) {
		if (tagCollection == null)
			tagCollection = new ArrayList<Tag>();
		((Collection<Tag>) tagCollection).add(tag);
	}

	@Override
	public void removeTag(Tag tag) {
		if (tagCollection == null)
			return;
		tagCollection.remove(tag);
	}

	@Override
	public Long getSize() {
		return this.size;
	}

	@Override
	public LocalDateTime getCreationTime() {
		return this.creationTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaItem other = (MediaItem) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
