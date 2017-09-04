package com.lp.gameclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lp.gameclient.adapter.common.RecyclerAdapter;
import com.lp.gameclient.adapter.common.RecyclerAdatpterHelper;
import com.lp.gameclient.data.models.ImageEntity;
import com.lp.gameclient.data.models.NewsEntity;
import com.lp.gameclient.data.retrofit.RetrofitClient;
import com.lp.gameclient.module.web.WebViewActivity;
import com.lp.gameclient.utils.ActivityUtil;
import com.lp.gamenewsdemo.R;

import java.util.List;

/**
 * Created by LP on 2017/9/4/15:01.
 */

public class NewsAdapter extends RecyclerAdapter<NewsEntity> {

    public static final int TEMPLATE_NO_PIC = 1;  //没有照片
    public static final int TEMPLATE_ONE_BIG_PIC = 2; //一张大图
    public static final int TEMPLATE_THREE_SMALL_PIC = 3; //三张小图
    public static final int TEMPLATE_ONE_SMALL_PIC = 4;  //一张小图
    private Context mContext;

    public NewsAdapter(Context context) {
        this(context, R.layout.item_news_template1, R.layout.item_news_template2,
                R.layout.item_news_template3, R.layout.item_news_template4);
    }

    public NewsAdapter(Context context, @NonNull int... layoutResIds){
        super(context, layoutResIds);
        this.mContext = context;
    }

    @Override
    protected void convert(RecyclerAdatpterHelper helper, NewsEntity item) {
        int type = getItemViewType(helper.getItemViewType());
        List<ImageEntity> img = item.getmImageEntityList();
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_pubtime, item.getCreate_time())
                .setText(R.id.tv_sourceName, "来源：" + item.getSource_name())
                .getItemView().setOnClickListener(v ->
                ActivityUtil.start(mContext, WebViewActivity.class, RetrofitClient.URL_BASE_NEWS +
                        item.getUrl()));
        if (img != null && !img.isEmpty()){
            if (type == TEMPLATE_ONE_BIG_PIC || type == TEMPLATE_ONE_SMALL_PIC)
                helper.setImageUrl(R.id.iv_thumb, img.get(0).getUrl(), false);
            else if (type == TEMPLATE_THREE_SMALL_PIC && img.size() == 3){
                helper.setImageUrl(R.id.iv_thumb, img.get(0).getUrl(), false)
                        .setImageUrl(R.id.iv_thumb2, img.get(1).getUrl(), false)
                        .setImageUrl(R.id.iv_thumb3, img.get(2).getUrl(), false);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        NewsEntity newsEntity = get(position);
        String templateId = newsEntity.getTemplate_id();
        if (TextUtils.isEmpty(templateId))
            templateId = "1";
        return Integer.parseInt(templateId);
    }

    @Override
    public int getLayoutresId(int viewType) {
        if (viewType == TEMPLATE_ONE_BIG_PIC)
            return R.layout.item_news_template2;
        else if (viewType == TEMPLATE_THREE_SMALL_PIC)
            return R.layout.item_news_template3;
        else if (viewType == TEMPLATE_ONE_SMALL_PIC)
            return R.layout.item_news_template4;
        else
            return R.layout.item_news_template1
    }
}
