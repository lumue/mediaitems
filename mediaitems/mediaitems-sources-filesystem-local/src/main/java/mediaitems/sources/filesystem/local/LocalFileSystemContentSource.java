package mediaitems.sources.filesystem.local;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.UUID;

import mediaitems.configuration.sources.ContentIterable;
import mediaitems.configuration.sources.api.error.ContentAccessException;
import mediaitems.configuration.sources.api.model.ContentHandle;
import mediaitems.configuration.sources.api.model.ContentSource;
import mediaitems.sources.api.io.ContentBrowser;
import mediaitems.sources.api.io.ContentReader;

public class LocalFileSystemContentSource implements ContentSource,
		ContentBrowser, ContentReader {

	private final String id;
	private String name;
	private URI url;

	public LocalFileSystemContentSource() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public LocalFileSystemContentSource(String id) {
		super();
		this.id = id;
	}

	public LocalFileSystemContentSource(String id, String name, String path) {
		super();
		this.id = id;
		this.name = name;
		try {
			this.url = new URI(path);
		} catch (URISyntaxException e) {
			throw new InvalidParameterException(path + "is not a valid url");
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalFileSystemContentSource other = (LocalFileSystemContentSource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocalFileSystemContentSource [id=" + id + ", name=" + name
				+ ", path=" + getUrl() + "]";
	}

	@Override
	public ContentIterable<? extends ContentHandle> list() {
		return new ContentIterable<ContentHandle>() {

			private AsynchronousRecursiveDirectoryStream directoryStream;

			@Override
			public Iterator<ContentHandle> iterator() {
				try {
					directoryStream = new AsynchronousRecursiveDirectoryStream(
							FileSystems.getDefault()
									.getPath(getUrl().getPath()), "*");
					return new DirectoryStreamWrappingContentIterator(
							directoryStream);
				} catch (IOException e) {
					throw new RuntimeException(e);
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
	public URI getUrl() {
		return this.url;
	}

	@Override
	public boolean supportsScheme(String scheme) {
		if ("file".equals(scheme))
			return true;

		return false;
	}

}
