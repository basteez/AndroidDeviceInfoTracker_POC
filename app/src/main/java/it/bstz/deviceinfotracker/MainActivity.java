package it.bstz.deviceinfotracker;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ActivityManager;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.telephony.TelephonyManager;
import android.widget.TextView;


import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;

    private TelephonyManager telephonyManager;
    private StorageManager storageManager;
    private StorageStatsManager storageStatsManager;
    private List<StorageVolume> storageVolumes;
    private DeviceInfoBundle infoBundle;
    private ActivityManager activityManager;
    private ConnectivityManager connectivityManager;


    private TextView tvStorageInternoTotale;
    private TextView tvStorageInternoUsato;
    private TextView tvReteDatiTipo;
    private TextView tvReteDatiPotenza;
    private TextView tvRamTotale;
    private TextView tvRamUsata;
    private TextView tvWifi;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStorageInternoTotale = this.findViewById(R.id.tv_storage_interno_totale);
        tvStorageInternoUsato = this.findViewById(R.id.tv_storage_interno_usato);
        tvReteDatiTipo = this.findViewById(R.id.tv_rete_dati_tipo);
        tvReteDatiPotenza = this.findViewById(R.id.tv_rete_dati_potenza);
        tvRamTotale = this.findViewById(R.id.tv_ram_totale);
        tvRamUsata = this.findViewById(R.id.tv_ram_usata);
        tvWifi = this.findViewById(R.id.tv_wifi);


        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        storageManager = (StorageManager) this.getSystemService(Context.STORAGE_SERVICE);
        storageStatsManager = (StorageStatsManager) this.getSystemService(STORAGE_STATS_SERVICE);
        storageVolumes = storageManager.getStorageVolumes();
        activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE,ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE}, PERMISSION_REQUEST_CODE);
            try {
                infoBundle = InfoFetchService.fetchInfo(getApplicationContext(), connectivityManager, activityManager, telephonyManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                infoBundle = InfoFetchService.fetchInfo(getApplicationContext(), connectivityManager, activityManager, telephonyManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        tvRamTotale.setText(infoBundle.ramTotale + " MB");
        tvRamUsata.setText(infoBundle.ramUsata + " MB");
        tvStorageInternoTotale.setText(infoBundle.memoriaInternaTotale + " MB");
        tvStorageInternoUsato.setText(infoBundle.memoriaInternaUsata + " MB");
        tvReteDatiTipo.setText(NetworkType.toStringValue(infoBundle.reteDati));
        tvReteDatiPotenza.setText(Integer.toString(infoBundle.potenzaSegnale) + " dBm");
        tvWifi.setText(Boolean.toString(infoBundle.wifiConnesso));
    }
}