package dushkof.seaWars.services;


import dushkof.seaWars.objects.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public String sayHi();

    public String checkUserPassword(String name, String password);

    boolean saveUser(User user);
}
