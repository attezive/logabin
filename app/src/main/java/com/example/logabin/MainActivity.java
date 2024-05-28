package com.example.logabin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.example.logabin.adapter.NavigationAdapter;
import com.example.logabin.db.LocalDatabase;
import com.example.logabin.db.dao.ElementDao;
import com.example.logabin.db.model.ElementModel;
import com.example.logabin.fragment.EditorFragment;
import com.example.logabin.fragment.EducationFragment;
import com.example.logabin.fragment.HomeFragment;
import com.example.logabin.fragment.MenuFragment;
import com.example.logabin.fragment.SchemesFragment;
import com.example.logabin.fragment.SettingsFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private LocalDatabase localDatabase;
    private static TabLayout tl;
    private static ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (localDatabase == null){
            localDatabase = Room.databaseBuilder(this, LocalDatabase.class, "all_elements").allowMainThreadQueries().build();
        }
        if (localDatabase.elementDao().getAllElementModels().isEmpty())
            initialDbLoad();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        EditorFragment.setAutoInteract(preferences.getBoolean("AutoInteract", false));

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tl = findViewById(R.id.navigation);
        vp = findViewById(R.id.viewpager);

        NavigationAdapter adapter = new NavigationAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MenuFragment(localDatabase.elementDao().getAllElementModels()));
        adapter.addFragment(new EditorFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new SettingsFragment(preferences));
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

    private void initialDbLoad(){
        ElementDao elementDao = localDatabase.elementDao();
        elementDao.insert(new ElementModel("Wire", 1, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("Not", 1, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("InputChannel", 0, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("OutputChannel", 1, 0, R.drawable.schemes));
        elementDao.insert(new ElementModel("And", 2, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("Or", 2, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("Xor", 2, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("NotAnd", 2, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("NotOr", 2, 1, R.drawable.schemes));
    }

    public static void setPage(int pageNumber){
        tl.selectTab(tl.getTabAt(pageNumber));
    }
}