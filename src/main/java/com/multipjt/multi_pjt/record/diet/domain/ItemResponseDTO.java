package com.multipjt.multi_pjt.record.diet.domain;

import lombok.Data;

@Data
public class ItemResponseDTO {
 
    private Long item_id;
    private String item_name;
    private Integer quantity;
    private Integer carbs;
    private Integer protein;
    private Integer fat;
    private Integer calories;
    private Long diet_id;    

}