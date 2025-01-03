package tlu.cse.android.ht63.quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.BTL_Quanlychitieu.Entity.Loaichi;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại thu
public interface LoaiChiDao {
    @Query("Select * from tableloaichi where tableloaichi.isDelete = 0 and tableloaichi.user like :struser")
    LiveData<List<Loaichi>> findAll(String struser);

    @Insert
    void insert(Loaichi loaiChi);

    @Update
    void update (Loaichi loaiChi);

    @Delete
    void delete(Loaichi loaiChi);
}