package mediaitems.metadata.domain;

public interface Taggable {

	public abstract Iterable<Tag> getTags();

	public abstract void addTag(Tag tag);

	public abstract void removeTag(Tag tag);

}