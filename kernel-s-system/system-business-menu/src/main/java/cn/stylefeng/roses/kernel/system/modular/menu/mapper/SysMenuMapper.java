/*
 * Copyright [2020-2030] [https://www.stylefeng.cn]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Guns采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改Guns源码头部的版权声明。
 * 3.请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 https://gitee.com/stylefeng/guns
 * 5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/stylefeng/guns
 * 6.若您的项目无法满足以上几点，可申请商业授权
 */
package cn.stylefeng.roses.kernel.system.modular.menu.mapper;

import cn.stylefeng.roses.kernel.system.api.pojo.menu.antd.AntdSysMenuDTO;
import cn.stylefeng.roses.kernel.system.modular.menu.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单mapper接口
 *
 * @author fengshuonan
 * @date 2020/3/13 16:05
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取系统所有菜单（适用于登录后获取左侧菜单）（适配antd vue版本）
     * <p>
     * 返回信息携带：菜单可以被那些角色访问
     *
     * @return 系统所有菜单
     * @author majianguo
     * @date 2021/1/7 15:27
     */
    List<AntdSysMenuDTO> getSystemAllMenus(@Param("menuIdList") List<Long> menuIdList);

}
