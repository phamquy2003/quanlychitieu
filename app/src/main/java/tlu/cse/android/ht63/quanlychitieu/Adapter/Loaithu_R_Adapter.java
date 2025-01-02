package tlu.cse.android.ht63.quanlychitieu.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class Loaithu_R_Adapter extends RecyclerView.Adapter<Loaithu_R_Adapter.LoaiThuViewHolder> {
    private LayoutInflater mlayoutInflater;
    private List<Loaithu> mList;
    public static ItemClickListener itemClickListener;
    public Loaithu_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ItemClickListener itemEditClickListener) {
        Loaithu_R_Adapter.itemClickListener = itemEditClickListener;
    }

    @NonNull
    @Override
    public LoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_loaikhoanthu, parent, false);
        return new LoaiThuViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHolder holder, int position) {
        if (mList !=null){
            holder.textviewName.setText(mList.get(position).Tenloaithu);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(mList.get(position));
                }
            });
        }
        setAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        if (mList==null)
            return 0;
        return mList.size();
    }
    public Loaithu getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Loaithu> mList) {
        this.mList = mList;

        notifyDataSetChanged();
    }

    private void setAnimation(View view) {
        ScaleAnimation animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    public static class LoaiThuViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewName;
        public CardView cardView;
        public LoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewName = itemView.findViewById(R.id.tv_loaikhoanthu);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
