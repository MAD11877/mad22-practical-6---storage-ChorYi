package sg.edu.np.mad.mad_exercise6;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(){}

    public User(String user_name, String user_description, int user_id, boolean user_followed){
        name = user_name;
        description = user_description;
        id = user_id;
        followed = user_followed;

    }
}

