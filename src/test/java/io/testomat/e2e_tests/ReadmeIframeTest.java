package io.testomat.e2e_tests;

import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TextReportExtension.class})

public class ReadmeIframeTest extends BaseTest {

    @Test
    @DisplayName("Update readme text in iframe")
    void updateReadmeTextInIframe() {

        app.projectsPage.isLoaded()
                .searchForProject(targetProjectName)
                .selectProject(targetProjectName);

        app.projectPage.isLoaded(targetProjectName)
                .openReadme()
                .clickOnEdit();

        app.readmePage.isLoaded()
                .clickOnEditReadme()
                .editTextInEditor(updatedText)
                .clickOnUpdateButton()
                .editReadmeIsNotAllowed()
                .clickOnCancelButton()
                .clickOnEditReadme()
                .changesWereNotAppliedAfterCancelling(updatedText);
    }


}
