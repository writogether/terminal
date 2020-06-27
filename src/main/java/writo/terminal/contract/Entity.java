package writo.terminal.contract;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.SneakyThrows;

/**
 * Every PO class should impl the interface to enable the conversion from PO to corresponding VO.
 */
public interface Entity {

    /**
     * Return VO with chosen type. Casts are needed for returned VO since java generic's limitation.
     */
    @SneakyThrows
    default View<? extends Entity> toView(Class<? extends View<? extends Entity>> type) {
        View<Entity> view = (View<Entity>) type.getConstructors()[0].newInstance();
        BeanUtil.copyProperties(this, view, CopyOptions.create().setIgnoreNullValue(true));
        return view;
    }

}
