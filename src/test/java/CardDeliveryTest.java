import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    DataGenerator dataGenerator = new DataGenerator();

    @Test
    void shouldDeliveryCard() throws InterruptedException {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue(dataGenerator.generateCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(dataGenerator.generateDate(4));
        $("[data-test-id=name] input").setValue(dataGenerator.generateName());
        $("[data-test-id=phone] input").setValue(dataGenerator.generatePhoneNumber());
        $(withText("Успешно")).shouldBe(Condition.hidden);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(20));
        $("[data-test-id=notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно забронирована на " + dataGenerator.generateDate(4))).shouldBe(Condition.visible);
        $("[data-test-id=date] input").doubleClick().sendKeys(dataGenerator.generateDate(5));
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(20));
        $("[data-test-id=notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно забронирована на " + dataGenerator.generateDate(5))).shouldBe(Condition.visible);

        Thread.sleep(10000);
    }
}
