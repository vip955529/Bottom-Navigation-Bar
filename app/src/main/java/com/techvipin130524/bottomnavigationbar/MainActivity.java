package com.techvipin130524.bottomnavigationbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.techvipin130524.bottomnavigationbar.databinding.ActivityMainBinding; // Import the binding class
import com.techvipin130524.bottomnavigationbar.fragment.HomeFragment;
import com.techvipin130524.bottomnavigationbar.fragment.LibraryFragment;
import com.techvipin130524.bottomnavigationbar.fragment.ProfileFragment;
import com.techvipin130524.bottomnavigationbar.fragment.ShortsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // Declare the binding variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge support
        EdgeToEdge.enable(this);

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the root view

        // Set padding for the system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize BottomNavigationView
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setBackground(null);

        // Set item click listener
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.shorts) {
                replaceFragment(new ShortsFragment());
            } else if (item.getItemId() == R.id.subscriptions) {
                replaceFragment(new ProfileFragment());
            } else if (item.getItemId() == R.id.library) {
                replaceFragment(new LibraryFragment());
            }
            return true;
        });

        binding.floatingActionButton.setOnClickListener(v->
                Toast.makeText(this, "Floating Action Button Clicked", Toast.LENGTH_SHORT).show());

    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Make sure to clean up the binding reference when the activity is destroyed
        binding = null;
    }
}
