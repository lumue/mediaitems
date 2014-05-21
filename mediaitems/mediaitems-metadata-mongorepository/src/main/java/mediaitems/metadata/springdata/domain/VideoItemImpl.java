package mediaitems.metadata.springdata.domain;

import java.util.ArrayList;
import java.util.List;

import mediaitems.metadata.domain.MediaType;
import mediaitems.metadata.domain.VideoItem;

import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

public class VideoItemImpl extends MediaItemImpl implements VideoItem {

	private final String videoCodec;
	private final Duration length;
	private final String audioCodec;

	
	
	public VideoItemImpl(
			String name,
			MediaType mediaType,
			List<mediaitems.metadata.springdata.domain.ContentLocation> contentLocation,
			Long size, LocalDateTime time, String videoCodec, Duration length,
			String audioCodec) {
		super(name, mediaType, contentLocation, size, time);
		this.videoCodec = videoCodec;
		this.length = length;
		this.audioCodec = audioCodec;
	}

	@Override
	public String getVideoCodec() {
		return this.videoCodec;
	}

	@Override
	public String getAudioCodec() {
		return this.audioCodec;
	}

	@Override
	public Duration getLength() {
		return this.length;
	}

	public static class VideoItemBuilderImpl implements
			VideoItemBuilder<VideoItemImpl> {

		private Duration length;
		private String audioCodec;
		private String videoCodec;
		private String name;
		private MediaType mediaType;
		private final List<ContentLocation> contentLocation = new ArrayList<ContentLocation>();
		private Long size;
		private LocalDateTime time;

		@Override
		public VideoItemImpl build() {
			return new VideoItemImpl(this.name, this.mediaType,
					this.contentLocation, this.size, this.time,
					this.videoCodec, this.length, this.audioCodec);

		}

		@Override
		public VideoItemBuilderImpl setName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public VideoItemBuilderImpl setMediaType(MediaType mediaType) {
			this.mediaType = mediaType;
			return this;
		}

		@Override
		public VideoItemBuilderImpl addContentLocation(String location) {
			this.contentLocation.add(new ContentLocation(location));
			return this;
		}

		@Override
		public VideoItemBuilderImpl setSize(Long size) {
			this.size = size;
			return this;
		}

		@Override
		public VideoItemBuilderImpl setCreationTime(LocalDateTime time) {
			this.time = time;
			return this;
		}

		@Override
		public mediaitems.metadata.domain.MediaItem.MediaItemBuilder<VideoItemImpl> setContentLocation(
				String location) {
			this.contentLocation.clear();
			this.addContentLocation(location);
			return this;
		}

		@Override
		public VideoItemBuilder<VideoItemImpl> setVideoCodec(String codec) {
			this.videoCodec = codec;
			return this;
		}

		@Override
		public VideoItemBuilder<VideoItemImpl> setAudioCodec(String codec) {
			this.audioCodec = codec;
			return this;
		}

		@Override
		public VideoItemBuilder<VideoItemImpl> setLength(Duration duration) {
			this.length = duration;
			return this;
		}

	}
}
