package ucai.cn.yongliprojection.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ucai.cn.yongliprojection.R;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class FooterHolder extends RecyclerView.ViewHolder{
    public TextView tvSumProfit,tvProfitRatio;
    public FooterHolder(View itemView) {
        super(itemView);
        tvSumProfit = (TextView) itemView.findViewById(R.id.sumProfit);
        tvProfitRatio = (TextView) itemView.findViewById(R.id.profitRatio);
    }
}
