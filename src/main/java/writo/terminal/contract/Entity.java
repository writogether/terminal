package writo.terminal.contract;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.SneakyThrows;

public interface Entity {

    @SneakyThrows
    default View<? extends Entity> toView(Class<? extends View<? extends Entity>> type) {
        View<Entity> view = (View<Entity>) type.getConstructors()[0].newInstance();
        BeanUtil.copyProperties(this, view, CopyOptions.create().setIgnoreNullValue(true));
        return view;
    }

}
