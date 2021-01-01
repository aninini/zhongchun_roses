package cn.stylefeng.roses.kernel.rule.pojo.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 请求基类，所有接口请求可继承此类
 *
 * @author fengshuonan
 * @date 2020/10/14 18:12
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private String searchBeginTime;

    /**
     * 结束时间
     */
    private String searchEndTime;

    /**
     * 分页：每页大小（默认20）
     */
    private Integer pageSize;

    /**
     * 分页：第几页（从1开始）
     */
    private Integer pageNo;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 正序或者倒序排列（asc 或 desc）
     */
    private String sortBy;

    /**
     * 其他参数（如有需要）
     */
    private Map<String, Object> otherParams;

    /**
     * 参数校验分组：分页
     */
    public @interface page {
    }

    /**
     * 参数校验分组：查询所有
     */
    public @interface list {
    }

    /**
     * 参数校验分组：增加
     */
    public @interface add {
    }

    /**
     * 参数校验分组：编辑
     */
    public @interface edit {
    }

    /**
     * 参数校验分组：删除
     */
    public @interface delete {
    }

    /**
     * 参数校验分组：详情
     */
    public @interface detail {
    }

    /**
     * 参数校验分组：导出
     */
    public @interface export {
    }

    /**
     * 参数校验分组：修改状态
     */
    public @interface updateStatus {
    }

}
