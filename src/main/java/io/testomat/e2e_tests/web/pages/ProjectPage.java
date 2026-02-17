package io.testomat.e2e_tests.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage {

    public ProjectPage isLoaded(String targetProjectName) {
        $(".first [href='/projects/manufacture-light/']").shouldHave(Condition.text(targetProjectName));
        return this;
    }

    private static final List<String> TOOLTIP_TEXTS = Arrays.asList(
            "Star",
            "Create next suite",
            "Edit suite",
            "Link suite",
            "Delete suite"
    );

    @NotNull
    private SelenideElement getFirstSuite() {
        return $$(".nestedItem.sort-list-empty").first().hover();

    }

    public void checkActionButtonsAreVisibleAfterHoveringSuite() {
        getFirstSuite().$$(".nestedItem-toolbar button").forEach(button ->
                button.shouldBe(visible)
        );
    }

    public void tooltipsAreVisibleAndCorrect() {
        ElementsCollection actionButtons = getFirstSuite().$$(".nestedItem-toolbar button");

        for (int i = 0; i < TOOLTIP_TEXTS.size(); i++) {
            actionButtons.get(i).hover();
            $$("[role=tooltip]")
                    .findBy(text(TOOLTIP_TEXTS.get(i)))
                    .shouldBe(visible);
        }

    }

    public void openFilters() {
        $(".filterbar-filter-btn-div button").click();
        $(".second h3").shouldBe(visible);
    }

    public void openDropdown() {
        $$(".second li [role=button]").findBy(text("Select state")).click();
    }

    public void selectState() {
        $$(".second [role=option]").findBy(text("Manual")).shouldBe(visible).click();
    }

    public void applyFilters() {
        $$(".pt-5 button").findBy(text("Apply")).click();
        $(".list-group-wrapper").shouldBe(visible);
        $$(".filterbar-v2-results span").first().shouldBe(visible);
    }

    public ProjectPage openReadme() {
        $$(".ember-basic-dropdown [role=button]").last().click();
        $(Selectors.byLinkText("Readme")).click();
        return this;
    }

    public ProjectPage clickOnEdit() {
        $(Selectors.byLinkText("Edit")).click();
        return this;
    }


}
