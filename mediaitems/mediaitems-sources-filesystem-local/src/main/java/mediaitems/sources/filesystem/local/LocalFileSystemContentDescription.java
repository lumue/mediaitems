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
	private final String mediaType;
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
		this.mediaType = fromMimeType(mimeType);
		this.size = size;
		this.creationTime = creationTime;
		this.lastAccessTime = lastAccessTime;
		this.modificationTime = modificationTime;
	}

	private static String fromMimeType(String mimeType) {
		if (mimeType.startsWith("video"))
			return "VIDEO";
		if (mimeType.startsWith("audio"))
			return "AUDIO";
		if (mimeType.startsWith("image"))
			return "IMAGE";
		if (mimeType.startsWith("text") || mimeType.startsWith("application"))
			return "DOCUMENT";

		return "GENERIC";
	}

	public static ContentDescription fromFile(File file) throws IOException {
		return fromPath(file.toPath());
	}

	public static ContentDescription fromPath(Path path) throws IOException {
		BasicFileAttributes attrs = Files.<BasicFileAttributes> readAttributes(
				path, BasicFileAttributes.class);
		return new LocalFileSystemContentDescription(path.toString(),
				Files.probeContentType(path), Long.valueOf(attrs.size()),
				new LocalDateTime(attrs.creationTime().toMillis()),
				new LocalDateTime(attrs.lastAccessTime().toMillis()),
				new LocalDateTime(attrs.creationTime().toMillis()));
	}




	@Override
	public String getPath() {
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

	@Override
	public String getMediaType() {
		// TODO Auto-generated method stub
		return null;
	}

}