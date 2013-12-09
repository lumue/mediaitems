package mediaitems.sources.filesystem.local;

import java.util.Iterator;
import java.util.UUID;

import mediaitems.sources.api.ContentHandle;
import mediaitems.sources.api.ContentSource;

public class LocalFileSystemContentSource implements ContentSource{

	private String id;
	private String name;
	private String path;	
	
	public LocalFileSystemContentSource() {
		super();
		this.id=UUID.randomUUID().toString();
	}

	public LocalFileSystemContentSource(String id) {
		super();
		this.id = id;
	}

	public LocalFileSystemContentSource(String id, String name, String path) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalFileSystemContentSource other = (LocalFileSystemContentSource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocalFileSystemContentSource [id=" + id + ", name=" + name
				+ ", path=" + path + "]";
	}

	@Override
	public Iterator<ContentHandle> iterator() {
		return new LocalFileSystemIterator(getPath());
		
	}
	
	
	
	
}
