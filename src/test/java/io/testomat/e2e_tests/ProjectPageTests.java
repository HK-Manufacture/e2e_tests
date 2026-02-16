package io.testomat.e2e_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.testomat.e2e_tests.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    @BeforeEach
    void openProjectsPage() {
        app.projectsPage.open();
        app.projectsPage.isLoaded();
    }

    @Test
    public void shouldFindProjectByAuthorizedUserTest() {

        app.projectsPage.searchForProject(targetProjectName);

        app.projectsPage.selectProject(targetProjectName);

        app.projectPage.isLoaded(targetProjectName);

    }

    @Test
    public void shouldNotConsistTestCasesTest() {

        app.projectsPage.searchForProject(targetProjectName);

        var targetProject = app.projectsPage.getTargetProject(targetProjectName);

        app.projectsPage.countOfTestsShouldBeEqualTo(targetProject, 1);

        var countsOfUsers = app.projectsPage.getCountOfUsers(targetProjectName);
        Integer digitUsers = parseIntegerFromString(countsOfUsers);
        Assertions.assertTrue(digitUsers > 1);

    }

    /*@Test
    public void shouldDisplayToolbarWithHoverSuitTest() {

        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);

        projectPage.checkActionButtonsAreVisibleAfterHoveringSuite();

        projectPage.tooltipsAreVisibleAndCorrect();

    }
    */

    @Test
    public void shouldFilterTestsByStateTest() {

        app.projectsPage.searchForProject(targetProjectName);

        app.projectsPage.selectProject(targetProjectName);

        app.projectPage.isLoaded(targetProjectName);

        app.projectPage.openFilters();

        app.projectPage.openDropdown();

        app.projectPage.selectState();

        app.projectPage.applyFilters();
    }

}
