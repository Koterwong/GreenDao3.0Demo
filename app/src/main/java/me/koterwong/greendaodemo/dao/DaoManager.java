package me.koterwong.greendaodemo.dao;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by Koterwong on 2016/7/31.
 */
public class DaoManager {

    public static final String TAG = DaoManager.class.getSimpleName();

    /**
     * 数据库名
     */
    private static final String DB_NAME = "my_db.sqlite";

    private static DaoManager manager;
    private static DaoMaster daoMaster;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoSession daoSession;
    private Context context;

    private DaoManager(Context context) {
        this.context = context;
    }

    public static DaoManager getInstance(Context context) {
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (manager == null) {
                    manager = new DaoManager(context);
                }
            }
        }
        return manager;
    }

    /**
     * 判断数据库是否存在，如果没有就创建。
     *
     * @return 一个DaoMaster就代表着一个数据库的连接
     */
    public DaoMaster getDaoMaster() {

        if (daoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 完成数据的添加、删除、修改、查询的操作
     *
     * @return DaoSession完成对Entity的基本操作和Dao操作类
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 设置Debug开启，默认关闭
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭数据库连接,数据库开启的时候，使用完毕了必须要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }
}
