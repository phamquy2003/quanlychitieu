package tlu.cse.android.ht63.quanlychitieu.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Other.CustomNumber;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class ThuHome_R_Adapter extends RecyclerView.Adapter<ThuHome_R_Adapter.ThuViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<Thu> mList;
    public ThuHome_R_Adapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_khoanthuchi_home, parent, false);
        return new ThuViewHolder(view);
    }
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, int position) {
        if (mList !=null){
            Thu tmpthu = mList.get(mList.size()-position-1);
            holder.tv_makhoan.setText("Mã khoản: "+ String.valueOf(tmpthu.idthu));
            holder.tv_sotienkhoan.setText("+"+ CustomNumber.formatNumber((int)tmpthu.sotien)+ " VND");
            holder.Tv_tenkhoan.setText("Tên: " + tmpthu.ten);
            holder.tv_timekhoan.setText(Customdate.ConvertDate(tmpthu.date) + " \t " + tmpthu.time);
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
        public TextView tv_makhoan,tv_sotienkhoan, tv_timekhoan, Tv_tenkhoan;
        @SuppressLint("ResourceAsColor")
        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_makhoan = itemView.findViewById(R.id.tv_makhoanthuchi);
            tv_sotienkhoan = itemView.findViewById(R.id.sotienthuchi);
            tv_timekhoan = itemView.findViewById(R.id.tv_thoigianthuchi);
            Tv_tenkhoan = itemView.findViewById(R.id.tv_tenkhoanthuchi);
        }
    }
}
