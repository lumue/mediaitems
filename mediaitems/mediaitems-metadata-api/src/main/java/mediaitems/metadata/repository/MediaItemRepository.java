package mediaitems.metadata.repository;

import mediaitems.common.repository.api.ObjectRepository;
import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.domain.MediaItem.MediaItemBuilder;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.Tag;

public abstract interface MediaItemRepository extends
		ObjectRepository<MediaItem> {
	@Override
	public MediaItemBuilder<? extends MediaItem> createNewBuilder();

	Iterable<? extends MediaItem> getByMediaType(MediaType mediaType,
			Integer from, Integer to);

	Iterable<? extends MediaItem> getByTag(Tag tag, Integer from, Integer to);

}
