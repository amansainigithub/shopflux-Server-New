package com.bezkoder.springjwt.bucket.bucketInterfaces;

import com.bezkoder.springjwt.bucket.model.BucketFileLinkingForm;
import com.bezkoder.springjwt.bucket.model.BucketForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Bucket  {
    public BucketForm saveFilesInBucket(BucketForm bucketForm);

    public List<BucketForm> getFilesById(String pcId);

    public boolean deleteByBucketId(String bucketId);

    public boolean bindFile(BucketFileLinkingForm bucketFileLinkingForm);

    public boolean removeFileLinking(BucketFileLinkingForm bucketFileLinkingForm);

}
