package Aspects;

import org.aspectj.lang.Signature;

import java.util.logging.Logger;

/**
 * Created by mihai on 02.06.2014.
 */
public aspect Trace {

    private static Logger logger = Logger.getLogger("Log");

    pointcut tracePublicMethods(): execution(* *(..));

    before(): tracePublicMethods(){

        Signature signature = thisJoinPoint.getSignature();

        logger.info("[Entering:] " + signature.getDeclaringTypeName() + "." + signature.getName());

    }

    after(): tracePublicMethods(){

        Signature signature = thisJoinPoint.getSignature();

        logger.info("[Exiting:] " + signature.getDeclaringTypeName() + "." + signature.getName());

    }

}
