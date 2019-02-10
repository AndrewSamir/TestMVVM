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

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.testmvvm.R;
import com.example.testmvvm.data.Data;
import com.example.testmvvm.databinding.CategoryBinding;

import java.util.Collections;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder> {

  private List<Data> peopleList;

  public CategoryAdapter() {
    this.peopleList = Collections.emptyList();
  }

  @NonNull
  @Override public CategoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    CategoryBinding itemPeopleBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category,
            parent, false);
    return new CategoryAdapterViewHolder(itemPeopleBinding);
  }

  @Override public void onBindViewHolder(CategoryAdapterViewHolder holder, int position) {
    holder.bindPeople(peopleList.get(position));
  }

  @Override public int getItemCount() {
    return peopleList.size();
  }

  public void setPeopleList(List<Data> peopleList) {
    this.peopleList = peopleList;
    notifyDataSetChanged();
  }

  static class CategoryAdapterViewHolder extends RecyclerView.ViewHolder {
    CategoryBinding mCategoryBinding;

    CategoryAdapterViewHolder(CategoryBinding itemPeopleBinding) {
      super(itemPeopleBinding.itemPeople);
      this.mCategoryBinding = itemPeopleBinding;
    }

    void bindPeople(Data people) {
      if (mCategoryBinding.getItemCategoryViewModel() == null) {
        mCategoryBinding.setItemCategoryViewModel(
            new ItemCategoryViewModel(people, itemView.getContext()));
      } else {
        mCategoryBinding.getItemCategoryViewModel().setCategory(people);
      }
    }
  }
}
