package com.cs4125.clothing_shop.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand_name")
    private @NotBlank String brandName;
    private @NotBlank String description;
    private @NotBlank String imageUrl;

    public Brand(){
    }

    public Brand(@NotBlank String brandName, @NotBlank String description){
        this.brandName = brandName;
        this.description = description;
    }

    public Brand(@NotBlank String brandName,@NotBlank String description,@NotBlank String imageUrl) {
        this.brandName = brandName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Override
    public String toString(){
        return "User {brand id = " + id + ", brand name = " + brandName + ", description=" + description + "}";
    }
}


