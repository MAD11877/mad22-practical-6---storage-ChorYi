package sg.edu.np.mad.mad_exercise6;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActAdapter extends RecyclerView.Adapter<ListActViewHolder> {

    ArrayList<User> data;
    public ListActAdapter(ArrayList<User> data){
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).name.endsWith("7")){
            return 1; // display new layout
        }
        else{
            return 0; // display normal layout
        }
        //return super.getItemViewType(position);
    }

    @Override
    public ListActViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if (viewType == 1){
            item  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_newrcv, parent, false);
        }
        else{
            item  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_rcv, parent, false);
        }
        return new ListActViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ListActViewHolder holder, int position) {
        User ur = data.get(position);
        holder.urName.setText(ur.name);
        holder.urDesc.setText(ur.description);
        holder.urPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(holder.urPic.getContext());
                ab.setTitle("Profile");
                ab.setMessage(ur.name);
                ab.setCancelable(true);
                ab.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent listact = new Intent(holder.urPic.getContext(), MainActivity.class);
                        listact.putExtra("urFollowed", ur.followed);
                        holder.urPic.getContext().startActivity(listact);
                    }
                });

                ab.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                    }
                });
                AlertDialog alert = ab.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
