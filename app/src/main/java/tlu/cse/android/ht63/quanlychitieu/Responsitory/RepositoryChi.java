package tlu.cse.android.ht63.quanlychitieu.Responsitory;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.ChiDao;
import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeLoaiChi;
import com.google.BTL_Quanlychitieu.Entity.ThongKeTheoNgay;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.RoomDTB.AppDTB_Chi;

import java.util.Calendar;
import java.util.List;

public class RepositoryChi {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    private LiveData<List<Chi>> mAllChibymonth;

    private LiveData<Float> tongchi;

    public RepositoryChi(Application application) {
        this.mChiDao = AppDTB_Chi.getDatabase(application).chiDao();
        int thang = Calendar.getInstance().get(Calendar.MONTH)+1;
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        mAllChi= mChiDao.findAll(MyApplication.User.username);
        tongchi = mChiDao.sumTongChi((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"), MyApplication.User.username);
        mAllChibymonth = mChiDao.findAll((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"), MyApplication.User.username);
    }


    // câu lệnh sau where (thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%")
    public LiveData<List<Chi>> getAllchi(){
        return mAllChi;
    }

    public LiveData<List<Chi>> getAllChibymonth(){
        return mAllChibymonth;
    }

    public LiveData<Float> getTongchi(){
        return tongchi;
    }

    public LiveData<Float> getTongchi(int thang, int nam){
        return mChiDao.sumTongChi((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"), MyApplication.User.username);
    }

    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(int thang, int nam){
        return mChiDao.sumByLoaiChi((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"), MyApplication.User.username);
    }
    public LiveData<List<ThongKeTheoNgay>> getAllchitheongay(int thang, int nam)
    {
        return mChiDao.sumByNgay((thang < 10 ? nam+ "-0"+thang+"-%" : nam+"-"+thang+"-%"), MyApplication.User.username);
    }

    public void insert(Chi chi){
        new InsertThread(mChiDao, chi).start();
    }
    public void delete(Chi chi){
        new DeleteThread(mChiDao,chi).start();

    }

    public void update(Chi chi){
        new UpdateThread(mChiDao, chi).start();
    }
    class UpdateThread extends Thread {
        private ChiDao mChiDao;
        private Chi chi;
        public UpdateThread(ChiDao chiDao, Chi ...chis){
            this.mChiDao=chiDao;
            this.chi = chis[0];
        }
        @Override
        public void run() {
            mChiDao.update(this.chi);
        }
    }
    class InsertThread extends Thread{
        private ChiDao mChiDao;
        private Chi chi;
        public InsertThread(ChiDao chiDao, Chi ...chis){
            this.mChiDao=chiDao;
            chi = chis[0];
        }
        @Override
        public void run() {
            Log.d("them","thanhcong");
            mChiDao.insert(chi);
        }
    }
    class DeleteThread extends Thread{
        private ChiDao mChiDao;
        private Chi chi;
        public DeleteThread(ChiDao chiDao, Chi ...chi){
            this.mChiDao=chiDao;
            this.chi = chi[0];
        }
        @Override
        public void run() {
            mChiDao.delete(chi);
        }
    }
}