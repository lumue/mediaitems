package mediaitems.sources.filesystem.local;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

import mediaitems.sources.api.ContentDescription;
import mediaitems.sources.api.ContentHandle;

class LocalFileSystemContentHandle implements ContentHandle {

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