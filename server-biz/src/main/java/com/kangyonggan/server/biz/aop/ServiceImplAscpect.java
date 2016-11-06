package com.kangyonggan.server.biz.aop;

import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.dto.ResponseDto;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author kangyonggan
 * @since 2016/11/6
 */
@Component
@Log4j2
public class ServiceImplAscpect {

    /**
     * 入参日志、出参日志、异常捕获
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("目标方法为：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("入参为：" + Arrays.toString(joinPoint.getArgs()));

        Object returnValue;

        try {
            returnValue = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setRespCode(AppConstants.RESP_EXCEPTION);
            responseDto.setRespMsg("业务出现异常");
            returnValue = responseDto;

            log.error(responseDto.getRespMsg(), throwable);
        }

        log.info("出参为：" + returnValue);
        return returnValue;

    }
}
