package com.bezkoder.springjwt.entities.categoryProductSingleEntites;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class CStructureFinalCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 1000)
    private String finalCategoryName;

    @Column(length = 1000)
    private String finalCategoryId;

    @Column(length = 1000)
    private String finalCategoryType;

    @Column(length = 1000)
    private String finalCategoryDesc;


}
