package mediaitems.sync.api;

import mediaitems.configuration.sources.api.model.ContentHandle;
import mediaitems.configuration.sources.api.model.ContentSource;
import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.repository.MediaItemRepository;

public interface Synchronizer {
	
	public interface Event {
		public MediaItem getMediaItem();
		public ContentHandle getContentHandle();
	}

	public interface EventHandler {
		public void onEvent(Event event);
	}

	public void registerEventHandler(String eventType,EventHandler eventHandler,short priority);
	
	public void synchronize(ContentSource contentSource,MediaItemRepository mediaItemRepository);
	
	
}
