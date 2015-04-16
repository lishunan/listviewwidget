package com.lsn.listviewfooterloadingwight.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lsn.listviewfooterloadingwight.R;

/**
 * #####################################################
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 * #####################################################
 * <p/>
 * <p/>
 * Created by apple on 2015-04-16.
 * <p/>
 * 自定义 ListView 分页加载控件
 * <p/>
 * Author: Lishunan
 * <p/>
 * Email:li_shu_nan@sina.com
 */

public class ListViewFooterLoading extends ListView implements AbsListView.OnScrollListener {

    //底部View
    private View mView;

    //listview Item总数
    private int mTotalItemCount;

    //listview 最后一个Item数
    private int mLastItemCount;

    //判断加载状态
    private boolean mIsLoading = false;

    //底部View  LinearLayout布局
    private LinearLayout mFooterView;

    //创建接口
    private OnLoadingListener mOnLoadingListener;

    //三个构造小方法

    public ListViewFooterLoading(Context context, AttributeSet attrs) {

        super(context, attrs);

        initFootView(context);
    }

    public ListViewFooterLoading(Context context) {

        super(context);

        initFootView(context);
    }

    public ListViewFooterLoading(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        initFootView(context);
    }


    /**
     * 初始化添加listview底部View
     *
     * @param context
     */
    private void initFootView(Context context) {

        //获取充气机，tmd我学习android的时候那个比昂的教课老师就一直这么叫，充气机！充气机！
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //获取底部View
        mView = inflater.inflate(R.layout.layout_listviewloading_footer, null);

        //初始化LinearLayout
        mFooterView = (LinearLayout) mView.findViewById(R.id.layout_listviewloading_footer);

        //设置OnScrollListener接口
        this.setOnScrollListener(this);

        //添加底部View
        this.addFooterView(mView);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        //判断listview是否滚动到最后Item
        if (mTotalItemCount == mLastItemCount && scrollState == SCROLL_STATE_IDLE) {

            //是否显示底部加载View
            if (!mIsLoading) {

                //回调接口方法
                if (mOnLoadingListener != null) {

                    mIsLoading = true;

                    //显示加载View
                    mFooterView.setVisibility(View.VISIBLE);

                    mOnLoadingListener.onLoading();
                }

            }
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        //获取最后显示Item 位置
        mLastItemCount = firstVisibleItem + visibleItemCount;

        //获取总数
        mTotalItemCount = totalItemCount;

    }


    /**
     * 设置OnLoadingListener接口方法
     *
     * @param onLoadingListener
     */
    public void setmOnLoadingListener(OnLoadingListener onLoadingListener) {

        this.mOnLoadingListener = onLoadingListener;

    }

    /**
     * 创建加载数据接口
     */
    public interface OnLoadingListener {

        //创建接口回调方法
        public void onLoading();
    }


    /**
     * onLoading加载完成后调用
     * <p/>
     * 重点：注意异步
     * <p/>
     * 提示：setLoadFinish方法当加载数据结束时必须调用此方法，
     * 如没有调用，会发生加载页面一直显示不加载数据，因为此时增加item数量有误。
     */
    public void setLoadFinish() {

        //设置隐藏底部View
        mFooterView.setVisibility(View.GONE);

        mIsLoading = false;
    }
}
