/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.testmvvm.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.testmvvm.retorfitconfig.ApiCall;
import com.example.testmvvm.retorfitconfig.RestShopping;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestApplication extends MultiDexApplication
{

    private ApiCall apiCallInterface;
    private Scheduler scheduler;

    private static TestApplication get(Context context)
    {
        return (TestApplication) context.getApplicationContext();
    }

    public static TestApplication create(Context context)
    {
        return TestApplication.get(context);
    }

    public ApiCall getApiCall()
    {
        if (apiCallInterface == null)
        {
            apiCallInterface = RestShopping.create();
        }

        return apiCallInterface;
    }

    public Scheduler subscribeScheduler()
    {
        if (scheduler == null)
        {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setApiCall(ApiCall apiCallInterface)
    {
        this.apiCallInterface = apiCallInterface;
    }

    public void setScheduler(Scheduler scheduler)
    {
        this.scheduler = scheduler;
    }
}
