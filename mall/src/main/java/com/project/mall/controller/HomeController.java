package com.project.mall.controller;

import com.project.mall.model.Banner;
import com.project.mall.model.Good;
import com.project.mall.model.Recommend;
import com.project.mall.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Reisen
 * @title: HomeController
 * @projectName mall
 * @description: TODO
 * @date 2020/7/26 16:18
 */
@RestController
@RequestMapping(value = "home")
public class HomeController {

    @GetMapping(value = "multidata/banner")
    public JsonResult getBanner() {

        List<Banner> banners = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Banner banner = new Banner();
            banner.setAcm("acm");
            banner.setHeight("390");
            banner.setHeight923("390");

            banner.setImage923("https://aecpm.alicdn.com/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg");
            banner.setLink("https://click.mz.simba.taobao.com/ecpm?spm=875.7931836/B.2016006.d2&e=z8U1fpvLAKcPO53EdXrfSxsQmLP5zomMj6gFHnyctK57n%2BxzsrjTcOIm6wvQHibwPtqAKKPA65%2F%2F6eIJKzWyc2RV5VLNsTWptbR9sxq9q4jPdxtGLmpJMKiTZU1Z7A7u9iFUaGvyKCGT5fzkODgSz1197sOzUjzt4jY1ac3z3kmf52u0Wo2aiiEWLN70CJk%2BnTuJ%2BsiI2R7rHgcBQpOPjHVFfH8HsG8j3PENQJBkLUxTln%2Fgnu3nIl40SVVH6pdYKSgsgXnb7lJd0UW984Dj%2B%2BEXU%2FhrnpOVIDAfUCINLyjWDlNnFFk%2BSMAhDHBa67lVr1IbD%2B6pu6lorxAMObfr4vpibArCNRoIzDnl7hn%2BdyyB423jhK%2F7VuEXU%2FhrnpOVguhULnyIH39e%2BDHQfdoKdeoNfkr1L40vcdyqviB14Ey51V3FPLMf61XeaRgzxDgZjgm6wXM2mffjfre1A4W%2FYz70ULfhvSWn9M5mQon8EVCromyY6%2Fg2ohmxN6NfOmfM3uPJztm4Ps75RwDLuMfr1Y0qoRVkT9evcKr4T%2F76V%2B0%3D&u=https%3A%2F%2Fwww.tmall.com%2Fwow%2Fbrand%2Fact%2Ffashion%3Facm%3Dlb-zebra-2386-265936.1003.4.410386%26scm%3D1003.4.lb-zebra-2386-265936.OTHER_1_410386&k=557");
            banner.setTitle("换新女装节");
            banner.setWidth("750");
            banners.add(banner);
        }

        banners.get(0).setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3147019735,2004226719&fm=26&gp=0.jpg");
        banners.get(1).setImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1147802082,1925814848&fm=26&gp=0.jpg");
        banners.get(2).setImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2764719202,1230989758&fm=26&gp=0.jpg");
        banners.get(3).setImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=619078458,729041834&fm=11&gp=0.jpg");
        return JsonResult.buildSuccess(200, banners);
    }

    @GetMapping(value = "multidata/recommend")
    public JsonResult getRecommend() {
        List<Recommend> recommends = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Recommend recommend = new Recommend();
            recommend.setAcm("acm");
            recommend.setHeight("390");
            recommend.setHeight923("390");

            recommend.setImage923("http://aecpm.alicdn.com/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg");
            recommend.setLink("http://click.mz.simba.taobao.com/ecpm?spm=875.7931836/B.2016006.d2&e=z8U1fpvLAKcPO53EdXrfSxsQmLP5zomMj6gFHnyctK57n%2BxzsrjTcOIm6wvQHibwPtqAKKPA65%2F%2F6eIJKzWyc2RV5VLNsTWptbR9sxq9q4jPdxtGLmpJMKiTZU1Z7A7u9iFUaGvyKCGT5fzkODgSz1197sOzUjzt4jY1ac3z3kmf52u0Wo2aiiEWLN70CJk%2BnTuJ%2BsiI2R7rHgcBQpOPjHVFfH8HsG8j3PENQJBkLUxTln%2Fgnu3nIl40SVVH6pdYKSgsgXnb7lJd0UW984Dj%2B%2BEXU%2FhrnpOVIDAfUCINLyjWDlNnFFk%2BSMAhDHBa67lVr1IbD%2B6pu6lorxAMObfr4vpibArCNRoIzDnl7hn%2BdyyB423jhK%2F7VuEXU%2FhrnpOVguhULnyIH39e%2BDHQfdoKdeoNfkr1L40vcdyqviB14Ey51V3FPLMf61XeaRgzxDgZjgm6wXM2mffjfre1A4W%2FYz70ULfhvSWn9M5mQon8EVCromyY6%2Fg2ohmxN6NfOmfM3uPJztm4Ps75RwDLuMfr1Y0qoRVkT9evcKr4T%2F76V%2B0%3D&u=https%3A%2F%2Fwww.tmall.com%2Fwow%2Fbrand%2Fact%2Ffashion%3Facm%3Dlb-zebra-2386-265936.1003.4.410386%26scm%3D1003.4.lb-zebra-2386-265936.OTHER_1_410386&k=557");
            recommend.setTitle("换新女装节");
            recommend.setWidth("750");
            recommends.add(recommend);
        }
        for (int i = 0; i <4 ; i++) {
            Random random = new Random();
            int id = random.nextInt(1000);
            recommends.get(i).setImage("https://picsum.photos/id/"+id+"/200/200");
        }
        return JsonResult.buildSuccess(200,recommends);
    }

    @GetMapping(value = "data")
    public JsonResult getGoods(String type, int page) {
        System.out.println(type);
        System.out.println(page);
        List<Good> goods = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            Good good = new Good();
            good.setPrice("60");
            if ("pop".equals(type)) {
                good.setImg("https://picsum.photos/id/" + (i + 10) + "/200/200");
            }
            if ("news".equals(type)) {
                good.setImg("https://picsum.photos/id/" + (i + 110) + "/200/200");
            }
            if ("sell".equals(type)) {
                good.setImg("https://picsum.photos/id/" + (i + 210) + "/200/200");
            }
            good.setCfav("27");
            good.setId(""+i);
            good.setTitle("这个是商品标题");
            good.setType(type);
            goods.add(good);
        }
        return JsonResult.buildSuccess(200, goods);
    }

}
