package com.example.meme.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.meme.R;
import com.example.meme.adapter.CategoryAdapter;
import com.example.meme.model.Category;
//import com.example.meme.retrofit.ApiBanHang;
//import com.example.meme.retrofit.RetrofitClient;
import com.example.meme.ultil.CheckConnection;
import com.example.meme.ultil.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.core.Scheduler;
//import io.reactivex.rxjava3.disposables.CompositeDisposable;
//import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShoppingActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ListView listView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ArrayList<Category> categoryArrayList;
    CategoryAdapter categoryAdapter;
//    CompositeDisposable compositeDisposable = new CompositeDisposable();
//    ApiBanHang apiBanHang;

    public int id = 0;
    public String categoryName = "";
    public String imageName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        AnhXa();
//        apiBanHang = RetrofitClient.getInstance(Server.CATEGORY_PATH).create(ApiBanHang.class);

        if(CheckConnection.isConnected(getApplicationContext())) {
            ActionBar();
//            GetItemData();
            Toast.makeText(getApplicationContext(),"ok", Toast.LENGTH_SHORT).show();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Please, check your connection");
            finish();
        }

    }

//    private void GetItemData() {
//        apiBanHang.getCategoryName()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        loaiSpModel -> {
//                            if(loaiSpModel.isSuccess()) {
//                                Toast.makeText(getApplicationContext(), loaiSpModel.getResult().get(0).getCategoryName(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                );
//    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void AnhXa() {
        toolbar = findViewById(R.id.shoppingToolbar);
        recyclerView = findViewById(R.id.shoppingRecyclerView);
        listView = findViewById(R.id.shoppingListView);
        drawerLayout = findViewById(R.id.shoppingDrawerLayout);
        navigationView = findViewById(R.id.shoppingNavView);
        categoryArrayList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryArrayList, getApplicationContext());
        listView.setAdapter(categoryAdapter);
    }
}