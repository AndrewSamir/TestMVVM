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

package com.example.testmvvm.adapters.categories;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;

import com.example.testmvvm.activities.MainActivity;
import com.example.testmvvm.data.Data;


public class ItemCategoryViewModel extends BaseObservable
{

    private Data category;
    private Context context;

    public ItemCategoryViewModel(Data people, Context context)
    {
        this.category = people;
        this.context = context;
    }


    public String getCategoryName()
    {
        return category.getCategory();
    }

    public String getCategoryId()
    {
        return category.getCategoryId();
    }


    public void onItemClick(View view)
    {
        context.startActivity(new Intent(view.getContext(), MainActivity.class));
    }

    public void setCategory(Data people)
    {
        this.category = people;
        notifyChange();
    }
}
