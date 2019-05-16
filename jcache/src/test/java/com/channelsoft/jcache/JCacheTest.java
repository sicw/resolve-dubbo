package com.channelsoft.jcache;

import org.junit.Test;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

public class JCacheTest {
    @Test
    public void testJcache() throws ClassNotFoundException {
        //getCachingProvider方法，通过jdk中的spi接口去META-INF/services中查找实现了CacheProvider接口的类
        //然后返回给provider
        CachingProvider provider = Caching.getCachingProvider();
        Cache<Object,Object> cache = provider.getCacheManager().getCache("myCache",Object.class,Object.class);
        cache.put("k1","v1");
        System.out.println(cache.get("k1"));


        //在Test目录中的TestXXX类是可以读取到main文件夹中的类和资源的。
        System.out.println(Class.forName("com.channelsoft.jcache.impl.CachingProviderImpl"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("META-INF/services/javax.cache.spi.CachingProvider"));
    }
}