package com.example.logabin;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.logabin.adapter.ExampleViewPagerAdapter;
import com.example.logabin.fragment.EditorFragment;
import com.example.logabin.fragment.EducationFragment;
import com.example.logabin.fragment.HomeFragment;
import com.example.logabin.fragment.MenuFragment;
import com.example.logabin.fragment.SchemesFragment;
import com.example.logabin.fragment.SettingsFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TabLayout tl = findViewById(R.id.navigation);
        ViewPager vp = findViewById(R.id.viewpager);

        ExampleViewPagerAdapter adapter = new ExampleViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MenuFragment());
        adapter.addFragment(new EditorFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new SettingsFragment());
        adapter.addFragment(new SchemesFragment());
        adapter.addFragment(new EducationFragment());

        vp.setAdapter(adapter);

        tl.setupWithViewPager(vp);
        tl.getTabAt(0).setIcon(R.drawable.menu);
        tl.getTabAt(1).setIcon(R.drawable.pencil);
        tl.getTabAt(2).setIcon(R.drawable.home);
        tl.removeTabAt(3);
        tl.removeTabAt(3);
        tl.removeTabAt(3);
        tl.selectTab(tl.getTabAt(1));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}