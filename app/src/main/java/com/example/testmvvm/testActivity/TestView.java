package com.example.testmvvm.testActivity;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.testmvvm.R;
import com.example.testmvvm.adapters.categories.CategoryAdapter;
import com.example.testmvvm.databinding.TestXmlBinding;

import java.util.Observable;

public class TestView extends AppCompatActivity implements java.util.Observer
{
    private TestViewModel testViewModel;
    TestXmlBinding testXmlBinding;
    Button btnTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListPeopleView(testXmlBinding.listPeople);
        setupObserver(testViewModel);
        btnTest = findViewById(R.id.btnTest);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        testViewModel.reset();
    }

    private void initDataBinding()
    {
        testXmlBinding = DataBindingUtil.setContentView(this, R.layout.test_xml);
        testViewModel = new TestViewModel(this);
        testXmlBinding.setTestViewModel(testViewModel);
        setLiveDataListiner();
    }

    private void setupListPeopleView(RecyclerView listPeople)
    {
        CategoryAdapter adapter = new CategoryAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setLiveDataListiner()
    {
        testViewModel.testLiveData().observe(this, new Observer<Integer>()
        {
            @Override
            public void onChanged(@Nullable Integer integer)
            {
                btnTest.setText(integer + "");
            }
        });
    }


    public void setupObserver(Observable observable)
    {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        String flag = (String) arg;

        switch (flag)
        {
            case "test":

                break;
            case "call":
                if (o instanceof TestViewModel)
                {
                    CategoryAdapter peopleAdapter = (CategoryAdapter) testXmlBinding.listPeople.getAdapter();
                    TestViewModel peopleViewModel = (TestViewModel) o;
                    peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
                }
                break;
        }

    }
}
