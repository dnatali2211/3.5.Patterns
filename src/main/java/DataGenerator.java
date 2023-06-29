import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

public class DataGenerator {
    static Faker faker = new Faker(new Locale("ru"));

    public String generateDate(int daysToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate deliveryDate = currentDate.plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String deliveryDateStr = deliveryDate.format(formatter);
        return deliveryDateStr;
    }

    public String generateName() {
        return faker.name().name();
    }

    public String generatePhoneNumber() {
        return faker.numerify("+###########");
    }

    public static final List<String> cities = Arrays.asList(
            "Санкт-Петербург", "Омск", "Ульяновск", "Псков", "Йошкар-Ола", "Великий Новгород"
    );

    public static String generateCity() {
        return cities.get(faker.random().nextInt(cities.size()));
    }
}
