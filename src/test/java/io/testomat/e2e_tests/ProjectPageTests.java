package io.testomat.e2e_tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.testomat.e2e_tests.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    @BeforeAll
    static void openTestomatAndLogin() {
        open(baseUrl);
        loginUser(email, password);
    }

    @BeforeEach
    void openHomePage() {
        open(baseUrl);
    }

    @Test
    public void shouldFindProjectByAuthorizedUserTest() {

        searchForProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);

    }

    @Test
    public void shouldNotConsistTestCasesTest() {

        searchForProject(targetProjectName);

        SelenideElement targetProject = $$("#grid ul li").filter(visible).findBy(text(targetProjectName));

        countOfTestsShouldBeEqualTo(targetProject, 0);

        countOfUsersShouldBeGraterThen(1);

    }

    @Test
    public void shouldDisplayToolbarWithHoverSuitTest() {

        searchForProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);

        checkActionButtonsAreVisibleAfterHoveringSuite();

        tooltipsAreVisibleAndCorrect();

    }

    private static void tooltipsAreVisibleAndCorrect() {
        checkTooltipIsVisibleAndCorrect("md-icon-star-outline", "Star");
        checkTooltipIsVisibleAndCorrect("md-icon-plus-box-outline", "Create next suite");
        checkTooltipIsVisibleAndCorrect("md-icon-pencil-box-outline", "Edit suite");
        checkTooltipIsVisibleAndCorrect("md-icon-link-variant", "Link suite");
        checkTooltipIsVisibleAndCorrect("md-icon-trash-can-outline", "Delete suite");
    }

    private static void checkActionButtonsAreVisibleAfterHoveringSuite() {
        $$(".flex.justify-between.space-x-2.overflow-hidden.w-full").first().hover();
        for (String s : Arrays.asList(
                "md-icon-star-outline",
                "md-icon-plus-box-outline",
                "md-icon-pencil-box-outline",
                "md-icon-link-variant",
                "md-icon-trash-can-outline")) {
            getActionButtonAfterHoveringSuite(s).shouldBe(visible);
        }
    }

    private static void checkTooltipIsVisibleAndCorrect(String svgClass, String tooltipText) {
        getActionButtonAfterHoveringSuite(svgClass).hover();
        $$("[role=\"tooltip\"]").findBy(text(tooltipText)).shouldBe(visible);
    }

    @NotNull
    private static SelenideElement getActionButtonAfterHoveringSuite(String svgClass) {
        return $$(".nestedItem-toolbar svg").filterBy(cssClass(svgClass)).first().closest("button");
    }

    private void countOfUsersShouldBeGraterThen(int expectedCountOfUser) {
        String countsOfUsers = $$(String.format("[title='%s'] div", targetProjectName)).findBy(text("+")).getText();
        Integer digitUsers = parseIntegerFromString(countsOfUsers);
        Assertions.assertTrue(digitUsers > expectedCountOfUser);
    }

    private static void countOfTestsShouldBeEqualTo(SelenideElement targetProject, int expectedCountOfTests) {
        String countOfTests = targetProject.$("p").getText();
        Integer digitTests = parseIntegerFromString(countOfTests);
        Assertions.assertEquals(expectedCountOfTests, digitTests);
    }

    private static void waitForProjectPageIsLoaded(String targetProjectName) {
        SelenideElement manufactureLight = $(".first [href=\"/projects/manufacture-light/\"]").shouldHave(Condition.text(targetProjectName));
    }

    private static void selectProject(String targetProjectName) {
        $(byText(targetProjectName)).click();
    }

    private static void searchForProject(String targetProjectName) {
        $("#search").setValue(targetProjectName);
    }

    public static void loginUser(String email, String password) {
        $("#content-desktop #user_email").setValue(email);
        $("#content-desktop #user_password").setValue(password);

        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success").shouldBe(visible);
    }

}
