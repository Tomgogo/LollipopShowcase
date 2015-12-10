package com.leju.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;


public class MainAActivity extends AppCompatActivity {
    private TextView textView, select;
    private boolean isOpen = false;
    private RelativeLayout right;
//    private static final Logger logger = LoggerFactory.getLogger(MainAActivity.class);
    private static final Logger logger = LoggerFactory.getLogger("aa");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        textView = (TextView) findViewById(R.id.textview);
        Handler handler = new Handler();
        select = (TextView) findViewById(R.id.select);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Done");
            }//a mock for long time work
        }, 800000L);
//        java.util.logging.Logger.getLogger()
//        logger.isDebugEnabled();
//        Marker m =
//        logger.setLevel(Level.DEBUG);
//        logger.en
//        Logger logger = StaticLoggerBinder.getSingleton().getLoggerFactory().getLogger
//                ("aa");
        logger.debug("sddsdfdfdfdfdsfdfdfdsfdsfsddddddddddddddddddddwo qu");
        logger.error("sddsdfdfdfdfdsfdfdfdsfdsfsddddddddddddddddddddwo qu");
        logger.info("sddsdfdfdfdfdsfdfdfdsfdsfsddddddddddddddddddddwo qu");
        Log.e("liang", "xl debug=" + logger.isDebugEnabled());
        Log.d("liang", "xl android debug=" + Log.isLoggable("aa",Log.DEBUG));
        Log.d("liang", "xl android info=" + Log.isLoggable("aa",Log.INFO));
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        right = (RelativeLayout) findViewById(R.id.right_drawer);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(right);
//                if (!isOpen) {// 右边栏关闭时，打开
//                    isOpen = true;
//                    drawer.openDrawer(right);
//                } else {// 右边栏打开时，关闭
//                    isOpen = false;
//                    drawer.closeDrawer(right);
//                }

            }
        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        final AutoLayout autoLayout = (AutoLayout) findViewById(R.id.auto_layout);
//        Button request = (Button) findViewById(R.id.bt_request);
//        Button invalidate = (Button) findViewById(R.id.bt_invalidate);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        request.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                autoLayout.requestLayout();
//            }
//        });
//
//        invalidate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                autoLayout.invalidate();
//            }
//        });
    }

    /**
     * DrawerLayout状态变化监听
     */
    private class DrawerLayoutStateListener extends
            DrawerLayout.SimpleDrawerListener {
        /**
         * 当导航菜单滑动的时候被执行
         */
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
//                mMaterialMenuIcon.setTransformationOffset(
//                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
//                        isDirection_right ? 2 - slideOffset : slideOffset);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
