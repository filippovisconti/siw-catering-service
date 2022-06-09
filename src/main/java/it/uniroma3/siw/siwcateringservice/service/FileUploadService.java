package it.uniroma3.siw.siwcateringservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {
	@Value("${image.folder}")
	private String imageFolder;

	public File upload (MultipartFile imageFile, String newName) {
		try {
			Path path = Paths.get(imageFolder, newName);
			Files.write(path, imageFile.getBytes());
			return path.toFile();
		} catch (IOException e) {
			System.out.println("ERROR UPLOADING PHOTO: " + e.getMessage());
			return null;
		}
	}
}
