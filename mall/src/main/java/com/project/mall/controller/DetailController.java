package com.project.mall.controller;

import com.project.mall.dto.*;
import com.project.mall.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Reisen
 * @title: DetailController
 * @projectName reisen
 * @description: TODO
 * @date 2020/8/16 14:45
 */
@RestController
public class DetailController {

    @GetMapping(value = "detail")
    public JsonResult detail(String id) {
        ItemInfo itemInfo = new ItemInfo();
        List<String> imageList = new ArrayList<>();
        String image1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597572511377&di=3cd0d015259fa972254e1c2952d12708&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201709%2F28%2F150122888d8odsrjg85jjd.jpg";
        String image2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597572611121&di=0aa2f1e76c841204edd992a4c52e3b38&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201609%2F20%2F180335skywz0jb0wzw0666.jpg";
        String image3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597572611120&di=d44c899fd87a2fe308f1f9e91134e129&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F2018-11-05%2F5bdfd9407a9b2.jpg";
        imageList.add(image1);
        imageList.add(image2);
        imageList.add(image3);
        itemInfo.setTopImages(imageList);


        itemInfo.setTitle("新款潮流女装");
        itemInfo.setDiscount("活动价");
        itemInfo.setNewPrice("￥59.00");
        itemInfo.setOldPrice("￥69.00");
        itemInfo.setLowNowPrice(49.00);
        itemInfo.setDesc("现特价上市");
        itemInfo.setDiscountBgColor("#ff8198");

        DetailImage detailImage = new DetailImage();
        detailImage.setDesc("新款上市~");
        ArrayList images = new ArrayList();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image1);
        images.add(image2);
        images.add(image3);
        detailImage.setDetailImage(images);
        detailImage.setDesc("desc");
        detailImage.setAnchor("anchor");
        detailImage.setKey("key");

        itemInfo.setDetailImage(detailImage);

        String[] columns = {"销量 15800", "收藏333人", "默认快递"};
        itemInfo.setColumns(columns);

        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setLogo("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1828149217,1653444566&fm=26&gp=0.jpg");
        shopInfo.setName("女装专卖店");
        shopInfo.setFans(30);
        shopInfo.setSells(10);

        ArrayList scoreList = new ArrayList();
        Score score1 = new Score("店铺人气",4.6,false);
        Score score2 = new Score("店铺评价",4.9,true);
        Score score3 = new Score("发货速度",4.6,false);
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);

        shopInfo.setScore(scoreList);
        shopInfo.setGoodsCount(90);
        shopInfo.setAllGoodsUrl("goodsurl");
        shopInfo.setIsMarked(true);
        shopInfo.setLevel(4);
        shopInfo.setSells(86000);

        itemInfo.setShopInfo(shopInfo);

        ArrayList servicesList = new ArrayList();
        Services services1 = new Services();
        Services services2 = new Services();
        Services services3 = new Services();
        Services services4 = new Services();

        services1.setIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3135927016,678727836&fm=26&gp=0.jpg");
        services1.setName("退货补运费");
        services2.setIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3135927016,678727836&fm=26&gp=0.jpg");
        services2.setName("全国包邮");
        services3.setIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3135927016,678727836&fm=26&gp=0.jpg");
        services3.setName("7天无理由退款");
        services4.setIcon("icon3");
        services4.setName("72小时发货");
        servicesList.add(services1);
        servicesList.add(services2);
        servicesList.add(services3);
        servicesList.add(services4);

        itemInfo.setServices(servicesList);

        JsonResult result = new JsonResult();
        result.setData(itemInfo);
        return result;
    }

    @GetMapping(value = "columns")
    public JsonResult getColumns() {
        return JsonResult.buildSuccess(200, 10);
    }

    @GetMapping(value = "services")
    public JsonResult getServices() {
        return JsonResult.buildSuccess(200, "");
    }
}
