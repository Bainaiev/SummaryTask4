package ua.nure.bainaiev.SummaryTask4.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a service. Service must implement some interface for transaction handling.
 * @see ua.nure.bainaiev.SummaryTask4.annotation.Transactional
 * @see ua.nure.bainaiev.SummaryTask4.db.TransactionHandler
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
