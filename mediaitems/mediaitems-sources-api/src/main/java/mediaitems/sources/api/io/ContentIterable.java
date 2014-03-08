package mediaitems.sources.api.io;

import java.util.Iterator;

import mediaitems.sources.api.error.ContentAccessException;

public interface ContentIterable<T extends ContentHandle> extends Iterable<T> {
	
	
	/**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
	@Override
    Iterator<T> iterator();
	
	public void close() throws ContentAccessException;
}
