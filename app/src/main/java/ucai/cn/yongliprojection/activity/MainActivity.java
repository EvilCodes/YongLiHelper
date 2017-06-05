package ucai.cn.yongliprojection.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import ucai.cn.yongliprojection.R;
import ucai.cn.yongliprojection.fragments.HomePageFragment;
import ucai.cn.yongliprojection.fragments.InvestmentInformation;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private NavigationView navigationView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Bundle bundle ;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHomePage();
        setListener();

    }

    private void initHomePage() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HomePageFragment homePageFragment = new HomePageFragment();
        transaction.add(R.id.fragment_parent, homePageFragment).show(homePageFragment);
        transaction.commit();

    }

    private void setListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bundle.putInt("menu_id",item.getItemId());
                item.setChecked(true);
                InvestmentInformation investmentInformation = new InvestmentInformation();
                investmentInformation.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_parent,investmentInformation);
                transaction.commit();
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }


    private void initView() {
        bundle = new Bundle();
        navigationView = (NavigationView) findViewById(R.id.item_menu);
        mToolbar = (Toolbar) findViewById(R.id.item_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }
}
