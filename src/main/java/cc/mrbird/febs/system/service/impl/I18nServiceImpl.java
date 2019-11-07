package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.system.service.I18nService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author warning5
 */
@Service
@Slf4j
public class I18nServiceImpl implements I18nService {

    private Map<String, I18nLine> lines;
    Map<String, Properties> propertiesMap = Maps.newHashMap();

    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;
    public static final String cn = "messages_zh_CN.properties";
    public static final String en = "messages_en_US.properties";
    public static final String tw = "messages_zh_TW.properties";
    public static final String jp = "messages_ja_JP.properties";
    public static final String de = "messages.properties";

    @PostConstruct
    public void init() {
        try {
            Stream<Path> pathStream = Files.list(Paths.get(getClass().getClassLoader().getResource("i18n").toURI()));
            pathStream.forEach(path -> {
                Properties properties = new Properties();
                try {
                    properties.load(new FileReader(path.toFile()));
                    propertiesMap.put(path.getFileName().toString(), properties);
                } catch (IOException e) {
                    log.error("{}", e);
                }
            });
        } catch (Exception e) {
            log.error("{}", e);
        }
        Properties defaultProperties = propertiesMap.get(de);
        lines = Maps.newLinkedHashMapWithExpectedSize(defaultProperties.size());
        defaultProperties.keySet().forEach(key -> {
            I18nLine i18nLine = new I18nLine();
            i18nLine.setKey(key.toString());
            i18nLine.setCn((String) propertiesMap.get(cn).get(key));
            i18nLine.setEn((String) propertiesMap.get(en).get(key));
            i18nLine.setTw((String) propertiesMap.get(tw).get(key));
            i18nLine.setJp((String) propertiesMap.get(jp).get(key));
            lines.put(key.toString(), i18nLine);
        });
    }

    @Override
    public IPage<I18nLine> getI18nList(QueryRequest request) {
        Page<I18nLine> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setTotal(lines.size());
        List<I18nLine> values = Lists.newArrayList(lines.values());
        if (values.size() <= request.getPageNum()) {
            page.setRecords(values);
        } else {
            int totalPage = values.size() / request.getPageSize();
            int mod = values.size() % request.getPageSize();
            if (mod != 0) {
                totalPage++;
            }
            int start = (request.getPageNum() - 1) * request.getPageSize();
            if (request.getPageNum() > totalPage) {
                start = (totalPage - 1) * request.getPageSize();
            }
            int end = start + request.getPageSize();
            if (end > values.size()) {
                end = values.size();
            }
            page.setRecords(values.subList(start, end));
        }
        return page;
    }

    @Override
    public void update(I18nLine i18nLine) {
        if (StringUtils.isNotEmpty(i18nLine.getCn())) {
            propertiesMap.get(cn).setProperty(i18nLine.getKey(), i18nLine.getCn());
            save_properties(propertiesMap.get(cn), cn);

            propertiesMap.get(de).setProperty(i18nLine.getKey(), i18nLine.getCn());
            save_properties(propertiesMap.get(de), de);
        }
        if (StringUtils.isNotEmpty(i18nLine.getEn())) {
            propertiesMap.get(en).setProperty(i18nLine.getKey(), i18nLine.getEn());
            save_properties(propertiesMap.get(en), en);
        }
        if (StringUtils.isNotEmpty(i18nLine.getTw())) {
            propertiesMap.get(tw).setProperty(i18nLine.getKey(), i18nLine.getTw());
            save_properties(propertiesMap.get(tw), tw);
        }
        if (StringUtils.isNotEmpty(i18nLine.getJp())) {
            propertiesMap.get(jp).setProperty(i18nLine.getKey(), i18nLine.getJp());
            save_properties(propertiesMap.get(jp), jp);
        }
        lines.put(i18nLine.getKey(), i18nLine);
    }

    @Override
    public I18nLine getLine(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new FebsException("i18n key is null");
        }
        I18nLine i18nLine = new I18nLine();
        i18nLine.setKey(key);
        i18nLine.setCn((String) propertiesMap.get(cn).get(key));
        i18nLine.setEn((String) propertiesMap.get(en).get(key));
        i18nLine.setTw((String) propertiesMap.get(tw).get(key));
        i18nLine.setJp((String) propertiesMap.get(jp).get(key));
        return i18nLine;
    }

    private void save_properties(Properties properties, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(getClass().getClassLoader().getResource("i18n/" + fileName).toURI()));
            properties.store(fos, "save-" + fileName);
            fos.close();
        } catch (Exception e) {
            log.error("{}", e);
        }
    }
}
