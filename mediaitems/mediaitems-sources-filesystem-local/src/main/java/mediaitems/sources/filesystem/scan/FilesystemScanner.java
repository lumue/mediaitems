package mediaitems.sources.filesystem.scan;

import java.util.ArrayList;
import java.util.List;

import mediaitems.configuration.sources.ContentIterable;
import mediaitems.configuration.sources.api.error.ContentAccessException;
import mediaitems.configuration.sources.api.model.ContentHandle;
import mediaitems.configuration.sources.api.scan.ContentDiscoveredEvent;
import mediaitems.configuration.sources.api.scan.ScanEvent;
import mediaitems.configuration.sources.api.scan.ScanEventHandler;
import mediaitems.configuration.sources.api.scan.ScanFailedEvent;
import mediaitems.configuration.sources.api.scan.ScanStartedEvent;
import mediaitems.configuration.sources.api.scan.SourceScanner;
import mediaitems.sources.filesystem.local.LocalFileSystemContentSource;

public class FilesystemScanner implements
		SourceScanner<LocalFileSystemContentSource> {

	private final List<ScanEventHandler> handlers = new ArrayList<ScanEventHandler>();

	@Override
	public void registerScanEventHandler(ScanEventHandler scanEventHandler) {
		handlers.add(scanEventHandler);
	}

	@Override
	public void scan(LocalFileSystemContentSource source) {
		try {
			publishEvent(new ScanStartedEvent() {
			});
			ContentIterable<? extends ContentHandle> contentIterable = source
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
