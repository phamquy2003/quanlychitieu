package tlu.cse.android.ht63.quanlychitieu.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.Khoanchi;
import com.google.BTL_Quanlychitieu.ui.Chi.Loaikhoanchi.Loaikhoanchi;

public class Chi_VP2_Adapter extends FragmentStateAdapter {
    public Chi_VP2_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position ==0){
            fragment = Khoanchi.newInstance();
        }
        else {
            fragment = Loaikhoanchi.newInstance();
        }
        return fragment;
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
