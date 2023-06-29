import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    DataGenerator dataGenerator = new DataGenerator();

    @Test
    void shouldDeliveryCard() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue(dataGenerator.generateCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(dataGenerator.generateDate(4));
        $("[data-test-id=name] input").setValue(dataGenerator.generateName());
        $("[data-test-id=phone] input").setValue(dataGenerator.generatePhoneNumber());
        $(withText("Успешно")).shouldBe(Condition.hidden);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно запланирована на " + dataGenerator.generateDate(4))).shouldBe(Condition.visible);
        $("[data-test-id=date] input").doubleClick().sendKeys(dataGenerator.generateDate(7));
        $(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=replan-notification]").shouldHave(Condition.text(
                "У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно запланирована на " + dataGenerator.generateDate(7))).shouldBe(Condition.visible);
    }
}
