package sg.edu.np.mad.mad_exercise6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    public static ArrayList<User> userData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler db = new DBHandler(this);

        // Create a list of 20 User objects

        //ArrayList<User> userData = new ArrayList<>();
        for (int i=0; i<20; i++){
            Random r = new Random();
            User ur = new User();
            ur.name = "Name" + r.nextInt();
            ur.description = "Description " + Math.abs(r.nextInt());
            ur.id = i + 1;
            ur.followed = r.nextBoolean();

            db.addUser(ur);
        }
        userData = db.getUsers();

        RecyclerView rv = findViewById(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        ListActAdapter adapter = new ListActAdapter(userData);

        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }
}