package com.bezkoder.springjwt.bucket.BucketControllers;

import com.bezkoder.springjwt.bucket.bucketService.BucketImplementations;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.API_PUBLIC_BUCKET)
public class PublicBucketFileController {

    @Autowired
    private BucketImplementations bucketImplementations;

    @GetMapping("/files/{folderName}/{fileName:.+}")
    public ResponseEntity<Resource> getFile(
            @PathVariable("folderName") String folderName,
            @PathVariable("fileName") String fileName
    ) {
        Resource file = bucketImplementations.loadWithImagePath(folderName,fileName);
        System.out.println("File Name");
        System.out.println(file.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
