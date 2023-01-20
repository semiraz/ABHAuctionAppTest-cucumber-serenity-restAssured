package company;

public class CreateCreditCardRequest {
    private String holderFullName;
    private String number;
    private String expirationDate;
    private String verificationValue;

    public String getHolderFullName() {
        return holderFullName;
    }

    public String getNumber() {
        return number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getVerificationValue() {
        return verificationValue;
    }

    CreateCreditCardRequest(CreateCreditCardRequestBuilder builder) {
        this.holderFullName = builder.holderFullName;
        this.number = builder.number;
        this.expirationDate = builder.expirationDate;
        this.verificationValue = builder.verificationValue;
    }

    public static class CreateCreditCardRequestBuilder {
        private String holderFullName;
        private String number;
        private String expirationDate;
        private String verificationValue;

        public CreateCreditCardRequestBuilder setHolderFullName(String holderFullName) {
            this.holderFullName = holderFullName;
            return this;
        }

        public CreateCreditCardRequestBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public CreateCreditCardRequestBuilder setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public CreateCreditCardRequestBuilder setVerificationValue(String verificationValue) {
            this.verificationValue = verificationValue;
            return this;
        }

        public CreateCreditCardRequest build() {
            return  new CreateCreditCardRequest(this);
        }
    }
}