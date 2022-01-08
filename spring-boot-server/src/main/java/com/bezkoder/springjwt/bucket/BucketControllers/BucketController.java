package com.bezkoder.springjwt.bucket.BucketControllers;

import com.bezkoder.springjwt.bucket.backetMappingUrl.BucketMappingUrl;
import com.bezkoder.springjwt.bucket.bucketService.BucketImplementations;
import com.bezkoder.springjwt.bucket.model.BucketFileLinkingForm;
import com.bezkoder.springjwt.bucket.model.BucketForm;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class BucketController {

    @Autowired
    private BucketImplementations bucketImplementations;

    @PostMapping(BucketMappingUrl.UPLOADS_FILES)
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files,
                                         @PathVariable("pcName") String pcName,
                                         @PathVariable("pcId") String pcId)
    {
        try {

        List node = this.bucketImplementations.uploadService(files,pcName,pcId);
        if(node !=null)
        return ResponseEntity.status(HttpStatus.OK).body(node);
        else
            throw new NullPointerException(("Data Not Here !!!"));
    }
    catch (Exception e)
    {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Something went wrong"));
    }

    }

    @GetMapping(BucketMappingUrl.GET_FILES_BY_ID)
    public ResponseEntity<?> getFileById(@PathVariable("pcId")String pcId) throws URISyntaxException, IOException {


        List list= this.bucketImplementations.getFilesById(pcId);
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Data Not found Here !!!"));
        }

    }

    @DeleteMapping(BucketMappingUrl.DELETE_BUCKET_FILES_BY_ID)
    public ResponseEntity<?> deleteBucketFileById(@PathVariable("bucketId")String bucketId)
    {
            if(this.bucketImplementations.deleteByBucketId(bucketId)==true)
            {
                return ResponseEntity.status(HttpStatus.OK).build();
            }

            else
            {
             return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
    }


    @PutMapping(BucketMappingUrl.BIND_FILE_WITH_URLS)
    public ResponseEntity<?> bindFile( @RequestBody BucketFileLinkingForm bucketFileLinkingForm  )
    {
        try {
            if( this.bucketImplementations.bindFile(bucketFileLinkingForm))
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                throw new NullPointerException(("Data Not Here !!!"));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Something went wrong"));
        }
    }

    //ABSOLUTE    PATH
//    URL res = getClass().getClassLoader().getResource("");
//    File file = Paths.get(res.toURI()).toFile();
//    String absolutePath = file.getAbsolutePath();
//        System.out.println(absolutePath+"000000");


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
