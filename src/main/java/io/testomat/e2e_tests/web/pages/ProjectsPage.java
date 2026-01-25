package io.testomat.e2e_tests.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsPage {

    private final SelenideElement searchInput = $("#search");

    public void signInSuccessful() {
        $("#container .common-flash-success").shouldBe(visible);
    }

    public void open() {
        Selenide.open("/");
    }

    public void isLoaded() {
        searchInput.shouldBe(visible);
    }

    public void searchForProject(String targetProjectName) {
        searchInput.setValue(targetProjectName);
    }

    public void selectProject(String targetProjectName) {
        $(byText(targetProjectName)).click();
    }

    public SelenideElement getTargetProject(String targetProjectName) {
        return $$("#grid ul li")
                .filter(visible)
                .findBy(text(targetProjectName));
    }

    public String getCountOfUsers(String targetProjectName) {
        return $$(String.format("[title='%s'] div", targetProjectName)).findBy(text("+")).getText();
    }

    public void countOfTestsShouldBeEqualTo(SelenideElement targetProject, int expectedCountOfTests) {
        targetProject.shouldHave(text(expectedCountOfTests + " tests"));

        /*\String countOfTests = targetProject.$("p").getText();
        Integer digitTests = parseIntegerFromString(countOfTests);
        Assertions.assertEquals(expectedCountOfTests, digitTests);*/
    }

}
