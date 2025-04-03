package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("a", 1);
        Car car2 = new Car("b", 2);
        Car car3 = new Car("c", 3);
        Car car4 = new Car("c", 3);

        User user1 = new User("User1", "User1", "user1@hiber.com", car1);
        User user2 = new User("User2", "User2", "user2@hiber.com", car2);
        User user3 = new User("User3", "User3", "user3@hiber.com", car3);
        User user4 = new User("User4", "User4", "user4@hiber.com", car4);
        user4.setCar(car1);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().toString());
            System.out.println();
        }

        List<User> usersSpecific = userService.getUsersByCar("c", 3);
        for (User user : usersSpecific) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().toString());
            System.out.println();
        }

        context.close();
    }
}
