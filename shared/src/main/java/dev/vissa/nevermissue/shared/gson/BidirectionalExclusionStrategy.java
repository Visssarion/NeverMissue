package dev.vissa.nevermissue.shared.gson;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalField;
import dev.vissa.nevermissue.shared.gson.annotations.SecretField;
import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalClass;


public class BidirectionalExclusionStrategy implements ExclusionStrategy{

	List<Class<?>> classes = new ArrayList<Class<?>>();
	
	static final Class<BidirectionalClass> classAnnotation = BidirectionalClass.class;
	
	boolean showSecrets = false;
	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		System.err.println(f.getName());
		if(f.getAnnotation(BidirectionalField.class)!= null) {
			for(Class<?> clazz: classes) {
				if(clazz.equals(f.getDeclaredClass())) {
					//System.err.println("skipped");
					return true;
				}
			}
			//System.out.println(f.getDeclaredClass());
		}
		System.err.println(f.getAnnotations());
		SecretField secretAnnotation = f.getAnnotation(SecretField.class);
		if(secretAnnotation != null) {
			System.err.println(f.getName());
			if(secretAnnotation.neverShow()) {return true;}
			return !showSecrets;
		}
		
		
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
//		try {
//			clazz.getDeclaredMethod("test").invoke(clazz, null);
//		} catch (NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(clazz.getAnnotation(BidirectionalClass.class)!= null) {
			if (!classes.contains(clazz)) {
				classes.add(clazz);
			}
			System.out.println(classes);
			System.out.println(clazz.getName());
		}
		return false;
	}

	public void setShowSecrets(boolean showSecrets) {
		this.showSecrets = showSecrets;
	}

	public BidirectionalExclusionStrategy() {
	}

	public BidirectionalExclusionStrategy(boolean showSecrets) {
		this.showSecrets = showSecrets;
	}
	
	

	
}
