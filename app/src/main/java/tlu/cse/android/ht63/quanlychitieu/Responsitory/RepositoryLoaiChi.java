package tlu.cse.android.ht63.quanlychitieu.Responsitory;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.LoaiChiDao;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.RoomDTB.AppDTB_Chi;

import java.util.List;

public class RepositoryLoaiChi {
    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<Loaichi>> mAllLoaiChi;

    public RepositoryLoaiChi(Application application) {
        this.mLoaiChiDao = AppDTB_Chi.getDatabase(application).loaiChiDao();
        mAllLoaiChi= mLoaiChiDao.findAll(MyApplication.User.username);
    }
    public LiveData<List<Loaichi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }

    public void insert(Loaichi loaiChi){
        new InsertThread(mLoaiChiDao,loaiChi).start();
    }
    public void delete(Loaichi loaiChi){
        new DeleteThread(mLoaiChiDao, loaiChi).start();
    }
    public void update(Loaichi loaiChi){
        new UpdateThread(mLoaiChiDao,loaiChi).start();
    }
    class UpdateThread extends Thread {
        private LoaiChiDao mLoaiChiDao;
        private Loaichi loaichi;
        public UpdateThread(LoaiChiDao loaiChiDao, Loaichi ... loaiChis){
            this.mLoaiChiDao=loaiChiDao;
            this.loaichi = loaiChis[0];
        }
        @Override
        public void run() {
            mLoaiChiDao.update(loaichi);
        }
    }
    class InsertThread extends Thread{
        private LoaiChiDao mLoaiChiDao;
        private Loaichi loaichi;
        public InsertThread(LoaiChiDao loaiChiDao, Loaichi... loaiChis){
            this.mLoaiChiDao=loaiChiDao;
            this.loaichi = loaiChis[0];
        }
        @Override
        public void run() {
            mLoaiChiDao.insert(loaichi);
        }
    }
    class DeleteThread extends Thread{
        private LoaiChiDao mLoaiChiDao;
        private Loaichi loaichi;
        public DeleteThread(LoaiChiDao loaiChiDao, Loaichi... loaiChis){
            this.mLoaiChiDao=loaiChiDao;
            this.loaichi = loaiChis[0];
        }
        @Override
        public void run() {
            mLoaiChiDao.delete(loaichi);
        }
    }
}
