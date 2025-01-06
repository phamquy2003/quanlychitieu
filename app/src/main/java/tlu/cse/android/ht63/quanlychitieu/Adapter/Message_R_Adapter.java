package tlu.cse.android.ht63.quanlychitieu.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Entity.Message;
import com.google.BTL_Quanlychitieu.Other.CustomPicture;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class Message_R_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static int TYPE_ME = 1;
    private static int TYPE_OTHER = 2;
    List<Message> mylist;

    public void setdata(List<Message> tmpmess)
    {
        this.mylist = tmpmess;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_ME == viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_forme, parent, false);
            return new MeViewHolder(view);

        } else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_orther, parent, false);
            return new OtherViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = mylist.get(position);
        if (message == null) return;

        if (holder.getItemViewType() == TYPE_ME)
        {
            MeViewHolder meViewHolder = (MeViewHolder)holder;
            meViewHolder.tv_content1.setText(message.getMessage());
            meViewHolder.tv_time1.setText(message.getDatetime());

        } else if (holder.getItemViewType() == TYPE_OTHER)
        {
            OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
            otherViewHolder.img_avatar.setImageURI(CustomPicture.getUri(message.getAvatar()));
            otherViewHolder.tv_content.setText(message.getMessage());
            otherViewHolder.tv_time.setText(message.getUsername().substring(0,4) + "***  " + message.getDatetime());
        }

    }



    @Override
    public int getItemCount() {
        if (mylist != null)
            return mylist.size();
        return 0;
    }

    public class MeViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_content1,tv_time1;
        public MeViewHolder(View itemView) {
            super(itemView);
            tv_content1      = itemView.findViewById(R.id.tv_message1);
            tv_time1         = itemView.findViewById(R.id.tv_info1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mylist.get(position);
        if (message.getUsername().equals(MyApplication.User.username))
            return TYPE_ME;
        else
            return TYPE_OTHER;
    }

    public class OtherViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_content,tv_time;
        public ImageView img_avatar;
        public OtherViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content      = itemView.findViewById(R.id.tv_message);
            tv_time         = itemView.findViewById(R.id.tv_info);
            img_avatar       = itemView.findViewById(R.id.imgavatar);
        }
    }
}
