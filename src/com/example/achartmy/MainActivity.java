package com.example.achartmy;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private GraphicalView mChartView;
	private GraphicalView mChartView1;
	private GraphicalView mChartView2;
	private GraphicalView mChartView3;
	double[] xdata = new double[] { 5, 15, 25, 35, 45 };
	String[] xtitles = new String[] { "国企", "私企", "外资", "港台企业", "合资企业" };

	double[] ydata = new double[] { 11, 11, 22, 33, 44 };
	double[] ydata1 = new double[] { 11, 5, 22, 33, 44 };
	double[] ydata2 = new double[] { 11, 11, 22, 33, 44 };
	double[] ydata3 = new double[] { 11, 11, 22, 33, 44 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
/**
 * @param title  统计图的名称
 * @param xtitle  x轴的名称
 * @param ytitle  y轴的名称
 * @return
 * XYMultipleSeriesRenderer
 */
	public XYMultipleSeriesRenderer getRenderer(String title, String xtitle,
			String ytitle,int color) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// 通过SimpleSeriesDenderer设置描绘器的颜色
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(color);// 设置圆柱的颜色
		renderer.addSeriesRenderer(r);
		renderer.setChartTitle(title);// 设置柱图名称
		renderer.setChartTitleTextSize(16);
		renderer.setYTitle(ytitle);// 设置Y轴名称
		renderer.setXTitle(xtitle);
		renderer.setLabelsTextSize(13);

		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setMarginsColor(Color.rgb(239, 239, 239));// 设置间隔的颜色

		// 刻度设置
		renderer.setXAxisMin(0);// 设置X轴的最小值为0.5
		renderer.setXAxisMax(50);// 设置X轴的最大值为5
		renderer.setYAxisMin(0);// 设置Y轴的最小值为0
		renderer.setYAxisMax(60);// 设置Y轴最大值为500
		renderer.setXLabels(0);// 设置X轴显示的刻度标签的个数

		renderer.setMargins(new int[] { 0, 15, 0, 15 });// 设置图表的外边框(上/左/下/右)

		renderer.setShowGrid(true);// 设置是否在图表中显示网格
		renderer.setDisplayChartValues(true); // 设置是否在柱体上方显示值
		renderer.setZoomEnabled(false, false);// 设置不可缩放
		renderer.setPanEnabled(false);// 设置不可滚动
		renderer.setBarSpacing(0.5f);
		return renderer;
	}

	/**
	 * @param renderer
	 * @param ydata
	 *            y轴数据
	 * @param xtitles
	 *            各个柱状图的名称
	 * @return XYMultipleSeriesDataset
	 */
	public XYMultipleSeriesDataset getDataset(
			XYMultipleSeriesRenderer renderer, double[] ydata, String[] xtitles) {
		XYSeries mSeries = new XYSeries("");
		for (int i = 0; i < 5; i++) {
			double x = xdata[i];
			double y = ydata[i];
			// 把坐标添加到当前序列中去
			mSeries.add(x, y);
			renderer.addXTextLabel(xdata[i], xtitles[i]);
		}

		XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
		mDataset.addSeries(mSeries);
		return mDataset;
	}

	@Override
	protected void onResume() {
		super.onResume();
		LinearLayout layout = (LinearLayout) findViewById(R.id.view1);
		LinearLayout layout1 = (LinearLayout) findViewById(R.id.view2);
		LinearLayout layout2 = (LinearLayout) findViewById(R.id.view3);
		LinearLayout layout3 = (LinearLayout) findViewById(R.id.view4);
		XYMultipleSeriesRenderer ren1 = getRenderer("企业类型", "", "",Color.YELLOW);
		XYMultipleSeriesRenderer ren2 = getRenderer("企业行业", "", "",Color.BLACK);
		XYMultipleSeriesRenderer ren3 = getRenderer("企业性质", "", "",Color.BLUE);
		XYMultipleSeriesRenderer ren4 = getRenderer("企业规模", "", "",Color.GREEN);

		mChartView = ChartFactory.getBarChartView(this,
				getDataset(ren1, ydata, xtitles), ren1, Type.DEFAULT);
		mChartView1 = ChartFactory.getBarChartView(this,
				getDataset(ren2, ydata, xtitles), ren2, Type.DEFAULT);
		mChartView2 = ChartFactory.getBarChartView(this,
				getDataset(ren3, ydata, xtitles), ren3, Type.DEFAULT);
		mChartView3 = ChartFactory.getBarChartView(this,
				getDataset(ren4, ydata, xtitles), ren4, Type.DEFAULT);

		layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout1.addView(mChartView1, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout2.addView(mChartView2, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout3.addView(mChartView3, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

}
