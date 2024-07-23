package vo;

import lombok.Data;

/*
*  封装客户端条件
* */
@Data
public class PageKeyWordsVo {
     private Integer pageNum;
     private Integer pageSize;
     private String keyWords;
}
