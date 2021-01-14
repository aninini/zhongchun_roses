package cn.stylefeng.roses.kernel.loginlog.modular.controller;

import cn.stylefeng.roses.kernel.loginlog.modular.service.SysLoginLogService;
import cn.stylefeng.roses.kernel.resource.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.resource.api.annotation.GetResource;
import cn.stylefeng.roses.kernel.rule.pojo.response.ResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.SuccessResponseData;
import cn.stylefeng.roses.kernel.system.pojo.loginlog.request.SysLoginLogRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登陆日志控制器
 *
 * @author chenjinlong
 * @date 2021/1/13 17:51
 */
@RestController
@ApiResource(name = "登录日志")
public class SysLoginLogController {

    @Resource
    private SysLoginLogService sysLoginLogService;


    /**
     * 清空登录日志
     *
     * @author chenjinlong
     * @date 2021/1/13 17:51
     */
    @GetResource(name = "清空登录日志", path = "/loginLog/deleteAll")
    public ResponseData deleteAll() {
        sysLoginLogService.deleteAll();
        return new SuccessResponseData();
    }


    /**
     * 查询登录日志详情
     *
     * @author chenjinlong
     * @date 2021/1/13 17:51
     */
    @GetResource(name = "查看详情登录日志", path = "/loginLog/detail")
    public ResponseData detail(@Validated(SysLoginLogRequest.detail.class) SysLoginLogRequest sysLoginLogRequest) {
        return new SuccessResponseData(sysLoginLogService.detail(sysLoginLogRequest));
    }

    /**
     * 分页查询登录日志
     *
     * @author chenjinlong
     * @date 2021/1/13 17:51
     */
    @GetResource(name = "分页查询登录日志", path = "/loginLog/page")
    public ResponseData page(SysLoginLogRequest sysLoginLogRequest) {
        return new SuccessResponseData(sysLoginLogService.page(sysLoginLogRequest));
    }


}