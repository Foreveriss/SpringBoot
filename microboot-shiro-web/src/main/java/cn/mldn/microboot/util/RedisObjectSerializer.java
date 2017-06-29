package cn.mldn.microboot.util;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
public class RedisObjectSerializer implements RedisSerializer<Object> {
	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();
	private static final byte[] EMPTY_ARRAY = new byte[0];
	@Override
	public byte[] serialize(Object object) throws SerializationException {
		if (object == null) {
			return EMPTY_ARRAY;
		}
		try {
			return serializer.convert(object);
		} catch (Exception ex) {
			return EMPTY_ARRAY;
		}
	}
	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if (this.isEmpty(bytes)) {
			return null;
		}
		try {
			return deserializer.convert(bytes);
		} catch (Exception ex) {
			throw new SerializationException("序列化对象出错！", ex);
		}
	}
	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}