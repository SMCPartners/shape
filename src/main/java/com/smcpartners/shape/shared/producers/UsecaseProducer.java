package com.smcpartners.shape.shared.producers;

import com.smcpartners.shape.shared.usecasecommon.AbstractUsecase;
import com.smcpartners.shape.shared.usecasecommon.Usecase;
import com.smcpartners.shape.shared.usecasecommon.UsecaseAdapter;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.reflect.Type;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by johndestefano on 12/9/15.
 * <p>
 * Changes:<b/>
 */
public class UsecaseProducer {

    @Produces
    @Usecase
    public UsecaseAdapter createUserCase(@Any Instance<UsecaseAdapter> instance, InjectionPoint injectionPoint) throws Exception {

        // Get the Field or Parameter type and check to make sure its a type of AbstractUsecase
        Type injType = injectionPoint.getType();
        Class injClass = injType.getClass();
        if (!AbstractUsecase.class.isAssignableFrom(injType.getClass())) {
            throw new Exception("Injected is not a type of AbstractUsecase");
        }

        // Create the return instance using the AbstractUsecase (this) as the parameter to the
        // Use case constructor
        Bean<?> injBean = injectionPoint.getBean();
        CreationalContext<?> createCtx = CDI.current().getBeanManager().createCreationalContext(injBean);
        Object thisPointer = CDI.current().getBeanManager().getReference(injBean, injectionPoint.getBean().getClass(), createCtx);
        UsecaseAdapter adapter = UsecaseAdapter.class.cast(thisPointer);

        // Create UsecaseAdapter
        AbstractUsecase retInst = AbstractUsecase.class.cast(CDI.current().select(injClass).get());

        // Set the this pointer
        Class[] params = new Class[1];
        params[0] = injBean.getBeanClass();
        retInst.setServiceAdapter(adapter);

        return UsecaseAdapter.class.cast(retInst);
    }
}
