package it.bstz.deviceinfotracker;

public enum NetworkType {
    /*
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_GSM = 16;
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_TYPE_IWLAN = 18;
    public static final int NETWORK_TYPE_NR = 20;
     */
    UNKNOWN,
    GPRS,
    EDGE,
    UMTS,
    CDMA,
    EVDO_0,
    EVDO_A,
    _1xRTT,
    HSDPA,
    HSUPA,
    HSPA,
    IDEN,
    EVDO_B,
    LTE,
    EHRPD,
    HSPAP,
    GSM,
    TD_SCDMA,
    IWLAN,
    NR;

    public static NetworkType from(int networkType){
        switch (networkType){
            case 0:
                return UNKNOWN;
            case 1:
                return GPRS;
            case 2:
                return EDGE;
            case 3:
                return UMTS;
            case 4:
                return CDMA;
            case 5:
                return EVDO_0;
            case 6:
                return EVDO_A;
            case 7:
                return _1xRTT;
            case 8:
                return HSDPA;
            case 9:
                return HSUPA;
            case 10:
                return HSPA;
            case 11:
                return IDEN;
            case 12:
                return EVDO_B;
            case 13:
                return LTE;
            case 14:
                return EHRPD;
            case 15:
                return HSPAP;
            case 16:
                return GSM;
            case 17:
                return TD_SCDMA;
            case 18:
                return IWLAN;
            case 20:
                return NR;
            default:
                return UNKNOWN;
        }
    }

    public static String toStringValue(NetworkType nt){
        switch (nt){
            case UNKNOWN:
                return "Unknown";
            case GPRS:
                return "GPRS";
            case EDGE:
                return "EDGE";
            case UMTS:
                return "UMTS";
            case CDMA:
                return "CDMA";
            case EVDO_0:
                return "EVDO_0";
            case EVDO_A:
                return "EVDO_A";
            case _1xRTT:
                return "1xRTT";
            case HSDPA:
                return "HSDPA";
            case HSUPA:
                return "HSUPA";
            case HSPA:
                return "HSPA";
            case IDEN:
                return "IDEN";
            case EVDO_B:
                return "EVDO_B";
            case LTE:
                return "LTE";
            case EHRPD:
                return "EHRPD";
            case HSPAP:
                return "HSPAP";
            case GSM:
                return "GSM";
            case TD_SCDMA:
                return "TD_SCDMA";
            case IWLAN:
                return "IWLAN";
            case NR:
                return "NR";
            default:
                return "Unknown";
        }
    }
}
