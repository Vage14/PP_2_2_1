package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Model_1", 2021)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Model_2", 2023)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Model_3", 2024)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Model_4", 2025)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
            System.out.println();
        }
        String carModel = "Model_1";
        int carSeries = 2022;

        User foundUser = userService.findUserByCarModelAndSeries(carModel, carSeries);
        if (foundUser != null) {
            System.out.println("Found user: ");
            System.out.println(foundUser);
        } else {
            System.out.println("User with a car " + carModel + " series " + carSeries + " not found.");
        }

        context.close();
    }
}
