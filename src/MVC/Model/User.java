package MVC.Model;

public class User {
    private String username;
    private String password;
    private String email;

    public String setUsername(String username){ return  this.username=username; }

    public String setPassword(String password){ return  this.password=password; }

    public String setEmail(String email){ return  this.email=email; }

    public String getUsername(){ return username; }

    public String getPassword(){ return password; }

    public String getEmail(){ return email; }
}
