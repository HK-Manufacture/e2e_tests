package io.testomat.e2e_tests.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage {

    private static final List<String> ACTION_BUTTON_ICONS = Arrays.asList(
            "md-icon-star-outline",
            "md-icon-plus-box-outline",
            "md-icon-pencil-box-outline",
            "md-icon-link-variant",
            "md-icon-trash-can-outline"
    );

    public void isLoaded(String targetProjectName) {
        $(".first [href=\"/projects/manufacture-light/\"]").shouldHave(Condition.text(targetProjectName));
    }

    public void tooltipsAreVisibleAndCorrect() {
        checkTooltipIsVisibleAndCorrect("md-icon-star-outline", "Star");
        checkTooltipIsVisibleAndCorrect("md-icon-plus-box-outline", "Create next suite");
        checkTooltipIsVisibleAndCorrect("md-icon-pencil-box-outline", "Edit suite");
        checkTooltipIsVisibleAndCorrect("md-icon-link-variant", "Link suite");
        checkTooltipIsVisibleAndCorrect("md-icon-trash-can-outline", "Delete suite");
    }

    public void checkActionButtonsAreVisibleAfterHoveringSuite() {
        $$(".flex.justify-between.space-x-2.overflow-hidden.w-full").first().hover();
        for (String stileItem : ACTION_BUTTON_ICONS) {
            getActionButtonAfterHoveringSuite(stileItem).shouldBe(visible);
        }
    }

    private static void checkTooltipIsVisibleAndCorrect(String svgClass, String tooltipText) {
        getActionButtonAfterHoveringSuite(svgClass).hover();
        $$("[role=\"tooltip\"]").findBy(text(tooltipText)).shouldBe(visible);
    }

    private static SelenideElement getActionButtonAfterHoveringSuite(String svgClass) {
        return $$(".nestedItem-toolbar svg").filterBy(cssClass(svgClass)).first().closest("button");
    }

    public void openFilters() {
        $(".filterbar-filter-btn-div button").click();
        $(".second h3").shouldBe(visible);
    }

    public void openDropdown() {
        $$(".second li [role='button']").findBy(text("Select state")).click();
    }

    public void selectState() {
        $$(".second [role=\"option\"]").findBy(text("Manual")).shouldBe(visible).click();
    }

    public void applyFilters() {
        $$(".pt-5 button").findBy(text("Apply")).click();
        $(".list-group-wrapper").shouldBe(visible);
        $$(".filterbar-v2-results span").first().shouldBe(visible);
    }
}
