package repositories;

import model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class UserRepository extends BaseRepository<User, String> {

    public User findByUsernameAndPassword(String username, String password){
        Map<String, Object> m = new HashMap<>();
        m.put("a", username);
        m.put("b", password);
        return matchOne("e.username=:a AND e.password=:b", m);
    }

    public User findByUsername(String username){
        Map<String, Object> m = new HashMap<>();
        m.put("a", username);
        return matchOne("e.username=:a", m);
    }

}
