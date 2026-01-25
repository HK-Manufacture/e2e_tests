package io.testomat.e2e_tests;

import io.testomat.e2e_tests.web.pages.ProjectPage;
import io.testomat.e2e_tests.web.pages.ProjectsPage;
import io.testomat.e2e_tests.web.pages.SignInPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.testomat.e2e_tests.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    private static final SignInPage signInPage = new SignInPage();
    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final ProjectPage projectPage = new ProjectPage();

    @BeforeAll
    static void openTestomatAndLogin() {

        signInPage.open();
        signInPage.loginUser(email, password);
        projectsPage.signInSuccessful();
    }

    @BeforeEach
    void openProjectsPage() {
        projectsPage.open();
        projectsPage.isLoaded();
    }

    @Test
    public void shouldFindProjectByAuthorizedUserTest() {

        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);

    }

    @Test
    public void shouldNotConsistTestCasesTest() {

        projectsPage.searchForProject(targetProjectName);

        var targetProject = projectsPage.getTargetProject(targetProjectName);

        projectsPage.countOfTestsShouldBeEqualTo(targetProject, 1);

        var countsOfUsers = projectsPage.getCountOfUsers(targetProjectName);
        Integer digitUsers = parseIntegerFromString(countsOfUsers);
        Assertions.assertTrue(digitUsers > 1);

    }

    @Test
    public void shouldDisplayToolbarWithHoverSuitTest() {

        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);

        projectPage.checkActionButtonsAreVisibleAfterHoveringSuite();

        projectPage.tooltipsAreVisibleAndCorrect();

    }

    @Test
    public void shouldFilterTestsByStateTest() {

        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);

        projectPage.openFilters();

        projectPage.openDropdown();

        projectPage.selectState();

        projectPage.applyFilters();
    }

}
