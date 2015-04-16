# listviewwidget
自定义分页ListView控件
自娱自乐，不喜勿喷

只需实现下面接口就可实现分页加载逻辑

       litView.setmOnLoadingListener(new ListViewFooterLoading.OnLoadingListener() {
            @Override
            public void onLoading() {

                //加载数据
                //date.add("aaaaaa");

                
                litView.setLoadFinish();


            }
        });
        

Email：li_shu_nan@sina.com
