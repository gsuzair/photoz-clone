package com.unibamberg.uzair.photoz.clone.service;

import com.unibamberg.uzair.photoz.clone.model.Photo;
import com.unibamberg.uzair.photoz.clone.repository.PhotozRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@Component
@Service
public class PhotozService {
    private final PhotozRepository photozRepository;

    public PhotozService(PhotozRepository photozRepository){
        this.photozRepository = photozRepository;
    }

    public Iterable<Photo> get(){
        return photozRepository.findAll();
    }

    public Photo get(Integer id){
        return photozRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photozRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photozRepository.save(photo);
        return photo;
    }
}
