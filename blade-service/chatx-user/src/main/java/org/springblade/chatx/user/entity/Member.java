/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.chatx.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.springblade.core.mp.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Blade
 * @since 2020-06-30
 */
@Data
@TableName("u_member")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Member对象", description = "Member对象")
public class Member extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 展示id
     */
    @ApiModelProperty(value = "展示id")
    private Long userNum;
  private String nickName;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 盐
     */
    @ApiModelProperty(value = "盐")
    private String salt;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 缩略图
     */
    @ApiModelProperty(value = "缩略图")
    private String thumbnail;
  private Integer channel;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String arearId;
    /**
     * 渠道
     */
    @ApiModelProperty(value = "渠道")
    private LocalDateTime birthday;
    /**
     * 微信open_id
     */
    @ApiModelProperty(value = "微信open_id")
    private String wxOpenid;
    /**
     * qq_open_id
     */
    @ApiModelProperty(value = "qq_open_id")
    private String qqOpenid;
  private String email;

  private Integer height;
  private Integer weight;
  private String sigin;
  private Integer videoPrice;
  private Integer voicePrice;
  private String userType;


}
