package company;

import java.util.List;

public class CreateProductRequest {
    private String name;
    private String description;
    private List<String> imageURLs;
    private int startPrice;
    private String categoryId;
    private String subcategoryId;
    private String creationDateTime;
    private String expirationDateTime;
    private String remainingTime;
    private List<String> bids;
    private String userId;
    private Address address;

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public String getSubcategoryId() {
        return subcategoryId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public List<String> getBids() {
        return bids;
    }

    public String getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    CreateProductRequest(CreateProductRequestBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.imageURLs = builder.imageURLs;
        this.startPrice = builder.startPrice;
        this.categoryId = builder.categoryId;
        this.subcategoryId = builder.subcategoryId;
        this.creationDateTime = builder.creationDateTime;
        this.expirationDateTime = builder.expirationDateTime;
        this.remainingTime = builder.remainingTime;
        this.bids = builder.bids;
        this.userId = builder.userId;
        this.address = builder.address;

    }
    public static class CreateProductRequestBuilder {
        private String name;
        private String description;
        private List<String> imageURLs;
        private int startPrice;
        private String categoryId;
        private String subcategoryId;
        private String creationDateTime;
        private String expirationDateTime;
        private String remainingTime;
        private List<String> bids;
        private String userId;
        private Address address;

        public CreateProductRequestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CreateProductRequestBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CreateProductRequestBuilder setImageURLs(List<String> imagesUrls) {
            this.imageURLs = imagesUrls;
            return this;
        }

        public CreateProductRequestBuilder setStartPrice(int startPrice) {
            this.startPrice = startPrice;
            return this;
        }

        public CreateProductRequestBuilder setCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }
        public CreateProductRequestBuilder setSubcategoryId(String subcategoryId) {
            this.subcategoryId = subcategoryId;
            return this;
        }
        public CreateProductRequestBuilder setCreationDateTime(String creationDateTime) {
            this.creationDateTime = creationDateTime;
            return this;
        }

        public CreateProductRequestBuilder setExpirationDateTime(String expirationDateTime) {
            this.expirationDateTime = expirationDateTime;
            return this;
        }

        public CreateProductRequestBuilder setRemainingTime(String remainingTime) {
            this.remainingTime = remainingTime;
            return this;
        }

        public CreateProductRequestBuilder setBids(List<String> bids) {
            this.bids = bids;
            return this;
        }

        public CreateProductRequestBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public CreateProductRequestBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public CreateProductRequest build() {
            return new CreateProductRequest(this);
        }
    }
}
