// package com.sunny.core.util;
//
// import lombok.extern.slf4j.Slf4j;
//
// import java.io.File;
// import java.lang.annotation.Annotation;
// import java.net.URL;
// import java.net.URLDecoder;
// import java.util.Enumeration;
// import java.util.HashSet;
// import java.util.Set;
//
// @Slf4j
// public class PackageUtils {
//
// 	public static Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> ann, String pks){
// 		Set<Class<?>> annCls = new HashSet<>();
// 		Set<Class<?>> cles = getClass(pks);
// 		if(cles != null && cles.size() > 0) {
// 			for (Class<?> cls : cles) {
// 				Annotation[] annotations = cls.getAnnotations();
// 				for(Annotation a : annotations) {
// 					if(a.annotationType() == ann) {
// 						annCls.add(cls);
// 					}
// 				}
// 			}
// 		}
// 		return annCls;
// 	}
//
// 	public static Set<Class<?>> getClass(String pks) {
// 		return getClass(pks.split(",;| "));
// 	}
//
// 	public static Set<Class<?>> getClass(String...pks) {
// 		return getClass(true, pks);
// 	}
//
// 	public static Set<Class<?>> getClass(boolean recursive, String...pks) {
// 		Set<Class<?>> classes = new HashSet<>();
// 		if(pks != null && pks.length > 0) {
// 			for(String pk : pks) {
// 				classes.addAll(getClasses(recursive, pk));
// 			}
// 		}
// 		return classes;
// 	}
//
// 	private static Set<Class<?>> getClasses(boolean recursive, String pk) {
// 		Set<Class<?>> cls = new HashSet<>();
//
// 		String pkName = pk;
// 		String pkDirName = pk.replace(".", "/");
//
// 		Enumeration<URL> urls;
// 		try {
// 			ClassLoader loader = Thread.currentThread().getContextClassLoader();
// 			urls = loader.getResources(pkDirName);
// 			while(urls.hasMoreElements()) {
// 				URL url = urls.nextElement();
// 				if(url == null) continue;
// 				String protocol = url.getProtocol();
// 				if("file".equals(protocol)) {
// 					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
// 					findAndAddClassInPackageByFile(pkName, filePath, recursive, cls);
// 				} else if("jar".equals(protocol)) {
// 					// ignore
// 				} else {
// 					// ignore
// 				}
// 			}
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 		return cls;
// 	}
//
// 	private static void findAndAddClassInPackageByFile(String pkName, String filePath, final boolean recursive,
// 													   Set<Class<?>> cls) {
// 		File dir = new File(filePath);
//
// 		if(!dir.exists() || !dir.isDirectory()) {
// 			log.warn("该包下没有任何文件，是个空包");
// 			return;
// 		}
//
// 		File[] files = dir.listFiles((file) -> (recursive && file.isDirectory()) || (file.getName().endsWith(".class")));
//
// 		if(files != null && files.length > 0) {
// 			for (File f : files) {
// 				if (f.isDirectory()) {
// 					findAndAddClassInPackageByFile(pkName + "." + f.getName(), f.getAbsolutePath(), recursive, cls);
// 				} else {
// 					String className = f.getName().substring(0, f.getName().length() - 6);
// 					try {
// 						cls.add(Thread.currentThread().getContextClassLoader().loadClass(pkName + "." + className));
// 					} catch (Exception e) {
// 						e.printStackTrace();
// 					}
// 				}
// 			}
// 		}
// 	}
//
// }
