package mediaitems.common.repository.api;

import mediaitems.common.domain.api.Builder;

public interface ObjectRepository<T> {

	public T create(Builder<? extends T> builder);

	public T get(String key);

	public Iterable<? extends T> getAll();

	public Iterable<? extends T> getAll(Integer from, Integer to);

	public void deleteAll();

	public Builder<? extends T> createNewBuilder();
}
