package com.bezkoder.springjwt.bucket.model;

import lombok.Data;

@Data
public class BucketFileLinkingForm {

    private String  bucketId;
    private String categoryId;
    private String categoryHierarchyName;
    private String fileTemplateName; //like image or videos or pdf....etc

}
