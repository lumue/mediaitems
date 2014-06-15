package mediaitems.application.sources.integration;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import mediaitems.common.domain.api.Builder;
import mediaitems.configuration.sources.api.model.ContentSource;
import mediaitems.configuration.sources.api.model.ContentSource.ContentSourceBuilder;
import mediaitems.configuration.sources.api.repository.ContentSourceRepository;

import org.springframework.stereotype.Component;

/**
 * Nur für Testzwecke
 * @author lm
 *
 */
@Component("staticContentSourceRepo")
public class StaticContentSourceRepo implements ContentSourceRepository {

	private final Map<String, ContentSource> contentSources = new ConcurrentHashMap<String, ContentSource>();
	
	@PostConstruct
	public void populate()
	{
		create(createBuilder("thinkpad", "file:///mnt/media_thinkpad/media"));
		create(createBuilder("home", "file:///home/lm"));
	}

	private ContentSourceBuilder createBuilder(String name, String url) {
		ContentSourceBuilder builder = createNewBuilder();
		builder.setUrl(URI.create(url));
		builder.setName(name);
		builder.setKey(name);
		return builder;
	}

	@Override
	public ContentSource create(Builder<? extends ContentSource> builder) {
		ContentSource source = builder.build();
		contentSources.put(source.getId(), source);
		return source;
	}

	@Override
	public ContentSource get(String key) {
		return contentSources.get(key);
	}

	@Override
	public Iterable<? extends ContentSource> getAll() {
		return contentSources.values();
	}

	@Override
	public Iterable<? extends ContentSource> getAll(Integer from, Integer to) {
		return contentSources.values();
	}

	@Override
	public void deleteAll() {
		contentSources.clear();
	}

	@Override
	public ContentSourceBuilder createNewBuilder() {
		return new ContentSourceBuilder();
	}

}
