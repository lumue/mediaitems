package mediaitems.metadata.repository;

import mediaitems.metadata.domain.Builder;

public interface MetadataRepository<T> {
	
	public T create(Builder<? extends T> builder);
	public T get(String key);
	public Iterable<? extends T> getAll();
	
	public Builder<? extends T> createNewBuilder();
}
