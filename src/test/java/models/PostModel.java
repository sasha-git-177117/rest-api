package models;

import lombok.*;

@Data
public class PostModel {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
