package com.yonoo.naverloginhelper;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

@SuppressWarnings("rawtypes")
public class RobotiumActivity extends ActivityInstrumentationTestCase2 {
    private Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.nhn.android.ndrive";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public RobotiumActivity() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void main() {
        //Wait for activity: 'com.example.ExampleActivty'
        //Clear the EditText editText1
        solo.enterText(2,"");
        solo.enterText(4,"");
//        solo.clearEditText((android.widget.EditText) solo.getView("editText1"));
//        solo.enterText((android.widget.EditText) solo.getView("editText1"), "This is an example text");
    }
}