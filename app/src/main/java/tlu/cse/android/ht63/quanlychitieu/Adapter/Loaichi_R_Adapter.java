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

import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class Loaichi_R_Adapter extends RecyclerView.Adapter<Loaichi_R_Adapter.LoaiChiViewHolder> {
    private LayoutInflater mlayoutInflater;
    private List<Loaichi> mList;
    public static ItemClickListener itemClickListener;
    public Loaichi_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ItemClickListener itemEditClickListener) {
        Loaichi_R_Adapter.itemClickListener = itemEditClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_loaikhoanchi, parent, false);
        return new LoaiChiViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        if (mList !=null){
            holder.textviewName.setText(mList.get(position).Tenloaichi);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(mList.get(position));
                }
            });
        }
        setAnimation(holder.itemView);
    }

    private void setAnimation(View view) {
        ScaleAnimation animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        if (mList==null)
            return 0;
        return mList.size();
    }
    public Loaichi getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Loaichi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewName;
        public CardView cardView;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewName = itemView.findViewById(R.id.tv_loaikhoanchi);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
