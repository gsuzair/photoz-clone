package com.unibamberg.uzair.photoz.clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService){
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get(){
        return photozService.get();
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id){
        Photo photo = photozService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photozService.get(id);
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id){
        Photo photo = photozService.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
