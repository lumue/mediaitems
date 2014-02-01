package mediaitems.sources.filesystem.local;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Iterator;

import mediaitems.configuration.sources.api.model.ContentHandle;

class DirectoryStreamWrappingContentIterator implements Iterator<ContentHandle> {

	private final Iterator<Path> fileIterator;

	private Iterator<Path> newFileIterator(DirectoryStream<Path> directoryStream)
			throws IOException {
		return directoryStream.iterator();
	}

	DirectoryStreamWrappingContentIterator(DirectoryStream<Path> directoryStream)
			throws IOException {
		this.fileIterator = newFileIterator(directoryStream);
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
