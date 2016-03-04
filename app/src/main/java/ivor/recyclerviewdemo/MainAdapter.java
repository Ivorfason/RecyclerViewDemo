package ivor.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private List<String> mNumData;
    private List<Integer> mItemHeight;
    private LayoutInflater mInflater = null;

    public MainAdapter(Context context, List<String> mNumData) {
        this.mNumData = mNumData;
        this.mInflater = LayoutInflater.from(context);
    }

    public MainAdapter(Context context, List<String> mNumData, List<Integer> mItemHeight) {
        this.mNumData = mNumData;
        this.mItemHeight = mItemHeight;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.ivor_main_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // 设置瀑布流Item的随机高度
        ViewGroup.LayoutParams layoutParams = holder.numberTV.getLayoutParams();
        layoutParams.height = mItemHeight.get(position);
        holder.numberTV.setLayoutParams(layoutParams);

        holder.numberTV.setText(mNumData.get(position));
    }

    @Override
    public int getItemCount() {
        return mNumData.size();
    }

    public void addData(int position) {
        mNumData.add(position, "Ivor");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mNumData.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView numberTV;

        public MyViewHolder(View view)
        {
            super(view);
            numberTV = (TextView) view.findViewById(R.id.ivor_num_tv);
        }
    }
}