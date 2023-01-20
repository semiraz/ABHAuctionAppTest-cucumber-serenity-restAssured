package company;

public class Address {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String country;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
    Address(AddressBuilder builder) {
        this.street = builder.street;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
        this.state = builder.state;
        this.country = builder.country;
    }

    public static class AddressBuilder {
        private String street;
        private String city;
        private String zipCode;
        private String state;
        private String country;

        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
