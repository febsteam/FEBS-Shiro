package cc.mrbird.pet.domain;
import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_dona")
public class Dona  implements Serializable{

    private static final long serialVersionUID = 791600191687528530L;
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "DONA_ID")
    @ExportConfig(value = "编号")
    private Long donaId;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "DONA_USER")
    @ExportConfig(value = "捐赠人名")
    private String donaUser;

    @Column(name = "DONA_NAME")
    @ExportConfig(value = "捐赠物品名")
    private String donaName;

    @Column(name = "DONA_DESC")
    @ExportConfig(value = "描述")
    private String donaDesc;


    @Column(name = "CREATE_TIME")
    @ExportConfig(value = "捐赠时间", convert = "c:cc.mrbird.common.util.poi.convert.TimeConvert")
    private Date createTime;



    public Long getDonaId() {
        return donaId;
    }

    public void setDonaId(Long donaId) {
        this.donaId = donaId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDonaUser() {
        return donaUser;
    }

    public void setDonaUser(String donaUser) {
        this.donaUser = donaUser;
    }

    public String getDonaName() {
        return donaName;
    }

    public void setDonaName(String donaName) {
        this.donaName = donaName;
    }

    public String getDonaDesc() {
        return donaDesc;
    }

    public void setDonaDesc(String donaDesc) {
        this.donaDesc = donaDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
