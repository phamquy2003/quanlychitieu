package tlu.cse.android.ht63.quanlychitieu.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.R;
import com.google.BTL_Quanlychitieu.ui.Chi.Loaikhoanchi.Loaikhoanchi;

public class loaikhoanchiDialog {
    Loaichi loaichi;

    Loaikhoanchi fragmentloaikhoanchi;

    EditText ed_loaikhoanchi;


    String type;

    public loaikhoanchiDialog(Loaikhoanchi loaikhoanchi, String type, Loaichi loaichi)
    {
        this.fragmentloaikhoanchi = loaikhoanchi;
        this.loaichi = loaichi;
        this.type = type;
    }

    public void show(){
        final Dialog dialog = new Dialog(fragmentloaikhoanchi.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loaichi);

        Window window = dialog.getWindow();
        if (window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = Gravity.CENTER;
        window.setAttributes(windowAttr);
        dialog.setCancelable(true);

        Button btn_thoat, btn_luu;
        btn_thoat = dialog.findViewById(R.id.btn_thoat);
        btn_luu = dialog.findViewById(R.id.btn_luu);
        ed_loaikhoanchi = dialog.findViewById(R.id.ed_tenloaikhoanchi);


        if (type.equals("edit"))
        {
            ed_loaikhoanchi.setText(loaichi.Tenloaichi);
        }


        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();;
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("edit")) {
                    loaichi.Tenloaichi = ed_loaikhoanchi.getText().toString();
                    fragmentloaikhoanchi.getViewModel().update(loaichi);
                    Toast.makeText(fragmentloaikhoanchi.getContext(), "Update thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Loaichi tmp = new Loaichi();
                    tmp.Tenloaichi = ed_loaikhoanchi.getText().toString();
                    tmp.user = MyApplication.User.username;
                    fragmentloaikhoanchi.getViewModel().insert(tmp);
                    Toast.makeText(fragmentloaikhoanchi.getContext(), "Insert thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

