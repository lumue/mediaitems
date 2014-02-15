package mediaitems.sources.filesystem.scan;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import mediaitems.sources.api.error.ContentAccessException;
import mediaitems.sources.api.io.ContentBrowser;
import mediaitems.sources.api.io.ContentHandle;
import mediaitems.sources.api.io.ContentIterable;
import mediaitems.sources.api.scan.ContentDiscoveredEvent;
import mediaitems.sources.api.scan.ScanEvent;
import mediaitems.sources.api.scan.ScanEventHandler;
import mediaitems.sources.api.scan.ScanFailedEvent;
import mediaitems.sources.api.scan.ScanStartedEvent;
import mediaitems.sources.api.scan.SourceScanner;

public class FilesystemScanner implements SourceScanner {

	private final List<ScanEventHandler> handlers = new ArrayList<ScanEventHandler>();

	@Override
	public void registerScanEventHandler(ScanEventHandler scanEventHandler) {
		handlers.add(scanEventHandler);
	}

	@Override
	public void scan(URI uri) {
		try {
			publishEvent(new ScanStartedEvent() {
			});
			ContentIterable<? extends ContentHandle> contentIterable = getContentBrowser()
					.list();
			for (final ContentHandle contentHandle : contentIterable) {
				publishEvent(new ContentDiscoveredEvent() {
					@Override
					public ContentHandle getContentHandle() {
						return contentHandle;
					}
				});
			}
			contentIterable.close();
		} catch (ContentAccessException e) {
			scanFailed(e);
		}

	}

	private ContentBrowser getContentBrowser() {
		// TODO Auto-generated method stub
		return null;
	}

	private void scanFailed(ContentAccessException e) {
		publishEvent(new ScanFailedEvent() {

		});
	}

	private void publishEvent(ScanEvent scanEvent) {
		for (ScanEventHandler handler : handlers) {
			handler.handleEvent(scanEvent);
		}
	}

}
