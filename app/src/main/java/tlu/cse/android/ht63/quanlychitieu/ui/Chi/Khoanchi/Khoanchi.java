package tlu.cse.android.ht63.quanlychitieu.ui.Chi.Khoanchi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Adapter.Chi_R_Adapter;
import com.google.BTL_Quanlychitieu.Dialog.AlertDialogg;
import com.google.BTL_Quanlychitieu.Dialog.khoanchiDialog;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Listener.DialogListener;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Khoanchi extends Fragment {

    private KhoanchiViewModel mViewModel;

    Khoanchi current;

    public KhoanchiViewModel getViewmodel() {
        return mViewModel;
    }

    public static Khoanchi newInstance() {
        return new Khoanchi();
    }

    RecyclerView rv_khoanchi;
    Chi_R_Adapter mAdapter;
    FloatingActionButton fab;

    EditText ed_search;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_khoanchi = view.findViewById(R.id.rv_khoanchi);
        fab = view.findViewById(R.id.fabkhoanchi);
        current = this;
        mAdapter = new Chi_R_Adapter(getActivity());
        ed_search = view.findViewById(R.id.ed_search);
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                //observe cái này đển giám sát sự thay đổi của dữ liệu
                mViewModel.getAllChi().observe(current.getActivity(), new Observer<List<Chi>>() {
                    @Override
                    public void onChanged(List<Chi> chis) {
                        List<Chi> tmp = new ArrayList<Chi>();
                        for (Chi i: chis)
                        {
                            if (i.ten.contains(s.toString()))
                            {
                                tmp.add(i);
                            }
                        }
                        mAdapter.setList(tmp);
                    }
                });
            }
        });

        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {
            }

            @Override
            public void onItemClick(Chi position) {
                new khoanchiDialog(current, "seen", position).show();
            }
            @Override
            public void onItemClick(Loaichi position) {
            }

            @Override
            public void onItemClick(Loaithu position) {
            }

            @Override
            public void onItemClick(ChiDuKien position) {

            }
        });

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Thu position) {
            }

            @Override
            public void onItemClick(Chi position) {
                new khoanchiDialog(current, "edit", position).show();
            }

            @Override
            public void onItemClick(Loaichi position) {
            }

            @Override
            public void onItemClick(Loaithu position) {
            }

            @Override
            public void onItemClick(ChiDuKien position) {
            }
        });


        rv_khoanchi.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_khoanchi.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new khoanchiDialog(current, "insert", null).show();
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
                        Chi kt = mAdapter.getItem(position);
                        AlertDialogg dialogg = new AlertDialogg(current.getContext(),"Question","Bạn có chắc chắn muốn xóa khoản chi "+kt.idchi+"?", R.drawable.ic_launcher_foreground)
                        .setDialogListener(new DialogListener() {
                            @Override
                            public void dialogPositive() {
                                Toast.makeText(getActivity(),"Khoản chi "+kt.idchi+" đã được xóa",Toast.LENGTH_SHORT).show();
                                mViewModel.delete(kt);
                            }
                        });
                        dialogg.show();
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        helper.attachToRecyclerView((rv_khoanchi));

        mViewModel = new ViewModelProvider(this).get(KhoanchiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                mAdapter.setList(chis);
                rv_khoanchi.scrollToPosition(mAdapter.getItemCount()-1);
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoanchi, container, false);
    }
}