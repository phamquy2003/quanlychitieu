package tlu.cse.android.ht63.quanlychitieu.ui.Thu.Loaikhoanthu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Adapter.Loaithu_R_Adapter;
import com.google.BTL_Quanlychitieu.Dialog.AlertDialogg;
import com.google.BTL_Quanlychitieu.Dialog.loaikhoanthuDialog;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Listener.DialogListener;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Loaikhoanthu extends Fragment {

    private RecyclerView mRv;
    private Loaithu_R_Adapter mAdapter;

    FloatingActionButton fab;

    private LoaikhoanthuViewModel mViewModel;

    public static com.google.BTL_Quanlychitieu.ui.Thu.Loaikhoanthu.Loaikhoanthu newInstance() {
        return new com.google.BTL_Quanlychitieu.ui.Thu.Loaikhoanthu.Loaikhoanthu();
    }

    public LoaikhoanthuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loaikhoanthu, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.rv_Loaikhoanthu);
        fab = view.findViewById(R.id.fabloaikhoanthu);
        mAdapter = new Loaithu_R_Adapter(getActivity());
        mRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRv.setAdapter(mAdapter);
        final com.google.BTL_Quanlychitieu.ui.Thu.Loaikhoanthu.Loaikhoanthu currentFragment = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loaikhoanthuDialog(currentFragment, "insert", null).show();
            }
        });

        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {
            }

            @Override
            public void onItemClick(Chi position) {

            }

            @Override
            public void onItemClick(Loaithu position) {
                new loaikhoanthuDialog(currentFragment, "edit", position).show();
            }

            @Override
            public void onItemClick(ChiDuKien position) {

            }

            @Override
            public void onItemClick(Loaichi position) {

            }
        });
        ItemTouchHelper helper=new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getLayoutPosition();
                        Loaithu lt = mAdapter.getItem(position);
                        AlertDialogg dialogg = new AlertDialogg(currentFragment.getContext(),"Question","Bạn có chắc chắn muốn xóa loại thu ("+lt.Tenloaithu+")?", R.drawable.ic_launcher_foreground);
                        dialogg.setDialogListener(new DialogListener() {
                            @Override
                            public void dialogPositive() {
                                lt.isDelete = 1;
                                Toast.makeText(getActivity(),"Loại thu ("+lt.Tenloaithu+") đã được xóa",Toast.LENGTH_SHORT).show();
                                mViewModel.update(lt);
                            }
                        });
                        dialogg.show();
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        helper.attachToRecyclerView((mRv));
        mViewModel = new ViewModelProvider(this).get(LoaikhoanthuViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<Loaithu>>() {
            @Override
            public void onChanged(List<Loaithu> loaiThus) {
                mAdapter.setList(loaiThus);
                mRv.scrollToPosition(mAdapter.getItemCount()-1);
            }
        });
    }


}