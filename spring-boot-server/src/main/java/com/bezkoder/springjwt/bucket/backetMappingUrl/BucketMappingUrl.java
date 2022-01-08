package com.bezkoder.springjwt.bucket.backetMappingUrl;

import org.springframework.stereotype.Component;

@Component
public class BucketMappingUrl {

    public static final String UPLOADS_FILES="/uploadFiles/{pcName}/{pcId}";
    public static final String GET_FILES_BY_ID="/getFiles/{pcId}";
    public static final String DELETE_BUCKET_FILES_BY_ID="/deleteFiles/{bucketId}";

    public static final String BIND_FILE_WITH_URLS="/bindFile";


}
