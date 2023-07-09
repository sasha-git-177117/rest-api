package models;

import lombok.*;

@Data
public class AddressModel {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoModel geo;

}
