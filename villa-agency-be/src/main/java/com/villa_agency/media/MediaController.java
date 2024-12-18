package com.villa_agency.media;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.villa_agency.response.GenericResponse;

@RestController
public class MediaController {

	MediaService mediaService;

	public MediaController(MediaService mediaService) {
		super();
		this.mediaService = mediaService;
	}

	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public GenericResponse<String> uploadFile(@RequestPart("file") MultipartFile file) {
		String message = "";

		try {
			mediaService.save(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();

			GenericResponse<String> response = new GenericResponse<>(HttpStatus.OK.value(), message, null);

			// return GenericResponse.status(HttpStatus.OK).body(message);

			return response;
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();

			// return GenericResponse.status(HttpStatus.EXPECTATION_FAILED).body(message);

			GenericResponse<String> response = new GenericResponse<>(HttpStatus.FAILED_DEPENDENCY.value(), message, null);

			return response;
		}
		// return null;
	}

	@GetMapping("/files")
	public ResponseEntity<List<Media>> getListFiles() {
		List<Media> fileInfos = mediaService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(MediaController.class, "getFile", path.getFileName().toString()).build().toString();

			return new Media(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = mediaService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
}
