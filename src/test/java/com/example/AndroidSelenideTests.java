package com.example;

import com.codeborne.selenide.Condition;
import com.example.config.TestBase;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AndroidSelenideTests extends TestBase {
    @Test
    void searchTest() {

        step("Tap SKIP button", () -> {
            $(AppiumBy.xpath("//*[@class='android.widget.Button'][@text='SKIP']"))
                    .click();
        });

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).shouldBe(Condition.visible, Duration.ofSeconds(5));
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .setValue("BrowserStack");
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).clear();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Appium");
        });

        step("Verify content found", () -> {
            $$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(sizeGreaterThan(0));
        });
    }
}
