package cc.mrbird.febs.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author MrBird
 */
@Data
public class JvmInfo implements Serializable {

    private static final long serialVersionUID = -5178501845351050670L;
    /**
     * JVM 最大内存
     */
    private Double jvmMemoryMax;
    /**
     * JVM 可用内存
     */
    private Double jvmMemoryCommitted;
    /**
     * JVM 已用内存
     */
    private Double jvmMemoryUsed;
    /**
     * JVM 缓冲区已用内存
     */
    private Double jvmBufferMemoryUsed;
    /**
     * 当前缓冲区数量
     */
    private Double jvmBufferCount;
    /**
     * JVM 守护线程数量
     */
    private Double jvmThreadsdaemon;
    /**
     * JVM 当前活跃线程数量
     */
    private Double jvmThreadsLive;
    /**
     * JVM 峰值线程数量
     */
    private Double jvmThreadsPeak;
    /**
     * JVM 已加载 Class 数量
     */
    private Double jvmClassesLoaded;
    /**
     * JVM 未加载 Class 数量
     */
    private Double jvmClassesUnloaded;
    /**
     * GC 时, 年轻代分配的内存空间
     */
    private Double jvmGcMemoryAllocated;
    /**
     * GC 时, 老年代分配的内存空间
     */
    private Double jvmGcMemoryPromoted;
    /**
     * GC 时, 老年代的最大内存空间
     */
    private Double jvmGcMaxDataSize;
    /**
     * FullGC 时, 老年代的内存空间
     */
    private Double jvmGcLiveDataSize;
}
