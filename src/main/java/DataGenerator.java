import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.github.javafaker.Faker;

public class DataGenerator {
    Faker faker = new Faker(new Locale("ru"));
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

    public String generateCity() {
        return faker.address().city();
    }
    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
