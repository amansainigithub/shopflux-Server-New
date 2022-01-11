package com.bezkoder.springjwt.bucket;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class BucketUrl {
    public static final String ROOT="ROOT";
    public static final String SUB="SUB";
    public static final String PRODUCT="PRODUCT";



//    public static String IMAGE_ACCESS_URL="http://localhost:3355/";
    public static String IMAGE_ACCESS_URL="http://64.227.8.158:3355/";

    public  static String API_ACCESS_URI="api/public/bucket/files/";

    public final static  String BANNER_ACCESS_URI ="api/public/bucket/files/";

}
