package com.draco.comatose;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button toggle;

    public final String device_idle_constants =
            "inactive_to=60000," +
            "sensing_to=0," +
            "locating_to=0," +
            "locating_accuracy=10000," +
            "motion_inactive_to=0," +
            "idle_after_inactive_to=0," +
            "idle_pending_to=120000," +
            "max_idle_pending_to=120000," +
            "idle_pending_factor=2," +
            "idle_to=1800000," +
            "max_idle_to=21600000," +
            "idle_factor=2," +
            "min_time_to_alarm=3600000," +
            "max_temp_app_whitelist_duration=300000," +
            "mms_temp_app_whitelist_duration=60000," +
            "sms_temp_app_whitelist_duration=20000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (Button) findViewById(R.id.toggle);

        permission_check();
        refreshTitle();

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
    }

    void toggle() {
        if (enabled()) {
            Settings.Global.putString(getContentResolver(), "device_idle_constants", null);
            //Toast.makeText(getApplicationContext(), "Disabled", Toast.LENGTH_SHORT).show();
            refreshTitle();
        } else {
            Settings.Global.putString(getContentResolver(), "device_idle_constants", device_idle_constants);
            //Toast.makeText(getApplicationContext(), "Enabled" + device_idle_constants, Toast.LENGTH_SHORT).show();
            refreshTitle();
        }
    }

    boolean enabled() {
        String currentDeviceIdleConstants = Settings.Global.getString(getContentResolver(), "device_idle_constants");
        //Log.d("CURRENT_STATUS", currentDeviceIdleConstants);
        return !(currentDeviceIdleConstants == null);
    }

    void refreshTitle() {
        boolean currentlyEnabled = enabled();
        setTitle(getResources().getString(R.string.app_name) + " : " + (currentlyEnabled ? "Enabled" : "Disabled"));
        if (currentlyEnabled) {
            toggle.setText(getString(R.string.disable));
            toggle.setBackgroundColor(getResources().getColor(R.color.colorRed));
        } else {
            toggle.setText(getString(R.string.enable));
            toggle.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
    }
    void permission_check() {
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_SECURE_SETTINGS);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            AlertDialog.Builder error = new AlertDialog.Builder(MainActivity.this);
            error.setTitle("Missing Permissions");
            error.setMessage("To allow this app to work, you must run an ADB command via your computer.\n\nadb shell pm grant " + getApplication().getPackageName() + " android.permission.WRITE_SECURE_SETTINGS");
            error.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    permission_check();
                }
            });
            error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(0);
                }
            });
            error.setCancelable(false);
            error.show();
        }
    }

    /*public void execute(final String command) {
        nonRootSession = new Shell.Builder().
                useSH().
                setWantSTDERR(true).
                setWatchdogTimeout(5).
                setMinimalLogging(true).
                open(new Shell.OnCommandResultListener() {
                    @Override
                    public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                        if (exitCode != Shell.OnCommandResultListener.SHELL_RUNNING) {
                            Log.i(TAG, "Error opening shell: exitCode " + exitCode);
                        } else {
                            nonRootSession.addCommand(command, 0, new Shell.OnCommandResultListener() {
                                @Override
                                public void onCommandResult(int commandCode, int exitCode, List<String> output) {
                                    logcat.append(output.toString() + "\n");
                                    Log.d("OUTPUT", output.toString());
                                }
                            });
                        }
                    }
                });
    }*/
}
