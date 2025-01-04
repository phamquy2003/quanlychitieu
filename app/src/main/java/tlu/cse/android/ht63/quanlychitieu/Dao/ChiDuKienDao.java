package tlu.cse.android.ht63.quanlychitieu.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại chi
public interface ChiDuKienDao {
    @Query("Select * from tabledukienchi where tabledukienchi.user like :struser")
    LiveData<List<ChiDuKien>> findAll(String struser);

    @Query("Select * from tabledukienchi where tabledukienchi.date like :str and tabledukienchi.user like :struser")
    LiveData<List<ChiDuKien>> findAll(String str, String struser);

    @Query("Select sum(sotien) from tabledukienchi where tabledukienchi.user like :struser")
    LiveData<Float> sumTongChiDuKien(String struser);

    @Query("Select sum(sotien) from tabledukienchi where tabledukienchi.date like :strlike and tabledukienchi.user like :struser")
    LiveData<Float> sumTongChiDuKien(String strlike, String struser);
    @Insert
    void insert(ChiDuKien chi);

    @Update
    void update (ChiDuKien chi);

    @Delete
    void delete(ChiDuKien chi);
}