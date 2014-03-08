package mediaitems.sources.api.scan;

import mediaitems.sources.api.io.ContentHandle;

public interface ContentDiscoveredEvent extends ScanEvent {
	public ContentHandle getContentHandle();
}
