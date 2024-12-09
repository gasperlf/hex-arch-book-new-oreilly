package net.ontopsolutions.application.usecases;

import net.ontopsolutions.domain.entity.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);

}
