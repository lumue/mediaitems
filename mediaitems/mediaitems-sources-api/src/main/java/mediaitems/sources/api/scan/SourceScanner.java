package mediaitems.sources.api.scan;

import java.net.URI;

public interface SourceScanner {

	public void registerScanEventHandler(ScanEventHandler scanEventHandler);

	public void scan(URI source);
}
