package mediaitems.application.sources.integration;

import java.net.URI;

import mediaitems.configuration.sources.api.model.ContentSource;
import mediaitems.configuration.sources.api.repository.ContentSourceRepository;
import mediaitems.sources.AbstractSourceScanner;
import mediaitems.sources.api.io.ContentBrowser;
import mediaitems.sources.api.scan.ContentDiscoveredEvent;
import mediaitems.sources.api.scan.ScanCompleteEvent;
import mediaitems.sources.api.scan.ScanEvent;
import mediaitems.sources.api.scan.ScanEventHandler;
import mediaitems.sources.api.scan.ScanFailedEvent;
import mediaitems.sources.api.scan.SourceScanner;
import mediaitems.sources.filesystem.local.LocalFileSystemContentSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
/**
 * Scan ContentSources and report discoveries to channel
 * @author lm
 *
 */
@MessageEndpoint(value = "contentDiscoveryEndpoint")
public class ContentDiscoveryEndpoint extends MessageProducerSupport {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ContentDiscoveryEndpoint.class);

	@Autowired(required = true)
	private ContentSourceRepository contentSourceRepository;

	public void startScan() {
		Iterable<? extends ContentSource> contentSources = contentSourceRepository
				.getAll();
		for (ContentSource contentSource : contentSources) {
			startSourceScan(contentSource);
		}
	}

	private void startSourceScan(final ContentSource contentSource) {
		LOGGER.info("starting scan on " + contentSource);
		SourceScanner scanner = new AbstractSourceScanner() {

			@Override
			protected ContentBrowser getContentBrowser(URI uri) {
				return new LocalFileSystemContentSource(uri);
			}
		};
		scanner.registerScanEventHandler(new ScanEventHandler() {

			@Override
			public void handleEvent(ScanEvent event) {
				
				LOGGER.debug(event.getClass().toString() + " " + event.toString() + " received");
				
				if (event instanceof ScanFailedEvent) {
					ScanFailedEvent scanFailedEvent = (ScanFailedEvent) event;
					LOGGER.error("scan failed", scanFailedEvent.getCause());
				}

				if (event instanceof ScanCompleteEvent) {
					LOGGER.info("scan complete");
				}

				if (event instanceof ContentDiscoveredEvent) {
					ContentDiscoveredEvent contentDiscoveredEvent = (ContentDiscoveredEvent) event;
					Message<ContentDiscoveredMessageContent> message = new GenericMessage<ContentDiscoveredMessageContent>(
							new ContentDiscoveredMessageContent(
									contentDiscoveredEvent.getContentHandle(),
									contentSource));
					LOGGER.debug("sending content discovered message " + message);
					sendMessage(message);
				}
				
			}
		});
		scanner.scan(contentSource.getUrl());
		LOGGER.info("scan on " + contentSource + " started");
	}
}
