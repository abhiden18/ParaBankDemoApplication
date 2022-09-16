package restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class info implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("seed")
    private String seed;
    @JsonProperty("results")
    private int results;
    @JsonProperty("page")
    private int page;
    @JsonProperty("version")
    private String version;
}
