package mediaitems.metadata.repository;

import mediaitems.metadata.domain.VideoItem;
import mediaitems.metadata.domain.VideoItem.VideoItemBuilder;

public interface VideoItemRepository<T extends VideoItem> extends MediaItemRepository<T> {
	@Override
	public VideoItemBuilder<T> createNewBuilder();



}
