package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.model.Message;
import com.example.myapplication.model.Product;
import com.example.myapplication.others.DataToken;
import com.example.myapplication.service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import static com.example.myapplication.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.myapplication.others.ShowNotifyUser.showProgressDialog;
import static com.example.myapplication.service.ServiceAPI.BASE_Service;

public class AddProductActivity extends AppCompatActivity {
    private EditText edtName, edtPrice;
    private Button btnAdd;
    private DataToken dataToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dataToken = new DataToken(AddProductActivity.this);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog(AddProductActivity.this, "Đang thêm sản phẩm");
                Product product = new Product(edtName.getText().toString(), edtPrice.getText().toString());
                addProduct(product);
            }
        });
    }

    private void addProduct(Product product) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.AddProduct(dataToken.getToken(), product)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Message message) {
        dismissProgressDialog();
        try {
            Toast.makeText(getApplicationContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
            if (message.getStatus() == 1) {
                startActivity(new Intent(AddProductActivity.this, ProductActivity.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(Throwable error) {
        dismissProgressDialog();
        Toast.makeText(AddProductActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
    }
}