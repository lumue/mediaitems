package mediaitems.metadata.repository;

import mediaitems.common.repository.api.ObjectRepository;
import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.domain.MediaItem.MediaItemBuilder;
import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.Tag;

public abstract interface MediaItemRepository<T extends MediaItem> extends ObjectRepository<T> {
	@Override
	public MediaItemBuilder<T> createNewBuilder();

	Iterable<T> getByMediaType(MediaType mediaType,
			Integer from, Integer to);

	Iterable<T> getByTag(Tag tag, Integer from, Integer to);

}
