package mediaitems.sources.filesystem.local;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.joda.time.LocalDateTime;

import mediaitems.sources.api.ContentDescription;
import mediaitems.sources.api.ContentHandle;

class LocalFileSystemIterator implements Iterator<ContentHandle> {

	private static class LocalFileSystemContentDescription implements
			ContentDescription {
		private final String name;
		private final String mimeType;
		private final Long size;
		private final LocalDateTime creationTime;
		private final LocalDateTime lastAccessTime;
		private final LocalDateTime modificationTime;
		private Map<String, Object> properties=Collections.emptyMap();

		
		
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
			Path path=file.toPath();
			BasicFileAttributes attrs = Files.<BasicFileAttributes>readAttributes(path, BasicFileAttributes.class);
			return new LocalFileSystemContentDescription(readName(file),
														 Files.probeContentType(path),
														 Long.valueOf(attrs.size()),
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

	}

	private Iterator<File> fileIterator;

	private Iterator<File> newFileIterator(final File root) {
		return new Iterator<File>() {
			final List<Iterator<File>> dirStack = new ArrayList<Iterator<File>>();
			Iterator<File> currentDir = Arrays.asList(root.listFiles())
					.iterator();
			
			final boolean returnDirs = false;
			final boolean returnFiles = true;

			@Override
			public boolean hasNext() {
				return currentDir.hasNext();
			}

			@Override
			public File next() {
				while (currentDir.hasNext()) {
					final File entry = currentDir.next();
					if (entry.isDirectory()) {
						final File[] entries = entry.listFiles();
						if (entries.length > 0) {
							dirStack.add(currentDir);
							currentDir = Arrays.asList(entry.listFiles())
									.iterator();
							if (returnDirs) {
								return entry;
							}
						}
					} else {
						while (!currentDir.hasNext() && !dirStack.isEmpty()) {
							currentDir = dirStack.remove(dirStack.size() - 1);
						}
						if (returnFiles) {
							return entry;
						}
					}
				}
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	LocalFileSystemIterator(String path) {
		this.fileIterator = newFileIterator(new File(path));
	}

	@Override
	public boolean hasNext() {
		return fileIterator.hasNext();
	}

	@Override
	public ContentHandle next() {
		return LocalFileSystemContentHandle.fromFile(fileIterator.next());
	}

	@Override
	public void remove() {
		throw new IllegalAccessError();
	}

}
