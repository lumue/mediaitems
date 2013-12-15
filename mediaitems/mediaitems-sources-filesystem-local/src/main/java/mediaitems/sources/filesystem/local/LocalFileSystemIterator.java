package mediaitems.sources.filesystem.local;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import mediaitems.sources.api.ContentDescription;
import mediaitems.sources.api.ContentHandle;

import org.joda.time.LocalDateTime;

class LocalFileSystemIterator implements Iterator<ContentHandle> {

	private static class LocalFileSystemContentDescription implements
			ContentDescription {
		private final String name;
		private final String mimeType;
		private final Long size;
		private final LocalDateTime creationTime;
		private final LocalDateTime lastAccessTime;
		private final LocalDateTime modificationTime;
		private final Map<String, Object> properties = Collections.emptyMap();

		private LocalFileSystemContentDescription(String name, String mimeType,
				Long size, LocalDateTime creationTime,
				LocalDateTime lastAccessTime, LocalDateTime modificationTime) {
			super();
			this.name = name;
			this.mimeType = mimeType;
			this.size = size;
			this.creationTime = creationTime;
			this.lastAccessTime = lastAccessTime;
			this.modificationTime = modificationTime;
		}

		public static ContentDescription fromFile(File file) throws IOException {
			Path path = file.toPath();
			BasicFileAttributes attrs = Files
					.<BasicFileAttributes> readAttributes(path,
							BasicFileAttributes.class);
			return new LocalFileSystemContentDescription(readName(file),
					Files.probeContentType(path), Long.valueOf(attrs.size()),
					new LocalDateTime(attrs.creationTime().toMillis()),
					new LocalDateTime(attrs.lastAccessTime().toMillis()),
					new LocalDateTime(attrs.creationTime().toMillis()));

		}

		private static String readName(File file) {
			return file.getName();
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public Map<String, Object> getProperties() {
			return this.properties;
		}

		@Override
		public String getMimeType() {
			return mimeType;
		}

		@Override
		public Long getSize() {
			return size;
		}

		@Override
		public LocalDateTime getCreationTime() {
			return creationTime;
		}

		@Override
		public LocalDateTime getModificationTime() {
			return modificationTime;
		}

		@Override
		public LocalDateTime getLastAccessTime() {
			return lastAccessTime;
		}

	}

	private static class LocalFileSystemContentHandle implements ContentHandle {

		private final File file;
		private String canonicalPath;
		private ContentDescription contentDescription;

		private LocalFileSystemContentHandle(File file) {
			this.file = file;
		}

		@Override
		public OutputStream newOutputStream() throws IOException {
			return new FileOutputStream(this.file);
		}

		@Override
		public ContentDescription getDescription() throws IOException {

			if (this.contentDescription != null)
				return this.contentDescription;

			this.contentDescription = readContentDescription();

			return this.contentDescription;
		}

		private ContentDescription readContentDescription() throws IOException {
			return LocalFileSystemContentDescription.fromFile(file);
		}

		public static ContentHandle fromFile(File file) {
			return new LocalFileSystemContentHandle(file);
		}

		@Override
		public String getLocationIdentifier() throws IOException {

			if (canonicalPath != null)
				return canonicalPath;

			synchronized (this.file) {
				canonicalPath = file.getCanonicalPath();
			}

			return canonicalPath;
		}

		@Override
		public byte[] createThumb() throws IOException {
			return null;
		}

		public static ContentHandle fromPath(Path next) {
			return fromFile(next.toFile());
		}

	}

	private final Iterator<Path> fileIterator;

	private Iterator<Path> newFileIterator(final Path root) throws IOException {
		return new AsynchronousRecursiveDirectoryStream(root, "*").iterator();
	}

	LocalFileSystemIterator(String path) throws IOException {
		this.fileIterator = newFileIterator(FileSystems.getDefault().getPath(
				path));
	}

	@Override
	public boolean hasNext() {
		return fileIterator.hasNext();
	}

	@Override
	public ContentHandle next() {
		return LocalFileSystemContentHandle.fromPath(fileIterator.next());
	}

	@Override
	public void remove() {
		throw new IllegalAccessError();
	}

}
