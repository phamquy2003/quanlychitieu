package tlu.cse.android.ht63.quanlychitieu.ui.Thu.Khoanthu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Entity.Loaithu;
import com.google.BTL_Quanlychitieu.Entity.Thu;
import com.google.BTL_Quanlychitieu.Repository.RepositoryLoaiThu;
import com.google.BTL_Quanlychitieu.Repository.RepositoryThu;

import java.util.List;

public class KhoanthuViewModel extends AndroidViewModel {
    private RepositoryThu mrepositoryThu;
    private RepositoryLoaiThu mRepositoryLoaiThu;

    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<Loaithu>> mAllLoaiThu;

    public KhoanthuViewModel(@NonNull Application application) {
        super(application);
        mrepositoryThu= new RepositoryThu(application);
        mAllThu = mrepositoryThu.getAllthu(); //trả về ds loại thu có trong csdl
        mRepositoryLoaiThu = new RepositoryLoaiThu(application);
        mAllLoaiThu = mRepositoryLoaiThu.getAllLoaiThu();
    }

    public LiveData<List<Loaithu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public LiveData<List<Thu>> getAllThu(){
        return mAllThu;
    }

    public void insert(Loaithu loaithu){
        mRepositoryLoaiThu.insert(loaithu);
    }
    public void insert(Thu thu){
        mrepositoryThu.insert(thu);
    }
    public void delete(Thu thu){
        mrepositoryThu.delete(thu);
    }
    public void update(Thu thu){
        mrepositoryThu.update(thu);
    }
}