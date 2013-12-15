package mediaitems.metadata.repository;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.domain.MediaItem.MediaItemBuilder;
import mediaitems.metadata.domain.MediaType;

public interface MediaItemRepository extends MetadataRepository<MediaItem> {
	@Override
	public MediaItemBuilder<? extends MediaItem> createNewBuilder();

	Iterable<? extends MediaItem> getByMediaType(MediaType mediaType,
			Integer from, Integer to);

}
