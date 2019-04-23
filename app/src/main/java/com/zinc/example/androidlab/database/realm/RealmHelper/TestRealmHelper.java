package com.zinc.example.androidlab.database.realm.RealmHelper;

import android.util.Log;

import com.zinc.example.androidlab.database.realm.Bean.Test2Bean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

/**
 * author： zhao zhongxin
 * date： 2019/4/14
 * description：
 */
public class TestRealmHelper {

    private static final String TAG = "SearchSkillMainHelper";

    private Realm mRealm;

    public void addOrUpdateRealmBean(){
        acquireRealm();
        if (mRealm == null) {
            Log.i(TAG, "数据库打开异常");
            return;
        }
        try {
            mRealm.beginTransaction();
            Test2Bean testBean = new Test2Bean();
            testBean.setData(System.currentTimeMillis()+"");
            testBean.setTest(System.currentTimeMillis()+"");
            mRealm.copyToRealmOrUpdate(testBean);
            Log.i("zzx", "addOrUpdateSkillMainRealmBean: ");
            mRealm.commitTransaction();
            Log.i(TAG, "添加或更新操作引导本地数据成功！");
        } catch (RealmPrimaryKeyConstraintException e) {
            mRealm.cancelTransaction();
            Log.i(TAG, "addOrUpdateSkillMainRealmBean = " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            mRealm.cancelTransaction();
            Log.i(TAG, "addOrUpdateSkillMainRealmBean = " + e.getMessage());
            e.printStackTrace();
        }finally {
            closeRealm();
        }
    }

    /**
     * query （查询全部）
     */
    public List<Test2Bean> queryInfo() {
        acquireRealm();
        RealmResults<Test2Bean> testBeans;
        if (mRealm == null) {
            return null;
        }

        try {
            testBeans = mRealm.where(Test2Bean.class).findAll();
            Log.i("zzx", "querySkillMainInfo test2Beans: "+testBeans.size());
            return mRealm.copyFromRealm(testBeans);
        } catch (Exception e) {
            Log.i(TAG, "querySkillMainInfo Exception = " + e.getMessage());
            e.printStackTrace();
            return null;
        }finally {
            closeRealm();
        }
    }

    /**
     * 关闭Realm
     */
    private void closeRealm() {
        if (mRealm != null) {
            if (!mRealm.isClosed()) {
                mRealm.close();
            }
        }
    }

    /**
     * 获取Realm实例
     */
    private void acquireRealm() {
        try{
            mRealm = Realm.getDefaultInstance();
        }catch (Exception e){
            Log.i(TAG, "Realm 获取失败！"+e.getMessage());
        }
    }
}
