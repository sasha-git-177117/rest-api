package models;

import lombok.*;

@Data
public class TestDataModel {
    private Integer validPostId;
    private Integer invalidPostId;
    private Integer validUserId;
    private Integer validUserIdForValidPost;
}