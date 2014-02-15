package mediaitems.sources.filesystem.local;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

import mediaitems.sources.api.io.ContentHandle;

class LocalFileSystemContentHandle implements ContentHandle {

	private final File file;

	private LocalFileSystemContentHandle(File file) {
		this.file = file;
	}

	public static ContentHandle fromFile(File file) {
		return new LocalFileSystemContentHandle(file);
	}

	public static ContentHandle fromPath(Path next) {
		return fromFile(next.toFile());
	}

	@Override
	public URI getLocationIdentifier() throws IOException {
		return file.toURI();
	}

}