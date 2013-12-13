package mediaitems.metadata.repository;

import mediaitems.metadata.domain.Builder;

public interface MetadataRepository<T> {
	public T create(Builder<T> builder);
	public T get(String key);
	public Iterable<T> getAll();
}
