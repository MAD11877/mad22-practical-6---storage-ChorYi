

package sg.edu.np.mad.mad_exercise6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://madpractical6-90caa-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference ref = database.getReference("Users");

        ((Button)findViewById(R.id.login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name= findViewById(R.id.login_user);
                TextView pwd = findViewById(R.id.login_pwd);
                String urname = name.getText().toString();
                String urpwd = pwd.getText().toString();
                ref.child("mad").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if ((urname.equals(snapshot.child("username").getValue().toString())) && (urpwd.equals(snapshot.child("password").getValue().toString()))){
                            Intent i = new Intent(LoginActivity.this, ListActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("fail", "Failed to read value written.", error.toException());
                    }
                });
            }
        });


    }

}