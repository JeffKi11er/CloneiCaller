package com.example.cloneicaller.common;

import android.Manifest;
import android.app.Activity;
import android.app.Person;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.ContactsContract;

import androidx.core.app.ActivityCompat;

import com.example.cloneicaller.Models.Contact;
import com.example.cloneicaller.item.BlockerPersonItem;
import com.example.cloneicaller.item.ItemPerson;

import java.lang.reflect.Method;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class Common {
    public static final int VIEW_TYPE_GROUP = 0;
    public static final int VIEW_TYPE_PERSON = 1;
    public static final int RESULT_CODE = 1000;
    public static List<String> alphabet_available = new ArrayList<>();

    //thanhnv
    public static List<Contact> getCallDetails(Context context) {

        List<Contact> contactList = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int country = cursor.getColumnIndex(CallLog.Calls.COUNTRY_ISO);
        int network = cursor.getColumnIndex(CallLog.Calls.CACHED_NUMBER_TYPE);


        cursor.moveToFirst();
        while (cursor.moveToNext()) {

            String phoneNum = cursor.getString(number);
            String dur = cursor.getString(duration);
            String callDate = cursor.getString(date);
            String nameCon = cursor.getString(name);
            String callType = cursor.getString(type);
            String countryiso = cursor.getString(country);
            String networkname = cursor.getString(network);
            String dir = null;

            int dirCode = Integer.parseInt(callType);

            switch (dirCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            long seconds = Long.parseLong(callDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateString = formatter.format(new Date(seconds));

            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            String time = format.format(new Time(seconds));

            contactList.add(new Contact(phoneNum, time, dateString, nameCon, dir, countryiso, networkname));

        }
        return contactList;
    }

    public static HashMap<String, List<Contact>> groupDataIntoHashMap(List<Contact> contactList) {

        HashMap<String, List<Contact>> groupedHashMap = new LinkedHashMap<>();

        for (Contact contact : contactList) {

            String hashMapKey = contact.getDate();

            if (groupedHashMap.containsKey(hashMapKey)) {
                groupedHashMap.get(hashMapKey).add(contact);
            } else {
                List<Contact> list = new ArrayList<>();
                list.add(contact);
                groupedHashMap.put(hashMapKey, list);
            }
        }
        return groupedHashMap;
    }

    public static String findPhoneFromDiary(String phoneNumber, Context context) {

        String phoneNum = "";
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

            String[] projection = new String[]{ContactsContract.PhoneLookup.NUMBER};

            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

            if (cursor != null)
                while (cursor.moveToNext()) {
                    phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.NUMBER));
                }
            cursor.close();

        } catch (Exception e) {

        }
        return phoneNum;
    }

    public static String findPhoneFromCallog(String phoneNumber, Context context) {

        String dialed = "";
        String columns[] = new String[]{
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
                CallLog.Calls.TYPE};
        String args[] = new String[1];
        args[0] = phoneNumber;
        Cursor c;
        c = context.getContentResolver().query(Uri.parse("content://call_log/calls"),
                columns, CallLog.Calls.NUMBER + "=?", args, null);
        while (c.moveToNext()) {
            dialed = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
        }
        return dialed;
    }

    public static String formatPhoneNumber(String phoneNumber) {
        String phoneNum;
        if (phoneNumber.startsWith("+84")) {
            phoneNum = phoneNumber.replace(" ", "");
        } else {
            StringBuilder stringBuilder = new StringBuilder(phoneNumber).replace(0, 1, "+84");
            phoneNum = String.valueOf(stringBuilder);
            phoneNum = phoneNum.replace(" ", "");
        }
        return phoneNum;
    }


    public static ArrayList<ItemPerson> sortList(ArrayList<ItemPerson> people) {
        Collections.sort(people, new Comparator<ItemPerson>() {
            @Override
            public int compare(ItemPerson o1, ItemPerson o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return people;
    }

    public static List<BlockerPersonItem> sortBlockList(List<BlockerPersonItem> people) {
        Collections.sort(people, new Comparator<BlockerPersonItem>() {
            @Override
            public int compare(BlockerPersonItem o1, BlockerPersonItem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return people;
    }

    public static ArrayList<ItemPerson> addAlphabet(ArrayList<ItemPerson> list) {
        int i = 0;
        ArrayList<ItemPerson> customList = new ArrayList<>();
        ItemPerson firstPerson = new ItemPerson();
        firstPerson.setName(String.valueOf(list.get(0).getName().charAt(0)));
        firstPerson.setViewType(VIEW_TYPE_GROUP);
        alphabet_available.add(String.valueOf(list.get(0).getName().charAt(0)));
        customList.add(firstPerson);
        for (i = 0; i < list.size() - 1; i++) {
            ItemPerson itemPerson = new ItemPerson();
            char name1 = list.get(i).getName().charAt(0);
            char name2 = list.get(i + 1).getName().charAt(0);
            if (name1 == name2) {
                list.get(i).setViewType(VIEW_TYPE_PERSON);
                customList.add(list.get(i));
            } else {
                list.get(i).setViewType(VIEW_TYPE_PERSON);
                customList.add(list.get(i));
                itemPerson.setName(String.valueOf(name2));
                itemPerson.setViewType(VIEW_TYPE_GROUP);
                alphabet_available.add(String.valueOf(name2));
                customList.add(itemPerson);
            }
        }
        list.get(i).setViewType(VIEW_TYPE_PERSON);
        customList.add(list.get(i));
        return customList;
    }

    public static List<BlockerPersonItem> addAlphabetBlocker(List<BlockerPersonItem> blocker) {
        int i = 0;
        ArrayList<BlockerPersonItem> customList = new ArrayList<>();
        BlockerPersonItem blockerPersonItem = new BlockerPersonItem();
        blockerPersonItem.setName(String.valueOf(blocker.get(0).getName().charAt(0)));
        blockerPersonItem.setTypeArrange(VIEW_TYPE_GROUP);
        alphabet_available.add(String.valueOf(blocker.get(0).getName().charAt(0)));
        customList.add(blockerPersonItem);
        for (i = 0; i < blocker.size() - 1; i++) {
            BlockerPersonItem itemPerson = new BlockerPersonItem();
            char name1 = blocker.get(i).getName().charAt(0);
            char name2 = blocker.get(i + 1).getName().charAt(0);
            if (name1 == name2) {
                blocker.get(i).setTypeArrange(VIEW_TYPE_PERSON);
                customList.add(blocker.get(i));
            } else {
                blocker.get(i).setTypeArrange(VIEW_TYPE_PERSON);
                customList.add(blocker.get(i));
                itemPerson.setName(String.valueOf(name2));
                itemPerson.setTypeArrange(VIEW_TYPE_GROUP);
                alphabet_available.add(String.valueOf(name2));
                customList.add(itemPerson);
            }
        }
        blocker.get(i).setTypeArrange(VIEW_TYPE_PERSON);
        customList.add(blocker.get(i));
        return customList;
    }

    public static int findPositionWithName(String name, ArrayList<ItemPerson> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return i;
            }
        }
        return 1;
    }

    public static ArrayList<String> getAlphabet() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 65; i < 90; i++) {
            char character = (char) i;
            result.add(String.valueOf(character));
        }
        return result;
    }

    public static ArrayList<ItemPerson> resolverArrayList(ItemPerson itemPerson, Context context) {
        ArrayList<ItemPerson> personArrayList = new ArrayList<>();
        if (itemPerson == null) {
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            String selection = null;
            String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            String[] selectionArgs = null;
            String sortOrder = null;
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                StringBuilder stringBuilder = new StringBuilder(num).replace(0, 1, "+84");
                String number = String.valueOf(stringBuilder).replace(" ", "");
                personArrayList.add(new ItemPerson(name, -1, number));
            }
        }
        return personArrayList;
    }

    public static ArrayList<String> covertToStringArray(ArrayList<ItemPerson> people) {
        ArrayList<String> name = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            name.add(people.get(i).getName());
        }
        return name;
    }

    public static void disconnectCall() {
        try {

            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUnknown(String number, Context context) {
        boolean identified = false;
        ArrayList<ItemPerson> personArrayList = new ArrayList<>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = null;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String[] selectionArgs = null;
        String sortOrder = null;
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            personArrayList.add(new ItemPerson(name, -1, num));
        }
        for (ItemPerson itemPerson : personArrayList) {
            if (number == itemPerson.getNumber()) {
                identified = true;
            }
        }
        return identified;
    }

    public static List<BlockerPersonItem> checkLier(List<BlockerPersonItem> blockerPersonItems) {
        ArrayList<BlockerPersonItem> checkL = new ArrayList<>();
        for (BlockerPersonItem blockerPersonItem : blockerPersonItems) {
            if (blockerPersonItem.getType().equals("LỪA ĐẢO") || blockerPersonItem.getType().equals("ĐÒI NỢ")) {
                checkL.add(blockerPersonItem);
            }
        }
        return checkL;
    }

    public static List<BlockerPersonItem> checkRealDialer(List<BlockerPersonItem> blockerPersonItems) {
        ArrayList<BlockerPersonItem> blocker = new ArrayList<>();
        for (BlockerPersonItem blockerPersonItem : blockerPersonItems) {
            if (blockerPersonItem.getNumber() != null) {
                blocker.add(blockerPersonItem);
            }
        }
        return blocker;
    }

    public static List<BlockerPersonItem> checkAdvertise(List<BlockerPersonItem> blockerPersonItems) {
        ArrayList<BlockerPersonItem> checkA = new ArrayList<>();
        for (BlockerPersonItem blockerPersonItem : blockerPersonItems) {
            if (blockerPersonItem.getType().equals("QUẢNG CÁO")) {
                checkA.add(blockerPersonItem);
            }
        }
        return checkA;
    }

    public static boolean checkInside(String number, List<BlockerPersonItem> check) {
        boolean identified = false;
        for (BlockerPersonItem blockerPersonItem : check) {
            if (number.equals(blockerPersonItem.getNumber())) {
                identified = true;
            }
        }
        return identified;
    }
}
