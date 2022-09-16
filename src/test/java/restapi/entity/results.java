package restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class results implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("gender")
    private String gender;
    @JsonProperty("name")
    private name nameObj;
    @JsonProperty("location")
    private location locationObj;
    @JsonProperty("email")
    private String email;
    @JsonProperty("login")
    private login loginObj;
    @JsonProperty("dob")
    private dob dobObj;
    @JsonProperty("registered")
    private registered registeredObj;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("id")
    private id idObj;
    @JsonProperty("picture")
    private picture pictureObj;
    @JsonProperty("nat")
    private String nat;

}
