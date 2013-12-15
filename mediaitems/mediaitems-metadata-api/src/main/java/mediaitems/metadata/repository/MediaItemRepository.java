package mediaitems.metadata.repository;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.domain.MediaItem.MediaItemBuilder;


public interface MediaItemRepository extends MetadataRepository<MediaItem> {
	@Override
	public MediaItemBuilder<? extends MediaItem> createNewBuilder();
}
