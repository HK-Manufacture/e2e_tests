package io.testomat.e2e_tests;

import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {

    public static Dotenv env = Dotenv.load();

    static String baseUrl = env.get("BASE_URL");
    static String email = env.get("EMAIL");
    static String password = env.get("PASSWORD");
    String targetProjectName = "Manufacture light";

}
