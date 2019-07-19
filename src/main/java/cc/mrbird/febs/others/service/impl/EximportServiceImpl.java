package cc.mrbird.febs.others.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.others.entity.Eximport;
import cc.mrbird.febs.others.mapper.EximportMapper;
import cc.mrbird.febs.others.service.IEximportService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MrBird
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EximportServiceImpl extends ServiceImpl<EximportMapper, Eximport> implements IEximportService {

    @Value("${febs.max.batch.insert.num:1000}")
    private int batchInsertMaxNum;

    @Override
    public IPage<Eximport> findEximports(QueryRequest request, Eximport eximport) {
        Page<Eximport> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, null);
    }

    @Override
    @Transactional
    public void batchInsert(List<Eximport> list) {
        int total = list.size();
        int max = batchInsertMaxNum;
        int count = total / max;
        int left = total % max;
        int length;
        if (left == 0) length = count;
        else length = count + 1;
        for (int i = 0; i < length; i++) {
            int start = max * i;
            int end = max * (i + 1);
            if (i != count) {
                log.info("正在插入第" + (start + 1) + " ~ " + end + "条记录 ······");
                saveBatch(list, end);
            } else {
                end = total;
                log.info("正在插入第" + (start + 1) + " ~ " + end + "条记录 ······");
                saveBatch(list, end);
            }
        }
    }

}
