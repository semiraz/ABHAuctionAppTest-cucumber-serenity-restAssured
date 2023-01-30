package company;

public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private String dateOfBirth;
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public UpdateUserRequest(UpdateUserRequestBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.profileImageUrl = builder.profileImageUrl;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;
    }

    public static class UpdateUserRequestBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String profileImageUrl;
        private String dateOfBirth;
        private Address address;

        public UpdateUserRequestBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UpdateUserRequestBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UpdateUserRequestBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UpdateUserRequestBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UpdateUserRequestBuilder setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public UpdateUserRequestBuilder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UpdateUserRequestBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }
         public UpdateUserRequest build() {
            return new UpdateUserRequest(this);
         }
    }
}
