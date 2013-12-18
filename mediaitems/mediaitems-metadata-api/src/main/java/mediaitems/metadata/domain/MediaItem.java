package mediaitems.metadata.domain;

import org.joda.time.LocalDateTime;

public interface MediaItem extends Taggable {

	public interface MediaItemBuilder<T extends MediaItem> extends Builder<T> {
		MediaItemBuilder<T> setName(String name);

		MediaItemBuilder<T> setMediaType(MediaType mediaType);

		MediaItemBuilder<T> setContentLocation(String string);

		MediaItemBuilder<T> setSize(Long size);

		MediaItemBuilder<T> setCreationTime(LocalDateTime time);
	}

	public String getKey();

	public MediaType getMediaType();

	public ContentLocation getContentLocation();

	public String getName();

	public void setName(String name);

	public Long getSize();

	public LocalDateTime getCreationTime();

}
