package ucai.cn.yongliprojection.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ucai.cn.yongliprojection.R;
import ucai.cn.yongliprojection.bean.PlanItem;
import ucai.cn.yongliprojection.viewholder.FooterHolder;
import ucai.cn.yongliprojection.viewholder.ItemHolder;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class ItemAdapter extends RecyclerView.Adapter {
    private ArrayList<PlanItem> arrayList;
    private Context mContext;
    private static final int ITEM_FOOTER = 0;
    private static final int ITEM_SELECTED = 1;
    private RecyclerView.ViewHolder viewHolder;
    private int sumYearProfit;
    private int sumCapital;
    private int originalCapital;



    public ItemAdapter(ArrayList<PlanItem> arrayList, Context mContext,int originalCapital) {
        this.originalCapital = originalCapital;
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_FOOTER://case后边必须跟常量
                View viewFooter = LayoutInflater.from(mContext).inflate(R.layout.layout_footer, parent, false);//解决item没有填满的问题
                viewHolder = new FooterHolder(viewFooter);
                break;
            case ITEM_SELECTED:
                View viewItem = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);//解决item没有填满的问题
                viewHolder = new ItemHolder(viewItem);
                break;
        }
        return viewHolder;

//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);//注意一点那就是该处inflate()方法的第二个参数要写成为null
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {

            PlanItem planItem = arrayList.get(position);
            ((ItemHolder) holder).tvMonth.setText(planItem.getMonth() + "");
            ((ItemHolder) holder).tvCapital.setText(planItem.getCapitalNum() + "");
            ((ItemHolder) holder).tvMonthNum.setText(planItem.getMonthNum() + "");
            ((ItemHolder) holder).tvWithdrawNum.setText(planItem.getWithdrawNum() + "");
            ((ItemHolder) holder).tvYearNum.setText(planItem.getYearNum() + "");
            if ((position + 1) % 2 == 0) {
                ((ItemHolder) holder).itemView.setBackgroundColor(Color.parseColor("#BFEFFF"));
            }
            sumYearProfit += planItem.getYearNum();
            if (position == getItemCount() - 2) {
                sumCapital = planItem.getCapitalNum() + planItem.getWithdrawNum();

            }
        } else if (holder instanceof FooterHolder) {
            Log.e("ItemAdapter", "sum=" + (sumCapital + sumYearProfit));
            int sum=sumCapital+sumYearProfit;
            ((FooterHolder) holder).tvSumProfit.setText(sum + "");
            ((FooterHolder)holder).tvProfitRatio.setText("1 :  "+(sum/originalCapital));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size() == 0 ? 0 : arrayList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = 0;
        if (position == getItemCount() - 1) {
            itemType = ITEM_FOOTER;

        } else {
            itemType = ITEM_SELECTED;
        }
        return itemType;
    }
}
