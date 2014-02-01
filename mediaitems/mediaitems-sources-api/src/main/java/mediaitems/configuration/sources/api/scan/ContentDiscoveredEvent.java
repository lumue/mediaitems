package mediaitems.configuration.sources.api.scan;

import mediaitems.configuration.sources.api.model.ContentHandle;

public interface ContentDiscoveredEvent extends ScanEvent {
	public ContentHandle getContentHandle();
}
