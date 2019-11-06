package cc.mrbird.febs.system.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/**
 * @author warning5
 */
public interface I18nService {

    IPage<I18nLine> getI18nList(QueryRequest request);

    void update(I18nLine i18nLine);

    I18nLine getLine(String key);

    @Data
    class I18nLine {
        private String key;
        private String tw;
        private String en;
        private String cn;
        private String jp;
    }
}
