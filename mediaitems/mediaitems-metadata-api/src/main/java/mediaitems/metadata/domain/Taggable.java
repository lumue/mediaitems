package mediaitems.metadata.domain;

public interface Taggable<T extends Tag> {

	public abstract Iterable<T> getTags();

	public abstract void addTag(T tag);

	public abstract void removeTag(Tag tag);

}