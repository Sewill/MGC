package com.mgc.club.app.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.mgc.club.app.Adapters.SectionsPager_Adapter;
import com.mgc.club.app.Fragments.Certificates_Fragment;
import com.mgc.club.app.Fragments.Events_Fragment;
import com.mgc.club.app.Fragments.Feed_Fragment;
import com.mgc.club.app.Fragments.Places_Fragment;
import com.mgc.club.app.R;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by savva on 06.07.2015.
 */
public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    //    SectionsPager_Adapter mSectionsPagerAdapter;
    SectionsPager_Adapter new_adapter;
    private TabHost tabHost;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();

    private class TabInfo {
        private String tag;
        private Class<?> clss;
        private Bundle args;
        private Fragment fragment;

        TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }

    }

    private void intialiseViewPager() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Certificates_Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this, Places_Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this, Feed_Fragment.class.getName()));
        fragments.add(Fragment.instantiate(this, Events_Fragment.class.getName()));
        this.new_adapter = new SectionsPager_Adapter(super.getSupportFragmentManager(), fragments);

        this.mViewPager = (ViewPager) super.findViewById(R.id.pager);
        this.mViewPager.setAdapter(this.new_adapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void initialiseTabHost(Bundle args) {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabInfo tabInfo = null;

//        LinearLayout linearLayout = new LinearLayout(getApplicationContext());

//        View view = new View(getApplicationContext());

//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setImageBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.logo_mgc)).getBitmap());
//        linearLayout.addView(imageView);


//        TextView title =new TextView(getApplicationContext());
//        title.setText("Назад");
//        title.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
//        linearLayout.addView(title);

//        TabHost.TabSpec photospec = tabHost.newTabSpec("En Vivo");
//        photospec.setIndicator(linearLayout);

//        TabHost.TabSpec photospec = tabHost.newTabSpec("En Vivo");
//        photospec.setIndicator("xx", getResources().getDrawable(R.drawable.logo_mgc));
//
//        MainActivity.AddTab(this,this.tabHost,photospec, tabInfo=new TabInfo("En Vivo",Places_Fragment.class,args));
//        this.mapTabInfo.put(tabInfo.tag, tabInfo);
//        tabHost.addTab(photospec);

        MainActivity.AddTab(this, this.tabHost, this.tabHost.newTabSpec("Tab1").setIndicator("Сертификаты"), (tabInfo = new TabInfo("Tab1", Certificates_Fragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        MainActivity.AddTab(this, this.tabHost, this.tabHost.newTabSpec("Tab2").setIndicator("Места"), (tabInfo = new TabInfo("Tab2", Places_Fragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
//        MainActivity.AddTab(this, this.tabHost, this.tabHost.newTabSpec("Tab3").setIndicator("Мероприятия"), (tabInfo = new TabInfo("Tab3", Feed_Fragment.class, args)));
//        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        MainActivity.AddTab(this, this.tabHost, this.tabHost.newTabSpec("Tab4").setIndicator("Новости"), (tabInfo = new TabInfo("Tab4", Events_Fragment.class, args)));
        this.mapTabInfo.put(tabInfo.tag, tabInfo);
        // Default to first tab
        //this.onTabChanged("Tab1");
        //
        tabHost.setOnTabChangedListener(this);


        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            ViewGroup v = (ViewGroup)tabWidget.getChildAt(i);
            TextView tv = (TextView) v.getChildAt(1);
            if(tv!=null) {
                tv.setTextSize(9);
            }
        }
    }

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            tabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
        // Intialise ViewPager
        this.intialiseViewPager();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPager_Adapter(getSupportFragmentManager(),this);
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    class TabFactory implements TabHost.TabContentFactory {

        private final Context mContext;

        /**
         * @param context
         */
        public TabFactory(Context context) {
            mContext = context;
        }

        /**
         * (non-Javadoc)
         *
         * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
         */
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }

    }

    private static void AddTab(MainActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {

        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    /**
     * (non-Javadoc)
     *
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
     */
    public void onTabChanged(String tag) {
        //TabInfo newTab = this.mapTabInfo.get(tag);
        int pos = this.tabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {
        this.tabHost.setCurrentTab(position);
    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see android.support.v4.view.ViewPager#SCROLL_STATE_IDLE
     * @see android.support.v4.view.ViewPager#SCROLL_STATE_DRAGGING
     * @see android.support.v4.view.ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
