package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestData {
    TEST_DATA_PATH("src/test/resources/testData.json"),
    TEST_VALID_POST_FOR_VALID_USER_PATH("src/test/resources/validPostForValidUser.json"),
    TEST_VALID_USER_PATH("src/test/resources/validUser.json");

    public final String label;
}
