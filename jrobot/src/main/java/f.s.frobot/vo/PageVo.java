package f.s.frobot.vo;

import java.util.List;

import lombok.Data;

@Data
public class PageVo<T> {
    private Integer pageIndex = 1;

    private Integer pageSize = 10;

    private Integer total;//总行数

    private Integer totalPages;//总页数

    private List<T> rows; // 数据

    //总页数
    public Integer getTotalPages() {
          return  this.total %  this.pageSize > 0 ? this.total / this.pageSize + 1 : this.total / this.pageSize;
      }

}
