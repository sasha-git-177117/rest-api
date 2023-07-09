package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Endpoints {
    GET_ALL_POSTS("/posts"),
    GET_POST_BY_ID ("/posts/%s"),
    GET_ALL_USERS("/users"),
    GET_USER_BY_ID ("/users/%s"),
    POST_POST("/posts");

    public final String label;
}
