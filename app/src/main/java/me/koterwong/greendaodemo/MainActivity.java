package me.koterwong.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.koterwong.greendaodemo.dao.DaoUtils;
import me.koterwong.greendaodemo.entity.User;

public class MainActivity extends AppCompatActivity {

    private DaoUtils<User> daoUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoUtils = new DaoUtils<>(this);
    }

    public void insert(View view) {
        User user = new User(1L, "wyk");
        boolean flag = daoUtils.insertEntity(user);
        Toast.makeText(this, "" + flag, Toast.LENGTH_SHORT).show();
    }

    public void query(View view) {
        User user = new User(1L, "wyk");
        User u = daoUtils.quetyOneByKey(user, 1L);
        Toast.makeText(this, "" + u.getName(), Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        User user = new User(1L, "koterwong");
        boolean flag = daoUtils.updateEntity(user);
        Toast.makeText(this, "" + flag, Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        User user = new User(1L, "koterwong");
        boolean flag = daoUtils.deleteEntity(user);
        Toast.makeText(this, "" + flag, Toast.LENGTH_SHORT).show();
    }

}
