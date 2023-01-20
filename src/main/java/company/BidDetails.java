package company;

public class BidDetails {
    private double price;
    private String productId;
    private String userId;

    public double getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }
    BidDetails (BidDetailsBuilder builder) {
        this.price = builder.price;
        this.productId = builder.productId;
        this.userId = builder.userId;
    }
    public static class BidDetailsBuilder {
        private double price;
        private String productId;
        private String userId;

        public BidDetailsBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public BidDetailsBuilder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public BidDetailsBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public BidDetails build() {
            return new BidDetails(this);
        }
    }
}