package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User user = null;
        for (User value : users) {
            if (value.getUsername().equals(login)) {
                user = value;
            }
        }
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("Пользователь не валидный");
        }
        return user.isValid();
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}