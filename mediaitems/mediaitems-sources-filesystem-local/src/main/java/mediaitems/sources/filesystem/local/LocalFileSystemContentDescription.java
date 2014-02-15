package mediaitems.sources.filesystem.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Map;

import mediaitems.sources.api.io.ContentDescription;

import org.joda.time.LocalDateTime;

class LocalFileSystemContentDescription implements ContentDescription {
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
		BasicFileAttributes attrs = Files.<BasicFileAttributes> readAttributes(
				path, BasicFileAttributes.class);
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