package ucai.cn.yongliprojection.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import ucai.cn.yongliprojection.R;
import ucai.cn.yongliprojection.adapter.ItemAdapter;
import ucai.cn.yongliprojection.bean.PlanData;
import ucai.cn.yongliprojection.bean.PlanItem;
import ucai.cn.yongliprojection.fragments.InvestmentInformation;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class DetailedInformation extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ArrayList<PlanItem> itemList;
    private Object intentData;
    private int id;
    private PlanData planData;
    private TextView tableTitle;
    private String[] planTitle;
    private ItemAdapter adapter;
    private int monthStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailinformation);
        initView();
        getIntentData();
        caculateItemData();
        initItemList();
    }

    private void initItemList() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ItemAdapter(itemList, this, planData.getCapital());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvTable);
        tableTitle = (TextView) findViewById(R.id.table_title);
    }

    private void getIntentData() {
        id = getIntent().getIntExtra("menu_id", 0);
        planData = (PlanData) getIntent().getSerializableExtra("planData");
        planTitle = getResources().getStringArray(R.array.planTitle);
        itemList = new ArrayList<>();
    }

    private void caculateItemData() {
        int capitalNum = planData.getCapital();
        int monthNum = planData.getCapital() * planData.getMonthRate() / 100;
        int withdrawNum = monthNum * planData.getWithdrawalRate() / 100;
        monthStatus = planData.getMonthStatus();
        Log.e("DetailedInformation", "mothStatus=" + monthStatus);
        int yearNum = monthNum - withdrawNum;
        switch (id) {

            case R.id.menu_one:
                tableTitle.setText(planTitle[0]);
                caculatePlanOneData(capitalNum, monthNum, withdrawNum, yearNum);
                break;
            case R.id.menu_two:
                tableTitle.setText(planTitle[1]);
                caculatePlanTwoData(capitalNum, monthNum, withdrawNum, yearNum);
                break;
            case R.id.menu_three:
                tableTitle.setText(planTitle[2]);
                caculatePlanThreeData(capitalNum, monthNum, withdrawNum, yearNum);
                break;
            case R.id.menu_four:
                tableTitle.setText(planTitle[3]);
                caculatePlanFourData(capitalNum, monthNum, withdrawNum, yearNum);
                break;
            case R.id.menu_five:
                tableTitle.setText(planTitle[4]);
                caculatePlanFiveData(capitalNum, monthNum, withdrawNum, yearNum);
                break;
        }
    }

    private void caculatePlanFiveData(int capitalNum, int monthNum, int withdrawNum, int yearNum) {
        String month = null;
        int requiredWithdrawNum = 0;
        int requiredCapitalNum = capitalNum;

        if (monthStatus == InvestmentInformation.HALF_MONTH_SELECTED) {

            for (int i = 1; i < 25; i++) {
                float requiredMonth = getRequiredMonth(month, i);
                if (i > 1) {
                    requiredCapitalNum += requiredWithdrawNum;//形参可以当成是方法内部的变量使用吗？
                    if (i == 2) {
                        requiredCapitalNum -= capitalNum;
                    }

                    int requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredWithdrawNum = requiredMonthNum * planData.getWithdrawalRate() / 100;
                    int requiredYearNum = requiredMonthNum - requiredWithdrawNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    itemList.add(planItem);//非空验证是必须要明确的一点

                } else {

                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    Log.e("DetailedInformation", "planItem1=" + planItem.toString());
                    itemList.add(planItem);
                }
                if (i == 1) {
                    requiredWithdrawNum = getRequiredWithdrawNum(withdrawNum);

                } else {
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                }
            }
        } else {
            for (int i = 1; i < 13; i++) {
//                float requiredMonth = getRequiredMonth(month, i);
                float requiredMonth = i;
                if (i > 1) {
                    requiredCapitalNum += requiredWithdrawNum;//形参可以当成是方法内部的变量使用吗？
                    if (i == 2) {
                        requiredCapitalNum -= capitalNum;
                    }

                    int requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredWithdrawNum = requiredMonthNum * planData.getWithdrawalRate() / 100;
                    int requiredYearNum = requiredMonthNum - requiredWithdrawNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    itemList.add(planItem);//非空验证是必须要明确的一点

                } else {

                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    Log.e("DetailedInformation", "planItem1=" + planItem.toString());
                    itemList.add(planItem);
                }
                if (i == 1) {
                    requiredWithdrawNum = getRequiredWithdrawNum(withdrawNum);

                } else {
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                }
            }

        }


    }

    private void caculatePlanFourData(int capitalNum, int monthNum, int withdrawNum, int yearNum) {
        String month = null;
        int requiredWithdrawNum = 0;
        int requiredCapitalNum = capitalNum;
        if (monthStatus == InvestmentInformation.HALF_MONTH_SELECTED) {

            for (int i = 1; i < 25; i++) {
                float requiredMonth = getRequiredMonth(month, i);
                if (i > 1) {
                    requiredCapitalNum += requiredWithdrawNum;//形参可以当成是方法内部的变量使用吗？
                    int requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredWithdrawNum = requiredMonthNum * planData.getWithdrawalRate() / 100;
                    int requiredYearNum = requiredMonthNum - requiredWithdrawNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    itemList.add(planItem);//非空验证是必须要明确的一点

                } else {

                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    Log.e("DetailedInformation", "planItem1=" + planItem.toString());
                    itemList.add(planItem);

                }
                if (i == 1) {
                    requiredWithdrawNum = getRequiredWithdrawNum(withdrawNum);

                } else {
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                }
            }
        } else {
            for (int i = 1; i < 13; i++) {
                float requiredMonth = i;
                if (i > 1) {
                    requiredCapitalNum += requiredWithdrawNum;//形参可以当成是方法内部的变量使用吗？
                    int requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredWithdrawNum = requiredMonthNum * planData.getWithdrawalRate() / 100;
                    int requiredYearNum = requiredMonthNum - requiredWithdrawNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    itemList.add(planItem);//非空验证是必须要明确的一点

                } else {

                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    Log.e("DetailedInformation", "planItem1=" + planItem.toString());
                    itemList.add(planItem);

                }
                if (i == 1) {
                    requiredWithdrawNum = getRequiredWithdrawNum(withdrawNum);

                } else {
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                }
            }

        }

    }

    private void caculatePlanThreeData(int capitalNum, int monthNum, int withdrawNum, int yearNum) {
        String month = null;
        int requiredWithdrawNum = 0;
        int requiredCapitalNum = capitalNum;
        if (monthStatus == InvestmentInformation.HALF_MONTH_SELECTED) {

            for (int i = 1; i < 25; i++) {
                float requiredMonth = getRequiredMonth(month, i);
                if (i > 1) {
                    requiredCapitalNum += requiredWithdrawNum;//形参可以当成是方法内部的变量使用吗？
                    if (i == 7) {
                        requiredCapitalNum -= capitalNum;//解耦单一原则中的解耦
                    }
                    int requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredWithdrawNum = requiredMonthNum * planData.getWithdrawalRate() / 100;
                    int requiredYearNum = requiredMonthNum - requiredWithdrawNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    itemList.add(planItem);//非空验证是必须要明确的一点
                } else {
                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    Log.e("DetailedInformation", "planItem1=" + planItem.toString());
                    itemList.add(planItem);
                }
                if (i == 1) {

                    requiredWithdrawNum = getRequiredWithdrawNum(withdrawNum);

                } else {

                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                }
            }
        } else {
            for (int i = 1; i < 13; i++) {
                float requiredMonth = i;
                if (i > 1) {
                    requiredCapitalNum += requiredWithdrawNum;//形参可以当成是方法内部的变量使用吗？
                    if (i == 7) {
                        requiredCapitalNum -= capitalNum;//解耦单一原则中的解耦
                    }
                    int requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredWithdrawNum = requiredMonthNum * planData.getWithdrawalRate() / 100;
                    int requiredYearNum = requiredMonthNum - requiredWithdrawNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    itemList.add(planItem);//非空验证是必须要明确的一点
                } else {
                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    Log.e("DetailedInformation", "planItem1=" + planItem.toString());
                    itemList.add(planItem);
                }
                if (i == 1) {

                    requiredWithdrawNum = getRequiredWithdrawNum(withdrawNum);

                } else {

                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                }
            }
        }

    }


    private void caculatePlanTwoData(int capitalNum, int monthNum, int withdrawNum, int yearNum) {
        String month = null;
        int requiredWithdrawNum = withdrawNum;
        int requiredCapitalNum = capitalNum;
        int requiredMonthNum;
        int requiredYearNum;

        if (monthStatus == InvestmentInformation.HALF_MONTH_SELECTED) {

            for (int i = 1; i < 25; i++) {
                float requiredMonth = getRequiredMonth(month, i);
                if (i > 7) {
                    requiredCapitalNum += requiredWithdrawNum;
                    requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredYearNum = requiredMonthNum * planData.getYearRate() / 100;
                    requiredWithdrawNum = requiredMonthNum - requiredYearNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                    itemList.add(planItem);
                } else {
                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    itemList.add(planItem);

                }
                if (i == 7) {
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);

                }

            }
        } else {
            for (int i = 1; i < 13; i++) {
                float requiredMonth = i;
                if (i > 7) {
                    requiredCapitalNum += requiredWithdrawNum;
                    requiredMonthNum = requiredCapitalNum * planData.getMonthRate() / 100;
                    requiredYearNum = requiredMonthNum * planData.getYearRate() / 100;
                    requiredWithdrawNum = requiredMonthNum - requiredYearNum;
                    PlanItem planItem = new PlanItem(requiredMonth, requiredCapitalNum, requiredMonthNum, requiredWithdrawNum, requiredYearNum);
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);
                    itemList.add(planItem);
                } else {
                    PlanItem planItem = new PlanItem(requiredMonth, capitalNum, monthNum, withdrawNum, yearNum);
                    itemList.add(planItem);

                }
                if (i == 7) {
                    requiredWithdrawNum = getRequiredWithdrawNum(requiredWithdrawNum);

                }

            }

        }

    }

    private void caculatePlanOneData(int capitalNum, int monthNum, int withdrawNum, int yearNum) {
        for (int i = 0; i < 12; i++) {
            PlanItem planItem = new PlanItem(i + 1, capitalNum, monthNum, withdrawNum, yearNum);
            itemList.add(planItem);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        itemList.clear();
    }

    private int getRequiredWithdrawNum(int withdrawNum) {
        if (withdrawNum > 100) {
            if (withdrawNum % 100 >= 50) {
                withdrawNum -= withdrawNum % 100;
                withdrawNum += 100;
            } else if (withdrawNum % 100 < 50) {
                withdrawNum -= withdrawNum % 100;
            }

        } else {
            withdrawNum = 100;
        }
        return withdrawNum;
    }

    private float getRequiredMonth(String month, int i) {
        if (i % 2 == 0) {
            month = (((i + 1) / 2) + "." + 2);
        } else if (i % 2 == 1) {
            month = (((i + 1) / 2) + "." + 1);
        }
        return Float.parseFloat(month);
    }

}
