package tech.ajira.woodbeei.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tech.ajira.woodbeei.R;
import tech.ajira.woodbeei.filterView.ProductList;
import tech.ajira.woodbeei.filterView.ProductAdapter;
import tech.ajira.woodbeei.filterView.FilterFragment;
import tech.ajira.woodbeei.filterView.Product;
import tech.ajira.woodbeei.filterView.Util;

public class ProductListActivity extends AppCompatActivity implements AAH_FabulousFragment.Callbacks, AAH_FabulousFragment.AnimationListener {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ProductList mData;
    ProductAdapter mAdapter;
    Picasso p;
    List<Product> mList = new ArrayList<>();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    FilterFragment dialogFrag, dialogFrag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mData = Util.getMovies();
        p = Picasso.with(this);
        mList.addAll(mData.getAllMovies());
        mAdapter = new ProductAdapter(mList, p, ProductListActivity.this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        fab.setVisibility(View.VISIBLE);

        dialogFrag1 = FilterFragment.newInstance();
        dialogFrag1.setParentFab(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFrag1.show(getSupportFragmentManager(), dialogFrag1.getTag());
            }
        });

        dialogFrag = FilterFragment.newInstance();
    }

    @Override
    public void onResult(Object result) {
        Log.d("k9res", "onResult: " + result.toString());
        if (result.toString().equalsIgnoreCase("swiped_down")) {
            //do something or nothing
        } else {
            if (result != null) {
                ArrayMap<String, List<String>> applied_filters = (ArrayMap<String, List<String>>) result;
                if (applied_filters.size() != 0) {
                    List<Product> filteredList = mData.getAllMovies();
                    //iterate over arraymap
                    for (Map.Entry<String, List<String>> entry : applied_filters.entrySet()) {
                        Log.d("k9res", "entry.key: " + entry.getKey());
                        switch (entry.getKey()) {
                            case "genre":
                                filteredList = mData.getGenreFilteredMovies(entry.getValue(), filteredList);
                                break;
                            case "rating":
                                filteredList = mData.getRatingFilteredMovies(entry.getValue(), filteredList);
                                break;
                            case "year":
                                filteredList = mData.getYearFilteredMovies(entry.getValue(), filteredList);
                                break;
                            case "quality":
                                filteredList = mData.getQualityFilteredMovies(entry.getValue(), filteredList);
                                break;
                        }
                    }
                    Log.d("k9res", "new size: " + filteredList.size());
                    mList.clear();
                    mList.addAll(filteredList);
                    mAdapter.notifyDataSetChanged();

                } else {
                    mList.addAll(mData.getAllMovies());
                    mAdapter.notifyDataSetChanged();
                }
            }
            //handle result
        }
    }

    public ArrayMap<String, List<String>> getApplied_filters() {
        return applied_filters;
    }

    public ProductList getmData() {
        return mData;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (dialogFrag.isAdded()) {
            dialogFrag.dismiss();
            dialogFrag.show(getSupportFragmentManager(), dialogFrag.getTag());
        }
        if (dialogFrag1.isAdded()) {
            dialogFrag1.dismiss();
            dialogFrag1.show(getSupportFragmentManager(), dialogFrag1.getTag());
        }

    }

    @Override
    public void onOpenAnimationStart() {
        Log.d("aah_animation", "onOpenAnimationStart: ");
    }

    @Override
    public void onOpenAnimationEnd() {
        Log.d("aah_animation", "onOpenAnimationEnd: ");

    }

    @Override
    public void onCloseAnimationStart() {
        Log.d("aah_animation", "onCloseAnimationStart: ");

    }

    @Override
    public void onCloseAnimationEnd() {
        Log.d("aah_animation", "onCloseAnimationEnd: ");

    }
}

