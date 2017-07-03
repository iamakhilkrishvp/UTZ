package cied.in.Utz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cied.in.Utz.Fragments.ComplianceFragment;
import cied.in.Utz.Fragments.NonComplianceFragment;
import cied.in.Utz.Fragments.ScoringResultFragment;

public class ResultSheetActivity extends AppCompatActivity {


    private ViewPager viewPager;
    FragmentManager myFragmentManager;
    FragmentTransaction myFragmentTransaction;
    private TabLayout tabLayout;
    TextView complianceTabText,scoringResultTabText,headerText,nonComplianceText;
    Typeface typeface;
    ImageView backIcon;
    int subAuditId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_sheet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        headerText = (TextView) toolbar.findViewById(R.id.textView21);
        backIcon = (ImageView) toolbar.findViewById(R.id.imageView);
        headerText.setTypeface(typeface);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),BasicAuditDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupTabIcons() {

        complianceTabText = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        complianceTabText.setText("Compliance %");
        complianceTabText.setTypeface(typeface);
        tabLayout.getTabAt(0).setCustomView(complianceTabText);

        scoringResultTabText = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        scoringResultTabText.setText("Scoring Results");
        scoringResultTabText.setTypeface(typeface);
        tabLayout.getTabAt(1).setCustomView(scoringResultTabText);

        nonComplianceText = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        nonComplianceText.setText("Non-Compliance");
        nonComplianceText.setTypeface(typeface);
        tabLayout.getTabAt(2).setCustomView(nonComplianceText);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ComplianceFragment(), "Compliance %");
        adapter.addFrag(new ScoringResultFragment(), "Scoring Results");
        adapter.addFrag(new NonComplianceFragment(), "Non-Compliance");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
