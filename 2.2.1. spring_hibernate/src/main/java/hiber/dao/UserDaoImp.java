package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByCar(String model, int series) {
        String HQL =
                "from User user where user.car.model " +
                        "= :model and user.car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery(HQL, User.class);

        return query
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();
    }
}
