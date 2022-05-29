package sg.edu.np.mad.mad_exercise6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //User ur = UserDetails();
        DBHandler db = new DBHandler(this);

        int pos = getIntent().getIntExtra("position", 0);

        User ur = ListActivity.userData.get(pos);

        TextView user_id = findViewById(R.id.userid);
        TextView user_desc = findViewById(R.id.description);

        user_id.setText(ur.name);
        user_desc.setText(ur.description);

        // Follow button
        Button btn = findViewById(R.id.flwbutton);
        follow(ur, btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ur.followed == false) {
                    ur.followed = true;
                    db.updateUser(ur);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();

                } else {
                    ur.followed = false;
                    db.updateUser(ur);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                follow(ur, btn);
                //db.updateUser(ur);
            }
        });

        // Message button
        Button msgbtn = findViewById(R.id.msgbutton);
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent msgact = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(msgact);
            }
        });
    }

    // Methods

    /*public User UserDetails() {
        User ur = new User("MAD", "Week 2 practical", 1, false);
        return ur;
    } */

    public void follow(User ur, Button btn) {

        TextView txt = btn;
        if (ur.followed == false) {
            txt.setText("Follow");

        } else {
            txt.setText("Unfollow");
        }
    }
}

