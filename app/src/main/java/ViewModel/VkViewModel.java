package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Data.DBProfile;
import Data.VkRepository;

public class VkViewModel extends AndroidViewModel {

    private final VkRepository vkRepository;
    public VkViewModel(@NonNull Application application) {
        super(application);
        vkRepository = new VkRepository();
    }

    public LiveData<DBProfile> getUsernameVk(String token, String userId){
        return vkRepository.getInfoProfile(token, userId);
    }

//    public void insert(DBProfile dbProfile){
//        credoRepository.insertCredo(dbCredo);
//    }
//
//    public void update(DBProfile dbProfile){
//        credoRepository.updateCredo(dbCredo);
//    }
//
//    public void delete(DBProfile dbProfile){
//        credoRepository.deleteCredo(dbCredo);
//    }
//
//    public List<DBProfile> getAllCredo(){
//        return credoRepository.getAllCredo();
//    }
//
//    public DBProfile getCredoId(long id){
//        return credoRepository.getUserFromIdLive(id);
//    }
}
