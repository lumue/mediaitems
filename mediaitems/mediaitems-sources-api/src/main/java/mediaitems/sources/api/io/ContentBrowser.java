package mediaitems.sources.api.io;

import mediaitems.configuration.sources.ContentIterable;
import mediaitems.configuration.sources.api.model.ContentHandle;

public interface ContentBrowser {

	public boolean supportsScheme(String scheme);

	public ContentIterable<? extends ContentHandle> list();
}
