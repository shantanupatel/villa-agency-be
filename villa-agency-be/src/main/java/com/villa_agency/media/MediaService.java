package com.villa_agency.media;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

	void init();

	public Stream<Path> loadAll();

	public void save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();
}
