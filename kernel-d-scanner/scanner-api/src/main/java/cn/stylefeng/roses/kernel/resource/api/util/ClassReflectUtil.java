package cn.stylefeng.roses.kernel.resource.api.util;

import cn.hutool.core.util.ClassUtil;
import cn.stylefeng.roses.kernel.resource.api.annotation.field.ChineseDescription;
import cn.stylefeng.roses.kernel.resource.api.pojo.resource.FieldMetadata;
import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 类的反射工具
 *
 * @author fengshuonan
 * @date 2020/12/8 18:23
 */
public class ClassReflectUtil {

    /**
     * 获取一个类的所有字段描述
     *
     * @param clazz 类的class类型
     * @return 该类下所有字段的描述信息
     * @author fengshuonan
     * @date 2020/12/8 18:27
     */
    public static Set<FieldMetadata> getClassFieldDescription(Class<?> clazz) {

        HashSet<FieldMetadata> metadataHashSet = new HashSet<>();

        if (clazz == null) {
            return metadataHashSet;
        }

        // 获取类中的所有字段
        Field[] declaredFields = ClassUtil.getDeclaredFields(clazz);

        for (Field declaredField : declaredFields) {

            FieldMetadata fieldMetadata = new FieldMetadata();

            // 获取字段的名称
            String name = declaredField.getName();
            fieldMetadata.setFieldName(name);

            // 获取字段的类型
            Class<?> declaredFieldType = declaredField.getType();
            fieldMetadata.setFieldClassType(declaredFieldType.getSimpleName());

            // 如果字段类型是Object类型，则遍历Object类型里的字段
            if (BaseRequest.class.isAssignableFrom(declaredFieldType)) {
                fieldMetadata.setGenericFieldMetadata(getClassFieldDescription(declaredFieldType));
            }

            // 获取字段的所有注解
            Annotation[] annotations = declaredField.getAnnotations();
            if (annotations != null && annotations.length > 0) {

                // 设置字段的所有注解
                fieldMetadata.setAnnotations(annotationsToStrings(annotations));

                // 遍历字段上的所有注解，找到带groups属性的，按group分类组装注解
                Map<String, Set<String>> groupAnnotations = new HashMap<>();
                for (Annotation annotation : annotations) {
                    Class<?>[] validateGroupsClasses = invokeAnnotationMethodIgnoreError(annotation, "groups", Class[].class);
                    if (validateGroupsClasses != null) {
                        for (Class<?> validateGroupsClass : validateGroupsClasses) {
                            addGroupValidateAnnotation(annotation, validateGroupsClass, groupAnnotations);
                        }
                    }
                }
                // 设置分组注解
                fieldMetadata.setGroupAnnotations(groupAnnotations);

                // 填充字段的中文名称
                ChineseDescription chineseDescription = declaredField.getAnnotation(ChineseDescription.class);
                if (chineseDescription != null) {
                    fieldMetadata.setChineseName(chineseDescription.value());
                }
            }

            metadataHashSet.add(fieldMetadata);
        }

        return metadataHashSet;
    }

    /**
     * 调用注解上的某个方法，并获取结果，忽略异常
     *
     * @author fengshuonan
     * @date 2020/12/8 17:13
     */
    private static <T> T invokeAnnotationMethodIgnoreError(Annotation apiResource, String methodName, Class<T> resultType) {
        try {
            Class<? extends Annotation> annotationType = apiResource.annotationType();
            Method method = annotationType.getMethod(methodName);
            return (T) method.invoke(apiResource);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // 忽略异常
        }
        return null;
    }

    /**
     * 将字段上的分组注解添加到对应的组中
     *
     * @param fieldAnnotation     字段上的注解
     * @param validateGroupsClass 校验分组
     * @param groupAnnotations    分组注解集合
     * @author fengshuonan
     * @date 2020/12/8 19:12
     */
    private static void addGroupValidateAnnotation(Annotation fieldAnnotation, Class<?> validateGroupsClass, Map<String, Set<String>> groupAnnotations) {
        Set<String> annotations = groupAnnotations.get(validateGroupsClass.getSimpleName());
        if (annotations == null) {
            annotations = new HashSet<>();
        }
        annotations.add(fieldAnnotation.annotationType().getSimpleName());
        groupAnnotations.put(validateGroupsClass.getSimpleName(), annotations);
    }

    /**
     * 注解转化为string名称
     * <p>
     * 例如：
     * NotBlack注解 > NotBlack
     *
     * @author fengshuonan
     * @date 2020/12/9 13:39
     */
    private static Set<String> annotationsToStrings(Annotation[] annotations) {
        Set<String> strings = new HashSet<>();

        if (annotations == null || annotations.length == 0) {
            return strings;
        }

        for (Annotation annotation : annotations) {
            strings.add(annotation.annotationType().getSimpleName());
        }

        return strings;
    }

}
