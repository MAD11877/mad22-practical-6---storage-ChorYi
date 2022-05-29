package sg.edu.np.mad.mad_exercise6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListActViewHolder extends RecyclerView.ViewHolder{
    TextView urName;
    TextView urDesc;
    ImageView urPic;
    public ListActViewHolder(View item){
        super(item);
        urName = item.findViewById(R.id.userName);
        urDesc = item.findViewById(R.id.userDesc);
        urPic = item.findViewById(R.id.userImage);
    }
}
