package cc.mrbird.febs.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author MrBird
 */
@Data
@TableName("t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2354394771912648574L;
    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;


}
