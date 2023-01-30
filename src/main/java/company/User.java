package company;

import com.thoughtworks.qdox.model.expression.Add;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
    private String profileImageUrl;
    private String dateOfBirth;
    private Address address;
    private CreateCreditCardRequest card;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.phoneNumber = builder.phoneNumber;
        this.profileImageUrl = builder.profileImageUrl;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;
        this.card = builder.card;
        this.userId = builder.userId;
    }
    public static class UserBuilder{
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String role;
        private String phoneNumber;
        private String profileImageUrl;
        private String dateOfBirth;
        private Address address;
        private CreateCreditCardRequest card;
        private String userId;

        public UserBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public UserBuilder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserBuilder setCard(CreateCreditCardRequest card) {
            this.card = card;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}