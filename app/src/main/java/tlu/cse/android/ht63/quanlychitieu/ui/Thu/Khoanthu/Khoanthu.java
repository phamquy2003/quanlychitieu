package tlu.cse.android.ht63.quanlychitieu.ui.Thu.Khoanthu;

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

import com.google.BTL_Quanlychitieu.Adapter.Thu_R_Adapter;
import com.google.BTL_Quanlychitieu.Dialog.AlertDialogg;
import com.google.BTL_Quanlychitieu.Dialog.khoanthuDialog;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Listener.DialogListener;
import com.google.BTL_Quanlychitieu.Listener.ItemClickListener;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.Thu.KhoanthuViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Khoanthu extends Fragment {

    private KhoanthuViewModel mViewModel;

    com.google.BTL_Quanlychitieu.ui.Thu.Khoanthu.Khoanthu current;

    public KhoanthuViewModel getViewmodel() {
        return mViewModel;
    }

    public static com.google.BTL_Quanlychitieu.ui.Thu.Khoanthu.Khoanthu newInstance() {
        return new com.google.BTL_Quanlychitieu.ui.Thu.Khoanthu.Khoanthu();
    }

    RecyclerView rv_khoanthu;
    Thu_R_Adapter mAdapter;
    FloatingActionButton fab;

    EditText ed_search;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_khoanthu = view.findViewById(R.id.rv_khoanthu);
        fab = view.findViewById(R.id.fabkhoanthu);
        current = this;
        mAdapter = new Thu_R_Adapter(getActivity());
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Chi position) {

            }

            @Override
            public void onItemClick(Thu position) {
                new khoanthuDialog(current, "seen", position).show();
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
                new khoanthuDialog(current, "edit", position).show();
            }

            @Override
            public void onItemClick(Chi position) {

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


        rv_khoanthu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_khoanthu.setAdapter(mAdapter);

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
                mViewModel.getAllThu().observe(current.getActivity(), new Observer<List<Thu>>() {
                    @Override
                    public void onChanged(List<Thu> thus) {
                        List<Thu> tmp = new ArrayList<Thu>();
                        for (Thu i: thus)
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new khoanthuDialog(current, "insert", null).show();
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
                        Thu kt = mAdapter.getItem(position);
                        AlertDialogg dialogg = new AlertDialogg(current.getContext(),"Question","Bạn có chắc chắn muốn xóa khoản thu "+kt.idthu+"?", R.drawable.ic_launcher_foreground);
                        dialogg.setDialogListener(new DialogListener() {
                            @Override
                            public void dialogPositive() {


                                Toast.makeText(getActivity(),"Khoản thu "+kt.idthu+" đã được xóa",Toast.LENGTH_SHORT).show();
                                mViewModel.delete(kt);
                            }
                        });
                        dialogg.show();
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );
        helper.attachToRecyclerView((rv_khoanthu));
        mViewModel = new ViewModelProvider(this).get(KhoanthuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
                rv_khoanthu.scrollToPosition(mAdapter.getItemCount()-1);
            }
        });

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoanthu, container, false);
    }

}