package ucai.cn.yongliprojection.bean;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class PlanItem {
    private float month;//改为float类型的数据Float.parseFloat()
    private int capitalNum;
    private int monthNum;
    private int withdrawNum;
    private int yearNum;

    public PlanItem(float month, int capitalNum, int monthNum, int withdrawNum, int yearNum) {
        this.month = month;
        this.capitalNum = capitalNum;
        this.monthNum = monthNum;
        this.withdrawNum = withdrawNum;
        this.yearNum = yearNum;
    }

    public float getMonth() {
        return month;
    }

    public void setMonth(float month) {
        this.month = month;
    }

    public int getCapitalNum() {
        return capitalNum;
    }

    public void setCapitalNum(int capitalNum) {
        this.capitalNum = capitalNum;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public int getWithdrawNum() {
        return withdrawNum;
    }

    public void setWithdrawNum(int withdrawNum) {
        this.withdrawNum = withdrawNum;
    }

    public int getYearNum() {
        return yearNum;
    }

    public void setYearNum(int yearNum) {
        this.yearNum = yearNum;
    }


    @Override
    public String toString() {
        return "PlanItem{" +
                "month=" + month +
                ", capitalNum=" + capitalNum +
                ", monthNum=" + monthNum +
                ", withdrawNum=" + withdrawNum +
                ", yearNum=" + yearNum +
                '}';
    }

}
