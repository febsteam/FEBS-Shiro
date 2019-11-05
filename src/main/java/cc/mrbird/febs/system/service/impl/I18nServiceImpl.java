package cc.mrbird.febs.system.service.impl;

import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import cc.mrbird.febs.system.service.I18nService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
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

    private List<String> keys = Lists.newArrayList();
    Map<String, Properties> propertiesMap = Maps.newHashMap();

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
        System.out.println();
    }

    public List<String> getKeys() {
        return keys;
    }
}
