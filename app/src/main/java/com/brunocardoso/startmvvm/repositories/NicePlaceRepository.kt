package com.brunocardoso.startmvvm.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.brunocardoso.startmvvm.models.NicePlace

class NicePlaceRepository {

    private var instance: NicePlaceRepository? = null
    private var dataSet: MutableList<NicePlace>? = ArrayList<NicePlace>()

    fun getInstance(): NicePlaceRepository?{
        if (instance == null){
            instance = NicePlaceRepository()
        }
        return instance
    }

    fun getNicePlaces(): MutableLiveData<MutableList<NicePlace>>{
        setNicePlaces()

        var data = MutableLiveData<MutableList<NicePlace>>()
        data.value = dataSet

        return data
    }

    fun setNicePlaces(){
        dataSet = listOf<NicePlace>(
            NicePlace("Exemplo 1", ""),
            NicePlace("Exemplo 2", ""),
            NicePlace("Exemplo 3", "")
        ) as MutableList<NicePlace>
    }
}