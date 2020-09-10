package main;

import java.util.Scanner;

//http://poj.org/problem?id=1007

public class POJ1008 {
    private static String[] HaabMonths = {"pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin", "mol", "chen", "yax",
            "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab", "cumhu", "uayet"};
    private static String[] TzolkinDays = {"imix", "ik", "akbal", "kan", "chicchan", "cimi", "manik", "lamat", "muluk",
            "ok", "chuen", "eb", "ben", "ix", "mem", "cib", "caban", "eznab", "canac", "ahau"};

    public static int toDays(String oriStr) {
        int ret = 0;
        String[] strs = oriStr.split(" ");
        int day = Integer.parseInt(strs[0].replace(".", ""));
        String month = strs[1];
        int year = Integer.parseInt(strs[2]);
        int numofMonth = 0;
        for (String str : HaabMonths) {
            if (month.equals(str)) {
                break;
            } else {
                numofMonth++;
            }
        }
        ret = (day + 1) + ((numofMonth) * 20) + (year * 365);
        return ret;
    }

    public static String toTzo(int days) {
        int num;
        String dayName;
        int year;

        int remdays = days % 260;
        if (remdays != 0) {
            year = days / 260;
        } else {
            year = days / 260 - 1;
        }
        num = remdays % 13;
        if (num == 0) {
            num = 13;
        }
        int daySeq = remdays % 20;
        if (daySeq != 0) {
            dayName = TzolkinDays[daySeq - 1];
        } else {
            dayName = TzolkinDays[19];
        }

        return num + " " + dayName + " " + year;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        String useless = input.nextLine();
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            String oriStr = input.nextLine();
            int days = toDays(oriStr);
            System.out.println(toTzo(days));
        }

    }

}
