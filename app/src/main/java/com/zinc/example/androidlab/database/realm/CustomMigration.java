package com.zinc.example.androidlab.database.realm;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.annotations.PrimaryKey;

/**
 * author： zhao zhongxin
 * date： 2019/4/14
 * description：
 */
public class CustomMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        Log.i("zzx", "migrate: "+"migrate:1 oldVersion = " + oldVersion + " newVersion = " + newVersion);
        if(null == realm){
            return;
        }
        RealmSchema realmSchema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema test2Bean = realmSchema.create("Test2Bean");
            test2Bean.addField("data", String.class, FieldAttribute.PRIMARY_KEY);
            oldVersion++;
            Log.i("zzx", "migrate oldVersion: "+oldVersion);
        }

        if(oldVersion == 1){
            if(realmSchema.contains("Test2Bean")){
                RealmObjectSchema test2Bean = realmSchema.get("Test2Bean");
                test2Bean.addField("test", String.class);
            }
            oldVersion++;
            Log.i("zzx", "migrate oldVersion: "+oldVersion);
        }

        if(oldVersion == 2){
            if(realmSchema.contains("Test2Bean")){
                RealmObjectSchema test2Bean = realmSchema.get("Test2Bean");
                test2Bean.addField("test1", String.class);
                Log.i("zzx", "migrate add test1: ");
            }
            oldVersion++;
            Log.i("zzx", "migrate oldVersion: "+oldVersion);
        }

        if(oldVersion == 3){
            if(realmSchema.contains("Test2Bean")){
                RealmObjectSchema test2Bean = realmSchema.get("Test2Bean");
                test2Bean.addField("test2", String.class);
                Log.i("zzx", "migrate add test2: ");
            }
            oldVersion++;
            Log.i("zzx", "migrate oldVersion: "+oldVersion);
        }


        if(oldVersion == 7){
            if(realmSchema.contains("Test2Bean")){
                RealmObjectSchema test2Bean = realmSchema.get("Test2Bean");
                test2Bean.addField("test3", String.class);
                Log.i("zzx", "migrate add test3: ");
            }
            oldVersion++;
            Log.i("zzx", "migrate oldVersion: "+oldVersion);
        }

        if(oldVersion == 8){
            if(realmSchema.contains("Test2Bean")){
                RealmObjectSchema test2Bean = realmSchema.get("Test2Bean");
                test2Bean.addField("test4", String.class);
                Log.i("zzx", "migrate add test4: ");
            }
            oldVersion++;
            Log.i("zzx", "migrate oldVersion: "+oldVersion);
        }


    }
}
