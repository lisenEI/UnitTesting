package com.unit.test.robolectric.shadows;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.unit.test.unittesting.FragmentOne;
import com.unit.test.unittesting.MainActivity;
import com.unit.test.unittesting.MyApplication;
import com.unit.test.unittesting.R;
import com.unit.test.unittesting.ReciverOne;
import com.unit.test.unittesting.SecondActivity;
import com.unit.test.unittesting.ServiceOne;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowService;
import org.robolectric.shadows.ShadowToast;

import java.util.List;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Robolectric.buildActivity;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * @author lisen
 * @since 12-06-2018
 * <a href = https://developer.android.com/guide/topics/resources/providing-resources#QualifierRules> QualifierRules </a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowFoo.class})
//@Config(minSdk = 21, maxSdk = 28, shadows = {ShadowFoo.class}, application = MyApplication.class, qualifiers = "en-w480dp-h800dp-xxhdpi")
public class ShadowsTest {

    private static ShadowApplication shadowApplication;
    private static FragmentActivity activity;
    private static Context context;

    @BeforeClass
    public static void init() {
        //输出日志
        ShadowLog.stream = System.out;
    }

    @Before
    public void setUp() {
//        RuntimeEnvironment.application;
        context = ApplicationProvider.getApplicationContext();
        shadowApplication = extract(context);
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void toastTest() {

        String message = "toast";

        View hello = activity.findViewById(R.id.bt_toast);

        hello.performClick();

        String text = ShadowToast.getTextOfLatestToast();
        assertEquals(message, text);

        int count = ShadowToast.shownToastCount();
        assertEquals(1, count);

        Toast toast = ShadowToast.getLatestToast();
        assertNotNull(toast);

        boolean showedToast = ShadowToast.showedToast(message);
        assertTrue(showedToast);

        ShadowToast shadowToast = Shadows.shadowOf(toast);
        boolean cancelled = shadowToast.isCancelled();
        assertFalse(cancelled);

        List<Toast> toasts = shadowApplication.getShownToasts();
        assertEquals(1, toasts.size());

    }

    @Test
    public void startActivityTest() {
        View startActivity = activity.findViewById(R.id.bt_open_activity);
        startActivity.performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent nextIntent = shadowActivity.getNextStartedActivity();
        assertEquals(nextIntent.getComponent().getClassName(), SecondActivity.class.getName());

        String source = nextIntent.getStringExtra("source");
        assertEquals(MainActivity.class.toString(), source);

        int flags = nextIntent.getFlags();
        assertEquals(Intent.FLAG_ACTIVITY_CLEAR_TASK, flags);
    }

    @Test
    public void buildActivityTest() {
        //onCreate -> onStart
        buildActivity(SecondActivity.class)
                .create()
                .start();

        //onCreate
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        SecondActivity activity = buildActivity(SecondActivity.class, intent).create().get();
//        assertEquals(Intent.ACTION_VIEW, activity.getIntent().getAction());

        //onCreate -> onStart -> onResume
//        buildActivity(SecondActivity.class)
//                .create()
//                .start()
//                .resume()
//                .visible()
//                .get();

        //onCreate -> onSaveInstanceState -> onRestoreInstanceState
//        Bundle savedInstanceState = new Bundle();
//        savedInstanceState.putString("key", "saved");
//        SecondActivity ac = buildActivity(SecondActivity.class)
//                .create()
//                .saveInstanceState(savedInstanceState)
//                .restoreInstanceState(savedInstanceState)
//                .get();
    }

    @Test
    public void lifeCycleTest() {
        ActivityController<SecondActivity> controller = buildActivity(SecondActivity.class);
        SecondActivity activity = controller.get();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);

        assertEquals("null", activity.getLifeCycle());
        View view = activity.findViewById(R.id.bt_second_button);
        assertNull(view);

        controller.create(null);
        assertEquals("onCreate", activity.getLifeCycle());

        view = activity.findViewById(R.id.bt_second_button);
        assertNotNull(view);

        controller.start();
        assertEquals("onStart", activity.getLifeCycle());

        controller.resume();
        assertEquals("onResume", activity.getLifeCycle());

        controller.pause();
        assertEquals("onPause", activity.getLifeCycle());

        controller.stop();
        assertEquals("onStop", activity.getLifeCycle());

        // 注意此处应该是onStart，因为performRestart不仅会调用restart，还会调用onStart
        controller.restart();
        assertEquals("onStart", activity.getLifeCycle());

        controller.destroy();
        assertEquals("onDestroy", activity.getLifeCycle());
    }

    @Test
    public void resourceTest() {
        String appName = context.getResources().getString(R.string.app_name);
        assertEquals("UnitTesting", appName);
    }

    /**
     * 这里的弹窗只能是：android.app.AlertDialog
     */
    @Test
    public void dialogTest() {
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        //判断 dialog 尚未弹出
        assertNull(dialog);

        View showDialog = activity.findViewById(R.id.bt_dialog);
        showDialog.performClick();

        dialog = ShadowAlertDialog.getLatestAlertDialog();
        //判断 dialog 已经弹出
        assertNotNull(dialog);
        //获取 shadow 进行验证
        ShadowAlertDialog shadowAlertDialog = Shadows.shadowOf(dialog);
        assertEquals("this is a dialog", shadowAlertDialog.getMessage());
    }

    @Test
    public void fragmentTest() {
        View view = activity.findViewById(R.id.fl_fragment_container);
        view.performClick();

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(FragmentOne.class.getSimpleName());

        assertNotNull(fragment);

    }

    @Test
    public void registerTest() {
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryBroadcastReceivers(new Intent(ReciverOne.ACTION), PackageManager.MATCH_ALL);
        assertNotNull(resolveInfos);

        for (ResolveInfo info : resolveInfos) {
            System.out.println(info.toString());
            assertNotNull(info);
        }
    }

    @Test
    public void reciverTest() {
        Intent intent = new Intent(ReciverOne.ACTION);
        intent.putExtra(ReciverOne.EXTRA_MSG, "send_msg");
        ReciverOne reciverOne = new ReciverOne();
        reciverOne.onReceive(context, intent);

        String msg = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(ReciverOne.EXTRA_MSG, "");

        assertEquals("send_msg", msg);
    }

    @Test
    public void serviceTest() {
        ServiceController<ServiceOne> controller = Robolectric.buildService(ServiceOne.class);
        ServiceOne serviceOne = controller.get();
        ShadowService shadowService = Shadows.shadowOf(serviceOne);

        controller.create();
        controller.startCommand(0, 0);
        controller.bind();
        controller.unbind();
        controller.destroy();
    }

    @Test
    public void modelTest() {
        Foo foo = new Foo();
        foo.show();
    }

}
