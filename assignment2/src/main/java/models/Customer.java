package models;

import org.hibernate.validator.constraints.Email;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

public class Customer {
    private String name;
    private String ssn;

    @NotNull(message = "Zip Code cannot be null")
    @Pattern(regexp = "^\\d{5}-\\d{4}$", message = "Zip Code must match XXXXX-XXXX")
    private String zip;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email")
    @Size(min = 7, message = "Email size should be at least 7 characters")
    private String email;
    private String street;
    private String city;
    private String state;

    /** Construct from HttpRequest */
    public Customer(HttpServletRequest request) {
        this.name   = request.getParameter("customername");
        this.ssn    = request.getParameter("ssn");
        this.zip    = request.getParameter("zip");
        this.email  = request.getParameter("email");
        this.street = request.getParameter("street");
        this.city   = request.getParameter("city");
        this.state  = request.getParameter("state");
    }
    /** Getters and Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
