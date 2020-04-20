/**
 * Copyright 2018, Show-Fitness
 * FileName: PageUtil
 * Author:   Qing
 * Date:     2018/12/25 14:29
 * Description: 分页封装数据
 * version：   1.0
 */
package f.s.frobot.util;

import f.s.frobot.vo.PageVo;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 〈分页封装数据〉 
 *
 * @author Qing
 * @create 2018/12/25 
 * @since 1.0.0 */
public class PageUtil {

    //将数据封装到pageVo中
    public static PageVo processingData(PageVo pageVo, Object obj){
        List<Object> list = (List<Object>) obj;
        pageVo.setRows(list);
        Long total=new PageInfo<>(list).getTotal();
        pageVo.setTotal(total.intValue());
        return pageVo;
    }
}
