package me.koz.staffmode.mutechat;


public class MuteChat {

    private static boolean ismuted = false;

    public static boolean getMuted() {
        return ismuted;
    }

    public static void setIsmuted(boolean ism) {
        ismuted = ism;
    }
}

