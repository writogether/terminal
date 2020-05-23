package writo.terminal.contract;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

public interface View<T extends Entity> {

    @SneakyThrows
    default T toEntity() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
        T entity = (T) clazz.getConstructors()[0].newInstance();
        BeanUtil.copyProperties(this, entity, CopyOptions.create().setIgnoreNullValue(true));
        return entity;
    }

}
