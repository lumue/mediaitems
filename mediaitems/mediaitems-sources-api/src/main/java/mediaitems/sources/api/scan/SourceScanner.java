package mediaitems.sources.api.scan;

import mediaitems.sources.api.ContentSource;

public interface SourceScanner {
	
	public <T extends ScanEvent> void  registerScanEventHandler(ScanEventHandler<T> scanEventHandler);
	
	public void scan(ContentSource source);
}
