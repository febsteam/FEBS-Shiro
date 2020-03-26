package cc.mrbird.febs.generator.service.impl;

import cc.mrbird.febs.generator.entity.GeneratorConfig;
import cc.mrbird.febs.generator.mapper.GeneratorConfigMapper;
import cc.mrbird.febs.generator.service.IGeneratorConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MrBird
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GeneratorConfigServiceImpl extends ServiceImpl<GeneratorConfigMapper, GeneratorConfig> implements IGeneratorConfigService {

    @Override
    public GeneratorConfig findGeneratorConfig() {
        List<GeneratorConfig> generatorConfigs = this.baseMapper.selectList(null);
        return CollectionUtils.isNotEmpty(generatorConfigs) ? generatorConfigs.get(0) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGeneratorConfig(GeneratorConfig generatorConfig) {
        this.saveOrUpdate(generatorConfig);
    }
}
