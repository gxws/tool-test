package com.gxws.tool.test.data.specific;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.test.exception.CacheNotFoundException;

/**
 * 从缓存数据中获取指定值的值
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class SpecificDataCache {

	private final static Logger log = LoggerFactory.getLogger(SpecificDataCache.class);

	private Map<Class<?>, List<Map<String, String>>> classMap = new HashMap<>();

	private List<Map<String, String>> tempLine;

	private Map<String, String> tempKeyValue;

	private Class<?> tempClass;

	private Map<Class<?>, Integer> orderIndex = new HashMap<>();

	public String get(Class<?> cls, String key, boolean randomOrder) {
		List<Map<String, String>> classList;
		try {
			classList = classList(cls);
		} catch (CacheNotFoundException e) {
			return "";
		}
		Map<String, String> keyMap = null;
		String value = null;
		if (randomOrder) {
			keyMap = keyValueRandom(classList);
		} else {
			keyMap = keyValueOrder(classList, cls);
		}
		value = keyMap.get(key);
		if (null == value) {
			return "";
		} else {
			return value;
		}
	}

	public void prePutInLine(Class<?> cls) {
		try {
			tempLine = classList(cls);
		} catch (CacheNotFoundException e) {
			tempLine = new ArrayList<Map<String, String>>();
			classMap.put(cls, tempLine);
		}
		tempClass = cls;
		tempKeyValue = new HashMap<>();
	}

	public void putInLine(String key, String value) {
		tempKeyValue.put(key, value);
	}

	public void donePutInLine() {
		tempLine.add(tempKeyValue);
		classMap.put(tempClass, tempLine);
	}

	private List<Map<String, String>> classList(Class<?> cls) throws CacheNotFoundException {
		List<Map<String, String>> classList = classMap.get(cls);
		if (null == classList || 0 == classList.size()) {
			throw new CacheNotFoundException();
		} else {
			return classList;
		}
	}

	private Map<String, String> keyValueRandom(List<Map<String, String>> classList) {
		int index = (int) (classList.size() * Math.random());
		return classList.get(index);
	}

	private Map<String, String> keyValueOrder(List<Map<String, String>> classList, Class<?> cls) {
		Integer order = orderIndex.get(cls);
		if (null == order) {
			order = Integer.valueOf("0");
		} else if (order.intValue() >= classList.size()) {
			order = Integer.valueOf("0");
		}
		Map<String, String> result = classList.get(order.intValue());
		order = Integer.valueOf(order.intValue() + 1);
		orderIndex.put(cls, order);
		return result;
	}
}
