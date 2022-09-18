package restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("gender")
    private String gender;
    @JsonProperty("name")
    private Name nameObj;
    @JsonProperty("location")
    private Location locationObj;
    @JsonProperty("email")
    private String email;
    @JsonProperty("login")
    private Login loginObj;
    @JsonProperty("dob")
    private Dob dobObj;
    @JsonProperty("registered")
    private Registered registeredObj;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("id")
    private Id idObj;
    @JsonProperty("picture")
    private Picture pictureObj;
    @JsonProperty("nat")
    private String nat;

}
