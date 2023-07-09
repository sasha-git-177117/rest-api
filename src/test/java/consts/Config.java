package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Config {
    BASE_URI_PATH("src/test/resources/config.json"),
    BASE_URI_JSON_PATH("$.baseURI");

    public final String label;
}
