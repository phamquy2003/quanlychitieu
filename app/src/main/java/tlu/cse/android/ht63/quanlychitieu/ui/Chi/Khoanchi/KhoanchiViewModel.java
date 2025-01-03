package tlu.cse.android.ht63.quanlychitieu.ui.Chi.Khoanchi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.BTL_Quanlychitieu.Entity.Chi;
import com.google.BTL_Quanlychitieu.Entity.Loaichi;
import com.google.BTL_Quanlychitieu.Repository.RepositoryChi;
import com.google.BTL_Quanlychitieu.Repository.RepositoryLoaiChi;

import java.util.List;

public class KhoanchiViewModel extends AndroidViewModel {

    private RepositoryChi mrepositoryChi;
    private RepositoryLoaiChi mRepositoryLoaiChi;

    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<Loaichi>> mAllLoaiChi;


    public KhoanchiViewModel(@NonNull Application application) {
        super(application);
        mrepositoryChi= new RepositoryChi(application);
        mAllChi = mrepositoryChi.getAllchi();
        mRepositoryLoaiChi = new RepositoryLoaiChi(application);
        mAllLoaiChi = mRepositoryLoaiChi.getAllLoaiChi();
    }

    public LiveData<List<Loaichi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }

    public void insert(Loaichi loaichi){
        mRepositoryLoaiChi.insert(loaichi);
    }
    public void insert(Chi chi){
        mrepositoryChi.insert(chi);
    }
    public void delete(Chi chi){
        mrepositoryChi.delete(chi);
    }
    public void update(Chi chi){
        mrepositoryChi.update(chi);
    }
}