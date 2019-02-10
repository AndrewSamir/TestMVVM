package com.example.testmvvm.testActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.testmvvm.R;
import com.example.testmvvm.activities.BaseViewModel;
import com.example.testmvvm.application.TestApplication;
import com.example.testmvvm.data.Data;
import com.example.testmvvm.retorfitconfig.ApiCall;
import com.example.testmvvm.data.ModelCommenResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TestViewModel extends BaseViewModel
{
    //region fields
    public ObservableField<String> testString = new ObservableField<>();
    private ObservableInt testInt = new ObservableInt(0);
    private MutableLiveData<Integer> x = new MutableLiveData<>();
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Data> peopleList;
    //endregion

    //region constructor
    TestViewModel(@NonNull Context context)
    {
        this.context = context;
        peopleList = new ArrayList<>();
    }
    //endregion

    //region clicks
    public void AtBtnClick()
    {
        testInt.set(testInt.get() + 5);
        x.postValue(testInt.get() + 5);
        testString.set(testInt.get() + "");
/*
        if (testInt.get() > 20)
            setBtnVisibality();*/

        fetchCategories();
    }

    //endregion

    //region Live Data
    LiveData<Integer> testLiveData()
    {
        return x;
    }
    //endregion

    //region calls

    private void fetchCategories()
    {
        TestApplication peopleApplication = TestApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();

        Disposable disposable = peopleService.callcategories()
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ModelCommenResponse>()
                {
                    @Override
                    public void accept(ModelCommenResponse peopleResponse)
                    {
//                        showMessage(R.string.connection_error, context);
                        changePeopleDataSet(peopleResponse.getData());
                        Log.d("calls", peopleResponse.getStatus());
                    }
                }, new Consumer<Throwable>()
                {
                    @Override
                    public void accept(Throwable throwable)
                    {
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    //endregion

    
    //region functions
    private void changePeopleDataSet(List<Data> peoples)
    {
        peopleList.addAll(peoples);
        setChanged();
        notifyObservers("call");
    }

    List<Data> getPeopleList()
    {
        return peopleList;
    }


    private void unSubscribeFromObservable()
    {
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
        {
            compositeDisposable.dispose();
        }
    }

    void reset()
    {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
    //endregion
}
