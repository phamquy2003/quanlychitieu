package tlu.cse.android.ht63.quanlychitieu.Responsitory;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Dao.LoaiThuDao;
import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.BTL_Quanlychitieu.RoomDTB.App_DTB_Thu;

import java.util.List;

public class RepositoryLoaiThu {
    private LoaiThuDao mloaiThuDao;
    private LiveData<List<Loaithu>> mAllLoaiThu;

    public RepositoryLoaiThu(Application application) {
        this.mloaiThuDao = App_DTB_Thu.getDatabase(application).loaiThuDao();
        mAllLoaiThu= mloaiThuDao.findAll(MyApplication.User.username); //lấy về ds loại thu
    }
    public LiveData<List<Loaithu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }


    public void insert(Loaithu loaiThu){
        new InsertThread(mloaiThuDao, loaiThu).start();
    }
    public void delete(Loaithu loaiThu){
        new DeleteThread(mloaiThuDao,loaiThu).start();

    }
    public void update(Loaithu loaiThu){
        new UpdateThread(mloaiThuDao,loaiThu).start();
    }
    class UpdateThread extends Thread{
        private LoaiThuDao mloaiThuDao;
        private Loaithu loaithu;
        
        public UpdateThread(LoaiThuDao loaiThuDao, Loaithu ...loaithus){
            this.mloaiThuDao=loaiThuDao;
            this.loaithu = loaithus[0];
        }
        @Override
        public void run() {
            mloaiThuDao.update(loaithu);
        }
    }
    class InsertThread extends Thread{
        private LoaiThuDao mloaiThuDao;
        private Loaithu loaithu;
        public InsertThread(LoaiThuDao loaiThuDao, Loaithu ...loaithus){
            this.mloaiThuDao=loaiThuDao;
            this.loaithu = loaithus[0];
        }
        @Override
        public void run() {
            mloaiThuDao.insert(loaithu);
        }
    }
    class DeleteThread extends Thread{
        private LoaiThuDao mloaiThuDao;
        private Loaithu loaithu;
        public DeleteThread(LoaiThuDao loaiThuDao, Loaithu... loaiThus){
            this.mloaiThuDao=loaiThuDao;
            this.loaithu = loaiThus[0];
        }
        @Override
        public void run() {
            mloaiThuDao.delete(loaithu);
        }
    }
}
