package Web.service;


import Web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(int id);
    void createUser(User user);
    void updateUser(int id, User updatedUser);
    void deleteUser(int id);
}