package it.bstz.deviceinfotracker;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.app.ActivityManager.MemoryInfo;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingPermission")
public class InfoFetchService {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static DeviceInfoBundle fetchInfo(Context context, ConnectivityManager connectivityManager, ActivityManager activityManager, TelephonyManager tm) throws Exception{
        DeviceInfoBundle bundle = new DeviceInfoBundle();
        bundle.reteDati = NetworkType.from(tm.getDataNetworkType());
        bundle.potenzaSegnale = tm.getSignalStrength().getCellSignalStrengths().get(0).getDbm();

        //get storage
        StatFs internalStatFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        double internalTotalDiskSpace = (internalStatFs.getBlockCountLong() * internalStatFs.getBlockSizeLong()) / (1024 * 1024);
        double internalFreeSpace = (internalStatFs.getFreeBlocksLong() * internalStatFs.getBlockSizeLong()) / (1024 * 1024);
        bundle.memoriaInternaTotale = Double.toString(internalTotalDiskSpace);
        bundle.memoriaInternaUsata = Double.toString(internalTotalDiskSpace - internalFreeSpace);

        //get RAM
        MemoryInfo mi = new MemoryInfo();
        activityManager.getMemoryInfo(mi);
        double availableMegs = mi.availMem / (1024*1024);
        double totalMegs = mi.totalMem / (1024*1024);
        bundle.ramTotale = Double.toString(totalMegs);
        bundle.ramUsata = Double.toString(totalMegs-availableMegs);

        //Check wifi
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        bundle.wifiConnesso = networkInfo.isConnected();


        return bundle;



    }
}
