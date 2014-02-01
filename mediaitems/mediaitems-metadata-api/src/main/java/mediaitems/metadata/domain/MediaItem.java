package mediaitems.metadata.domain;

import java.util.List;

import org.joda.time.LocalDateTime;

public interface MediaItem<T extends Tag> extends Taggable<T> {

	public interface MediaItemBuilder<T extends MediaItem<?>> extends
			Builder<T> {
		MediaItemBuilder<T> setName(String name);

		MediaItemBuilder<T> setMediaType(MediaType mediaType);

		MediaItemBuilder<T> setContentLocation(String string);

		MediaItemBuilder<T> setSize(Long size);

		MediaItemBuilder<T> setCreationTime(LocalDateTime time);

		MediaItemBuilder<T> addContentLocation(String location);
	}

	public String getKey();

	public MediaType getMediaType();

	public List<? extends ContentLocation> getContentLocations();

	public String getName();

	public void setName(String name);

	public Long getSize();

	public LocalDateTime getCreationTime();

}
