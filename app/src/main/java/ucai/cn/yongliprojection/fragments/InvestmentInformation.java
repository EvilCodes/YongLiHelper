package ucai.cn.yongliprojection.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ucai.cn.yongliprojection.R;
import ucai.cn.yongliprojection.activity.DetailedInformation;
import ucai.cn.yongliprojection.bean.PlanData;

import static android.R.attr.fraction;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class InvestmentInformation extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private TextView tvTitle, tvDescription;
    private Button mButton;
    private Spinner spCapital, spMonthRate, spWithdrawalRate, spYearRate;
    private String[] planTitle;
    private String[] planDescription;
    private int[] capitalDataSource;
    private int[] monthRateData;
    private Integer[] capitalData;
    private Context context;
    private PlanData planData;
    private int capital, monthRate;
    private int menuId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investmentinformation, null);
        initView(view);
        initData();
        initAdapter();
        setListener();
        setMenuSelected();
        return view;
    }

    private void setMenuSelected() {
        Bundle bundle = getArguments();
        menuId = bundle.getInt("menu_id");
        switch (menuId) {
            case R.id.menu_one:
                tvTitle.setText(planTitle[0]);
                tvDescription.setText(planDescription[0]);
                break;
            case R.id.menu_two:
                tvTitle.setText(planTitle[1]);
                tvDescription.setText(planDescription[1]);
                break;
            case R.id.menu_three:
                tvTitle.setText(planTitle[2]);
                tvDescription.setText(planDescription[2]);
                break;
            case R.id.menu_four:
                tvTitle.setText(planTitle[3]);
                tvDescription.setText(planDescription[3]);
                break;
            case R.id.menu_five:
                tvTitle.setText(planTitle[4]);
                tvDescription.setText(planDescription[4]);
                break;
        }

    }

    private void setListener() {
        mButton.setOnClickListener(this);
        spCapital.setOnItemSelectedListener(this);
        spMonthRate.setOnItemSelectedListener(this);


    }

    private void initAdapter() {
        capitalData = new Integer[7];
        for (int i = 0; i < capitalDataSource.length; i++) {
            capitalData[i] = capitalDataSource[i];
        }
        ArrayAdapter capitalAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, capitalData);
        spCapital.setAdapter(capitalAdapter);

        ArrayAdapter monthRateAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, new Integer[]{monthRateData[0], monthRateData[1]});
        spMonthRate.setAdapter(monthRateAdapter);

        ArrayAdapter yearRateAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, new Integer[]{40});
        spYearRate.setAdapter(yearRateAdapter);

        ArrayAdapter withdrawalRateAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, new Integer[]{60});
        spWithdrawalRate.setAdapter(withdrawalRateAdapter);
    }

    private void initData() {
        context = getContext();
        planTitle = getResources().getStringArray(R.array.planTitle);
        planDescription = getResources().getStringArray(R.array.planDescription);
        monthRateData = getResources().getIntArray(R.array.monthRate);
        capitalDataSource = getResources().getIntArray(R.array.capital);
    }

    private void initView(View view) {
        tvTitle = (TextView) view.findViewById(R.id.plan_title);
        tvDescription = (TextView) view.findViewById(R.id.plan_description);
        mButton = (Button) view.findViewById(R.id.generate_excel);
        spCapital = (Spinner) view.findViewById(R.id.capital);
        spWithdrawalRate = (Spinner) view.findViewById(R.id.withdrawal_rate);
        spYearRate = (Spinner) view.findViewById(R.id.year_rate);
        spMonthRate = (Spinner) view.findViewById(R.id.month_rate);

    }

    @Override
    public void onClick(View v) {
        if (planData != null) {
            Intent intent = new Intent(getActivity(), DetailedInformation.class);
            intent.putExtra("planData", planData);
            intent.putExtra("menu_id", menuId);
            Log.e("main", "menu_id=" + menuId);
            context.startActivity(intent);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.capital:
                capital = capitalDataSource[position];

                break;
            case R.id.month_rate:
                monthRate = monthRateData[position];
                break;
        }
        if (capital != 0 && monthRate != 0) {

            planData = new PlanData(capital, monthRate, 40, 60);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
