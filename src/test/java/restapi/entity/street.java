package restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class street implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("number")
    private Integer number;
    @JsonProperty("name")
    private String name;
}
