package mediaitems.sources.api.io;


public interface ContentBrowser {

	public boolean supportsScheme(String scheme);

	public ContentIterable<? extends ContentHandle> list();
}
