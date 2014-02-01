package mediaitems.configuration.sources.api.scan;

import mediaitems.configuration.sources.api.model.ContentSource;

public interface SourceScanner<SOURCE extends ContentSource> {

	public void registerScanEventHandler(ScanEventHandler scanEventHandler);

	public void scan(SOURCE source);
}
