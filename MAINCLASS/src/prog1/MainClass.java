package prog1;

import javax.lang.model.SourceVersion;
import mainclass.Login;

public class MainClass {
    public static void main(String[] args) {
        Login login = new Login("Neo", "Nkedi");

        // Try registering a user
        String registerResult = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        System.out.println(registerResult);

        // Try logging in
        boolean loginSuccess = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        System.out.println(login.returnLoginStatus(loginSuccess));

        // Try failing login
        boolean loginFail = login.loginUser("kyl_1", "wrongPass");
        System.out.println(login.returnLoginStatus(loginFail));
    }

}

