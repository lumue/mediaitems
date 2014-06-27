package mediaitems.application.sources.controller;

import java.io.IOException;
import java.io.OutputStream;

import mediaitems.metadata.domain.MediaItem;
import mediaitems.metadata.repository.MediaItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentStreamingController {

	@Autowired(required = true)
	MediaItemRepository<? extends MediaItem> mediaItemRepository;

	@RequestMapping("content/get/{mediaitemKey}")
	@ResponseBody
	public OutputStream streamContent(@PathVariable String mediaitemKey)
			throws IOException {

		MediaItem mediaItem = mediaItemRepository.get(mediaitemKey);
		return createStream(mediaItem);
		// TODO implementieren
	}

	private OutputStream createStream(MediaItem mediaItem) {
		// TODO Auto-generated method stub
		return null;
	}
}
