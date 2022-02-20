package com.bezkoder.springjwt.repositories.productCategorySingleRepo;

import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalCategorySingleRepository  extends JpaRepository<CStructureFinalCategory,Long> {
}
