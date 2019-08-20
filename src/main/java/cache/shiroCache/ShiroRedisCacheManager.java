package cache.shiroCache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * ShiroRedis缓存管理器
 */
public class ShiroRedisCacheManager implements CacheManager, Destroyable {

    private static Logger logger = LoggerFactory.getLogger(ShiroRedisCacheManager.class);

    private JedisConnectionFactory jedisConnectionFactory;

    private RedisTemplate redisTemplate;

    public ShiroRedisCacheManager(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    public ShiroRedisCacheManager(JedisConnectionFactory jedisConnectionFactory, RedisTemplate redisTemplate) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        logger.debug("----------------ShiroRedisCacheManager_getCache_s=" + s + "----------------");

        return new ShiroRedisCache<>(s,jedisConnectionFactory,redisTemplate);
    }

    @Override
    public void destroy() throws Exception {

    }
}
