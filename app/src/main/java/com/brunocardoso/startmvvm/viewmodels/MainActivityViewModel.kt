package com.brunocardoso.startmvvm.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.brunocardoso.startmvvm.MainActivity
import com.brunocardoso.startmvvm.models.NicePlace
import com.brunocardoso.startmvvm.repositories.NicePlaceRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivityViewModel: ViewModel() {

    private var mNicePlaces: MutableLiveData<MutableList<NicePlace>>? = null
    private lateinit var mRepo: NicePlaceRepository
    private var mIsUpdating = MutableLiveData<Boolean>()

    fun init() {
        if (mNicePlaces != null) return

        mRepo = NicePlaceRepository().getInstance()!!
        mNicePlaces = mRepo.getNicePlaces()

    }

    fun addNewNicePlace(nicePlace: NicePlace) {
        mIsUpdating.postValue( true )

        doAsync {

            Thread.sleep(2000)

            uiThread {
                mNicePlaces?.let {

                    val currentPlaces = it.value as MutableList<NicePlace>
                    currentPlaces.add(nicePlace)

                    it.postValue( currentPlaces )
                    mIsUpdating.postValue(false)

                }
            }
        }
    }

    fun getNicePlaces(): MutableLiveData<MutableList<NicePlace>>? {
        return mNicePlaces
    }

    fun getUpdating() : LiveData<Boolean>{
        return mIsUpdating
    }
}