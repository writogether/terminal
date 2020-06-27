package writo.terminal.contract;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

/**
 * Every VO should impl the interface for the ability to generate PO in a line.
 *
 * @param <T> Corresponding PO for the VO
 */
public interface View<T extends Entity> {

    /**
     * return type `T` object with same value of `this`. Fields not contained in VO will be filled with default.
     */
    @SneakyThrows
    default T toEntity() {
        ParameterizedType t = (ParameterizedType) this.getClass().getGenericInterfaces()[0];
        Class c = (Class) t.getActualTypeArguments()[0];
        T entity = (T) c.getConstructors()[0].newInstance();
        BeanUtil.copyProperties(this, entity, CopyOptions.create().setIgnoreNullValue(true));
        return entity;
    }

}
