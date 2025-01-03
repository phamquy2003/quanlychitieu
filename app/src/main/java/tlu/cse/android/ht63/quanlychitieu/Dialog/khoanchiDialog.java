package tlu.cse.android.ht63.quanlychitieu.Dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.google.BTL_Quanlychitieu.Adapter.Loaichi_spinner_adapter;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.Chi.Khoanchi.Khoanchi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

public class khoanchiDialog {
    Chi chi;

    Khoanchi fragmentkhoanchi;

    String type;

    public khoanchiDialog(Khoanchi khoanchi, String type, Chi chi)
    {
        this.fragmentkhoanchi = khoanchi;
        this.chi = chi;
        this.type = type;
    }

    public void show(){
        final Dialog dialog = new Dialog(fragmentkhoanchi.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_khoanchi);

        Window window = dialog.getWindow();
        if (window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = Gravity.CENTER;
        window.setAttributes(windowAttr);
        dialog.setCancelable(true);

        Button btn_thoat, btn_luu;
        Spinner spinner;
        EditText ed_idchi, ed_Sotien, ed_tenkhoanchi, ed_ghichu;
        TextView tv_ngay, tv_gio;

        btn_thoat = dialog.findViewById(R.id.btn_thoat);
        btn_luu = dialog.findViewById(R.id.btn_luu);
        spinner = dialog.findViewById(R.id.spinerkhoanchi);
        Loaichi_spinner_adapter adapter = new Loaichi_spinner_adapter(fragmentkhoanchi.getContext());
        ed_idchi  = dialog.findViewById(R.id.ed_idchi);
        ed_Sotien = dialog.findViewById(R.id.ed_sotienchi);
        ed_tenkhoanchi = dialog.findViewById(R.id.ed_tenkhoanchi);
        ed_ghichu = dialog.findViewById(R.id.ghichuchi);
        tv_ngay = dialog.findViewById(R.id.tv_ngay);
        tv_gio = dialog.findViewById(R.id.tv_gio);
        tv_ngay.setText(Customdate.getLocaldatenow().toString());
        tv_gio.setText(Customdate.getLocaltimenow().toString());

        fragmentkhoanchi.getViewmodel().getAllLoaiChi().observe(fragmentkhoanchi.getActivity(), new Observer<List<Loaichi>>() {
            @Override
            public void onChanged(List<Loaichi> loaichis) {
                if (loaichis != null && loaichis.size() !=0)
                    adapter.setList(loaichis);
                else
                {
                    Loaichi tmp = new Loaichi();
                    tmp.user = MyApplication.User.username;
                    tmp.Tenloaichi = "Loại chi KXD";
                    fragmentkhoanchi.getViewmodel().insert(tmp);
                }
            }
        });
        spinner.setAdapter(adapter);

        if (type.equals("seen"))
        {
            btn_luu.setVisibility(View.INVISIBLE);
            ed_idchi.setText(String.valueOf(chi.idchi));
            ed_tenkhoanchi.setText(chi.ten);
            ed_Sotien.setText(String.valueOf(chi.sotien));
            ed_ghichu.setText(chi.ghichu);
            spinner.setSelection(adapter.findposbyid(chi.idloaichi));
            tv_ngay.setText(chi.date);
            tv_gio.setText(chi.time);
        } else
        if (type.equals("edit"))
        {
            ed_idchi.setText(String.valueOf(chi.idchi));
            ed_tenkhoanchi.setText(chi.ten);
            ed_Sotien.setText(String.valueOf(chi.sotien));
            ed_ghichu.setText(chi.ghichu);
            spinner.setSelection(adapter.findposbyid(chi.idloaichi));
            tv_ngay.setText(chi.date);
            tv_gio.setText(chi.time);
        }

        tv_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(fragmentkhoanchi.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearr, int monthh, int dayOfMonth) {
                        LocalDate tmp = Customdate.getLocaldate(yearr, monthh+1, dayOfMonth);
                        tv_ngay.setText(tmp.toString());
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });
        tv_gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int gio = calendar.get(Calendar.HOUR);
                int phut = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(fragmentkhoanchi.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        LocalTime tmp = Customdate.getLocaltime(hourOfDay, minute, 0);
                        tv_gio.setText(tmp.toString());
                    }
                }, gio, phut, true);

                timePickerDialog.show();
            }
        });


        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_Sotien.getText().toString().equals(""))
                {
                    ed_Sotien.setError("Yêu cầu nhập số tiền");
                    return;
                }
                if (type.equals("edit")) {
                    chi.ten = ed_tenkhoanchi.getText().toString();
                    chi.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    chi.ghichu = ed_ghichu.getText().toString();
                    chi.date = tv_ngay.getText().toString();
                    chi.time = tv_gio.getText().toString();
                    chi.idloaichi = adapter.getItem(spinner.getSelectedItemPosition()).idloaichi;
                    fragmentkhoanchi.getViewmodel().update(chi);
                    Toast.makeText(fragmentkhoanchi.getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Chi tmp = new Chi();
                    tmp.ten = ed_tenkhoanchi.getText().toString();
                    tmp.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    tmp.ghichu = ed_ghichu.getText().toString();
                    tmp.date = tv_ngay.getText().toString();
                    tmp.time = tv_gio.getText().toString();
                    tmp.idloaichi = adapter.getItem(spinner.getSelectedItemPosition()).idloaichi;
                    tmp.user = MyApplication.User.username;
                    fragmentkhoanchi.getViewmodel().insert(tmp);
                    Toast.makeText(fragmentkhoanchi.getContext(), "Insert thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

