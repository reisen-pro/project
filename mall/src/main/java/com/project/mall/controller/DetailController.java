package com.project.mall.controller;

import com.project.mall.dto.DetailImage;
import com.project.mall.dto.ItemInfo;
import com.project.mall.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


        itemInfo.setTitle("女装三折起");
        itemInfo.setDiscount(30);
        itemInfo.setNewPrice("￥59.00");
        itemInfo.setOldPrice("￥69.00");
        itemInfo.setLowNowPrice(49.00);
        itemInfo.setDesc("现特价上市");
        itemInfo.setLength(1);
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

        String[] columns = {"销量 1580", "收藏33人", "默认快递"};
        itemInfo.setColumns(columns);

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
