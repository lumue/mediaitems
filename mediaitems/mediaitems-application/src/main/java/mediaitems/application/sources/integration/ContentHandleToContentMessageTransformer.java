package mediaitems.application.sources.integration;

import java.net.URI;

import mediaitems.sources.api.error.ContentAccessException;
import mediaitems.sources.api.io.ContentDescription;
import mediaitems.sources.api.io.ContentReader;
import mediaitems.sources.filesystem.local.LocalFileSystemContentSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component("contentHandleToContentDescription")
public class ContentHandleToContentMessageTransformer {

	ContentReader contentReader = new LocalFileSystemContentSource(
			URI.create("file:///"));

	private final static Logger LOGGER = LoggerFactory.getLogger(ContentHandleToContentMessageTransformer.class);

	@Transformer
	public ContentDescription transform(ContentDiscoveredMessageContent contentDiscoveredMessageContent)
			throws ContentAccessException
	{
		LOGGER.debug(LOGGER.isDebugEnabled() ? "transforming contentHandle " + contentDiscoveredMessageContent.getContentHandle() + " on "
				+ contentDiscoveredMessageContent.getContentSource() + " to contentDescription" : "");

		ContentDescription contentDescription = contentReader.describe(contentDiscoveredMessageContent.getContentHandle());

		LOGGER.debug(LOGGER.isDebugEnabled() ? "transformed contentHandle " + contentDiscoveredMessageContent.getContentHandle() + " on "
				+ contentDiscoveredMessageContent.getContentSource() + " to contentDescription " + contentDescription : "");

		return contentDescription;
	}
	
	
}
