package io.testomat.e2e_tests;

import com.codeborne.selenide.Configuration;
import io.github.cdimascio.dotenv.Dotenv;
import io.testomat.e2e_tests.common.Application;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static Dotenv env = Dotenv.load();

    static {
        Configuration.baseUrl = env.get("BASE_URL");
        //Configuration.headless = true;
    }

    protected static String baseUrl = env.get("BASE_URL");
    protected static String email = env.get("EMAIL");
    protected static String password = env.get("PASSWORD");
    protected String targetProjectName = "Manufacture light";
    protected String updatedText = "TEST";

    protected static Application app = new Application();

    @BeforeAll
    static void openTestomatAndLogin() {

        app.signInPage.open();
        app.signInPage.loginUser(email, password);
        app.projectsPage.signInSuccessful();
    }

}
