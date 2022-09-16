package restapi.stepdefinitions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginUser {

    static String senderUserName;
    static String senderPassword;
    static String senderAccountNumber;

    static String payeeUserName;
    static String payeePassword;

}
