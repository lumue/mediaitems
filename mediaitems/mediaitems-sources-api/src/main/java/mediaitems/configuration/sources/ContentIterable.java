package mediaitems.configuration.sources;

import java.util.Iterator;

import mediaitems.configuration.sources.api.error.ContentAccessException;
import mediaitems.configuration.sources.api.model.ContentHandle;

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
