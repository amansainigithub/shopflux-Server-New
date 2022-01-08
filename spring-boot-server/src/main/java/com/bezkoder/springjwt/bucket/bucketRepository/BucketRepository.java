package com.bezkoder.springjwt.bucket.bucketRepository;

import com.bezkoder.springjwt.bucket.model.BucketForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketRepository extends JpaRepository<BucketForm,Long> {

    public List<BucketForm> findBypcId(String pcId);






}
