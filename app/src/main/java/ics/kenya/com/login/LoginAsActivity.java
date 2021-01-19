package ics.kenya.com.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.common.FieldOfficerLoginActivity;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.RegionalManagerLoginActivity;
import ics.kenya.com.login.adapter.MyAdapter;
import me.relex.circleindicator.CircleIndicator;

public class LoginAsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    @BindView(R.id.appCompatBupttonRegionalmanager)
    AppCompatButton appCompatBupttonRegionalmanager;

    @BindView(R.id.appCompatButtonFieldagent)
    AppCompatButton appCompatButtonFieldagent;


    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] img = {R.drawable.one, R.drawable.two, R.drawable.three};
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);
        init();
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appCompatBupttonRegionalmanager.setOnClickListener(v -> {
            Intent depositsintent = new Intent(getApplicationContext(), RegionalManagerLoginActivity.class);
            startActivity(depositsintent);

        });
        appCompatButtonFieldagent.setOnClickListener(v -> {
            Intent depositsintent = new Intent(getApplicationContext(), FieldOfficerLoginActivity.class);
            startActivity(depositsintent);
        });

    }//end onCreate

    private void init() {
        for (int i = 0; i < img.length; i++)
            ImgArray.add(img[i]);

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(LoginAsActivity.this, ImgArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == img.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        //Auto start
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 5000);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}



