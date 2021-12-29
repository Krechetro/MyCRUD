package Web.dao;


import org.springframework.stereotype.Component;
import Web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        createUsersTable();
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setLastname(updatedUser.getLastname());
        user.setAge(updatedUser.getAge());
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void createUsersTable() {
        entityManager.createNativeQuery("create table if not exists users " +
                "(id int primary key AUTO_INCREMENT, " +
                "name varchar(30), " +
                "lastname varchar(50), " +
                "age int," +
                "email varchar(50))").executeUpdate();
    }
}