package mediaitems.application.sources.integration;

import mediaitems.configuration.sources.api.model.ContentSource;
import mediaitems.sources.api.io.ContentHandle;

public class ContentDiscoveredMessageContent {
	private final ContentHandle contentHandle;
	private final ContentSource contentSource;

	public ContentDiscoveredMessageContent(ContentHandle contentHandle,
			ContentSource contentSource) {
		super();
		this.contentHandle = contentHandle;
		this.contentSource = contentSource;
	}

	public ContentHandle getContentHandle() {
		return contentHandle;
	}

	public ContentSource getContentSource() {
		return contentSource;
	}
}