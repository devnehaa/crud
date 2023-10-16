package com.image.crud.service;

import java.io.IOException;
import java.util.stream.Stream;

import com.image.crud.entity.FileDB;
import com.image.crud.repo.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;

  public FileDB store(MultipartFile file) throws IOException {

    String fileName = StringUtils.cleanPath(file.getOriginalFilename());


    String contentType = file.getContentType();
    byte[] bytes = file.getBytes();

    FileDB fileDB = FileDB.builder()
            .name(fileName)
            .type(contentType)
            .data(bytes)
            .build();

    return fileDBRepository.save(fileDB);

  }

  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }

}