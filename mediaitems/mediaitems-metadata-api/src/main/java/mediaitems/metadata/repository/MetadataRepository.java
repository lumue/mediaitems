package mediaitems.metadata.repository;

import mediaitems.metadata.domain.Builder;

public interface MetadataRepository<T> {

	public T create(Builder<? extends T> builder);

	public T get(String key);

	public Iterable<? extends T> getAll();

	public Iterable<? extends T> getAll(Integer from, Integer to);

	public void deleteAll();

	public Builder<? extends T> createNewBuilder();
}
