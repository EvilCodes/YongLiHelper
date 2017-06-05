package ucai.cn.yongliprojection.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ucai.cn.yongliprojection.R;

import static ucai.cn.yongliprojection.R.id.withdrawal_num;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class ItemHolder extends RecyclerView.ViewHolder{
    public TextView tvMonth,tvCapital,tvMonthNum,tvYearNum,tvWithdrawNum;
    public View itemView;
    public ItemHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        tvMonth = (TextView) itemView.findViewById(R.id.month);
        tvCapital = (TextView) itemView.findViewById(R.id.capital_num);
        tvMonthNum = (TextView) itemView.findViewById(R.id.month_num);
        tvYearNum = (TextView) itemView.findViewById(R.id.year_num);
        tvWithdrawNum = (TextView) itemView.findViewById(withdrawal_num);
    }
}
