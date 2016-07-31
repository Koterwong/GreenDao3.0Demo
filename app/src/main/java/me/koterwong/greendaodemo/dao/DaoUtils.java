package me.koterwong.greendaodemo.dao;

import android.content.Context;

import java.util.List;

/**
 * Created by Koterwong on 2016/7/31.
 */
public class DaoUtils<Entity> {

    private static final String TAG = "DaoUtils";

    private DaoManager manager;

    public DaoUtils(Context context) {
        manager = DaoManager.getInstance(context);
    }

    /**
     * 插入操作
     *
     * @param entity entity
     * @return boolean
     */
    public boolean insertEntity(Entity entity) {
        boolean flag = false;
        flag = manager.getDaoSession().insert(entity) != -1;
        return flag;
    }

    /**
     * 插入多条数据
     *
     * @param entities entities
     * @return boolean
     */
    public boolean insertMulitEntity(final List<Entity> entities) {
        boolean flag = false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override public void run() {
                    for (Entity e : entities) {
                        manager.getDaoSession().insertOrReplace(e);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新操作
     *
     * @param entity entity
     * @return boolean
     */
    public boolean updateEntity(Entity entity) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(entity);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除操作
     *
     * @param entity entity
     * @return boolean
     */
    public boolean deleteEntity(Entity entity) {
        boolean flag = false;
        try {
            manager.getDaoSession().delete(entity);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @param entity entityClass
     * @return boolean
     */
    public boolean deleteAll(Entity entity) {
        boolean flag = false;
        try {
            manager.getDaoSession().deleteAll(entity.getClass());
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return List<Entity>
     */
    @SuppressWarnings("unchecked")
    public List<Entity> listAll(Entity entity) {
        return (List<Entity>) manager.getDaoSession().loadAll(entity.getClass());
    }

    /**
     * 查询主键
     *
     * @param key key
     * @return Entity
     */
    public Entity quetyOneByKey(Entity entity, Long key) {
        return (Entity) manager.getDaoSession().load(entity.getClass(), key);
    }
}
