package tlu.cse.android.ht63.quanlychitieu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.Other.CustomNumber;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class Thu_R_Adapter extends RecyclerView.Adapter<Thu_R_Adapter.ThuViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<Thu> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public Thu_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        Thu_R_Adapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        Thu_R_Adapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_khoanthu, parent, false);
        return new ThuViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, int position) {
        if (mList !=null){
            Thu tmpthu = mList.get(position);
            holder.tv_makhoanthu.setText("Mã khoản thu: " +String.valueOf(tmpthu.idthu));
            holder.tv_sotienkhoanthu.setText(CustomNumber.formatNumber((int)tmpthu.sotien) + " VND");
            holder.Tv_tenkhoanthu.setText("Tên khoản thu: " + tmpthu.ten);
            holder.tv_timekhoanthu.setText("Thời gian: " + Customdate.ConvertDate(tmpthu.date) + " \t " + tmpthu.time);
            holder.imageviewCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemViewClickListener.onItemClick(tmpthu);
                }
            });

            holder.imageviewedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemEditClickListener.onItemClick(tmpthu);
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
    public Thu getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Thu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ThuViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_makhoanthu,tv_sotienkhoanthu, tv_timekhoanthu, Tv_tenkhoanthu;
        public ImageView imageviewCT,imageviewedit;
        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_makhoanthu       = itemView.findViewById(R.id.tv_makhoanthu);
            tv_sotienkhoanthu   = itemView.findViewById(R.id.tv_sotienthu);
            tv_timekhoanthu     = itemView.findViewById(R.id.tv_ngaythu);
            Tv_tenkhoanthu      = itemView.findViewById(R.id.tv_tenkhoanthu);
            imageviewCT         = itemView.findViewById(R.id.imgseen);
            imageviewedit       = itemView.findViewById(R.id.imgedit);
        }
    }
}
