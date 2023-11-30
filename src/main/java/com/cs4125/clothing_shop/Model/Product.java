package com.cs4125.clothing_shop.Model;

import com.cs4125.clothing_shop.Discount.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;

    private @NotNull int stock;
    private @NotNull String description;


    @Transient
    @JsonIgnore
    private DiscountState discountState;

    @Enumerated(EnumType.STRING)
    private Discount discount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;


    public Product(){

    }


    public Product(String name, String imageURL, double price, String description, Category category, Brand brand, Discount discount, int stock) {
        super();
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.stock = stock;
        setDiscount(discount);
        updateDiscountState(discount);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }



    public void setDiscountState(Discount discount) {
        updateDiscountState(discount);
    }

    public DiscountState getDiscountState(){
        return discountState;
    }


    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
        updateDiscountState(discount);
    }


    private void updateDiscountState(Discount discount) {
        switch (discount) {
            case NO_DISCOUNT:
                this.discountState = new NoDiscountState();
                break;
            case MID_SEASON_DISCOUNT:
                this.discountState = new MidSeasonDiscountState();
                break;
            case CLEARANCE_DISCOUNT:
                this.discountState = new ClearanceDiscount();
                break;
            default:
                throw new IllegalArgumentException("Incorrect discount state");
        }
    }




    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString(){
        return "product id = " + id + ", product name = " + name + ", description = " + description + ", price = " + price + ", category:" + category + ", brand = " +brand;
    }
}
