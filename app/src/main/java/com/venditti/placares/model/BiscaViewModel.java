package com.venditti.placares.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BiscaViewModel extends ViewModel {

    private List<Players> listPlayers;


    public static class ViewModelFactory implements ViewModelProvider.Factory {
        private BiscaViewModel biscaViewModel;
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if(modelClass.isAssignableFrom(BiscaViewModel.class)){
                try {
                    return modelClass.getConstructor(BiscaViewModel.class).newInstance(biscaViewModel);
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            throw new IllegalArgumentException("Unkown ViewModel Class");
        }
    }

/*    private final MutableLiveData<AtomicInteger> gameCount = new MutableLiveData<>();
    public LiveData<AtomicInteger> getGameCount(){
        return gameCount;
    }
    public void setGameCount(AtomicInteger gameCount){
        this.gameCount.setValue(gameCount);
    }

    public void incrementGameCount(){
        this.gameCount.getValue().getAndIncrement();
    }*/

}
