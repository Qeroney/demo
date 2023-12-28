package com.example.demo.aspect;

import com.example.demo.api.product.dto.ProductDto;
import com.example.demo.feign.LoggingServiceFeign;
import com.example.demo.feign.dto.CreateProductHistoryDto;
import com.example.demo.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class Logging {


    private final AuthService authService;

    private final LoggingServiceFeign loggingServiceFeign;


    @Pointcut("@annotation(com.example.demo.aspect.annotation.LogProductCreation)")
    public void controllerPointcut(){
    }

    @Around("controllerPointcut()")
    public Object logRequest(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
            System.out.println(result.getClass().getName());

            ProductDto dto = (ProductDto) result;
            loggingServiceFeign.createHistory(CreateProductHistoryDto.builder()
                                                                     .userId(authService.getAuthorizedUserId())
                                                                     .productId(dto.getId())
                                                                     .build());

        }catch (Throwable e){
            e.printStackTrace();
        }
        return result;
    }
}
