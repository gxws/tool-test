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

	private Map<Class<?>, List<Map<String, String>>> classMap;

	private List<Map<String, String>> tempLine;

	private Map<String, String> tempKeyValue;

	private Class<?> tempClass;

	private int order = 0;

	public SpecificDataCache() {
		classMap = new HashMap<>();
	}

	public String get(Class<?> cls, String key, boolean randomOrder) {
		List<Map<String, String>> classList;
		try {
			classList = classList(cls);
			return keyValue(classList, key, randomOrder);
		} catch (CacheNotFoundException e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}

	public void put(Class<?> cls, String key, String value) {

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

	private String keyValue(List<Map<String, String>> classList, String key, boolean randomOrder)
			throws CacheNotFoundException {
		Map<String, String> keyMap = null;
		String value = null;
		if (randomOrder) {
			int index = (int) (classList.size() * Math.random());
			keyMap = classList.get(index);
		} else {
			if (order >= classList.size()) {
				order = 0;
			}
			keyMap = classList.get(order);
			order = order + 1;
		}
		try {
			value = keyMap.get(key);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
		}
		if (null == value) {
			throw new CacheNotFoundException();
		} else {
			return value;
		}
	}
}
