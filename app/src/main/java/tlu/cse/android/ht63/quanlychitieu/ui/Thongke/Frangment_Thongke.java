package tlu.cse.android.ht63.quanlychitieu.ui.Thongke;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiThu;
import com.google.BTL_Quanlychitieu.Entity.ThongKeTheoNgay;
import com.google.BTL_Quanlychitieu.Other.CustomNumber;
import com.google.BTL_Quanlychitieu.databinding.FragmentFrangmentThongkeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Frangment_Thongke extends Fragment {
    LineChart lineChart_chitheongay;
    LineChart lineChart_thutheongay;
    LineChart lineChart_loaithu;
    LineChart lineChart_loaichi;
    Button btn_change;


    TextView tv_tongthu, tv_tongchi, tv_ngaythang;
    int tongthu = 0;
    int tongchi = 0;

    boolean kt1, kt2;
    Frangment_Thongke current;

    ThongkeViewModel mViewModel;

    FragmentFrangmentThongkeBinding binding;


    public ThongkeViewModel getViewmodel() {
        return mViewModel;
    }

    public static Frangment_Thongke newInstance() {
        return new Frangment_Thongke();
    }


    public static Frangment_Thongke newInstance(String param1, String param2) {
        Frangment_Thongke fragment = new Frangment_Thongke();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrangmentThongkeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(ThongkeViewModel.class);
        current = this;
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int thang = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        tv_ngaythang = binding.tvNgaythang;
        tv_tongchi = binding.tbTongchitk;
        tv_tongthu = binding.tbTongthutk;
        lineChart_loaithu = binding.chartTheoloaithu;
        lineChart_loaichi = binding.chartTheoloaichi;
        lineChart_chitheongay = binding.chartChitheongay;
        lineChart_thutheongay = binding.chartThutheongay;
        binding.tvNgaythang.setText(""+ thang + "-" + nam);
        btn_change = binding.btnChange;
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(current.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearr, int monthh, int dayOfMonth) {
                        tv_ngaythang.setText("" + (monthh+1) +"-"+yearr);
                        Loaddata(monthh+1,yearr);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });
        Loaddata(thang, nam);
    }

    void Loaddata(int thang, int nam)
    {
        kt1 = false;
        kt2 = false;
        mViewModel.getTongthu(thang, nam).observe(this.getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat != null)
                    tongthu = aFloat.intValue();
                else tongthu = 0;
                tv_tongthu.setText(CustomNumber.formatNumber(tongthu) + " VND");
            }
        });
        mViewModel.getTongchi(thang, nam).observe(this.getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if (aFloat!= null)
                    tongchi = aFloat.intValue();
                else tongchi = 0;
                tv_tongchi.setText(CustomNumber.formatNumber(tongchi) + " VND");
            }
        });

        mViewModel.getListchitheongay(thang, nam).observe(getActivity(), new Observer<List<ThongKeTheoNgay>>() {
            @Override
            public void onChanged(List<ThongKeTheoNgay> thongKeTheoNgays) {
                if(thongKeTheoNgays != null)
                    Loadcharttheongay(thongKeTheoNgays, lineChart_chitheongay, tongchi);
            }
        });

        mViewModel.getListthutheongay(thang, nam).observe(getActivity(), new Observer<List<ThongKeTheoNgay>>() {
            @Override
            public void onChanged(List<ThongKeTheoNgay> thongKeTheoNgays) {
                if (thongKeTheoNgays != null)
                    Loadcharttheongay(thongKeTheoNgays, lineChart_thutheongay, tongthu);
            }
        });

        mViewModel.sumByLoaiChi(thang, nam).observe(getActivity(), new Observer<List<ThongKeLoaiChi>>() {
            @Override
            public void onChanged(List<ThongKeLoaiChi> thongKeLoaiChis) {
                Loadcharttheoloaichi(thongKeLoaiChis);
            }
        });

        mViewModel.sumByLoaiThu(thang, nam).observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                Loadcharttheoloaithu(thongKeLoaiThus);
            }
        });
    }

    private void Loadcharttheoloaithu(List<ThongKeLoaiThu> thongKeLoaiThus) {
        List<String> xValues = new ArrayList<>();
        List<Entry> entries1 = new ArrayList<>();

        for (int i=0; i<thongKeLoaiThus.size(); i++)
        {
            ThongKeLoaiThu xx = thongKeLoaiThus.get(i);
            xValues.add(xx.Tenloaithu);
            entries1.add(new Entry(i, xx.tong));
        }

        Description description = new Description();
        description.setText("VNĐ");
        description.setPosition(150f, 15f); // vi tri hien description text
        lineChart_loaithu.setDescription(description);
        lineChart_loaithu.getAxisRight().setDrawLabels(false); // an thong tin chia cot phia trai


        XAxis xAxis = lineChart_loaithu.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // vi tri hien ten
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(thongKeLoaiThus.size()); // so cot hien thi
        xAxis.setGranularity(1f); // mat do hien ten

        YAxis yAxis = lineChart_loaithu.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum((float)tongthu);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);


        LineDataSet dataset1 = new LineDataSet(entries1, "Loại thu");
        dataset1.setColor(Color.RED);
        dataset1.setLineWidth(2f);
        LineData lineData = new LineData(dataset1);
        lineChart_loaithu.setData(lineData);
        lineChart_loaithu.invalidate();
    }

    private void Loadcharttheoloaichi(List<ThongKeLoaiChi> thongKeLoaiChis) {
        List<String> xValues = new ArrayList<>();
        List<Entry> entries1 = new ArrayList<>();

        for (int i=0; i<thongKeLoaiChis.size(); i++)
        {
            ThongKeLoaiChi xx = thongKeLoaiChis.get(i);
            xValues.add(xx.Tenloaichi);
            entries1.add(new Entry(i, xx.tong));
        }

        Description description = new Description();
        description.setText("VNĐ");
        description.setPosition(150f, 15f); // vi tri hien description text
        lineChart_loaichi.setDescription(description);
        lineChart_loaichi.getAxisRight().setDrawLabels(false); // an thong tin chia cot phia trai


        XAxis xAxis = lineChart_loaichi.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // vi tri hien ten
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(thongKeLoaiChis.size()); // so cot hien thi
        xAxis.setGranularity(1f); // mat do hien ten

        YAxis yAxis = lineChart_loaichi.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum((float)tongchi);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);


        LineDataSet dataset1 = new LineDataSet(entries1, "Loại chi");
        dataset1.setColor(Color.RED);
        dataset1.setLineWidth(2f);
        LineData lineData = new LineData(dataset1);
        lineChart_loaichi.setData(lineData);
        lineChart_loaichi.invalidate();
    }

    public void Loadcharttheongay(List<ThongKeTheoNgay> listtheongay, LineChart lineChart, int tong)
    {
        List<String> xValues = new ArrayList<>();
        List<Entry> entries1 = new ArrayList<>();
        for (int i=0; i<listtheongay.size(); i++)
        {
            ThongKeTheoNgay xx = listtheongay.get(i);
            xValues.add("" + xx.date.charAt(xx.date.length()-2)+ xx.date.charAt(xx.date.length()-1));
            entries1.add(new Entry(i, xx.tong));
        }

        Description description = new Description();
        description.setText("VNĐ");
        description.setPosition(150f, 15f); // vi tri hien description text
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false); // an thong tin chia cot phia trai


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // vi tri hien ten
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(listtheongay.size()); // so cot hien thi
        xAxis.setGranularity(1f); // mat do hien ten

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum((float)tong);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        LineDataSet dataset1 = new LineDataSet(entries1, "Khoản thu/chi");
        dataset1.setColor(Color.RED);
        dataset1.setLineWidth(2f);
        LineData lineData = new LineData(dataset1);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}