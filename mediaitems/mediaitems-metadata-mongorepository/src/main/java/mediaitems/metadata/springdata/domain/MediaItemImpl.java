package mediaitems.metadata.springdata.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.Tag;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mediaitems")
@TypeAlias("mediaitem")
public class MediaItemImpl implements
 mediaitems.metadata.domain.MediaItem {

	@Id
	private String key;

	@Indexed
	private String name;

	@DBRef
	private Set<Tag> tagCollection;

	MediaItemImpl(String name, MediaType mediaType,
			List<ContentLocation> contentLocation, Long size, LocalDateTime time) {
		super();
		this.name = name;
		this.mediaType = mediaType;
		this.contentLocation = contentLocation;
		this.size = size;
		this.creationTime = time;
	}


	private final MediaType mediaType;

	private final List<ContentLocation> contentLocation;

	@Indexed
	private final LocalDateTime creationTime;

	@Indexed
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
	public List<ContentLocation> getContentLocations() {
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
			MediaItemBuilder<MediaItemImpl> {

		private String name;
		private MediaType mediaType;
		private final List<ContentLocation> contentLocation = new ArrayList<ContentLocation>();
		private Long size;
		private LocalDateTime time;

		@Override
		public MediaItemImpl build() {
			return new MediaItemImpl(this.name, this.mediaType,
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
		public MongoMediaItemBuilder addContentLocation(String location) {
			this.contentLocation.add(new ContentLocation(location));
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

		@Override
		public mediaitems.metadata.domain.MediaItem.MediaItemBuilder<MediaItemImpl> setContentLocation(
				String location) {
			this.contentLocation.clear();
			this.addContentLocation(location);
			return this;
		}

	}

	@Override
	public Iterable<Tag> getTags() {
		return tagCollection;
	}

	@Override
	public void addTag(Tag tag) {
		if (this.tagCollection == null)
			tagCollection = new HashSet<Tag>();
		tagCollection.add(tag);
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
		MediaItemImpl other = (MediaItemImpl) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
