package restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("results")
    private List<Results> resultsObj = null;
    @JsonProperty("info")
    private Info infoObj;
}

