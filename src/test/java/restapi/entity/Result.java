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
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("results")
    private List<results> resultsObj = null;
    @JsonProperty("info")
    private info infoObj;
}

