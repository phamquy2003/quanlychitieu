package tlu.cse.android.ht63.quanlychitieu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.R;

import java.util.List;

public class Loaichi_spinner_adapter extends BaseAdapter {
    private List<Loaichi> mList;
    private LayoutInflater mLayoutInflater;

    public Loaichi_spinner_adapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public int findposbyid(int id)
    {
        if (mList != null)
            for (int i=0; i<mList.size(); i++)
                    if (mList.get(i).idloaichi == id) return i;
        return 0;
    }

    public void setList(List<Loaichi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if(mList ==null)
            return 0;
        return mList.size();
    }

    @Override
    public Loaichi getItem(int i) {
        if(mList==null)
            return null;
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KhoanChiViewHolder holder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.spinner_chi_item, null, false);
            holder = new KhoanChiViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (KhoanChiViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(i).Tenloaichi);
        return view;
    }
    public static class KhoanChiViewHolder{
        public TextView tvName;
        public KhoanChiViewHolder(View view){
            tvName = view.findViewById(R.id.tvName);
        }
    }
}