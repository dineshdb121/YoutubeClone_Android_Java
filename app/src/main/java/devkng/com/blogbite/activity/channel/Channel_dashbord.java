package devkng.com.blogbite.activity.channel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.tabs.TabLayout;
import devkng.com.blogbite.R;
import devkng.com.blogbite.activity.search.Search;
import devkng.com.blogbite.fragments.tab_layout.Cdf_about;
import devkng.com.blogbite.fragments.tab_layout.Cdf_audio;
import devkng.com.blogbite.fragments.tab_layout.Cdf_channel;
import devkng.com.blogbite.fragments.tab_layout.Cdf_collection;
import devkng.com.blogbite.fragments.tab_layout.Cdf_files;
import devkng.com.blogbite.fragments.tab_layout.Cdf_home;
import devkng.com.blogbite.fragments.tab_layout.Cdf_hub;
import devkng.com.blogbite.fragments.tab_layout.Cdf_images;
import devkng.com.blogbite.fragments.tab_layout.Cdf_page;
import devkng.com.blogbite.fragments.tab_layout.Cdf_videos;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class Channel_dashbord extends AppCompatActivity {
    private String getFb_account_name;
    private String getFb_account_profile_url;
    private ImageView imageView;
    private ConstraintLayout searchModel;
    private TabLayout tabLayout;
    private TextView tool_Title;
    private TextView tv;
    private ViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_dashbord);
        statusBarColor();
        this.tabLayout = (TabLayout) findViewById(R.id.channel_dash_tablayout);
        this.viewPager = (ViewPager) findViewById(R.id.channel_dash_viewpager);
        this.searchModel = (ConstraintLayout) findViewById(R.id.channel_dash_tool_search_contain);
        this.tv = (TextView) findViewById(R.id.cdb_textView);
        this.tool_Title = (TextView) findViewById(R.id.channel_dash_tool_title);
        this.imageView = (ImageView) findViewById(R.id.cdb_imageView);
        this.getFb_account_name = getIntent().getStringExtra("getFb_account_name");
        this.getFb_account_profile_url = getIntent().getStringExtra("getFb_account_profile_url");
        this.tv.setText(this.getFb_account_name);
        this.tool_Title.setText(this.getFb_account_name);
        Glide.with((FragmentActivity) this).load(this.getFb_account_profile_url).placeholder((int) R.drawable.account_for_text).error(R.drawable.account_for_text).diskCacheStrategy(DiskCacheStrategy.ALL).into(this.imageView);
        this.searchModel.setOnClickListener(new View.OnClickListener() { // from class: dkprojects.com.blogbite.activity.channel.-$$Lambda$Channel_dashbord$nnGiWwri6bxAJNWMJkVVt-yeaO4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Channel_dashbord.this.lambda$onCreate$0$Channel_dashbord(view);
            }
        });
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Cdf_home(), " Home ");
        adapter.addFragment(new Cdf_audio(), " Audios ");
        adapter.addFragment(new Cdf_images(), " Images ");
        adapter.addFragment(new Cdf_videos(), " Videos ");
        adapter.addFragment(new Cdf_page(), " page ");
        adapter.addFragment(new Cdf_files(), " Files ");
        adapter.addFragment(new Cdf_hub(), " Hub ");
        adapter.addFragment(new Cdf_collection(), " Collections ");
        adapter.addFragment(new Cdf_channel(), " Channels ");
        adapter.addFragment(new Cdf_about(), " About ");
        this.viewPager.setAdapter(adapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    public /* synthetic */ void lambda$onCreate$0$Channel_dashbord(View view) {
        Intent i = new Intent(this, Search.class);
        startActivity(i);
    }

    private void statusBarColor() {
        int currentNightMode = getResources().getConfiguration().uiMode & 48;
        switch (currentNightMode) {
            case 16:
                Window window = getWindow();
                View view = window.getDecorView();
                new WindowInsetsControllerCompat(window, view).setAppearanceLightStatusBars(true);
                return;
            default:
                return;
        }
    }

    /* loaded from: classes8.dex */
    private class TabAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList();
        List<String> stringList = new ArrayList();

        public void addFragment(Fragment fragment, String s) {
            this.fragmentList.add(fragment);
            this.stringList.add(s);
        }

        public TabAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int position) {
            return this.fragmentList.get(position);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.fragmentList.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            return this.stringList.get(position);
        }
    }
}