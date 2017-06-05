package ucai.cn.yongliprojection.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class PlanData implements Serializable{
    private int capital;
    private int monthRate;
    private int yearRate;
    private int withdrawalRate;

    public PlanData(int capital, int monthRate, int yearRate, int withdrawalRate) {
        this.capital = capital;
        this.monthRate = monthRate;
        this.yearRate = yearRate;
        this.withdrawalRate = withdrawalRate;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(int monthRate) {
        this.monthRate = monthRate;
    }

    public int getYearRate() {
        return yearRate;
    }

    public void setYearRate(int yearRate) {
        this.yearRate = yearRate;
    }

    public int getWithdrawalRate() {
        return withdrawalRate;
    }

    public void setWithdrawalRate(int withdrawalRate) {
        this.withdrawalRate = withdrawalRate;
    }

    @Override
    public String toString() {
        return "PlanData{" +
                "capital=" + capital +
                ", monthRate=" + monthRate +
                ", yearRate=" + yearRate +
                ", withdrawalRate=" + withdrawalRate +
                '}';
    }
}
