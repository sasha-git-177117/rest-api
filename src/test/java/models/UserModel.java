package models;

import lombok.*;

@Data
public class UserModel {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private AddressModel address;
    private String phone;
    private String website;
    private CompanyModel company;

}
