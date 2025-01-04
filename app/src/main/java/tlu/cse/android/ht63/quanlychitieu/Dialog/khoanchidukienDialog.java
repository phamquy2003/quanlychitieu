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
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Other.CustomPendingIntent;
import com.google.BTL_Quanlychitieu.Other.Customdate;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.home.HomeFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class khoanchidukienDialog {
    ChiDuKien chidukien;
    LocalDate tmpLocaldate = Customdate.getLocaldatenow();
    LocalTime tmpLocaltime = Customdate.getLocaltimenow();
    HomeFragment homeFragment;

    String type;

    public khoanchidukienDialog(HomeFragment homeFragment, String type, ChiDuKien chi)
    {
        this.homeFragment = homeFragment;
        this.chidukien = chi;
        this.type = type;
    }

    public void show(){
        final Dialog dialog = new Dialog(homeFragment.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_khoanchidukien);

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
        Loaichi_spinner_adapter adapter = new Loaichi_spinner_adapter(homeFragment.getContext());
        ed_idchi  = dialog.findViewById(R.id.ed_idchi);
        ed_Sotien = dialog.findViewById(R.id.ed_sotienchi);
        ed_tenkhoanchi = dialog.findViewById(R.id.ed_tenkhoanchi);
        ed_ghichu = dialog.findViewById(R.id.ghichuchi);
        tv_ngay = dialog.findViewById(R.id.tv_ngay);
        tv_gio = dialog.findViewById(R.id.tv_gio);

        tv_gio.setText(Customdate.getLocaltimenow().toString());
        tv_ngay.setText(Customdate.getLocaldatenow().toString());
        homeFragment.getViewmodel().getAllLoaichi().observe(homeFragment.getActivity(), new Observer<List<Loaichi>>() {
            @Override
            public void onChanged(List<Loaichi> loaichis)
            {
                if (loaichis != null && loaichis.size() !=0)
                    adapter.setList(loaichis);
                else
                {
                    Loaichi tmp = new Loaichi();
                    tmp.user = MyApplication.User.username;
                    tmp.Tenloaichi = "Loại chi KXD";
                    homeFragment.getViewmodel().insert(tmp);
                }
            }
        });
        spinner.setAdapter(adapter);

        if (type.equals("seen"))
        {
            btn_luu.setText("Hoàn thành");
            ed_idchi.setText(String.valueOf(chidukien.iddukien));
            ed_tenkhoanchi.setText(chidukien.ten);
            ed_Sotien.setText(String.valueOf(chidukien.sotien));
            ed_ghichu.setText(chidukien.ghichu);
            tv_gio.setText(chidukien.time);
            tv_ngay.setText(chidukien.date);
            spinner.setSelection(adapter.findposbyid(chidukien.idloaichi));
        }

        if (type.equals("edit"))
        {
            ed_idchi.setText(String.valueOf(chidukien.iddukien));
            ed_tenkhoanchi.setText(chidukien.ten);
            ed_Sotien.setText(String.valueOf(chidukien.sotien));
            ed_ghichu.setText(chidukien.ghichu);
            tv_gio.setText(chidukien.time);
            tv_ngay.setText(chidukien.date);
            spinner.setSelection(adapter.findposbyid(chidukien.idloaichi));
        }
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(homeFragment.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearr, int monthh, int dayOfMonth) {
                        tmpLocaldate = Customdate.getLocaldate(yearr, monthh+1, dayOfMonth);
                        tv_ngay.setText(tmpLocaldate.toString());
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(homeFragment.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tmpLocaltime = Customdate.getLocaltime(hourOfDay, minute, 0);
                        tv_gio.setText(tmpLocaltime.toString());
                    }
                }, gio, phut, true);

                timePickerDialog.show();
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
                if (type.equals("seen"))
                {
                    Chi tmpchi = new Chi();
                    tmpchi.ten = chidukien.ten;
                    tmpchi.sotien = chidukien.sotien;
                    tmpchi.idloaichi = chidukien.idloaichi;
                    tmpchi.ghichu = chidukien.ghichu;
                    tmpchi.date = chidukien.date;
                    tmpchi.time = chidukien.time;
                    tmpchi.user = chidukien.user;
                    homeFragment.getViewmodel().insertchi(tmpchi);
                    homeFragment.getViewmodel().deletechidk(chidukien);
                    CustomPendingIntent.removePendingIntent(homeFragment.getContext(), chidukien);
                    Toast.makeText(homeFragment.getContext(), "Đã hoàn thành", Toast.LENGTH_SHORT).show();
                } else
                if (type.equals("edit")) {
                    chidukien.ten = ed_tenkhoanchi.getText().toString();
                    chidukien.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    chidukien.ghichu = ed_ghichu.getText().toString();
                    chidukien.date = tv_ngay.getText().toString();
                    chidukien.time = tv_gio.getText().toString();
                    chidukien.idloaichi = adapter.getItem(spinner.getSelectedItemPosition()).idloaichi;
                    homeFragment.getViewmodel().updatechidk(chidukien);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalDateTime tmpLocaldatetime = LocalDateTime.of(tmpLocaldate, tmpLocaltime);
                        CustomPendingIntent.removePendingIntent(homeFragment.getContext(), chidukien);
                        CustomPendingIntent.CreatePendingintent(homeFragment.getContext(), chidukien, tmpLocaldatetime);
                    }
                    Toast.makeText(homeFragment.getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    ChiDuKien tmp = new ChiDuKien();
                    tmp.ten = ed_tenkhoanchi.getText().toString();
                    tmp.sotien = Float.parseFloat(ed_Sotien.getText().toString());
                    tmp.ghichu = ed_ghichu.getText().toString();
                    tmp.date = tv_ngay.getText().toString();
                    tmp.time = tv_gio.getText().toString();
                    tmp.idloaichi = adapter.getItem(spinner.getSelectedItemPosition()).idloaichi;
                    tmp.user = MyApplication.User.username;
                    tmp.idpending = (int) new Date().getTime();
                    homeFragment.getViewmodel().insertchidk(tmp);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalDateTime tmpLocaldatetime = LocalDateTime.of(tmpLocaldate, tmpLocaltime);
                        CustomPendingIntent.CreatePendingintent(homeFragment.getContext(), tmp, tmpLocaldatetime);
                    }
                    Toast.makeText(homeFragment.getContext(), "Insert thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

