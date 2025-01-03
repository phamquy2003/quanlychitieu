package tlu.cse.android.ht63.quanlychitieu.ui.Chi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.BTL_Quanlychitieu.Adapter.Chi_VP2_Adapter;
import com.google.BTL_Quanlychitieu.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FrangmentChi extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ViewPager2 mVp;

    public TabLayout mTl;
    public FrangmentChi() {
    }

    public static FrangmentChi newInstance(String param1, String param2) {
        FrangmentChi fragment = new FrangmentChi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // hiển thị nội dung tương ứng với tablayout
        mVp = view.findViewById(R.id.pageviewchi);
        // // ánh xa tới phần tablayout ở bên trên cùng // gồm chi và khoản chi
        mTl = view.findViewById(R.id.tablayoutchi);


        Chi_VP2_Adapter adapter = new Chi_VP2_Adapter(getActivity());
        mVp.setAdapter(adapter);

        // kết nôi viewpager với tablayout
        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position ==0){
                    tab.setIcon(R.drawable.baseline_shopping_cart_checkout_24);
                    tab.setText("Khoản chi");
                }else {
                    tab.setIcon(R.drawable.baseline_add_card_24);
                    tab.setText("Loại khoản Chi");
                }
            }
        }).attach();
    }


    //code tự sinh ra
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frangment_chi, container, false);
    }
}