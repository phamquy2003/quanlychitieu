package tlu.cse.android.ht63.quanlychitieu.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.Other.CustomNumber;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class Chi_R_Adapter extends RecyclerView.Adapter<Chi_R_Adapter.ChiViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<Chi> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public Chi_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        Chi_R_Adapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        Chi_R_Adapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_khoanchi, parent, false);
        return new ChiViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, int position) {
        if (mList !=null){
            Chi tmpchi = mList.get(position);
            holder.tv_makhoanchi.setText("Mã khoa chi: "+ String.valueOf(tmpchi.idchi));
            holder.tv_sotienkhoanchi.setText(CustomNumber.formatNumber((int)tmpchi.sotien)+ " VND");
            holder.Tv_tenkhoanchi.setText("Tên khoản chi: " + tmpchi.ten);
            holder.tv_timekhoanchi.setText("Thời gian: " + Customdate.ConvertDate(tmpchi.date) + " \t " + tmpchi.time);
            holder.imageviewCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemViewClickListener.onItemClick(tmpchi);
                }
            });

            holder.imageviewedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemEditClickListener.onItemClick(tmpchi);
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
    public Chi getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Chi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_makhoanchi,tv_sotienkhoanchi, tv_timekhoanchi, Tv_tenkhoanchi;
        public ImageView imageviewCT,imageviewedit;
        public ChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_makhoanchi = itemView.findViewById(R.id.tv_makhoanchi);
            tv_sotienkhoanchi = itemView.findViewById(R.id.tv_sotienchi);
            tv_timekhoanchi = itemView.findViewById(R.id.tv_ngaychi);
            Tv_tenkhoanchi = itemView.findViewById(R.id.tv_tenkhoanchi);
            imageviewCT = itemView.findViewById(R.id.imgseen);
            imageviewedit = itemView.findViewById(R.id.imgedit);
        }
    }
}
