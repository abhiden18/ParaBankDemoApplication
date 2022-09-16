package restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class timezone implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("offset")
    private String offset;
    @JsonProperty("description")
    private String description;
}
