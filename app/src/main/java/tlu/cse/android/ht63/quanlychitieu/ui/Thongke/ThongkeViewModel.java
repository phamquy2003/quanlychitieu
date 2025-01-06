package tlu.cse.android.ht63.quanlychitieu.ui.Thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiThu;
import com.google.BTL_Quanlychitieu.Entity.ThongKeTheoNgay;
import com.google.BTL_Quanlychitieu.Repository.RepositoryChi;
import com.google.BTL_Quanlychitieu.Repository.RepositoryLoaiChi;
import com.google.BTL_Quanlychitieu.Repository.RepositoryLoaiThu;
import com.google.BTL_Quanlychitieu.Repository.RepositoryThu;

import java.util.ArrayList;
import java.util.List;

public class ThongkeViewModel extends AndroidViewModel {

    private LiveData<List<ThongKeTheoNgay>> Alltheongay;
    private RepositoryThu mRepositoryThu;

    private RepositoryChi mRepositoryChi;

    private RepositoryLoaiChi mRepositoryLoaiChi;

    private RepositoryLoaiThu mRepositoryLoaiThu;


    public ThongkeViewModel(@NonNull Application application) {
        super(application);
        mRepositoryThu       =  new RepositoryThu(application);
        mRepositoryChi       =  new RepositoryChi(application);
        mRepositoryLoaiThu   =  new RepositoryLoaiThu(application);
        mRepositoryLoaiChi   =  new RepositoryLoaiChi(application);
    }

    public List<List<ThongKeTheoNgay>> pulldata(int thang, int nam)
    {
        List<List<ThongKeTheoNgay>> tmp = new ArrayList<>();
        tmp.add(mRepositoryChi.getAllchitheongay(thang, nam).getValue());
        tmp.add(mRepositoryThu.getAllthutheongay(thang, nam).getValue());
        return tmp;
    }

    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(int thang, int nam){
        return mRepositoryChi.sumByLoaiChi(thang, nam);
    }

    public LiveData<List<ThongKeLoaiThu>> sumByLoaiThu(int thang, int nam){
        return mRepositoryThu.sumByLoaiThu(thang, nam);
    }

    public LiveData<List<ThongKeTheoNgay>> getListchitheongay(int thang, int nam)
    {
        return mRepositoryChi.getAllchitheongay(thang, nam);
    }
    public LiveData<List<ThongKeTheoNgay>> getListthutheongay(int thang, int nam)
    {
        return mRepositoryThu.getAllthutheongay(thang, nam);
    }
    public LiveData<Float> getTongchi(int thang, int nam)
    {
        return mRepositoryChi.getTongchi(thang, nam);
    }
    public LiveData<Float> getTongthu(int thang, int nam)
    {
        return mRepositoryThu.sumTongThu(thang, nam);
    }

}