package com.sundeinfo.kd.zs.service.base;

import com.sundeinfo.core.permission.domain.UserDomain;
import com.sundeinfo.core.permission.model.AnyUser;
import com.sundeinfo.core.permission.utility.AuthenticationGetter;
import com.sundeinfo.core.utility.ConvertUtils;
import com.sundeinfo.foundation.mvc.controller.AbstractController;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class BaseService<T> extends AbstractController<T> {

    protected AuthenticationGetter authenticationGetter;

    protected abstract void setAuthenticationGetter(AuthenticationGetter authenticationGetter);

    protected int getUserId() {
        AnyUser anyUser = this.authenticationGetter.getAnyUser();
        if (anyUser == null) return 0;
        UserDomain user = anyUser.getUser();
        if (user == null) return 0;
        return anyUser.getUser().getId();
    }

    protected <F, K> List<K> convertToDto(List<F> list, Class<K> type) throws InstantiationException, IllegalAccessException {
        List<K> toList = new ArrayList<>();
        for (F item : list) {
            toList.add(ConvertUtils.convert(item, type));
        }
        return toList;
    }

    protected <T> T convertToDto(Object source, Class<T> type)  {
        try {
            return ConvertUtils.convert(source, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> boolean compareDto(T src, T dest, Class<T> type, List<String> withOutItem) {
        try {
            for (Field field : type.getDeclaredFields()) {
                if (withOutItem.contains(field.getName())) {
                    continue;
                }
                PropertyDescriptor prop = new PropertyDescriptor(field.getName(), type);
                Method method = prop.getReadMethod();
                Object srcValue = method.invoke(src);
                Object destValue = method.invoke(dest);

                if (srcValue == null && destValue != null) {
                    return false;
                }

                if (srcValue != null && destValue == null) {
                    return false;
                }

                if (srcValue == null && destValue == null) {
                    continue;
                }

                if (!srcValue.equals(destValue)) {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    protected <T> boolean compareDto(T src, T dest, Class<T> type) {
        return compareDto(src, dest, type, Arrays.asList(new String[]{"createtime", "createby", "updatetime", "updateby"}));
    }

}
