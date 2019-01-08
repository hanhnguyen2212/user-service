package hanh.com.hn.userservice.Services;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidation {


    public static boolean isValid(String passwordhere) {
        boolean checkUpperCase =false;
        boolean checkSpecialCase= false;
        for (int p = 0; p < passwordhere.length(); p++) {
            if (Character.isUpperCase(passwordhere.charAt(p))) {
                checkUpperCase=true;
            }

            for (int r = 0; r < passwordhere.length(); r++) {
                if (isSpecialCharacter(passwordhere.charAt(r))) {
                    checkSpecialCase=true;
                }
            }
        }
        if(checkSpecialCase&&checkUpperCase)
            return true;
        else
            return false;
    }
    private static boolean isSpecialCharacter(Character c)
    {
        return !c.toString().matches("[^a-z A-Z0-9]");
    }
}
