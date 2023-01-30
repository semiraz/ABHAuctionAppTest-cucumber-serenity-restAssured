package company;

public class UpdateUser {
    private CreateCreditCardRequest updateCreditCardRequest;
    private UpdateUserRequest updateUserRequest;

    public CreateCreditCardRequest getUpdateCreditCardRequest() {
        return updateCreditCardRequest;
    }

    public UpdateUserRequest getUpdateUserRequest() {
        return updateUserRequest;
    }
    public UpdateUser(UpdateUserBuilder builder) {
        this.updateUserRequest = builder.updateUserRequest;
        this.updateCreditCardRequest = builder.updateCreditCardRequest;
    }

    public static class UpdateUserBuilder {
        private CreateCreditCardRequest updateCreditCardRequest;
        private UpdateUserRequest updateUserRequest;

        public UpdateUserBuilder setUpdateCreditCardRequest(CreateCreditCardRequest updateCreditCardRequest) {
            this.updateCreditCardRequest = updateCreditCardRequest;
            return this;
        }

        public UpdateUserBuilder setUpdateUserRequest(UpdateUserRequest updateUserRequest) {
            this.updateUserRequest = updateUserRequest;
            return this;
        }

        public UpdateUser build() {
            return new UpdateUser(this);
        }
    }
}
