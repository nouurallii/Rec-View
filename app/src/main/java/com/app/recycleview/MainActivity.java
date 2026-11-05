package com.app.recycleview;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProductAdapter adapter;
    private final List<Product> allProducts = new ArrayList<>();
    private final List<Product> visibleProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadProducts();
        visibleProducts.addAll(allProducts);

        RecyclerView recyclerView = findViewById(R.id.productsRecyclerView);
        // Two-column grid matching the reference design.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter(visibleProducts);
        recyclerView.setAdapter(adapter);

        EditText searchInput = findViewById(R.id.searchInput);
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> filterProducts(searchInput.getText().toString()));
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            filterProducts(searchInput.getText().toString());
            return true;
        });
    }

    private void loadProducts() {
        allProducts.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam", "US$48.89", R.drawable.camera_tripod));
        allProducts.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam", "US$55.89", R.drawable.printer));
        allProducts.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam", "US$78.89", R.drawable.camera_tower));
        allProducts.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam", "US$88.55", R.drawable.camera_box));
        allProducts.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam", "US$23.67", R.drawable.webcam));
        allProducts.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam", "US$44.89", R.drawable.camera_tripod2));
        allProducts.add(new Product("Smart Wireless Security Camera", "US$39.99", R.drawable.camera_tripod));
        allProducts.add(new Product("HD Indoor Security Camera", "US$62.50", R.drawable.camera_box));
    }

    private void filterProducts(String query) {
        visibleProducts.clear();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(query.trim().toLowerCase())) {
                visibleProducts.add(product);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
