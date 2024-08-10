package br.com.systemit.auth.rest.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ApplicationControllerAdvice {

//    @ExceptionHandler(RegraNegocioException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
//        String mensagemErro = ex.getMessage();
//        return new ApiErrors(mensagemErro);
//    }
//
//    @ExceptionHandler(PedidoNaoEncontradoException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrors handlePedidoNotFoundException( PedidoNaoEncontradoException ex ){
//        return new ApiErrors(ex.getMessage());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
//        List<String> errors = ex.getBindingResult().getAllErrors()
//                .stream()
//                .map(erro -> erro.getDefaultMessage())
//                .collect(Collectors.toList());
//        return new ApiErrors(errors);
//    }
}
