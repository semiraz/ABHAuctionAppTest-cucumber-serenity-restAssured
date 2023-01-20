package company;

public class CreateNewItem {
    private CreateProductRequest createProductRequest;
    private CreateCreditCardRequest createCreditCardRequest;

    public CreateProductRequest getCreateProductRequest() {
        return createProductRequest;
    }

    public CreateCreditCardRequest getCreateCreditCardRequest() {
        return createCreditCardRequest;
    }

    CreateNewItem(CreateNewItemBuilder builder) {
        this.createProductRequest = builder.createProductRequest;
        this.createCreditCardRequest = builder.creditCardRequest;
    }
    public static class CreateNewItemBuilder{
        private CreateProductRequest createProductRequest;
        private CreateCreditCardRequest creditCardRequest;

        public CreateNewItemBuilder setCreateProductRequest(CreateProductRequest createProductRequest) {
            this.createProductRequest = createProductRequest;
            return this;
        }

        public CreateNewItemBuilder setCreditCardRequest(CreateCreditCardRequest creditCardRequest) {
            this.creditCardRequest = creditCardRequest;
            return this;
        }
        public CreateNewItem build() {
            return new CreateNewItem(this);
        }
    }
}
