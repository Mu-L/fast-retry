package com.burukeyou.retry.core.annotations;


import com.burukeyou.retry.core.RetryQueue;
import com.burukeyou.retry.core.RetryResultPolicy;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Retry annotation
 *
 * @author burukeyou
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FastRetry {

    /***
     * Use the BeanName of the specified retry queue
     *          If not specified, get it from spring-context according to the  value of {@link FastRetry#queueClass()}
     *          If none of them are configured, use the default built-in retry queue
     *
     * @return   the name of retry-queue
     */
    @AliasFor("value")
    String queueName() default "";

    /**
     * as same as {@link FastRetry#queueName()}
     * @return the name of retry-queue
     */
    @AliasFor("queueName")
    String value() default "";

    /**
     * Use the bean class of the specified retry queue
     * @return the class of retry-queue
     */
    Class<? extends RetryQueue>[] queueClass() default {};

    /**
     * Flag to say that whether try again when an exception occurs
     * @return try again if true
     */
    boolean retryIfException() default true;

    /**
     * Exception types that are retryable.
     *
     * @return exception types to retry
     */
    Class<? extends Exception>[] retryIfExceptionOfType() default {};

    /**
     * Flag to say that whether recover when an exception occurs
     *
     * @return throw exception if false, if true return null and print exception log
     */
    boolean exceptionRecover() default false;

    /**
     *  use custom result retry strategy,
     *  this policy can determine whether a retry is needed based on the results
     *
     * @return the class of retry-result-policy
     */
    Class<? extends RetryResultPolicy<?>>[] retryStrategy() default {};

    /**
     * @return the maximum number of attempts , if -1, it means unlimited
     */
    int maxAttempts() default -1;


    /**
     * Specify the RetryWait properties for retrying this operation. The default is a simple
     * {@link RetryWait} specification with no properties - see its documentation for
     * defaults.
     * @return a RetryWait specification
     */
    RetryWait retryWait() default @RetryWait();

}
