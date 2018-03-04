package com.example.andrew.ivegan.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.andrew.ivegan.Fragment.BlankFragment;
import com.example.andrew.ivegan.Fragment.MapFragment;
import com.example.andrew.ivegan.Fragment.RecyclerViewFragment;
import com.example.andrew.ivegan.R;

public class CardListActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        // Установить Toolbar для замены ActionBar'а.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Найти наш view drawer'а
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        // Поиск navigation view
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_draw);
        // Раздуть header view во время выполнения
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        // Теперь, при необходимости, мы можем найти элементы внутри
        // header'а
        ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);

        Fragment fragment = null;
        Class fragmentClass = MapFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Действие home/up action bar'а должно открывать или закрывать drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Создать новый фрагмент и задать фрагмент для отображения
        // на основе нажатия на элемент навигации
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = RecyclerViewFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = MapFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = BlankFragment.class;
                break;
            default:
                fragmentClass = BlankFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставить фрагмент, заменяя любой существующий
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Выделение существующего элемента выполнено с помощью
        // NavigationView
        menuItem.setChecked(true);
        // Установить заголовок для action bar'а
        setTitle(menuItem.getTitle());
        // Закрыть navigation drawer
        mDrawer.closeDrawers();
    }
}
