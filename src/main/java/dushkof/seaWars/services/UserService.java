package dushkof.seaWars.services;


public interface UserService {
    public String sayHi();

    public String checkUserPassword(String name, String password);

    String userCreate(String name, String password, String nickname);
}
