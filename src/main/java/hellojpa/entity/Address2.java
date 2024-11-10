package hellojpa.entity;

import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 * 값타입 컬렉션
 */
@Embeddable
public class Address2 {
    private String street;
    private String city;
    private String zipCode;

    public Address2() {
    }

    public Address2(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address2 address2 = (Address2) o;
        return Objects.equals(street, address2.street) && Objects.equals(city, address2.city)
                && Objects.equals(zipCode, address2.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }
}
