package com.avee.utility;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;

public class BeansUtility<T>  extends BeanUtilsBean{
	
	public void copy(Object dest , Object orig , List<String> exclusionsList) throws IllegalAccessException, InvocationTargetException{
		// Validate existence of the specified beans
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		PropertyDescriptor[] origDescriptors = getPropertyUtils().getPropertyDescriptors(orig);

		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if (exclusionsList != null) {
				if (exclusionsList.contains(name) || "class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
			}
			if (getPropertyUtils().isReadable(orig, name) && getPropertyUtils().isWriteable(dest, name)) {
				try {
					Object value = getPropertyUtils().getSimpleProperty(orig, name);
					if(value != null){
						copyProperty(dest, name, value);
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
