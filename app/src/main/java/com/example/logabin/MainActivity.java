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
import com.example.logabin.db.EducationDatabase;
import com.example.logabin.db.ElementDatabase;
import com.example.logabin.db.dao.EducationDao;
import com.example.logabin.db.dao.ElementDao;
import com.example.logabin.db.model.EducationModel;
import com.example.logabin.db.model.ElementModel;
import com.example.logabin.fragment.EditorFragment;
import com.example.logabin.fragment.EducationFragment;
import com.example.logabin.fragment.HomeFragment;
import com.example.logabin.fragment.MenuFragment;
import com.example.logabin.fragment.SchemesFragment;
import com.example.logabin.fragment.SettingsFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ElementDatabase elementDatabase;
    private EducationDatabase educationDatabase;
    private static TabLayout tl;
    private static ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (elementDatabase == null){
            elementDatabase = Room.databaseBuilder(this, ElementDatabase.class, "all_elements").allowMainThreadQueries().build();
        }
        if (elementDatabase.elementDao().getAllElementModels().isEmpty())
            initialDbLoad();

        if (educationDatabase == null){
            educationDatabase = Room.databaseBuilder(this, EducationDatabase.class, "all_educations").allowMainThreadQueries().build();
        }
        if (educationDatabase.educationDao().getAllEducationModels().isEmpty())
            initialEducationLoad();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        EditorFragment.setAutoInteract(preferences.getBoolean("AutoInteract", false));

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tl = findViewById(R.id.navigation);
        vp = findViewById(R.id.viewpager);

        NavigationAdapter adapter = new NavigationAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MenuFragment(elementDatabase.elementDao().getAllElementModels()));
        adapter.addFragment(new EditorFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new SettingsFragment(preferences));
        adapter.addFragment(new SchemesFragment());
        adapter.addFragment(new EducationFragment(educationDatabase.educationDao().getAllEducationModels()));

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
        ElementDao elementDao = elementDatabase.elementDao();
        elementDao.insert(new ElementModel("Wire", 1, 1, R.drawable.wire_line_horizontal));
        elementDao.insert(new ElementModel("Not", 1, 1, R.drawable.not_false));
        elementDao.insert(new ElementModel("InputChannel", 0, 1, R.drawable.input));
        elementDao.insert(new ElementModel("OutputChannel", 1, 0, R.drawable.output));
        elementDao.insert(new ElementModel("And", 2, 1, R.drawable.and_top_false));
        elementDao.insert(new ElementModel("Or", 2, 1, R.drawable.or_top_false));
        elementDao.insert(new ElementModel("Xor", 2, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("NotAnd", 2, 1, R.drawable.schemes));
        elementDao.insert(new ElementModel("NotOr", 2, 1, R.drawable.schemes));
    }

    private void initialEducationLoad(){
        EducationDao educationDao = educationDatabase.educationDao();
        educationDao.insert(new EducationModel("And", R.string.and_information, R.drawable.logic_and_design, R.drawable.logic_and_table));
    }

    public static void setPage(int pageNumber){
        tl.selectTab(tl.getTabAt(pageNumber));
    }
}