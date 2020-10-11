package dushkof.seaWars.objects;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "usr")
@ToString(of = {"id", "name", "password"})
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String password;

    protected User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
