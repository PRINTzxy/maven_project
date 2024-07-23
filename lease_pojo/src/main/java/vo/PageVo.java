package vo;


import lombok.Data;
import pojo.SysSchedule;

import java.util.List;

/*
* 封装响应的数据
* */
@Data
public class PageVo {
    private Integer pageNum;
    private Integer pageSize;
    private String keyWords;
    private Long totalPage;
    private List<SysSchedule> scheduleList;
}
