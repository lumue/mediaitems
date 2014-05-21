package mediaitems.sources.api.io;

import mediaitems.sources.api.error.ContentAccessException;

public interface ContentReader {
	public boolean supportsScheme(String scheme);

	public ContentDescription describe(ContentHandle handle)
			throws ContentAccessException;
}
