package mediaitems.common.repository.api;

import mediaitems.common.domain.api.Builder;

public interface ObjectRepository<T> {

	public T create(Builder<T> builder);

	public T get(String key);

	public Iterable<T> getAll();

	public Iterable<T> getAll(Integer from, Integer to);

	public void deleteAll();

	public Builder<T> createNewBuilder();
}
