package com.academyatinfo.multtable.levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.academyatinfo.multtable.learntable.LearnTable;
import com.academyatinfo.multtable.tablelevels.TableLevels;
import com.academyatinfo.multtable.R;
import com.academyatinfo.multtable.databases.DataBaseLearn;
import com.academyatinfo.multtable.exam.Exam;
import com.academyatinfo.multtable.tools.BaseActivity;

public class Levels extends BaseActivity implements LevelsContract.View {

    private DataBaseLearn dataBaseLearn;
    private int table;
    private Intent intent;
    private ImageView imageBack;
    private TextView table_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBaseLearn = new DataBaseLearn(this);
        imageBack = (ImageView) findViewById(R.id.image_back);
        table_number = (TextView) findViewById(R.id.table_number);
        intent = getIntent();
        table = intent.getIntExtra("index_table", 0);
        table_number.setText(table + "");
        for (int i = 1; i <= 6; i++) {
            if (dataBaseLearn.getData(table, i))
                add_image_check(i);
        }
    }

    public void level_one(View view) {
        intent = new Intent(this, LearnTable.class);
        intent.putExtra("table", table);
        startActivityForResult(intent, 1);
    }

    public void level_two(View view) {
        if (dataBaseLearn.getData(table, 1)) {
            intent = new Intent(this, TableLevels.class);
            intent.putExtra("index_table", table);
            startActivityForResult(intent, 1);
        }
    }

    public void level_three(View view) {
        if (dataBaseLearn.getData(table, 2)) {
            intent = new Intent(this, Exam.class);
            intent.putExtra("long_table", table);
            intent.putExtra("level", 3);
            startActivityForResult(intent, 1);
        }
    }

    public void level_four(View view) {
        if (dataBaseLearn.getData(table, 3)) {
            intent = new Intent(this, Exam.class);
            intent.putExtra("long_table", table);
            intent.putExtra("level", 4);
            startActivityForResult(intent, 1);
        }
    }

    public void level_five(View view) {
        if (dataBaseLearn.getData(table, 4)) {
            intent = new Intent(this, Exam.class);
            intent.putExtra("long_table", table);
            intent.putExtra("level", 5);
            startActivityForResult(intent, 1);
        }
    }

    public void review(View view) {
        if (dataBaseLearn.getData(table, 5)) {
            intent = new Intent(this, Exam.class);
            intent.putExtra("long_table", table);
            intent.putExtra("level", 6);
            startActivityForResult(intent, 1);
        }
    }

    public void back_main(View view) {
        setResult(1);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            int result = data.getIntExtra("level", 0);
            dataBaseLearn.updateData(table, result);
            add_image_check(result);
        }
    }

    public void add_image_check(int column_index) {
        switch (column_index) {
            case 1:
                ((ImageView) findViewById(R.id.result_one)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearn.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_two)).setImageResource(R.drawable.ic_check_false);
                break;
            case 2:
                ((ImageView) findViewById(R.id.result_two)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearn.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_three)).setImageResource(R.drawable.ic_check_false);
                break;
            case 3:
                ((ImageView) findViewById(R.id.result_three)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearn.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_four)).setImageResource(R.drawable.ic_check_false);
                break;
            case 4:
                ((ImageView) findViewById(R.id.result_four)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearn.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.result_five)).setImageResource(R.drawable.ic_check_false);
                break;
            case 5:
                ((ImageView) findViewById(R.id.result_five)).setImageResource(R.drawable.ic_check_true);
                if (!dataBaseLearn.getData(table, column_index + 1))
                    ((ImageView) findViewById(R.id.img_review)).setImageResource(R.drawable.ic_check_false);
                break;
            case 6:
                ((ImageView) findViewById(R.id.img_review)).setImageResource(R.drawable.ic_check_true);
                imageBack.setVisibility(View.VISIBLE);
                break;
        }
    }

}