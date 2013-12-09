package mediaitems.sync.api;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.sources.api.ContentHandle;

public interface Synchronizer {
			public interface SynchronizerCallback {
				public void execute(MediaItem mediaItem,ContentHandle contentHandle);
			}

			public void execute(SynchronizerCallback onNewItem,SynchronizerCallback onChangedItem,SynchronizerCallback onDeletedItem);
}
