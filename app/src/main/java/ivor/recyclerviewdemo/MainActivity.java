package ivor.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private Button mAddBtn;
    private Button mDelBtn;
    private List<String> mDatas;
    private List<Integer> mHeights;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ivor_main);

        initData();
        initView();
        initListener();
    }

    private void initView() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.ivor_main_rv);
        // 1、设置LayoutManager为LinearLayoutManager
        // this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // this.mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        // 2、设置LayoutManager为GridLayoutManager
        // this.mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        // this.mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        // 3、设置LayoutManager为StaggeredGridLayoutManager（瀑布流）
        this.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        this.mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        // this.mAdapter = new MainAdapter(this, mDatas);
        // 设置瀑布流Item的随机高度
        this.mAdapter = new MainAdapter(this, mDatas, mHeights);

        // 对Item操作时的动画
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mRecyclerView.setAdapter(mAdapter);

        this.mAddBtn = (Button) findViewById(R.id.ivor_add_btn);
        this.mDelBtn = (Button) findViewById(R.id.ivor_delete_btn);
    }

    private void initListener() {
        this.mAddBtn.setOnClickListener(this);
        this.mDelBtn.setOnClickListener(this);
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 1; i < 101; i++)
        {
            mDatas.add(i + "");
        }

        // 设置瀑布流Item的随机高度
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < 101; i++)
        {
            mHeights.add((int)(100 + Math.random() * 300));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ivor_add_btn:
                mAdapter.addData(1);
                break;
            case R.id.ivor_delete_btn:
                mAdapter.removeData(1);
                break;
        }
    }
}