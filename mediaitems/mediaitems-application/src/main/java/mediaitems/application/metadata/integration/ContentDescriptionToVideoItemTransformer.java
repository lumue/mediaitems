package mediaitems.application.metadata.integration;

import mediaitems.metadata.domain.VideoItem;
import mediaitems.metadata.domain.VideoItem.VideoItemBuilder;
import mediaitems.metadata.repository.VideoItemRepository;
import mediaitems.sources.api.io.ContentDescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component("contentDescriptionToVideoItemTransformer")
public class ContentDescriptionToVideoItemTransformer {

	private final VideoItemRepository<?> videoItemRepository;
	
	@Autowired
	public ContentDescriptionToVideoItemTransformer(VideoItemRepository<?> videoItemRepository) {
		super();
		this.videoItemRepository = videoItemRepository;
	}

	@SuppressWarnings("unchecked")
	@Transformer
	public VideoItem transform(ContentDescription contentDescription) {

		@SuppressWarnings("rawtypes")
		VideoItemBuilder builder = this.videoItemRepository.createNewBuilder();

		String path = contentDescription.getPath();
		builder.setName(path.substring(path.lastIndexOf('/') + 1));
		builder.addContentLocation(path);
		builder.setCreationTime(contentDescription.getCreationTime());
		builder.setSize(contentDescription.getSize());

		return videoItemRepository.create(builder);

	}

}
