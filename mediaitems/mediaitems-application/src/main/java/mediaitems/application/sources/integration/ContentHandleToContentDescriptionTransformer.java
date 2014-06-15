package mediaitems.application.sources.integration;

import java.net.URI;

import mediaitems.sources.api.error.ContentAccessException;
import mediaitems.sources.api.io.ContentDescription;
import mediaitems.sources.api.io.ContentHandle;
import mediaitems.sources.api.io.ContentReader;
import mediaitems.sources.filesystem.local.LocalFileSystemContentSource;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
@Component("contentHandleToContentDescription")
public class ContentHandleToContentDescriptionTransformer {

	ContentReader contentReader = new LocalFileSystemContentSource(
			URI.create("file:///"));

	@Transformer
	public ContentDescription transform(ContentHandle contentHandle)
			throws ContentAccessException
	{
		return contentReader.describe(contentHandle);
	}
	
	
}
