package io.testomat.e2e_tests.web.pages;

import static com.codeborne.selenide.Condition.innerText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ReadmePage {

    public ReadmePage isLoaded() {
        $(".setting-header").shouldBe(visible);
        return this;
    }

    public ReadmePage clickOnEditReadme() {
        $(byText("Edit Readme")).click();
        switchTo().frame($("#modal-overlays iframe[src='/ember-monaco/frame.html']"));
        return this;
    }

    public ReadmePage editTextInEditor(String updatedText) {
        $(".view-lines.monaco-mouse-cursor-text").click();
        $(".inputarea.monaco-mouse-cursor-text").setValue(updatedText);
        return this;
    }

    public ReadmePage clickOnUpdateButton() {
        switchTo().defaultContent();
        $(byText("Update")).click();
        return this;
    }

    public ReadmePage clickOnCancelButton() {
        switchTo().defaultContent();
        $(byText("Cancel")).click();
        return this;
    }

    public ReadmePage editReadmeIsNotAllowed() {
        switchTo().defaultContent();
        $(".ember-notify-show.callout.error.alert.ember-view.ember-notify.clearfix")
                .should(visible);
        return this;
    }

    public ReadmePage changesWereNotAppliedAfterCancelling(String updatedText) {
        $(".view-lines.monaco-mouse-cursor-text").shouldNotHave(innerText(updatedText));
        return  this;
    }

}

