package mediaitems.sources.filesystem.local;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;

import mediaitems.sources.api.error.ContentAccessException;
import mediaitems.sources.api.error.ContentAccessException.RuntimeContentAccessException;
import mediaitems.sources.api.io.ContentBrowser;
import mediaitems.sources.api.io.ContentDescription;
import mediaitems.sources.api.io.ContentHandle;
import mediaitems.sources.api.io.ContentIterable;
import mediaitems.sources.api.io.ContentReader;

public class LocalFileSystemContentSource implements ContentBrowser,
		ContentReader {

	private final Path path;

	private final FileChecksumBuilder checksumBuilder = new FileChecksumBuilder();

	public LocalFileSystemContentSource(URI uri) {
		this.path = FileSystems.getDefault().getPath(uri.getPath());
	}

	@Override
	public ContentIterable<? extends ContentHandle> list() {
		return new ContentIterable<ContentHandle>() {

			private AsynchronousRecursiveDirectoryStream directoryStream;

			@Override
			public Iterator<ContentHandle> iterator() {
				try {
					directoryStream = new AsynchronousRecursiveDirectoryStream(
							path, "*");
					return new DirectoryStreamWrappingContentIterator(
							directoryStream);
				} catch (IOException e) {
					throw new RuntimeContentAccessException(e);
				}

			}

			@Override
			public void close() throws ContentAccessException {
				try {
					directoryStream.close();
				} catch (IOException e) {
					throw new ContentAccessException(e);
				}
			}
		};
	}

	@Override
	public boolean supportsScheme(String scheme) {
		if ("file".equals(scheme))
			return true;

		return false;
	}

	@Override
	public ContentDescription describe(ContentHandle handle)
			throws ContentAccessException {
		try {
			return LocalFileSystemContentDescription.fromPath(FileSystems
					.getDefault().getPath(
							handle.getLocationIdentifier().getPath()));
		} catch (IOException e) {
			throw new ContentAccessException(e);
		}
	}

	@Override
	public String checksum(ContentHandle handle) throws ContentAccessException {
		try {
		return this.checksumBuilder.buildChecksum(FileSystems.getDefault()
				.getPath(handle.getLocationIdentifier().getPath()));
		} catch (IOException e) {
			throw new ContentAccessException(e);
		}
	}

}
