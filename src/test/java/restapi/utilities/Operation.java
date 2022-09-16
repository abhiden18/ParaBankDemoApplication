package restapi.utilities;

import java.util.Random;

public class Operation {

    public static int getRandomNumber(){
        return new Random().nextInt(20);
    }
}
